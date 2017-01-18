package ru.siblion.nesterov.client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by alexander on 17.01.2017.
 */
public class MyServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        /*User user = new User("Alexander", "passwd");
        request.setAttribute("user", user);*/
        /*String string = "FROM SERVLET";
        String username = request.getParameter("name");
        request.setAttribute("username", username);
        response.sendRedirect("index.jsp");*/


        // Must set the content type first
        response.setContentType("text/html");
        // Now obtain a PrintWriter to insert HTML into
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>" +
                "Get request!</title></head>");
        out.println("<body><h1>Get request!</h1></body></html>");


    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

    }

}
