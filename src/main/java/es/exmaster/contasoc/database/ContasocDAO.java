package es.exmaster.contasoc.database;

import java.sql.*;

public class ContasocDAO {
    private static final String DB_URL = "jdbc:sqlite:C:/Contasoc/contasoc2.db";

    public static void createTables() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement()) {
            String query =
                    "CREATE TABLE IF NOT EXISTS Socios (" +
                            "socioId INTEGER UNIQUE NOT NULL AUTO_INCREMENT," +
                            "numHuerto INTEGER UNIQUE NOT NULL," +
                            "nombre VARCHAR(100) NOT NULL," +
                            "dni CHAR(9) UNIQUE NOT NULL," +
                            "telefono UNIQUE INTEGER(9)," +
                            "correo UNIQUE VARCHAR(250)," +
                            "fechaAlta DATE NOT NULL," +
                            "fechaEntrega DATE," +
                            "fechaBaja DATE," +
                            "notas VARCHAR(300)," +
                            "tipo VARCHAR(21) NOT NULL," +
                            "estado VARCHAR(8)," +
                            "PRIMARY KEY (socioId)," +
                            "UNIQUE (socioId, numHuerto)," +
                            "CONSTRAINT invalidDniFormat CHECK (dni LIKE '%_')," +
                            "CONSTRAINT invalidPhoneFormat CHECK  (telefono LIKE '_________' AND (telefono >= 000000000 AND telefono <= 999999999))," +
                            "CONSTRAINT invalidEmailFormat CHECK (email LIKE '%@%')," +
                            "CONSTRAINT invalidEndDate CHECK (fechaBaja > fechaAlta)," +
                            "CONSTRAINT invalidType CHECK (tipo IN ('HORTELANO','LISTA_ESPERA','HORTELANO_INVERNADERO'))," +
                            "CONSTRAINT invalidState CHECK (estado IN ('ACTIVO','INACTIVO')" +
                            ");" +

                            "CREATE TABLE IF NOT EXISTS Gastos (" +
                            "gastoId INTEGER UNQUE NOT NULL AUTO_INCREMENT," +
                            "fecha DATE NOT NULL," +
                            "proveedor VARCHAR(150) NOT NULL," +
                            "concepto VARCHAR(200) NOT NULL," +
                            "cantidad REAL NOT NULL," +
                            "numFactura VARCHAR(300) UNIQUE NOT NULL," +
                            "tipo VARCHAR(5) NOT NULL," +
                            "PRIMARY KEY (gastoId)," +
                            "CONSTRAINT invalidExpenseType CHECK (tipo IN ('BANCO','CAJA'))," +
                            "CONSTRAINT invalidDate CHECK (fecha > SYS.DATE())," +
                            ");" +

                            "CREATE TABLE IF NOT EXISTS Informe (" +
                            "inicialBanco REAL NOT NULL," +
                            "inicialCaja REAL NOT NULL" +
                            ");" +

                            "CREATE TABLE IF NOT EXISTS Ingresos (" +
                            "ingresoId INTEGER UNIQUE NOT NULL AUTO_INCREMENT," +
                            "socioId INTEGER UNIQUE NOT NULL," +
                            "fecha DATE NOT NULL," +
                            "concepto VARCHAR(200) NOT NULL," +
                            "cantidad REAL NOT NULL," +
                            "tipo VARCHAR(5) NOT NULL," +
                            "PRIMARY KEY (ingresoId)," +
                            "FOREIGN KEY (socioId) REFERENCES Socios," +
                            "CONSTRAINT invalidDate CHECK (fecha > SYS.DATE())," +
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
