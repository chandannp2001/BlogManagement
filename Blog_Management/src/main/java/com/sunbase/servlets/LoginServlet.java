package com.sunbase.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.sunbase.dao.UserDAO;
import com.sunbase.dao.impl.UserDAOImpl;
import com.sunbase.models.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		HttpSession session = req.getSession();
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUserByUsername(username);
		// Basic validation
		boolean isValid = true;
		if (user == null) {
			req.setAttribute("usernameError", "UserName Not Found.");
			isValid = false;
		}
		if (user != null && !BCrypt.checkpw(password, user.getPassword())) {
			req.setAttribute("passwordError", "Password Doesn't Match.");
			isValid = false;
		}

		if (!isValid) {
			// Forward back to the login page with errors
			req.setAttribute("username", username);
			req.setAttribute("password", userDAO);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		} else {

			session.setAttribute("user", user);
			resp.sendRedirect("Home"); // Redirect to a welcome page or dashboard
			return;
		}

	}
}
