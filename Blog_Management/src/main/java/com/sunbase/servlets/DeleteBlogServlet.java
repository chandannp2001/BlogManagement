package com.sunbase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbase.dao.BlogDAO;
import com.sunbase.dao.impl.BlogDAOImpl;
import com.sunbase.models.Blog;
import com.sunbase.models.User;

@WebServlet("/DeleteBlog")
public class DeleteBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null && user.getRole().equals("Admin")) {

			int blogId = Integer.parseInt(request.getParameter("id"));
			BlogDAO blogDAO = new BlogDAOImpl();
			Blog blogById = blogDAO.getBlogById(blogId);

			boolean isDeleted = blogDAO.deleteBlog(blogId);
			if (blogById != null) {
				if (isDeleted) {
					request.getSession().setAttribute("message", "Blog " + blogById.getTitle() + " Deleted successful");
				} else {
					request.getSession().setAttribute("message", "Blog " + blogById.getTitle() + "Deletion failed");
				}
			}
			response.sendRedirect("Home");
		}else {
			response.sendRedirect("Home");
		}
	}
}