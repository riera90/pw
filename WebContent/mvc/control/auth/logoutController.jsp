<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customer" class="es.uco.pw.display.javabean.CustomerBean" scope="session"/>

<%
    customer.setUserId(-1);
    customer.clearLoginAttempt();
    response.sendRedirect("/pw");
%>


