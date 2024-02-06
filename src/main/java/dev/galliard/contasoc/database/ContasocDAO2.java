package dev.galliard.contasoc.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Deprecated
public class ContasocDAO2 {
    public static final String DB_URL = "jdbc:mariadb://212.227.230.89:3306/contasoc?user=contasoc_user&password=contasoc$user";

    public static Optional<List<String>> execute(String query) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            List<String> resultados = new ArrayList<>();
            while (rs.next()) {
                resultados.add(rs.getString(1));
            }
            return Optional.of(resultados);
        }
    }

    public static List<String> leerTabla(String nombreTabla) throws SQLException {
        List<String> resultados = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + nombreTabla)) {
            while (rs.next()) {
                resultados.add(rs.getString(1));
            }
        }
        return resultados;
    }

    public static String select(String table, Object[] atributos, String condicion) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT ");
        for (int i = 0; i < atributos.length; i++) {
            query.append(atributos[i]);
            if (i < atributos.length - 1) {
                query.append(", ");
            }
        }
        query.append(" FROM ").append(table).append(" WHERE ").append(condicion);
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query.toString())) {
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        return null;
    }

    public static void insert(String tabla, String[] atributos, String[] valores) throws SQLException {
        StringBuilder query = new StringBuilder("INSERT INTO ").append(tabla).append(" (");
        for (int i = 0; i < atributos.length; i++) {
            query.append(atributos[i]);
            if (i < atributos.length - 1) {
                query.append(", ");
            }
        }
        query.append(") VALUES (");
        for (int i = 0; i < valores.length; i++) {
            query.append("?");
            if (i < valores.length - 1) {
                query.append(", ");
            }
        }
        query.append(")");
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
            for (int i = 0; i < valores.length; i++) {
                pstmt.setString(i + 1, valores[i]);
            }
            pstmt.executeUpdate();
        }
    }

    public static void update(String tabla, String[] atributos, String[] nuevosValores, String[] condiciones) throws SQLException {
        StringBuilder query = new StringBuilder("UPDATE ").append(tabla).append(" SET ");
        for (int i = 0; i < atributos.length; i++) {
            query.append(atributos[i]).append(" = ?");
            if (i < atributos.length - 1) {
                query.append(", ");
            }
        }
        query.append(" WHERE ");
        for (int i = 0; i < condiciones.length; i++) {
            query.append(condiciones[i]);
            if (i < condiciones.length - 1) {
                query.append(" AND ");
            }
        }
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
            for (int i = 0; i < nuevosValores.length; i++) {
                pstmt.setString(i + 1, nuevosValores[i]);
            }
            pstmt.executeUpdate();
        }
    }

    public static void delete(String tabla, String[] condiciones) throws SQLException {
        StringBuilder query = new StringBuilder("DELETE FROM ").append(tabla).append(" WHERE ");
        for (int i = 0; i < condiciones.length; i++) {
            query.append(condiciones[i]);
            if (i < condiciones.length - 1) {
                query.append(" AND ");
            }
        }
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query.toString());
        }
    }
}