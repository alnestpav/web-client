package ru.siblion.nesterov.client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by alexander on 25.01.2017.
 */
public class SignoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true); // что значит аргумент?
        session.invalidate();
        response.sendRedirect("signin.jsp");

    }



}
