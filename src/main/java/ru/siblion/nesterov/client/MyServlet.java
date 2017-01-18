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

    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        // Must set the content type first
        response.setContentType("text/html");
        // Now obtain a PrintWriter to insert HTML into
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>" +
                "Hello World!</title></head>");
        out.println("<body><h1>Hello World!</h1></body></html>");
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>" +
                "Hello World!</title></head>");
        out.println("<body><h1>Hello World!</h1></body></html>");
    }

}
