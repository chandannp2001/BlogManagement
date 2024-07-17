 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sunbase.models.Blog,com.sunbase.models.User"%>
<%
    Blog blog = (Blog) request.getAttribute("blog");
    User Author = (User) request.getAttribute("Author");
    String message = (String)session.getAttribute("message");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blog Details</title>
<link rel="stylesheet" type="text/css" href="css/ReadMore.css">
<script src="js/viewer.js"></script>
</head>
<body>
    <nav class="navbar">
        <div class="nav-left">
            <a href="Home">Home</a>
        </div>
        
        <a href="Logout" class="logout-button">Logout</a>
    </nav>

    <div class="container">
    <% if (message != null) { %>
            <div class="message" ><h3 style="color: red; text-align: center; padding: 10px;"><%= message %></h3></div>
            <%
                session.removeAttribute("message");
            %>
        <%}%>
    <%if(blog!=null){ %>
        <h2><%= blog.getTitle() %></h2>
        <div class="blog-details">
           <%
                if (blog.getImageVideoPath() != null) {
            %> 
            <img src="img/1173346.png" alt="<%= blog.getTitle() %>" class="blog-detail-image">
            <!-- Replace with blog.getImageVideoPath() when the image path is available -->
             <%
                }
            %> 
            <h3>Author: <%=Author.getUsername() %></h3>
            <h3>Posted At: <%=blog.getCreatedAt() %></h3>
            <h3>Last Updated: <%=blog.getUpdatedAt() %></h3>
            <h4><%=blog.getContent()%></h4>
            <%}else{ %>
            <h2>Blog Not Found</h2><%} %>
        </div>
    </div>
</body>
</html>
