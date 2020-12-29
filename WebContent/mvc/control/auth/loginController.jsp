<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="es.uco.pw.business.dao.user.DAOUser, es.uco.pw.data.dto.user.DTOUser, es.uco.pw.business.Utils.Algo ,java.util.LinkedList ,java.security.NoSuchAlgorithmException" %>
<%@ page import="es.uco.pw.business.dao.user.UserBuilder" %>

<%
	String email = request.getParameter("email");
	String password = request.getParameter("password");

	if (email == "" || password == ""){
		%><jsp:forward page="/mvc/view/auth/loginView.jsp"/><%
	}

	LinkedList<DTOUser> users;
	String hashedPassword = null;
	try {
		hashedPassword = new Algo().getSHA256AsHex(password);
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	users = new DAOUser().getByField("email", email);
	System.out.println(UserBuilder.toJson(users.get(0)));
	System.out.println((users.get(0).getPassword()));
	System.out.println(hashedPassword);

	if (users.size() == 1 && users.get(0).getPassword() == hashedPassword){
		%><jsp:forward page="/mvc/view/success.jsp"/><%
	}else{
		%><jsp:forward page="/mvc/view/auth/loginView.jsp"/><%
	}
%>

