<%@ page import="java.util.LinkedList" %>
<%@ page import="es.uco.pw.business.dao.post.DAOPost" %>
<%@ page import="es.uco.pw.data.dto.post.DTOPost" %>
<%@ page import="es.uco.pw.data.dto.topic.DTOTopic" %>
<%@ page import="es.uco.pw.business.dao.topic.DAOTopic" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>My posts</title>
    <%
        String postsIdsString = request.getParameter("postsIds");
        LinkedList<Integer> postsIds = new LinkedList<>();
        for (String postIdString : postsIdsString.substring(1,postsIdsString.length()-1).split(", ")){
            try{
                postsIds.add(Integer.parseInt(postIdString));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        LinkedList<DTOTopic> topics = new DAOTopic().get();
    %>
</head>
    <body>
    <jsp:include page="/include/topBar.jsp"/>
    <button onclick="addPost()">add</button>

    <div id="postsContainer">
            <%
                DAOPost postController = new DAOPost();
                for (Integer id : postsIds){
                    DTOPost post = postController.get(id);
            %>
            <div id="<%=post.getId()%>">
                <div id="title" name="title"><%=post.getTitle()%></div>
                <div id="type" name="type"><%=post.getType()%></div>
                <div id="topics" name="topics"><%=post.getTopics()%></div>
                <div id="body" name="body"><%=post.getBody()%></div>
                <button onclick="editPost(<%=post.getId()%>)">edit</button>
            </div>
            <%}%>
        </div><br>
    </body>
</html>
