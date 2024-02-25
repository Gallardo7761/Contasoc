package dev.galliard.contasoc.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Parsers {
    public static String dateParser(Date date) {
        if (date == null) {
            return null;
        }
        LocalDate localDate = date.toLocalDate();
        return localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear();
    }

    public static String dashDateParser(String date) {
        String res = null;
        SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoNuevo = new SimpleDateFormat("d MMM yyyy", new Locale("es", "ES"));
        try {
            if (!date.isEmpty()) {
                res = formatoNuevo.format(formatoOriginal.parse(date));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public static String dashDateParserReversed(String date) {
        String res = null;
        SimpleDateFormat formatoOriginal = new SimpleDateFormat("d MMM yyyy", new Locale("es", "ES"));
        SimpleDateFormat formatoNuevo = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (!date.isEmpty()) {
                if (date.isEmpty()) {
                    res = null;
                } else {
                    res = formatoNuevo.format(formatoOriginal.parse(date));
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
