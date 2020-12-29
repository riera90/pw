<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.data.dto.user.DTOUser, es.uco.pw.business.dao.user.DAOUser"%>
<%@ page import="es.uco.pw.business.dao.user.UserBuilder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>


<%
String fistName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
String email = request.getParameter("email");
//Date bornAt = null;
Date bornAt = new Date();
try {
	bornAt = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("bornAt"));
} catch (ParseException e) {
	e.printStackTrace();
}
String password = request.getParameter("password");
System.out.println("password: "+ password);
System.out.println("email: "+ email);

if (email == "" || password == "" || bornAt == null) {
	%><jsp:forward page="/mvc/view/auth/registerView.jsp"/><%
}
	DTOUser user = new DTOUser();
	user.setFirstName(fistName);
	user.setLastName(lastName);
	user.setEmail(email);
	user.setPassword(password);
	user.setBornAt(bornAt);
	user.setRoleId(1);
	DAOUser userController = new DAOUser();
	System.out.println(UserBuilder.toJson(user));
	System.out.println(userController.post(user).toString());
%>

<jsp:forward page="/mvc/view/success.jsp">
	<jsp:param name="msg" value="<%= password %>"/>
</jsp:forward>