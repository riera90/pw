<%@ page import="es.uco.pw.business.dao.topic.DAOTopic" %>
<%@ page import="es.uco.pw.data.dto.topic.DTOTopic" %>
<%@ page import="java.util.LinkedList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>
            <%
                String id = request.getParameter("id");

                if (id == null) out.println("Register");
                else out.println("Modify");
            %>
        </title>
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

            String firstName = request.getParameter("firstName");
            if (firstName == null) firstName = "";

            String lastName = request.getParameter("lastName");
            if (lastName == null) lastName = "";

            String email = request.getParameter("email");
            if (email == null) email = "";

            String bornAt = request.getParameter("bornAt");
            if (bornAt == null) bornAt = "";

            String interestsIds = request.getParameter("interestsIds");
            if (interestsIds == null) interestsIds = "";
        %>
        <form name="form" method="POST" action="../../control/auth/registerController.jsp" onsubmit="safePasswordPost()">

            <div class="column">
                <label for="firstName">First Name :</label>
                <div><input id="firstName" type="text" name="firstName" placeholder="First Name" value="<%=firstName%>" required></div>
                <label for="lastName">Last Name :</label>
                <div><input id="lastName" type="text" name="lastName" placeholder="Last Name" value="<%=lastName%>" required></div>
                <label for="email">email :</label>
                <div><input id="email" type="text" name="email" placeholder="Email" value="<%=email%>" required></div>
                <label for="bornAt">Birth Date :</label>
                <div><input id="bornAt" type="text" name="bornAt" placeholder="DD-MM-YYYY" value="<%=bornAt%>" required></div>
                <label for="plainTextPassword">Password :</label>
                <div><input id="plainTextPassword" type="password" name="plainTextPassword" placeholder="Password" required></div>
                <label for="topics">Interests :</label>
                <select name="topics" id="topics" multiple>
                    <%
                        DAOTopic topicController = new DAOTopic();
                        LinkedList<DTOTopic> allTopics = topicController.get();
                        for (String stringId : interestsIds.substring(1,interestsIds.length()-1).split(", ")){
                            DTOTopic topic = topicController.get(Integer.parseInt(stringId));
                            for (int i = 0; i < allTopics.size(); i++){
                                if (topic.getId().equals(allTopics.get(i).getId())){
                                    allTopics.remove(i);
                                }
                            }
                            %>
                                <option value="<%=topic.getId().toString()%>" selected><%=topic.getName()%></option>
                            <%
                        }
                        for (DTOTopic topic : allTopics){
                            %>
                                <option value="<%=topic.getId().toString()%>"><%=topic.getName()%></option>
                            <%
                        }
                    %>

                </select>
                <input id="password" type="hidden" name="password">
                <div><input type="submit" value="Register"></div>
            </div>
        </form>
    </body>
</html>
