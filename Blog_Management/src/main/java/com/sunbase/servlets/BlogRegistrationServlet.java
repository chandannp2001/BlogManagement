package com.sunbase.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.sunbase.dao.BlogDAO;
import com.sunbase.dao.impl.BlogDAOImpl;
import com.sunbase.models.Blog;
import com.sunbase.models.User;

@WebServlet("/BlogRegister")
@MultipartConfig
public class BlogRegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "C:\\Users\\chand\\eclipse-workspace\\Blog_Management\\src\\main\\webapp\\uploads";
	private BlogDAO blogDAOImpl;

	@Override
	public void init() {
		blogDAOImpl = new BlogDAOImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		
		if (user != null && user.getRole().equals("Admin")) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			Part filePart = request.getPart("file");

			// Define the path to save the uploaded file
			String fileName = getFileName(filePart);
//			System.out.println(getServletContext().getRealPath("/"));
			String savePath = SAVE_DIR + File.separator + fileName;

			String savedfiePath = "uploads/" + fileName;
			// Save the file
			File fileSaveDir = new File(savePath);
			filePart.write(savePath);

//		User user = (User)session.getAttribute("user");

			// Create a new Blog object

			Blog newBlog = new Blog(title, content, savedfiePath, user.getId());

			// Use the DAO to create the blog
			boolean Insertedblog = blogDAOImpl.createBlog(newBlog);
			if (Insertedblog) {
				request.getSession().setAttribute("message", "Blog " + newBlog.getTitle() + " Created successful");
			} else {
				request.getSession().setAttribute("message", "Blog " + newBlog.getTitle() + " Creation failed");
			}
			response.sendRedirect("Home");
			return;
		} else {
			// Redirect to HomeServlet
			response.sendRedirect("Home");
		}
	}

	private String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		for (String token : contentDisp.split(";")) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf("=") + 2, token.length() - 1);
			}
		}
		return "";
	}
}
