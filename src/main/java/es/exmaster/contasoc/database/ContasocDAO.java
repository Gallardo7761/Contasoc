package es.exmaster.contasoc.database;

import java.sql.*;

public class ContasocDAO {
    private static final String DB_URL = "jdbc:sqlite:C:/Contasoc/contasoc2.db";

    public static void createTables() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement()) {
            String query =
                    "CREATE TABLE IF NOT EXISTS Socios (" +
                        "socioId INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
                        "numHuerto INTEGER UNIQUE NOT NULL," +
                        "nombre VARCHAR(100) NOT NULL," +
                        "dni CHAR(9) UNIQUE NOT NULL," +
                        "telefono INTEGER(9) UNIQUE," +
                        "correo VARCHAR(250) UNIQUE," +
                        "fechaAlta DATE NOT NULL," +
                        "fechaEntrega DATE," +
                        "fechaBaja DATE," +
                        "notas VARCHAR(300)," +
                        "tipo VARCHAR(21) NOT NULL," +
                        "estado VARCHAR(8)," +
                        "UNIQUE (socioId, numHuerto)," +
                        "CONSTRAINT invalidDniFormat CHECK (dni LIKE '%_')," +
                        "CONSTRAINT invalidPhoneFormat CHECK  (telefono LIKE '_________' AND (telefono >= 000000000 AND telefono <= 999999999))," +
                        "CONSTRAINT invalidEmailFormat CHECK (correo LIKE '%@%')," +
                        "CONSTRAINT invalidEndDate CHECK (fechaBaja > fechaAlta)," +
                        "CONSTRAINT invalidType CHECK (tipo IN ('HORTELANO','LISTA_ESPERA','HORTELANO_INVERNADERO'))," +
                        "CONSTRAINT invalidState CHECK (estado IN ('ACTIVO','INACTIVO'))" +
                    ");" +

                    "CREATE TABLE IF NOT EXISTS Gastos (" +
                        "gastoId INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL ," +
                        "fecha DATE NOT NULL," +
                        "proveedor VARCHAR(150) NOT NULL," +
                        "concepto VARCHAR(200) NOT NULL," +
                        "cantidad REAL NOT NULL," +
                        "numFactura VARCHAR(300) UNIQUE NOT NULL," +
                        "tipo VARCHAR(5) NOT NULL," +
                        "CONSTRAINT invalidExpenseType CHECK (tipo IN ('BANCO','CAJA'))," +
                        "CONSTRAINT invalidDate CHECK (fecha > DATE('now'))" +
                    ");" +

                    "CREATE TABLE IF NOT EXISTS Informe (" +
                        "inicialBanco REAL NOT NULL," +
                        "inicialCaja REAL NOT NULL" +
                    ");" +

                    "CREATE TABLE IF NOT EXISTS Ingresos (" +
                        "ingresoId INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
                        "socioId INTEGER UNIQUE NOT NULL," +
                        "fecha DATE NOT NULL," +
                        "concepto VARCHAR(200) NOT NULL," +
                        "cantidad REAL NOT NULL," +
                        "tipo VARCHAR(5) NOT NULL," +
                        "FOREIGN KEY (socioId) REFERENCES Socios," +
                        "CONSTRAINT invalidDate CHECK (fecha > DATE('now'))," +
                        "CONSTRAINT invalidIncomeType CHECK (tipo IN ('BANCO','CAJA'))" +
                    ");";
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void select(String table, String[] atributos) {

    }

    public static void insert(String tabla, String[] atributos, String[] valores) {

    }

    public static void update(String tabla, String[] atributos, String[] nuevosValores) {

    }

    public static void delete(String tabla, String[] atributos, String[] valores) {

    }
}
