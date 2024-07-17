<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Blog</title>
    <link rel="stylesheet" type="text/css" href="css/AddBlog.css">
</head>
<body>
    <nav class="navbar">
        <div class="nav-left">
            <a href="Home">Home</a>
        </div>
        <form class="search-form" action="Search" method="post">
            <select id="searchType" name="searchType">
                <option value="title">Search by Title</option>
                <option value="date">Search by Date</option>
                <option value="author">Search by Author ID</option>
            </select>
            <input name="searchtext" type="text" placeholder="Search..." required>
            <button type="submit">Search</button>
        </form>
        <a href="Logout" class="logout-button">Logout</a>
    </nav>
    
    <div class="container">
        <h2>Add New Blog</h2>
        <form action="AddBlogServlet" method="POST" enctype="multipart/form-data">
            <div class="input-group">
                <label for="title">Title:</label>
                <input type="text" name="title" id="title" required>
            </div>
            <div class="input-group">
                <label for="content">Content:</label>
                <textarea name="content" id="content" required></textarea>
            </div>
            <div class="input-group">
                <label for="file">Image/Video:</label>
                <input type="file" name="file" id="file" accept="image/*,video/*" required>
            </div>
            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
