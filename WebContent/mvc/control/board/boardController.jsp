<%@ page import="java.util.LinkedList" %>
<%@ page import="es.uco.pw.data.dto.post.DTOPost" %>
<%@ page import="es.uco.pw.business.dao.post.DAOPost" %>
<%@ page import="es.uco.pw.business.dao.post.PostBuilder" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.Bool" %>
<%@ page import="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customer" class="es.uco.pw.display.javabean.CustomerBean" scope="session"/>

<%if (customer.getUserId() == -1){%>
    <jsp:forward page="/mvc/control/auth/loginController.jsp"/>
<%}%>

<%
    boolean[] filters = new boolean[3];

    String topicSearchId = request.getParameter("topicSearch");
    if (topicSearchId != null && topicSearchId != "")
        filters[0] = true;

    String titleSearch = request.getParameter("titleSearch");
    if (titleSearch != null && titleSearch != "")
        filters[1] = true;

    String typeSearch = request.getParameter("typeSearch");
    if (typeSearch != null && typeSearch != "")
        filters[2] = true;

    System.out.println("type:"+typeSearch);
    System.out.println("title:"+titleSearch);
    System.out.println("topic:"+topicSearchId);
    System.out.println("filters:"+ Arrays.toString(filters));


    LinkedList<Integer> postsIds = new LinkedList<>();
    LinkedList<DTOPost> posts;

    if (filters[0] && !filters[1] && !filters[2])
        posts = new DAOPost().getByField("topic", topicSearchId);
    else if (!filters[0] && filters[1] && !filters[2])
        posts = new DAOPost().getByFieldLike("title", titleSearch);
    else if (!filters[0] && !filters[1] && filters[2])
        posts = new DAOPost().getByField("type", typeSearch);
    else if (!filters[0] && !filters[1] && !filters[2])
        posts = new DAOPost().get();
    else{
        System.out.println("complex filters");
        posts = new DAOPost().get();
    }

    for (DTOPost post : posts){
        if (!post.getState().equals("published"))
            continue;
        if (post.getType().equals("targeted")) {
            for (Integer uid : post.getSentTo()) {
                if (uid.equals(customer.getUserId()))
                    postsIds.add(post.getId());
            }
        } else {
            postsIds.add(post.getId());
        }
    }
%>

<jsp:forward page="/mvc/view/board/boardView.jsp">
    <jsp:param name="postsIds" value="<%=postsIds%>"/>
</jsp:forward>