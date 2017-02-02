package ru.siblion.nesterov.client.utils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by alexander on 02.02.2017.
 */
public class Utils {

    public static XMLGregorianCalendar stringToXMLGregorianCalendar(DatatypeFactory datatypeFactoryInstance, String stringDate) {
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
