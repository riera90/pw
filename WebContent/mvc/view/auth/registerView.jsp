<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <form method="post" action="../../control/auth/registerController.jsp">
            <div class="column">
                <div><input type="text" id="firstName" placeholder="First Name" required></div>
                <div><input type="text" id="lastName" placeholder="Last Name" required></div>
                <div><input type="text" id="email" placeholder="Email" required></div>
                <div><input type="password" id="password" placeholder="Password" required></div>
                <div><input type="submit" value="Register"></div>
            </div>
        </form>
    </body>
</html>
