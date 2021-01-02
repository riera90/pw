<%@ page import="java.util.LinkedList" %>
<%@ page import="es.uco.pw.business.dao.post.DAOPost" %>
<%@ page import="es.uco.pw.data.dto.post.DTOPost" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Board</title>
        <%
            String postsIdsString = request.getParameter("postsIds");
            LinkedList<Integer> postsIds = new LinkedList<>();
            for (String postIdString : postsIdsString.substring(1,postsIdsString.length()-1).split(", ")){
                postsIds.add(Integer.parseInt(postIdString));
            }
        %>
        <script src="../../../js/boardFilter.js"></script>
    </head>
    <body>
        <div id="filterBox">
            <div id="typeFilterBox">
                <label for="typeFilter">Type</label><input id="typeFilter" type="text">
                <label for="titleFilter">Title</label><input id="titleFilter" type="text">
                <label for="topicFilter">Topic</label><input id="topicFilter" type="text">
                <button onclick="filter()">Filter</button>
            </div>
        </div>
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
                </div>
            <%}%>
        </div><br>
    </body>
</html>
