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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexander on 17.01.2017.
 */
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
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
        String outputFile = (String) clientResponse.getOutputFile();
        System.out.println("OutputFile: " + outputFile);
        if (outputFile != null) { // разобраться почему одновременно outputFile и logMessages существуют!
            System.out.println("1");
            request.setAttribute("outputFile", outputFile); // object к string преобразование нужно ли
            Pattern fileNamePattern = Pattern.compile("\\\\[^\\\\]*$");
            Matcher fileNameMatcher = fileNamePattern.matcher(outputFile);
            fileNameMatcher.find();
            String fileName = fileNameMatcher.group().substring(1); // получить имя файла, убрать первый символ '\'
            System.out.println("FILE NAME " + fileName);

            request.setAttribute("file", "download?fileName=" + fileName);

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


}
