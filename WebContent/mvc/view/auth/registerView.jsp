<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <meta charset="UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/forge/0.8.2/forge.all.min.js"></script>
        <script type="text/Javascript">
            function hashPassword(){
                var plainText = document.getElementById('plainTextPassword').value;
                var md = forge.md.sha256.create();
                md.start();
                md.update(plainText, "utf8");
                document.getElementById("password").innerHTML = md.digest().toHex();
                document.getElementById("plainTextPassword").remove();
            }
        </script>
    </head>
    <body>
        <form method="post" action="./mvc/control/auth/registerController.jsp" onsubmit="hashPassword()">
            <div class="column">
                <div><input type="text" id="firstName" placeholder="First Name" required></div>
                <div><input type="text" id="lastName" placeholder="Last Name" required></div>
                <div><input type="text" id="email" placeholder="Email" required></div>
                <div><input type="password" id="plainTextPassword" placeholder="Password" required></div>
                <input type="hidden" id="password" value="">
                <div><input type="submit" value="Register" onclick="safePost()"></div>
            </div>
        </form>
    </body>
</html>
