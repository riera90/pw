<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.data.dto.user.DTOUser" %>
<%
    DTOUser user = new DTOUser();
%>
<jsp:forward page="./mvc/view/auth/registerView.jsp"/>
