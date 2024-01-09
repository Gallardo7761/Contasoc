package dev.galliard.contasoc.util;

import java.sql.SQLException;

public class ContasocLogger {

    public static void error(String message, Throwable throwable) {
        System.out.println(ConsoleColor.RED + "[ERROR] " + message + "\nException: " + throwable.getMessage() + ConsoleColor.RESET);
    }

    public static void error(String message) {
        System.out.println(ConsoleColor.RED + "[ERROR] " + message + ConsoleColor.RESET);
    }

    public static void warn(String message) {
        System.out.println(ConsoleColor.YELLOW + "[WARN] " + message + ConsoleColor.RESET);
    }

    public static void info(String message) {
        System.out.println(ConsoleColor.BLUE + "[INFO] " + message + ConsoleColor.RESET);
    }

    public static void debug(String message) {
        System.out.println(ConsoleColor.CYAN + "[DEBUG] " + message + ConsoleColor.RESET);
    }

    public static void success(String message) {
        System.out.println(ConsoleColor.GREEN + "[SUCCESS] " + message + ConsoleColor.RESET);
    }

    public static void dispatchSQLException(SQLException e) {
        String ex = e.getMessage();
        if(ex.contains("telefonoNoValido")) {
            ErrorHandler.errorAlLeerTlf();
        } else if (ex.contains("tipoNoValido")) {
            ErrorHandler.error("El tipo no es el adecuado");
        } else if(ex.contains("fechaIncoherente")) {
            ErrorHandler.error("La fecha de baja no puede ser anterior a la de alta");
        } else if(ex.contains("emailNoValido")) {
            ErrorHandler.errorAlLeerCorreo();
        } else if(ex.contains("metodoContactoFaltante")) {
            ErrorHandler.error("El socio debe tener o bien un teléfono o bien un correo electrónico");
        } else if (ex.contains("estadoInvalido")) {
            ErrorHandler.error("El estado no es válido");
        } else if (ex.contains("cantidadInvalida")) {
            ErrorHandler.error("La cantidad no puede ser negativa");
        } else if(ex.contains("Socios.numeroSocio") && ex.contains("UNIQUE constraint failed")) {
            ErrorHandler.error("El número de socio ya existe");
        } else if(ex.contains("Socios.email") && ex.contains("UNIQUE constraint failed")) {
            ErrorHandler.error("El email ya existe");
        } else if(ex.contains("Socios.dni") && ex.contains("UNIQUE constraint failed")) {
            ErrorHandler.error("El DNI ya existe");
        } else if(ex.contains("Socios.telefono") && ex.contains("UNIQUE constraint failed")) {
            ErrorHandler.error("El teléfono ya existe");
        } else if(ex.contains("Gastos.factura") && ex.contains("UNIQUE constraint failed")) {
            ErrorHandler.error("La factura ya existe");
        } else {
            ErrorHandler.error("Error desconocido");
        }
        System.out.println(e.getMessage());
    }
}

enum ConsoleColor {
    // Colores de texto
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),

    // Colores de fondo
    BLACK_BACKGROUND("\u001B[40m"),
    RED_BACKGROUND("\u001B[41m"),
    GREEN_BACKGROUND("\u001B[42m"),
    YELLOW_BACKGROUND("\u001B[43m"),
    BLUE_BACKGROUND("\u001B[44m"),
    PURPLE_BACKGROUND("\u001B[45m"),
    CYAN_BACKGROUND("\u001B[46m"),
    WHITE_BACKGROUND("\u001B[47m");

    private final String code;

    ConsoleColor(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}