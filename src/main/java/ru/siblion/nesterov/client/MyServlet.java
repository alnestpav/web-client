package ru.siblion.nesterov.client;

import ru.siblion.nesterov.logreader.ws.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        String dateFromString = request.getParameter("dateFrom");
        String dateToString = request.getParameter("dateTo");
        String fileFormatString = request.getParameter("fileFormat");

        System.out.println(string);
        System.out.println(location);
        System.out.println(dateFromString);
        System.out.println(dateToString);
        System.out.println(fileFormatString);

        List<DateInterval> dateIntervals = new ArrayList<>();
        DateInterval dateInterval = new DateInterval();
        dateInterval.setDateFrom(dateFromString); // String преобразуется к XMLGregorianCalendar автоматически
        dateInterval.setDateFrom(dateToString);

        SoapWebService service = new SoapWebServiceService().getSoapWebServicePort();

        Request clientRequest = new Request();
        clientRequest.setString(string);
        clientRequest.setLocation(location);

        FileFormat fileFormat = null;
        if (!fileFormatString.equals("no")) { // потом переписать
            fileFormat = FileFormat.fromValue(fileFormatString);
        }
        clientRequest.setFileFormat(fileFormat);


        clientRequest.getDateIntervals().add(dateInterval);
        Response clientResponse = service.getListOfLogMessages(clientRequest);
        List<LogMessage> logMessages = clientResponse.getLogMessages();
        Object outputFile = clientResponse.getOutputFile();
        System.out.println("OutputFile: " + outputFile);
        if (outputFile != null) { // разобраться почему одновременно outputFile и logMessages существуют!
            System.out.println("1");
            request.setAttribute("outputFile", clientResponse.getOutputFile()); // object к string преобразование нужно ли
        } else if (logMessages != null) {
            System.out.println("2");
            request.setAttribute("logMessages", logMessages); //
        } else {
            System.out.println("3");
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    private void get

}
