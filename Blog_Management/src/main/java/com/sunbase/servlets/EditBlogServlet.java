package com.sunbase.servlets;


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

@WebServlet("/EditBlog")
public class EditBlogServlet extends HttpServlet {
    private BlogDAO blogDAO = new BlogDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	User user = (User)session.getAttribute("user");
    	
    	if(user!=null && user.getRole().equals("Admin")) {
    		
        int blogId = Integer.parseInt(request.getParameter("id"));
        Blog blog = blogDAO.getBlogById(blogId);

        if (blog != null) {
            request.setAttribute("blog", blog);
            request.getRequestDispatcher("BlogEdit.jsp").forward(request, response);
        } else {
        	request.getSession().setAttribute("message", "Blog not found");
            response.sendRedirect("admin_home.jsp");
        }
    	}else {
    		response.sendRedirect("home");
    	}
    }
    
   
}