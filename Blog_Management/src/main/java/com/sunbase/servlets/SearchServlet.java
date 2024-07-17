package com.sunbase.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbase.dao.impl.BlogDAOImpl;
import com.sunbase.models.Blog;
import com.sunbase.models.User;

@WebServlet("/Search")
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BlogDAOImpl blogDAOImpl;

	@Override
	public void init() throws ServletException {
		blogDAOImpl = new BlogDAOImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String searchType = req.getParameter("searchType");
		String searchtext = req.getParameter("query").toLowerCase().trim();

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		List<Blog> blogsBySearch = null;

		try {
		if (searchType != null) {

			if (searchType.equals("title")) {
				blogsBySearch = blogDAOImpl.getBlogsByTitle(searchtext);

			} else if (searchType.equals("date")) {
//				Timestamp valueOf = Timestamp.valueOf(searchtext);
				blogsBySearch = blogDAOImpl.getBlogsByDate(searchtext);
			} else if (searchType.equals("author")) {
				blogsBySearch = blogDAOImpl.getBlogsByAuthor(Integer.parseInt(searchtext));
			}
			if(!blogsBySearch.isEmpty()) {
				session.setAttribute("blogs", blogsBySearch);
				session.setAttribute("message", "Search Result For "+searchtext);
				
			}else {
				
				session.setAttribute("blogs", new ArrayList<Blog>());
				session.setAttribute("message", "No Search Result For "+searchtext);
				
			}
		}
		}catch(Exception e) {
			session.setAttribute("blogs", new ArrayList<Blog>());
			session.setAttribute("message", "No Search Result For "+searchtext+". Enter date in yyyy-mm-dd format");
			e.printStackTrace();
		}
			if (user != null ) {
				String role = user.getRole();
				if ("Admin".equals(role)) {
					resp.sendRedirect("admin_home.jsp");
				} else if ("Viewer".equals(role)) {
					resp.sendRedirect("viewer_home.jsp");
				}
			} else {
				resp.sendRedirect("login.jsp"); // Redirect to login if not logged in
			}

		
	}

}
