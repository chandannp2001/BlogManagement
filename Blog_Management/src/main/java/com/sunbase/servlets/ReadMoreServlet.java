package com.sunbase.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbase.dao.impl.BlogDAOImpl;
import com.sunbase.dao.impl.UserDAOImpl;
import com.sunbase.models.Blog;
import com.sunbase.models.User;

@WebServlet("/viewBlog")
public class ReadMoreServlet extends HttpServlet {

	private UserDAOImpl userDAOImpl;
	private BlogDAOImpl blogDAOImpl;

	@Override
	public void init() throws ServletException {
		userDAOImpl = new UserDAOImpl();
		blogDAOImpl = new BlogDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int viewId = Integer.parseInt(req.getParameter("id"));
		HttpSession session = req.getSession();
		Blog blogById = blogDAOImpl.getBlogById(viewId);
		if (blogById != null) {
			User author = userDAOImpl.getUserById(blogById.getAuthorId());
			req.setAttribute("blog", blogById);
			req.setAttribute("Author", author);
			
			req.getRequestDispatcher("ReadMore.jsp").forward(req, resp);
		}else {
			session.setAttribute("message","Not Found Blog" );
			req.getRequestDispatcher("ReadMore.jsp").forward(req, resp);
			return;
		}
	}
}
