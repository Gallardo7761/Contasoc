package dev.galliard.contasoc.database;

import dev.galliard.contasoc.util.ErrorHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContasocDAO {
    private static final String DB_URL = "jdbc:sqlite:C:/Contasoc/contasoc2.db";

    public static void createTablesAndTriggers() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String query = """            
            CREATE TABLE IF NOT EXISTS Socios(
            	idSocio INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL ,
            	numeroSocio INTEGER UNIQUE NOT NULL,
            	numeroHuerto INTEGER NOT NULL,
            	nombre VARCHAR(128) NOT NULL,
            	dni CHAR(9) UNIQUE NOT NULL,
            	telefono INTEGER UNIQUE,
            	email VARCHAR(64) UNIQUE,
            	fechaDeAlta DATE NOT NULL,
            	fechaDeEntrega DATE,
            	fechaDeBaja DATE,
            	notas VARCHAR(256),
            	tipo VARCHAR(32) NOT NULL,
            	estado VARCHAR(8),
            	CONSTRAINT telefonoNoValido CHECK ((telefono >= 000000000) AND (telefono <= 999999999)),
            	CONSTRAINT tipoNoValido CHECK (tipo IN ('HORTELANO','LISTA_ESPERA','HORTELANO_INVERNADERO','COLABORADOR')),
            	CONSTRAINT fechaIncoherente CHECK (fechaDeBaja >= fechaDeAlta),
            	CONSTRAINT emailNoValido CHECK (email LIKE '%@%.%'),
            	CONSTRAINT metodoContactoFaltante CHECK ((email IS NOT NULL) OR (telefono IS NOT NULL)),
            	CONSTRAINT estadoInvalido CHECK (estado IN ('ACTIVO', 'INACTIVO'))
 
                              );""";
            stmt.execute(query);

            query = """
            CREATE TABLE IF NOT EXISTS Ingresos (
            	idIngreso INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT,
            	numeroSocio INTEGER NOT NULL,
            	fecha DATE NOT NULL,
            	concepto VARCHAR(96) NOT NULL,
            	cantidad REAL NOT NULL,
            	tipo VARCHAR(5) NOT NULL,
            	CONSTRAINT cantidadInvalida CHECK (cantidad >= 0.0),
            	CONSTRAINT tipoInvalido CHECK (tipo IN ('BANCO', 'CAJA')),
            	FOREIGN KEY (numeroSocio) REFERENCES Socios (numeroSocio)
 
                              );""";

            stmt.execute(query);

            query = """
            CREATE TABLE IF NOT EXISTS Gastos (
            	idGasto INTEGER UNIQUE NOT NULL PRIMARY KEY AUTOINCREMENT,
            	fecha DATE NOT NULL,
            	proveedor VARCHAR(96) NOT NULL,
            	concepto VARCHAR(96) NOT NULL,
            	cantidad REAL NOT NULL,
            	factura VARCHAR(32) UNIQUE NOT NULL,
            	tipo VARCHAR(5) NOT NULL,
            	CONSTRAINT cantidadInvalida CHECK (cantidad >= 0.0),
            	CONSTRAINT tipoInvalido CHECK (tipo IN ('BANCO', 'CAJA'))	
 
                              );""";

            stmt.execute(query);

            query = """
            CREATE TABLE IF NOT EXISTS Balance (
            	inicialBanco REAL NOT NULL,
            	inicialCaja REAL NOT NULL
            );""";

            stmt.execute(query);

            query = """
            CREATE TRIGGER IF NOT EXISTS tr_IncrNumeroSocio
                AFTER INSERT ON Socios
            BEGIN
                UPDATE Socios
                SET numeroSocio = COALESCE((SELECT MAX(numeroSocio) FROM Socios), 0) + 1
                WHERE idSocio = NEW.idSocio AND NEW.numeroSocio IS NULL;
   
                            END;""";

            stmt.execute(query);

            query = """
            CREATE TRIGGER IF NOT EXISTS tr_SetEstadoActivo
                AFTER INSERT ON Socios
            BEGIN
                UPDATE Socios SET estado = 'ACTIVO'
                WHERE idSocio = NEW.idSocio;
   
                            END;""";

            stmt.execute(query);

            query = """
            CREATE TRIGGER IF NOT EXISTS tr_SetEstadoInactivo
                AFTER UPDATE ON Socios
            BEGIN
                UPDATE Socios SET estado = 'INACTIVO'
                WHERE idSocio = NEW.idSocio AND NEW.fechaDeBaja IS NOT NULL;
            END;""";

            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> leerTabla(String nombreTabla) {
        String query = "SELECT * FROM " + nombreTabla;
        List<String> aux = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                StringBuilder dataBuilder = new StringBuilder();

                for (int i = 2; i <= columnCount; i++) {
                    String value = rs.getString(i);
                    dataBuilder.append(value);

                    if (i < columnCount) {
                        dataBuilder.append(";");
                    }
                }

                aux.add(dataBuilder.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aux;
    }

    public static String select(String table, Object[] atributos, String condicion) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            StringBuilder query = new StringBuilder("SELECT ");
            for (Object atributo : atributos) {
                query.append(atributo).append(", ");
            }
            query = new StringBuilder(query.substring(0, query.length() - 2));
            query.append(" FROM ").append(table).append(";");
            if(!condicion.isEmpty()) {
                query = new StringBuilder(query.substring(0, query.length() - 1) + " WHERE " + condicion + ";");
            }
            System.out.println(query.toString());
            ResultSet rs = stmt.executeQuery(query.toString());
            StringBuilder result = new StringBuilder();
            while (rs.next()) {
                for (Object atributo : atributos) {
                    result.append(rs.getString(atributo.toString())).append(";");
                }
                result = new StringBuilder(result.substring(0, result.length() - 1));
                result.append("\n");
            }
            return result.toString();
        } catch (SQLException e) {
            ErrorHandler.error(e.toString());
        }
        return null;
    }

    public static void insert(String tabla, String[] atributos, String[] valores) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            StringBuilder query = new StringBuilder("INSERT INTO " + tabla + " (");
            for (String atributo : atributos) {
                query.append(atributo).append(", ");
            }
            query = new StringBuilder(query.substring(0, query.length() - 2));
            query.append(") VALUES (");
            for (String valor : valores) {
                if(valor.isEmpty()) {
                    query.append("NULL, ");
                } else {
                    if (valor.matches(".*[a-zA-Z].*") || valor.contains("-")) {
                        query.append("'").append(valor).append("', ");
                    } else {
                        query.append(valor).append(", ");
                    }
                }

            }
            query = new StringBuilder(query.substring(0, query.length() - 2));
            query.append(");");
            System.out.println(query.toString());
            stmt.execute(query.toString());
        } catch (SQLException e) {
            ErrorHandler.errorAlEscribirBDD(tabla);
        }
    }

    public static void update(String tabla, String[] atributos, String[] nuevosValores, String condicion) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            StringBuilder query = new StringBuilder("UPDATE " + tabla + " SET ");
            for (int i = 0; i < atributos.length; i++) {
                query.append(atributos[i]).append(" = '").append(nuevosValores[i]).append("', ");
            }
            query = new StringBuilder(query.substring(0, query.length() - 2));

            // Añadir la condición
            query.append(" WHERE ").append(condicion).append(";");

            stmt.execute(query.toString());
        } catch (SQLException e) {
            ErrorHandler.errorAlEscribirBDD(tabla);
        }
    }

    public static void delete(String tabla, String[] condiciones) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Procesar cada condición
            for (int i = 0; i < condiciones.length; i++) {
                String unaCondicion = condiciones[i].trim();

                // Eliminar las comillas simples si la condición contiene texto o caracteres especiales
                if (unaCondicion.split("=")[1].trim().matches(".*[a-zA-Z].*") ||
                        unaCondicion.split("=")[1].trim().contains("-")) {
                    condiciones[i] = unaCondicion.split("=")[0].trim() + " = " +
                            "'" + unaCondicion.split("=")[1].trim() + "'";
                }
            }

            // Unir las condiciones nuevamente con AND
            String condicion = String.join(" AND ", condiciones);

            String query = "DELETE FROM " + tabla + " WHERE " + condicion + ";";
            System.out.println(query);
            stmt.execute(query);
        } catch (SQLException e) {
            ErrorHandler.errorAlEscribirBDD(tabla);
        }
    }


    public static void fillTableFrom(JTable jTable, String sqlTable) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de llenarla

        try {
            // Conectar a la base de datos SQLite
            Connection connection = DriverManager.getConnection(DB_URL);

            // Consulta para seleccionar todos los registros de tu tabla
            String query = "SELECT * FROM " + sqlTable +";";

            // Preparar la consulta
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Ejecutar la consulta
                ResultSet resultSet = statement.executeQuery();

                // Llenar la JTable con los resultados de la consulta
                while (resultSet.next()) {
                    Object[] row = new Object[resultSet.getMetaData().getColumnCount() - 1]; // Ignorar la primera columna (ID)
                    for (int i = 1; i <= row.length; i++) { // Comenzar desde la primera columna
                        row[i - 1] = resultSet.getObject(i + 1); // Sumar 1 para saltar la primera columna (ID)
                    }
                    model.addRow(row);
                }
            }

            // Cerrar la conexión
            connection.close();
        } catch (SQLException e) {
            ErrorHandler.error(e.toString());
        }
    }

    public static void fillListaEspera(JTable tabla) {
        // Conexión a la base de datos SQLite
        ((DefaultTableModel) tabla.getModel()).setRowCount(0);
        final DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalTextPosition(SwingConstants.LEFT);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        try {
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 Statement statement = connection.createStatement()) {

                // Nombre de la tabla y consulta SQL para seleccionar todos los datos
                String equal = "LISTA_ESPERA";
                String query = "SELECT numeroSocio, nombre, telefono, email, fechaDeAlta FROM Socios WHERE tipo = '" + equal + "'";

                // Obtener metadatos de las columnas
                try ( // Ejecutar la consulta
                      ResultSet resultSet = statement.executeQuery(query)) {
                    // Obtener metadatos de las columnas
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    // Agregar filas a la JTable utilizando los datos del resultado de la consulta
                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];

                        // Obtener los valores de cada columna
                        for (int i = 1; i <= columnCount; i++) {
                            tabla.getColumnModel().getColumn(i-1).setCellRenderer(defaultTableCellRenderer);
                            row[i - 1] = resultSet.getObject(i);
                        }

                        // Agregar la fila al modelo de tabla
                        model.addRow(row);
                    }
                    // Cerrar la conexión y liberar recursos
                }
            }
        } catch (SQLException e) {
            ErrorHandler.errorAlLeerBDD("Socios");
        }
    }
}
