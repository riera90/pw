<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customer" class="es.uco.pw.display.javabean.CustomerBean" scope="session"/>

<%@ page import="es.uco.pw.data.dto.user.DTOUser, es.uco.pw.business.dao.user.DAOUser"%>
<%@ page import="es.uco.pw.business.dao.user.UserBuilder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.text.DateFormat" %>


<%

	if (customer.getUserId() != -1){
		DTOUser user = new DAOUser().get(customer.getUserId());
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = dateFormat.format(user.getBornAt());
		%>
		<jsp:forward page="/mvc/view/auth/registerView.jsp">
			<jsp:param name="id" value="<%=user.getId().toString() %>"/>
			<jsp:param name="email" value="<%=user.getEmail() %>"/>
			<jsp:param name="firstName" value="<%=user.getFirstName() %>"/>
			<jsp:param name="lastName" value="<%=user.getLastName() %>"/>
			<jsp:param name="bornAt" value="<%=dateString%>"/>
		</jsp:forward>
		<%
	}

	String fistName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String email = request.getParameter("email");
	String bornAtString = request.getParameter("bornAt");
	Date bornAt = null;
	try {
		if (bornAtString != null)
			bornAt = new SimpleDateFormat("dd-MM-yyyy").parse(bornAtString);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	String password = request.getParameter("password");
	System.out.println("password: "+ password);
	System.out.println("email: "+ email);

	if (fistName == null || lastName == null || email == null || password == null || bornAt == null) {
		%>
		<jsp:forward page="/mvc/view/auth/registerView.jsp"/>
		<%
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