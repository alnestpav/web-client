package ru.siblion.nesterov.client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;


/**
 * Created by alexander on 23.01.2017.
 */
public class DownloadFileServlet extends HttpServlet {
    private static final String PATH = "http://192.168.0.164:7001/logreader-1.0.1/resources/restWebService/";
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String fileName = URLEncoder.encode(request.getParameter("fileName"), "UTF-8"); // сохраняет знак плюс "+"
        System.out.println("fileName " + fileName);
        response.sendRedirect(PATH + fileName);
    }
}
