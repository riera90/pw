<%@ page import="java.util.LinkedList" %>
<%@ page import="es.uco.pw.business.dao.post.DAOPost" %>
<%@ page import="es.uco.pw.data.dto.post.DTOPost" %>
<%@ page import="es.uco.pw.data.dto.topic.DTOTopic" %>
<%@ page import="es.uco.pw.business.dao.topic.DAOTopic" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Board</title>
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
        <script src="../../../js/boardFilter.js"></script>
    </head>
    <body>
        <form name="searchForm" method="POST" action="../../control/board/boardController.jsp">
            <div id="searchBox">
                <label for="topicSearch">Topic</label>
                <select id="topicSearch" name="topicSearch">
                    <option value="">--</option>
                    <%for (DTOTopic topic : topics){%>
                    <option value="<%=topic.getId().toString()%>"><%=topic.getName()%></option>
                    <%}%>
                </select>
                <label for="titleSearch">Title</label><input id="titleSearch" name="titleSearch" type="text">
                <label for="typeSearch">Type</label>
                <select id="typeSearch" name="typeSearch">
                    <option value="">--</option>
                    <option value="general">General</option>
                    <option value="targeted">Targeted</option>
                    <option value="flash">Flash</option>
                    <option value="themed">Themed</option>
                </select>
                <input type="submit" value="Search">
            </div>
        </form>

        <div id="filterBox">
            <label for="topicFilter">Topic</label>
            <select id="topicFilter">
                <option value="">--</option>
                <%for (DTOTopic topic : topics){%>
                    <option value="<%=topic.getId().toString()%>"><%=topic.getName()%></option>
                <%}%>
            </select>
            <label for="titleFilter">Title</label><input id="titleFilter" type="text">
            <label for="typeFilter">Type</label>
            <select id="typeFilter">
                <option value="">--</option>
                <option value="general">General</option>
                <option value="targeted">Targeted</option>
                <option value="flash">Flash</option>
                <option value="themed">Themed</option>
            </select>
            <button onclick="filter()">Filter</button>
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
