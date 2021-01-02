<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <meta charset="UTF-8">
</head>
<body>
<jsp:include page="/include/topBar.jsp"/>

<a id="menuSignUp" href="/pwanabeldiego">Home</a><br>
<%
    String msg = request.getParameter("msg");
    out.println("msg: "+msg);
%>
</body>
</html>
