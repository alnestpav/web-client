package ru.siblion.nesterov.client.servlets;

import ru.siblion.nesterov.client.managing.RecordsManager;
import ru.siblion.nesterov.client.type.Action;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by alexander on 25.01.2017.
 */
public class SigninServlet extends HttpServlet {
    @EJB
    private RecordsManager recordsManager;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher;
        if (request.getUserPrincipal() != null) {
            recordsManager.addRecord(request.getRemoteUser(), Action.SIGN_IN, "Sign in", new Date());
            requestDispatcher = request.getRequestDispatcher("/search");
        } else {
            requestDispatcher = request.getRequestDispatcher("signin.jsp");
        }
        requestDispatcher.forward(request, response);
    }



}