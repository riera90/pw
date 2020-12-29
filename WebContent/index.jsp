<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customer" class="es.uco.pw.display.javabean.CustomerBean" scope="session"/>

<a id="menuLogin" class="itemMenu" href="./mvc/control/auth/loginController.jsp">Log In</a>
<a id="menuSignUp" href="./mvc/control/auth/registerController.jsp">
    <%
        if (customer.getUserId() == -1) out.println("Register");
        else out.println("Modify");
    %>
</a>