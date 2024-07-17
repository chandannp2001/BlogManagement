<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sunbase.models.Blog"%>
<%
Blog blog = (Blog) request.getAttribute("blog");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Blog</title>
<link rel="stylesheet" type="text/css" href="css/BlogEdit.css">
<script src="js/admin.js"></script>
</head>
<body>
    <nav class="navbar">
        <div class="nav-left">
            <a href="Home">Home</a>
        </div>
        <form class="search-form">
            <select id="searchType" name="searchType">
                <option value="title">Search by Title</option>
                <option value="date">Search by Date</option>
                <option value="author">Search by Author ID</option>
            </select> 
            <input type="text" placeholder="Search..." id="searchInput">
            <a href="javascript:void(0);" class="search-icon" onclick="searchBlogs()">Search</a>
        </form>
        <a href="Logout" class="logout-button">Logout</a>
    </nav>

    <div class="container">
        <h2>Edit Blog</h2>
        <form action="UpdateBlog" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<%=blog.getId()%>">
            <div class="input-group">
                <label for="title">Title:</label> 
                <input type="text" name="title" id="title" value="<%=blog.getTitle()%>" required>
            </div>
            <div class="input-group">
            <label>Image</label>
                <% if (blog.getImageVideoPath() != null) { %>
                    <img src="<%=blog.getImageVideoPath()%>" alt="<%=blog.getTitle()%>" class="blog-image">
                <% } %>
            </div>
            <div class="input-group">
                <label for="content">Content:</label>
                <textarea name="content" id="content" required> <%=blog.getContent()%></textarea>
            </div>
            <div class="input-group">
               <label for="file">Update Image:</label>
                <input type="file" name="file" id="file" accept="image/*,video/*" >
            </div>
           
            <div class="button-group">
                <button type="submit" class="save-button">Save</button>
                <a href="admin_home.jsp" class="cancel-button">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
