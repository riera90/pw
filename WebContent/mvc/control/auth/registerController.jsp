<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.data.dto.user.DTOUser, es.uco.pw.business.dao.user.DAOUser, es.uco.pw.business.Utils.Algo, java.security.NoSuchAlgorithmException" %>

<%if (request.getParameter("firstName") == ""
		|| request.getParameter("lastName") == ""
		|| request.getParameter("password") == "")
{%>
<jsp:forward page="../../view/auth/registerView.jsp"/>
<%}%>

<%
	String fistName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	DTOUser user = new DTOUser();
	user.setFirstName(fistName);
	user.setLastName(lastName);
	user.setEmail(email);
	user.setPassword(password);
	DAOUser userController = new DAOUser();
	userController.post(user);
%>

<jsp:forward page="../../view/success.jsp"/>