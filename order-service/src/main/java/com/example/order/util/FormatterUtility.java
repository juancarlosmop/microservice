package com.example.order.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatterUtility {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static String getDatetime() {
        return LocalDateTime.now().format(formatter);
    }

    public static Date formatDate(String dateString) {
        Date date = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(dateString);
            System.err.println("prueba:"+date);
        } catch (ParseException e) {
            // Manejo adecuado de la excepción, por ejemplo, logueando el error
            System.err.println("Error parsing date: " + e.getMessage());
        }
        return date;
    }
}
