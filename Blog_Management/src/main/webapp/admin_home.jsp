<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sunbase.models.Blog"%>
<%
List<Blog> blogs = (List<Blog>) session.getAttribute("blogs");
String message = (String)session.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blog Viewer</title>
<link rel="stylesheet" type="text/css" href="css/admin_home.css">
<script src="js/admin.js"></script>
</head>
<body>
	<nav class="navbar">
		<div class="nav-left">
			<a href="Home">Home</a>
			<a href="BlogRegistration.jsp">Add Blog</a>
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
		<h2>Admin DashBoard</h2>
		
		  <% if (message != null) { %>
            <div class="message" ><h3 style="color: red; text-align: center; padding: 10px;"><%= message %></h3></div>
            <%
                session.removeAttribute("message");
            %>
        <%}%>
		<div class="blog-cards">
			<%
			int currentPage = (request.getParameter("pageNumber") != null) ? Integer.parseInt(request.getParameter("pageNumber"))
					: 1;
			int numberofDetails = 6;//number of customer details to be display per page.

			if (blogs != null) {
				int i = 0;
				int startIndex = currentPage == 1 ? 0 : (currentPage - 1) * numberofDetails;
				int endIndex = currentPage * numberofDetails;

				for (Blog blog : blogs) {
					i++;
					if (i > startIndex && i <= endIndex) {
			%>
			<div class="blog-card">
				<h3><%=blog.getTitle()%></h3>
				<%
				if (blog.getImageVideoPath() != null) {
				%>
				<img src="<%=blog.getImageVideoPath() %>" alt="<%=blog.getTitle()+"uvsc"%>"
					class="blog-image">
				<!--blog.getImageVideoPath()h() %> -->
				<%
				}
				%>
				<p><%=blog.getContent().substring(0, Math.min(blog.getContent().length(), 100)) + "..."%></p>
				<a href="viewBlog?id=<%=blog.getId()%>" target="_blank"
					class="read-more">Read More</a>
				<a href="EditBlog?id=<%=blog.getId()%>" class="edit-button">Edit</a>
				<a href="javascript:void(0);" onclick="confirmDelete(<%= blog.getId()%>)"  class="delete-button">Delete</a>
			</div>
			<%
			}
			}
			} else{
			%>
			<p>No Blogs Found.</p>
			<%
			}
			%>
		</div>
		<!-- pagination -->
		<div class="pagination">
			<%
			
			if (blogs!= null && blogs.size() > 0) {
				int totalCustomers = blogs.size();
				int totalPages = (int) Math.ceil((double) totalCustomers / numberofDetails);
				if (totalPages > 1) {
			%>
			<span>Page: </span>
			<%
			if (currentPage != 1) {
			%>
			<span><a class="nextpreivous"
				href="pagination?pageNumber=<%=currentPage - 1%>"> Previous</a></span>
			<%
			}
			for (int i = 1; i <= totalPages; i++) {
			if (i == currentPage) {
			%>

			<span class="current-page"><%=i%> </span>


			<%
			} else {
			%>
			<a class="Remaining-page" href="pagination?pageNumber=<%=i%>"><%=i%></a>
			<%
			}
			}
			if (currentPage != totalPages) {
			%>

			<span><a class="nextpreivous"
				href="pagination?pageNumber=<%=currentPage + 1%>"> </a></span>
			<%
			}
			}
			}
			%>
		</div>
	</div>
</body>
</html>
