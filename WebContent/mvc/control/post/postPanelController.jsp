<%@ page import="java.util.LinkedList" %>
<%@ page import="es.uco.pw.data.dto.post.DTOPost" %>
<%@ page import="es.uco.pw.business.dao.post.DAOPost" %>
<%@ page import="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customer" class="es.uco.pw.display.javabean.CustomerBean" scope="session"/>

<%if (customer.getUserId() == -1){%>
<jsp:forward page="/mvc/view/auth/loginView.jsp"/>
<%}%>

<%
    boolean[] filters = new boolean[3];

    LinkedList<Integer> postsIds = new LinkedList<>();
    LinkedList<DTOPost> posts;
    posts = new DAOPost().getByField("owner_id", customer.getUserId().toString());
    for (DTOPost post : posts)
        postsIds.add(post.getId());

%>

<jsp:forward page="/mvc/view/post/postPanelView.jsp">
    <jsp:param name="postsIds" value="<%=postsIds%>"/>
</jsp:forward>