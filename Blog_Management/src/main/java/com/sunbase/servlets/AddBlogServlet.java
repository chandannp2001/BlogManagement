package com.sunbase.servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/AddBlogServlet")
@MultipartConfig
public class AddBlogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String SAVE_DIR = "C:\\Users\\chand\\eclipse-workspace\\Blog_Management\\src\\main\\webapp\\uploads";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Part filePart = request.getPart("file");
        System.out.println(filePart);
        
        // Define the path to save the uploaded file
        String fileName = getFileName(filePart);
        System.out.println(fileName);
        System.out.println(getServletContext().getRealPath("/"));
        String savePath =SAVE_DIR + File.separator + fileName;

        // Save the file
        File fileSaveDir = new File(savePath);
        filePart.write(savePath);

        // Here, you would normally save the blog data into the database
        // For simplicity, we'll just log the details
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
        System.out.println("File Path: " + savePath);

//        // Redirect to a confirmation page or back to the form with a success message
//        request.getSession().setAttribute("message", "Blog added successfully!");
        response.sendRedirect("login.jsp");
    }

    // Utility method to get the file name from the part
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

