
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Blog Registration</title>
<link rel="stylesheet" type="text/css" href="css/BlogRegistration.css">
</head>
<body>

	<nav class="navbar">
		<div class="nav-left">
			<a href="Home">Home</a>
		</div>

		<a href="Logout" class="logout-button">Logout</a>
	</nav>


	<div class="container">
		<h2>Register a New Blog</h2>
		<form action="BlogRegister" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="title">Title:</label> <input type="text" id="title"
					name="title" placeholder="Enter blog title" required>
			</div>
			<div class="form-group">
				<label for="content">Content:</label>
				<textarea id="content" name="content"
					placeholder="Enter blog content" required></textarea>
			</div>
			<div class="form-group">
				 <label for="file">Image:</label>
                <input type="file" name="file" id="file" accept="image/*,video/*" required>
			</div>
			<div class="form-group">
				<button type="submit">Submit</button>
			</div>
		</form>
		<div class="link">
			<p>
				<a href="Home">View Blogs</a>
			</p>
		</div>
	</div>
</body>
</html>