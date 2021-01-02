<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customer" class="es.uco.pw.display.javabean.CustomerBean" scope="session"/>
<%@ page import="es.uco.pw.business.dao.user.DAOUser, es.uco.pw.data.dto.user.DTOUser, java.util.LinkedList"%>


<%
	if (customer.getUserId() != -1){
%>
		<jsp:forward page="/mvc/control/board/boardController.jsp"/>
<%
	}

	if (customer.getLoginAttempts() > 3){
		response.sendRedirect("https://www.uco.es");
	}else{

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email == null || password == null){
			%><jsp:forward page="/mvc/view/auth/loginView.jsp"/><%
		}

		LinkedList<DTOUser> users;
		users = new DAOUser().getByField("email", email);

		customer.registerLoginAttempt();

		if (users.size() == 1 && users.get(0).getPassword().equals(password)){
			customer.setUserId(users.get(0).getId());
			%>
				<jsp:forward page="/index.jsp"/>
			<%
		}else{
			%>
				<jsp:forward page="/mvc/view/auth/loginView.jsp">
					<jsp:param name="error" value="error, the user or the password is not correct"/>
				</jsp:forward>
			<%
		}
	}
%>

