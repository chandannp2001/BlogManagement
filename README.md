Project Overview
The Blog Management System is a web application designed for managing blog posts. It allows administrators to create, read, update, and delete blog posts efficiently.
Users can search for blogs by title, date, or author and view detailed information about each blog.

Features
  User authentication for secure access
  Dashboard for administrators
  Create new blog posts
  Edit existing blog posts
  Delete blog posts
  View all blog posts
  Search blog posts by title, date, or author
  Pagination for blog list
  
Technologies Used
  Backened-Java, JSP (JavaServer Pages), Servlets
  Database-MySQL
  FrontEnd-HTML, CSS, JavaScript


  Getting Started
  Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

Prerequisites
Ensure you have the following software installed on your machine:

Java Development Kit (JDK)
Apache Tomcat
MySQL Database

Installation
  Clone the repository: git clone https://github.com/your-username/blog-management-system.git
  cd blog-management-system

Create a MySQL database:

CREATE DATABASE blog_db;
USE blog_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) NOT NULL
);

CREATE TABLE blogs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    author_id INT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    imageVideoPath VARCHAR(255)
);
Configure database connection:

Edit the DatabaseUtil.class  to match your MySQL configuration:

properties
jdbc.url=jdbc:mysql://localhost:3306/blog_db
jdbc.username=root
jdbc.password=yourpassword

Deploy to Apache Tomcat:

 you can set up your IDE (e.g., Eclipse, IntelliJ) to deploy the project directly to Tomcat.

Start the server:

Start your Apache Tomcat server and navigate to http://localhost:8080/blog-management-system.

Create a account using register page before login

Use the following credentials to log in as an admin:

Username: yourUsername
Password: yourPassword
(You can create your own users through the database or modify the code to include user registration.)

Change the Save Directory for files which will uploaded by admin as your requriement by changing SAVE_DIR variable in BlogRegistrationServlet and UpdateBlogServlet class in com.sunbase.servlets package

Admin Dashboard:

Create, edit, and delete blog posts.
View all blogs with pagination.
Search blogs by title, date, or author.
Viewing Blogs:

Users can view blog summaries on the main page.
Click "Read More" to view detailed blog content.

Acknowledgments
Thanks to Tap Academy for their guidance and support.

