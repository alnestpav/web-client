package ru.siblion.nesterov.client.servlets;

import ru.siblion.nesterov.client.managing.RecordsManager;
import ru.siblion.nesterov.client.type.Action;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by alexander on 25.01.2017.
 */
public class SignoutServlet extends HttpServlet {
    @EJB
    private RecordsManager recordsManager;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        if (request.getRemoteUser() != null) {
            recordsManager.addRecord(request.getRemoteUser(), Action.SIGN_OUT, "Sign out", new Date());
            HttpSession session = request.getSession(false);
            session.invalidate();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("signin.jsp");
        requestDispatcher.forward(request, response);
    }



}
