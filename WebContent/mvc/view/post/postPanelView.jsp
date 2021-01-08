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
    <script>
        function addPost(){
            this.sessionStorage.setItem("postId", "-1");
            window.location.href=window.location.href.toString().split("/")[0]+"/pwanabeldiego/post";
            return true;
        }

        function editPost(id){
            window.location.href=window.location.href.toString().split("/")[0]+"/pwanabeldiego/post?id="+id;
            return true;
        }
    </script>
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
                <div id="<%=post.getId()%>" style="margin: 50px">
                    <div id="title" name="title">title: <%=post.getTitle()%></div>
                    <div id="type" name="type">type: <%=post.getType()%></div>
                    <div id="topics" name="topics">topics: <%for (Integer topic:post.getTopics()){out.println(new DAOTopic().get(topic).getName()+", ");}%></div>
                    <div id="body" name="body" style="margin: 10px"><%=post.getBody()%></div>
                </div>
                <button onclick="editPost(<%=post.getId()%>)">edit</button>
            </div>
            <%}%>
        </div><br>
    </body>
</html>
