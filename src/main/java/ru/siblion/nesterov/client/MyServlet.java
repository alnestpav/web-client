package ru.siblion.nesterov.client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alexander on 17.01.2017.
 */
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        User user = new User("Alexander", "passwd");
        request.setAttribute("user", user);
        String username = request.getParameter("name");
        System.out.println(username);
        request.setAttribute("username", username);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST");
        String string = request.getParameter("string");
        String location = request.getParameter("location");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        String fileFormat = request.getParameter("fileFormat");

        System.out.println(string);
        System.out.println(location);
        System.out.println(dateFrom);
        System.out.println(dateTo);
        System.out.println(fileFormat);

    }

}
