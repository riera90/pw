<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Log In</title>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/forge/0.8.2/forge.all.min.js"></script>
    <script>
        function safePasswordPost(){
            var plainText = document.getElementById('plainTextPassword').value;
            var md = forge.md.sha256.create();
            md.start();
            md.update(plainText, "utf8");
            document.getElementById('password').value = md.digest().toHex();
            document.getElementById('plainTextPassword').remove();
            return true;
        }
    </script>
</head>

    <body>
    <%
        String error = request.getParameter("error");
        if (error != null)
            out.println(error);
    %>
    <form name="form" method="POST" action="${pageContext.request.contextPath}/mvc/control/auth/loginController.jsp" onsubmit="safePasswordPost()">
        <div class="column">
            <div><input id="email" type="text" name="email" placeholder="Email" required></div>
            <div><input id="plainTextPassword" type="password" name="plainTextPassword" placeholder="Password" required></div>
            <input id="password" type="hidden" name="password">
            <div><input type="submit" value="Log In"></div>
        </div>
    </form>
    </body>
</html>
