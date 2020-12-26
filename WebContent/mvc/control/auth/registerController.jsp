<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.data.dto.user.DTOUser, es.uco.pw.business.dao.user.DAOUser" %>


<%
String fistName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
String email = request.getParameter("email");
String password = request.getParameter("password");
System.out.println("password: "+ password);
System.out.println("email: "+ email);

if (email == "" || password == "") {
	%><jsp:forward page="../../view/auth/registerView.jsp"/><%
}
	DTOUser user = new DTOUser();
	user.setFirstName(fistName);
	user.setLastName(lastName);
	user.setEmail(email);
	user.setPassword(password);
	DAOUser userController = new DAOUser();
	userController.post(user);

%>

<jsp:forward page="../../view/success.jsp">
	<jsp:param name="msg" value="<%= password %>"/>
</jsp:forward>