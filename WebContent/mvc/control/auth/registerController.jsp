<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="es.uco.pw.business.dao.user.DAOUser" %>
<%@ page import="es.uco.pw.data.dto.user.DTOUser" %>
<%@ page import="es.uco.pw.business.Utils.Algo" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>

<%if (request.getParameter("firstName") == ""
		|| request.getParameter("lastName") == ""
		|| request.getParameter("password" == ""))
{%>
	<jsp:forward page="../../view/auth/registerView.jsp"/>
<%}%>

<%
	DTOUser user = new DTOUser();
	user.setFirstName(request.getParameter("firstName"));
	user.setLastName(request.getParameter("lastName"));
	user.setEmail(request.getParameter("email"));
	Algo algo = new Algo();
	try {
		user.setPassword(algo.getSHA256AsHex(request.getParamenter("password")));
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	algo = null;
	DAOUser userController = new DAOUser();
	userController.post(user);
	userController = null;
	user = null;
%>

<jsp:forward page="../../view/auth/loginView.jsp"/>