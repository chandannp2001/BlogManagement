package com.sunbase.servlets;

import com.sunbase.models.Blog;
import com.sunbase.models.User;
import com.sunbase.dao.BlogDAO;
import com.sunbase.dao.impl.BlogDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/UpdateBlog")
@MultipartConfig
public class UpdateBlogServlet extends HttpServlet {
	
	private static final String SAVE_DIR = "C:\\Users\\chand\\eclipse-workspace\\Blog_Management\\src\\main\\webapp\\uploads";
	private BlogDAO blogDAO = new BlogDAOImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null && user.getRole().equals("Admin")) {

			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			Part filePart = request.getPart("file");
			System.out.println(filePart.getSize());
			
			Blog blog = blogDAO.getBlogById(id);
			blog.setTitle(title);
			blog.setContent(content);
			
			if(filePart.getSize()>0) {
				String fileName = getFileName(filePart);
				System.out.println(getServletContext().getRealPath("/"));
				String savePath = SAVE_DIR + File.separator + fileName;

				String savedfiePath = "uploads/" + fileName;
				File fileSaveDir = new File(savePath);
				filePart.write(savePath);
				blog.setImageVideoPath(savedfiePath);
			}
			
			boolean isUpdated = blogDAO.updateBlog(blog);
			Blog blogById = blogDAO.getBlogById(blog.getId());
			if (blogById != null) {

				if (isUpdated) {
					request.getSession().setAttribute("message", blogById.getTitle() + " Update successful");
				} else {
					request.getSession().setAttribute("message", blogById.getTitle() + " Update failed");
				}
			}

			response.sendRedirect("Home");
		} else {
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