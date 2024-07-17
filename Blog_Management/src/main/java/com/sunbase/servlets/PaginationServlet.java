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
import com.sunbase.models.User;

@WebServlet("/pagination")
public class PaginationServlet extends HttpServlet {

	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String pageNumber = req.getParameter("pageNumber");

		if (pageNumber != null && Integer.parseInt(pageNumber) > 0) {
			req.setAttribute("pageNumber", pageNumber);
		} else {
			req.setAttribute("pageNumber", null);
		}

		if ("Admin".equals(user.getRole())) {
			req.getRequestDispatcher("admin_home.jsp").forward(req, resp);
			return;
		} else if ("Viewer".equals(user.getRole())) {
			req.getRequestDispatcher("viewer_home.jsp").forward(req, resp);
			return;
		}

	}
}
