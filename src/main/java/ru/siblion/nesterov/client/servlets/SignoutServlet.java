package ru.siblion.nesterov.client.servlets;

import ru.siblion.nesterov.client.utils.ClientLogger;
import ru.siblion.nesterov.client.type.Action;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
    @EJB
    private ClientLogger clientLogger;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        if (request.getRemoteUser() != null) {
            clientLogger.log(request.getRemoteUser(), Action.SIGN_OUT, "Sign out");
            HttpSession session = request.getSession(false);
            session.invalidate();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("signin.jsp");
        requestDispatcher.forward(request, response);
    }



}
