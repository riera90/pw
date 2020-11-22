<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%Class.forName("es.uco.pw");%>
<%@ page import="java.security.NoSuchAlgorithmException" %>

<%/*if (request.getParameter("firstName") == ""
		|| request.getParameter("lastName") == ""
		|| request.getParameter("password") == "")
{*/%>
<%//}%>

<%
	/*DTOUser user = new DTOUser();
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
	user = null;*/
%>

<jsp:forward page="./mvc/view/success.jsp"/>