package ru.siblion.nesterov.client.servlets;

import ru.siblion.nesterov.client.managing.RecordsManager;
import ru.siblion.nesterov.client.managing.RoleManager;
import ru.siblion.nesterov.client.type.Role;
import ru.siblion.nesterov.logreader.ws.*;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.siblion.nesterov.client.utils.Utils.stringToXMLGregorianCalendar;

/**
 * Created by alexander on 17.01.2017.
 */
public class SearchServlet extends HttpServlet {
    @EJB
    RecordsManager recordsManager;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        Role userRole = Role.Default;
        for (Role role : Role.values()) {
            if (request.isUserInRole(role.toString())){
                userRole = role;
                break;
            }
        }

        Map<Role, Set<LocationType>> roles = new RoleManager().getRoles();
        Set<LocationType> locationTypeSet = roles.get(userRole);
        System.out.println("locationTypeSet " + locationTypeSet);
        request.setAttribute("locationTypeSet", locationTypeSet);
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

        recordsManager.addRecord(request.getRemoteUser(), "new request", "some message", new Date());

        LocationType locationType = LocationType.fromValue(locationTypeString);


        /* Если роль пользователя не поддерживает выбранный тип локации поиска,
        *  то перенаправить его на страницу с сообщением об ошибке */
        Role userRole = null;
        Map<Role, Set<LocationType>> roles = new RoleManager().getRoles();
        for (Map.Entry<Role, Set<LocationType>> role :roles.entrySet()) {
            if (request.isUserInRole(role.getKey().toString()) && !role.getValue().contains(locationType)) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
                requestDispatcher.forward(request, response);
            }
        }

        Request clientRequest = new Request();
        clientRequest.setString(string);
        clientRequest.setLocationType(locationType);
        clientRequest.setLocation(location);

        // Парсинг дат
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


        SoapWebService service = new SoapWebServiceService().getSoapWebServicePort();
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
            request.setAttribute("logMessages", logMessages);
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
