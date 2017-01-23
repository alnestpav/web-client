package ru.siblion.nesterov.client;

import ru.siblion.nesterov.logreader.ws.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexander on 17.01.2017.
 */
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST");
        String string = request.getParameter("string");
        String location = request.getParameter("location");
        String fileFormatString = request.getParameter("fileFormat");

        String[] dateFromStrings = request.getParameterValues("dateFrom");
        String[] dateToStrings = request.getParameterValues("dateTo");


        System.out.println(string);
        System.out.println(location);
        System.out.println(Arrays.toString(dateFromStrings));
        System.out.println(Arrays.toString(dateToStrings));
        System.out.println(fileFormatString);


        SoapWebService service = new SoapWebServiceService().getSoapWebServicePort();

        Request clientRequest = new Request();
        clientRequest.setString(string);
        clientRequest.setLocation(location);

        for (int i = 0; i < dateFromStrings.length; i++) {
            DateInterval dateInterval = new DateInterval();
            dateInterval.setDateFrom(dateFromStrings[i]); // String преобразуется к XMLGregorianCalendar автоматически
            dateInterval.setDateTo(dateToStrings[i]);
            clientRequest.getDateIntervals().add(dateInterval);
        }

        FileFormat fileFormat = null;
        if (!fileFormatString.equals("no")) { // потом переписать
            fileFormat = FileFormat.fromValue(fileFormatString);
        }
        clientRequest.setFileFormat(fileFormat);

        Response clientResponse = service.getListOfLogMessages(clientRequest);

        List<LogMessage> logMessages = clientResponse.getLogMessages();
        String outputFile = (String) clientResponse.getOutputFile();
        if (outputFile != null) { // разобраться почему одновременно outputFile и logMessages существуют!
            Pattern fileNamePattern = Pattern.compile("\\\\[^\\\\]*$");
            Matcher fileNameMatcher = fileNamePattern.matcher(outputFile);
            fileNameMatcher.find();
            String fileName = fileNameMatcher.group().substring(1); // получить имя файла, убрать первый символ '\'
            request.setAttribute("fileLink", "download?fileName=" + fileName);
        } else if (logMessages != null) {
            request.setAttribute("logMessages", logMessages); //
        } else {
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
