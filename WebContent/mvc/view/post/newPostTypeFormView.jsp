<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Post Type Selector</title>
    </head>
    <body onload="updatePostType()">
    <jsp:include page="/include/topBar.jsp"/>
    <form name="form" method="POST" action="${pageContext.request.contextPath}/post/new">
            <label for="type">Type :</label>
            <div><select name="type" id="type">
                <option value="general">General</option>
                <option value="targeted">Targeted</option>
                <option value="flash">Flash</option>
                <option value="themed">Themed</option>
            </select></div>
            <div><input type="submit" value="Save"></div>
        </div>
    </form>

    </body>
</html>
