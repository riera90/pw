<%@ page import="java.util.LinkedList" %>
<%@ page import="es.uco.pw.data.dto.post.DTOPost" %>
<%@ page import="es.uco.pw.business.dao.post.DAOPost" %>
<%@ page import="es.uco.pw.business.dao.post.PostBuilder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customer" class="es.uco.pw.display.javabean.CustomerBean" scope="session"/>

<%if (customer.getUserId() == -1){%>
    <jsp:forward page="/mvc/view/auth/loginView.jsp"/>
<%}%>

<%
    LinkedList<Integer> postsIds = new LinkedList<>();
    LinkedList<DTOPost> posts = new DAOPost().get();
    for (DTOPost post : posts){
        postsIds.add(post.getId());
    }
%>

<jsp:forward page="/mvc/view/board/boardView.jsp">
    <jsp:param name="postsIds" value="<%=postsIds%>"/>
</jsp:forward>