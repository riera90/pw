<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customer" class="es.uco.pw.display.javabean.CustomerBean" scope="session"/>

<%if (customer.getUserId() != -1) {%>
    <a id="menulogout" href="pwanabeldiego/logout">Logout</a><br>
<%}else{%>
    <a id="menuLogin" class="itemMenu" href="./mvc/control/auth/loginController.jsp">Log In</a><br>
<%}%>
<a id="menuSignUp" href="./mvc/control/auth/registerController.jsp">
    <%
        if (customer.getUserId() == -1) out.println("Register");
        else out.println("Modify");
    %>
</a><br>
<a id="menuBoard" href="./mvc/control/board/boardController.jsp">board</a><br>
