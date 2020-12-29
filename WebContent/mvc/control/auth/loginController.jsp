<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="es.uco.pw.business.dao.user.DAOUser, es.uco.pw.data.dto.user.DTOUser, java.util.LinkedList, es.uco.pw.business.dao.user.UserBuilder" %>


<%
	String email = request.getParameter("email");
	String password = request.getParameter("password");

	if (email == "" || password == ""){
		%><jsp:forward page="/mvc/view/auth/loginView.jsp"/><%
	}

	LinkedList<DTOUser> users;
	users = new DAOUser().getByField("email", email);

	if (users.size() == 1 && users.get(0).getPassword().equals(password)){
		%>
			<jsp:forward page="/mvc/view/success.jsp">
				<jsp:param name="msg" value="you are logged in"/>
			</jsp:forward>
		<%
	}else{
		%>
			<jsp:forward page="/mvc/view/auth/loginView.jsp">
				<jsp:param name="error" value="error, the user or the password is not correct"/>
			</jsp:forward>
		<%
	}
%>

