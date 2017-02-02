package ru.siblion.nesterov.client.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alexander on 25.01.2017.
 */
public class SigninServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher;
        if (request.getUserPrincipal() != null) {
            requestDispatcher = request.getRequestDispatcher("/search");
        } else {
            requestDispatcher = request.getRequestDispatcher("signin.jsp");
        }
        requestDispatcher.forward(request, response);
    }



}