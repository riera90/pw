<%@ page import="java.util.LinkedList" %>
<%@ page import="es.uco.pw.data.dto.post.DTOPost" %>
<%@ page import="es.uco.pw.business.dao.topic.DAOTopic" %>
<%@ page import="es.uco.pw.data.dto.topic.DTOTopic" %>
<%@ page import="es.uco.pw.business.dao.user.DAOUser" %>
<%@ page import="es.uco.pw.data.dto.user.DTOUser" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="es.uco.pw.business.Daemons.FlashPostDaemon" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%DTOPost post = (DTOPost) request.getSession().getAttribute("post");%>

        <title><%=post.getTitle()%></title>

        <script>
            function publish(){
                if (document.getElementById("type").value === "flash")
                    document.getElementById("state").value = "waiting";
                else
                    document.getElementById("state").value = "published";
                return true;
            }

            function saveDraft(){
                document.getElementById("state").value = "edited";
                return true;
            }

            function deletePost(){
                document.getElementById("state").value = "deleted";
                return true;
            }
        </script>
    </head>
    <body>
    <jsp:include page="/include/topBar.jsp"/>
    <div> Id: <%=post.getId()%> </div>
    <div> Type: <%=post.getType()%> </div>
    <div> State: <%=post.getState()%> </div>

    <form name="form" method="POST" action="${pageContext.request.contextPath}/post">
        <div class="column">
            <input name="type" id="type" value="<%=post.getType()%>" hidden>
            <input name="id" id="id" value="<%=post.getId()%>" hidden>
            <input name="state" id="state" value="<%=post.getState()%>" hidden>

            <label for="title">Title :</label>
            <div><input id="title" type="text" name="title" placeholder="Title" value="<%=post.getTitle() == null ? "" : post.getTitle()%>" required></div>

            <label for="topics" <%if (!post.getType().equals("themed")) out.println("hidden");%>>Topics :</label>
            <div><select name="topics" id="topics" multiple <%if (!post.getType().equals("themed")) out.println("hidden");%>>
                <%
                    DAOTopic topicController = new DAOTopic();
                    LinkedList<DTOTopic> allTopics = topicController.get();
                    for (int i = 0; i < post.getTopics().size(); i++){
                        DTOTopic topic = topicController.get(post.getTopics().get(i));
                        for (int j = 0; j < allTopics.size(); j++){
                            if (topic.getId().equals(allTopics.get(j).getId())){
                                allTopics.remove(j);
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
            </select></div>

            <label id="targetsLabel" for="targets"  <%if (!post.getType().equals("targeted")) out.println("hidden");%>>Send To :</label>
            <div><select name="targets" id="targets" multiple  <%if (!post.getType().equals("targeted")) out.println("hidden");%>>
                <%
                    DAOUser userController = new DAOUser();
                    LinkedList<DTOUser> allUsers = userController.get();
                    for (int i = 0; i < post.getSentTo().size(); i++){
                        DTOUser user = userController.get(post.getSentTo().get(i));
                        for (int j = 0; j < allUsers.size(); j++){
                            if (user.getId().equals(allUsers.get(j).getId())){
                                allUsers.remove(j);
                            }
                        }

                %>
                <option value="<%=user.getId().toString()%>" selected><%=user.getFirstName()+" "+user.getLastName()%></option>
                <%
                    }
                    for (DTOUser user : allUsers){
                %>
                <option value="<%=user.getId().toString()%>"><%=user.getFirstName()+" "+user.getLastName()%></option>
                <%
                    }
                %>
            </select></div>

            <%DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");%>
            <div id="dates" <%if (!post.getType().equals("flash")) out.println("hidden");%>>
                <label id="publicationTimeLabel" for="publicationTime">publication time :</label>
                <input type="datetime-local" id="publicationTime" name="publicationTime" placeholder="yyyy-mm-ddTss:mm" value="<%=post.getPublishedAt() == null ? "" : dateFormat.format(post.getPublishedAt())%>">
                <label id="deleteTimeLabel" for="deleteTime">delete time :</label>
                <input type="datetime-local" id="deleteTime" name="deleteTime" placeholder="yyyy-mm-ddTss:mm" value="<%=post.getDeletedAt() == null ? "" : dateFormat.format(post.getDeletedAt())%>">
            </div>

            <label for="body">Body :</label>
            <div><textarea id="body" name="body" rows="4" cols="50" required><%=post.getBody() == null ? "" : post.getBody()%></textarea></div>

            <div><input type="submit" value="publish/save" onclick="publish()"></div>
            <div><input type="submit" value="Save in drafts" onclick="saveDraft()"></div>
            <div><input type="submit" value="delete" onclick="deletePost()"></div>
        </div>
    </form>

    </body>
</html>
