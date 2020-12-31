<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customer" class="es.uco.pw.display.javabean.CustomerBean" scope="session"/>

<%@ page import="es.uco.pw.data.dto.user.DTOUser" %>
<%@ page import="es.uco.pw.business.dao.user.DAOUser" %>
<%@ page import="es.uco.pw.business.dao.user.UserBuilder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Iterator" %>


<%
	if (customer.getUserId() != -1 && request.getMethod().equals("GET")){
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
			<jsp:param name="topicsIds" value="<%=user.getInterests().toString()%>"/>
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
	String[] topics = request.getParameterValues("topics");

	if (fistName == null || lastName == null || email == null || bornAt == null || (password == null && customer.getUserId() == -1)) {
		%>
		<jsp:forward page="/mvc/view/auth/registerView.jsp"/>
		<%
	}
	DAOUser userController = new DAOUser();
	DTOUser user;
	if (customer.getUserId() != -1) {
		user = userController.get(customer.getUserId());
	} else {
		user = new DTOUser();
		user.setRoleId(1);
	}
	user.clearInterests();
	for (String topicIdString : topics){
		user.addInterest(Integer.parseInt(topicIdString));
	}
	user.setFirstName(fistName);
	user.setLastName(lastName);
	user.setEmail(email);
	user.setBornAt(bornAt);
	if (password != null)
		user.setPassword(password);
	DTOUser retUser = userController.put(user);
	System.out.println(UserBuilder.toJson(retUser));
%>

<jsp:forward page="/mvc/view/success.jsp">
	<jsp:param name="msg" value="<%= UserBuilder.toJson(user) %>"/>
</jsp:forward>