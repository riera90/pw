<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
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
        <form name="form" method="POST" action="../../control/auth/registerController.jsp" onsubmit="safePasswordPost()">
            <div class="column">
                <div><input id="firstName" type="text" name="firstName" placeholder="Nombre" required></div>
                <div><input id="lastName" type="text" name="lastName" placeholder="Apellidos" required></div>
                <div><input id="email" type="text" name="email" placeholder="Email" required></div>
                <div><input id="bornAt" type="text" name="bornAt" placeholder="Fecha de nacimiento DD-MM-YYYY" required></div>
                <div><input id="plainTextPassword" type="password" name="plainTextPassword" placeholder="Contraseña" required></div>
                <input id="password" type="hidden" name="password">
                <div><input type="submit" value="Registrar"></div>
            </div>
        </form>
    </body>
</html>
