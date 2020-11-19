<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="es.uco.pw.business.dao.user.DAOUser" %>
<%@ page import="es.uco.pw.data.dto.user.DTOUser" %>
<%@ page import="es.uco.pw.business.Utils.Algo" %>
<%@ page import="java.util.LinkedList" %>

<%if (request.getParameter("email") == "" || request.getParameter("password" == "")){%>
	<jsp:forward page="../../view/auth/loginView.jsp"/>
<%}%>

<%
	LinkedList<DTOUser> users;
	Algo algo = new Algo();
	String passwd = algo.getSHA256AsHex(request.getParamenter("password"));
	algo = null;
	DAOUser userController = new DAOUser();
	users = userController.getByField("email", request.getParameter("email"));
	userController = null;
%>
	<%if (users != null && users.size() == 1 && users.get(1).getPassword() == passwd){%>
		<jsp:forward page="../../view/success.jsp"/>
	<%}else{%>
		<jsp:forward page="../../view/auth/loginView.jsp"/>
	<%}%>

