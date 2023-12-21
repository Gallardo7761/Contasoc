package es.exmaster.contasoc.database;

import es.exmaster.contasoc.util.ErrorHandler;

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
import java.util.List;

public class ContasocDAO {
    private static final String DB_URL = "jdbc:sqlite:C:/Contasoc/contasoc2.db";

    public static void tablesFromScript(String fichero) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            Path path = Paths.get(ClassLoader.getSystemResource("resources/assets/" + fichero).toURI());
            List<String> lineas = Files.readAllLines(path, StandardCharsets.UTF_8);
            StringBuilder script = new StringBuilder();

            for (String linea : lineas) {
                script.append(linea).append("\n");
            }

            String[] queryArr = script.toString().split(";");

            for (String query : queryArr) {
                if (!query.trim().isEmpty()) {  // Ignorar consultas vacías
                    System.out.println(query.trim() + ";");
                    try {
                        stmt.execute(query.trim());
                    } catch (SQLException e) {
                        ErrorHandler.errorAlCrearBDD();
                    }
                }
            }

        } catch (IOException | SQLException | URISyntaxException e) {
            ErrorHandler.error();
        }
    }

    public static void triggersFromScript(String fichero) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            Path path = Paths.get(ClassLoader.getSystemResource("resources/assets/" + fichero).toURI());
            List<String> lineas = Files.readAllLines(path, StandardCharsets.UTF_8);
            StringBuilder script = new StringBuilder();

            for (String linea : lineas) {
                script.append(linea).append("\n");
            }

            String[] queryArr = script.toString().split("CREATE TRIGGER IF NOT EXISTS");

            for (String query : queryArr) {
                if (!query.trim().isEmpty()) {  // Ignorar consultas vacías
                    System.out.println("CREATE TRIGGER IF NOT EXISTS " + query.trim());
                    try {
                        stmt.execute("CREATE TRIGGER IF NOT EXISTS " + query.trim());
                    } catch (SQLException e) {
                        ErrorHandler.errorAlCrearBDD();
                    }
                }
            }

        } catch (IOException | SQLException | URISyntaxException e) {
            ErrorHandler.error();
        }
    }

    public static void createTriggers() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String query =
                "CREATE TRIGGER IF NOT EXISTS tr_IncrNumeroSocio " +
                "AFTER INSERT ON Socios " +
                "BEGIN " +
                "UPDATE Socios SET numeroSocio = COALESCE((SELECT MAX(numeroSocio) + 1 FROM Socios), 1)"+
                "WHERE idSocio = NEW.idSocio;" +
                "END;";
            stmt.execute(query);
            query =
                "CREATE TRIGGER IF NOT EXISTS tr_SetEstadoActivo " +
                "AFTER INSERT ON Socios "+
                "BEGIN "+
                "UPDATE Socios SET estado = 'ACTIVO' "+
                "WHERE idSocio = NEW.idSocio; "+
                "END; ";
            stmt.execute(query);
            query =
                "CREATE TRIGGER IF NOT EXISTS tr_SetEstadoInactivo "+
                "AFTER UPDATE ON Socios "+
                "BEGIN "+
                "UPDATE Socios SET estado = 'INACTIVO' "+
                "WHERE idSocio = NEW.idSocio AND NEW.fechaDeBaja IS NOT NULL; "+
                "END;";
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
            ResultSet rs = stmt.executeQuery(query.toString());
            StringBuilder result = new StringBuilder();
            while (rs.next()) {
                for (Object atributo : atributos) {
                    result.append(rs.getString(atributo.toString())).append(";");
                }
                result = new StringBuilder(result.substring(0, result.length() - 2));
                result.append("\n");
            }
            return result.toString();
        } catch (SQLException e) {
            ErrorHandler.errorAlLeerBDD(table);
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
                query.append("'").append(valor).append("', ");
            }
            query = new StringBuilder(query.substring(0, query.length() - 2));
            query.append(");");
            stmt.execute(query.toString());
        } catch (SQLException e) {
            ErrorHandler.errorAlEscribirBDD(tabla);
        }
    }

    public static void update(String tabla, String[] atributos, String[] nuevosValores) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            StringBuilder query = new StringBuilder("UPDATE " + tabla + " SET ");
            for (int i = 0; i < atributos.length; i++) {
                query.append(atributos[i]).append(" = '").append(nuevosValores[i]).append("', ");
            }
            query = new StringBuilder(query.substring(0, query.length() - 2));
            query.append(";");
            stmt.execute(query.toString());
        } catch (SQLException e) {
            ErrorHandler.errorAlEscribirBDD(tabla);
        }
    }

    public static void delete(String tabla, String condicion) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String query = "DELETE FROM " + tabla + " WHERE " + condicion + ";";
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
            String query = "SELECT * FROM " + sqlTable;

            // Preparar la consulta
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Ejecutar la consulta
                ResultSet resultSet = statement.executeQuery();

                // Llenar la JTable con los resultados de la consulta
                while (resultSet.next()) {
                    Object[] row = new Object[model.getColumnCount() - 1]; // Ignorar la primera columna (ID)
                    for (int i = 2; i <= model.getColumnCount(); i++) { // Comenzar desde la segunda columna
                        row[i - 2] = resultSet.getObject(i);
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
                String query = "SELECT numSocio, nombre, telefono, correo, fechaAlta FROM Socios WHERE tipo = '" + equal + "'";

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
