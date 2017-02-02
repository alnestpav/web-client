package ru.siblion.nesterov.client.servlets;

import ru.siblion.nesterov.client.managing.RoleManager;
import ru.siblion.nesterov.client.type.Role;
import ru.siblion.nesterov.logreader.ws.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
        String locationTypeString = request.getParameter("locationType");

        String[] dateFromStrings = request.getParameterValues("dateFrom");
        String[] dateToStrings = request.getParameterValues("dateTo");


        System.out.println(string);
        System.out.println(location);
        System.out.println(locationTypeString);
        System.out.println(Arrays.toString(dateFromStrings));
        System.out.println(Arrays.toString(dateToStrings));
        System.out.println(fileFormatString);


        SoapWebService service = new SoapWebServiceService().getSoapWebServicePort();

        Request clientRequest = new Request();
        clientRequest.setString(string);
        LocationType locationType = LocationType.fromValue(locationTypeString);

        /* Если роль пользователя не поддерживает выбранный тип локации поиска,
        *  то перенаправить его на страницу с сообщением об ошибке */
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
        Map<Role, Set<LocationType>> roles = new RoleManager().getRoles();

        for (Map.Entry<Role, Set<LocationType>> role :roles.entrySet()) {
            if (request.isUserInRole(role.getKey().toString()) && !role.getValue().contains(locationType)) {
                requestDispatcher.forward(request, response);
            }
        }

        clientRequest.setLocationType(locationType);
        clientRequest.setLocation(location);
        try {
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

            if (dateFromStrings != null) {
                XMLGregorianCalendar dateFrom;
                XMLGregorianCalendar dateTo;
                for (int i = 0; i < dateFromStrings.length; i++) {
                    DateInterval dateInterval = new DateInterval();
                    dateFrom = stringToXMLGregorianCalendar(datatypeFactory, dateFromStrings[i]);
                    dateTo = stringToXMLGregorianCalendar(datatypeFactory, dateToStrings[i]);

                    dateInterval.setDateFrom(dateFrom);
                    dateInterval.setDateTo(dateTo);
                    clientRequest.getDateIntervals().add(dateInterval);
                }
            } else {
                DateInterval dateInterval = new DateInterval();
                dateInterval.setDateFrom(null);
                dateInterval.setDateTo(null);
                clientRequest.getDateIntervals().add(dateInterval);
            }
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }


        FileFormat fileFormat = null;
        if (!fileFormatString.equals("no")) { // потом переписать
            fileFormat = FileFormat.fromValue(fileFormatString);
        }
        clientRequest.setFileFormat(fileFormat);

        Response clientResponse = service.getListOfLogMessages(clientRequest);

        List<LogMessage> logMessages = clientResponse.getLogMessages();
        String outputFile = (String) clientResponse.getOutputFile();
        String message = clientResponse.getMessage();
        request.setAttribute("message", message);
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
        requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);

    }

    private XMLGregorianCalendar stringToXMLGregorianCalendar(DatatypeFactory datatypeFactoryInstance, String stringDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = null;
        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        XMLGregorianCalendar xmlGregorianDate;
        xmlGregorianDate = datatypeFactoryInstance.newXMLGregorianCalendar(gregorianCalendar);
        return xmlGregorianDate;
    }


}
