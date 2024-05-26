package dev.gallardo.contasoc.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dev.gallardo.contasoc.Contasoc;
import dev.gallardo.contasoc.common.Trio;
import dev.gallardo.contasoc.database.objects.Gastos;
import dev.gallardo.contasoc.database.objects.Ingresos;
import dev.gallardo.contasoc.database.objects.Socios;

@SuppressWarnings("deprecation")
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
    
    // Método para convertir de "contasoc_backup_YYYYMMDD.sql" a "dd/MM/yyyy"
    public static String parseToDateString(String input) {
        String datePart = input.replace("contasoc_backup_", "").replace(".sql", "");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        java.util.Date date = null;
		try {
			date = formatter.parse(datePart);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Contasoc.logger.error(e.getMessage());
		}
        SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return outputFormatter.format(date);
    }
    
    // Método para convertir de "dd/MM/yyyy" a "contasoc_backup_YYYYMMDD.sql"
    public static String parseToBackupFileName(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(input.replace("Restaurar al ", ""), formatter);
        return "contasoc_backup_" + date.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".sql";
    }
    
    public static Trio<Socios,Ingresos,Gastos> parseSQLDump(String dump) {
    	Pattern socios = Pattern.compile("INSERT INTO `Socios` VALUES \\((.*?)\\);", Pattern.DOTALL);
        Pattern ingresos = Pattern.compile("INSERT INTO `Ingresos` VALUES \\((.*?)\\);", Pattern.DOTALL);
        Pattern gastos = Pattern.compile("INSERT INTO `Gastos` VALUES \\((.*?)\\);", Pattern.DOTALL);
        Matcher sociosMatcher = socios.matcher(dump);
        Matcher ingresosMatcher = ingresos.matcher(dump);
        Matcher gastosMatcher = gastos.matcher(dump);
        
        String lastSocio = "";
        String lastIngreso = "";
        String lastGasto = "";

        while (sociosMatcher.find()) {
            lastSocio = sociosMatcher.group(1);
        }

        while (ingresosMatcher.find()) {
            lastIngreso = ingresosMatcher.group(1);
        }

        while (gastosMatcher.find()) {
            lastGasto = gastosMatcher.group(1);
        }

        String[] valuesSocio = lastSocio.split("\\),\\(");
        String lastValuesSocio = valuesSocio[valuesSocio.length - 1];
        
        String[] valuesIngreso = lastIngreso.split("\\),\\(");
        String lastValuesIngreso = valuesIngreso[valuesIngreso.length - 1];
        
        String[] valuesGasto = lastGasto.split("\\),\\(");
        String lastValuesGasto = valuesGasto[valuesGasto.length - 1];

        lastValuesSocio = lastValuesSocio.replaceAll("[()]", "");
        lastValuesIngreso = lastValuesIngreso.replaceAll("[()]", "");
        lastValuesGasto = lastValuesGasto.replaceAll("[()]", "");
        lastValuesSocio = lastValuesSocio.replaceAll(",", ";");
        lastValuesIngreso = lastValuesIngreso.replaceAll(",", ";");
        lastValuesGasto = lastValuesGasto.replaceAll(",", ";");
        
        return Trio.of(Socios.parseSQLDump(lastValuesSocio), 
        		Ingresos.parseSQLDump(lastValuesIngreso), 
        		Gastos.parseSQLDump(lastValuesGasto));
    }
}
