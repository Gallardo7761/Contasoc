package dev.gallardo.contasoc.database;

import org.hibernate.exception.ConstraintViolationException;

import dev.gallardo.contasoc.Contasoc;
import dev.gallardo.contasoc.database.objects.Balance;
import dev.gallardo.contasoc.database.objects.Gastos;
import dev.gallardo.contasoc.database.objects.Ingresos;
import dev.gallardo.contasoc.database.objects.Socios;
import dev.gallardo.contasoc.ui.*;

import javax.swing.*;
import java.sql.*;
import java.util.Comparator;
import java.util.List;

public class DBUtils {
    public static String password;
    private static String DBURL = Contasoc.cfgManager.getProperty("DBURL");
    
    public static void fillSocios() {
        try {
            List<Socios> data = Contasoc.socios;
            DefaultListModel<SocioPanel> model = new DefaultListModel<>();
            UIContasoc.sociosLista.setModel(model);
            data.stream()
                    .sorted(Comparator.comparing(Socios::getNumeroSocio))
                    .forEach(s -> model.addElement(new SocioPanel(s)));
        } catch (ConstraintViolationException e) {
            Contasoc.logger.error("Error", e);
        }
    }

    public static void fillIngresos() {
        try {
            List<Ingresos> data = Contasoc.ingresos;
            DefaultListModel<IngresoPanel> model = new DefaultListModel<>();
            UIContasoc.ingresosLista.setModel(model);
            data.stream()
                    .sorted(Comparator.comparing(Ingresos::getFecha).reversed())
                    .forEach(i -> model.addElement(new IngresoPanel(i)));
        } catch (ConstraintViolationException e) {
            Contasoc.logger.error("Error", e);
        }
    }

    public static void fillGastos() {
        try {
            List<Gastos> data = Contasoc.gastos;
            DefaultListModel<GastoPanel> model = new DefaultListModel<>();
            UIContasoc.gastosLista.setModel(model);
            data.stream()
                    .sorted(Comparator.comparing(Gastos::getFecha).reversed())
                    .forEach(g -> model.addElement(new GastoPanel(g)));
        } catch (ConstraintViolationException e) {
            Contasoc.logger.error("Error", e);
        }
    }

    public static void fillListaEspera() {
        try {
            List<Socios> data = Contasoc.socios.stream().filter(s -> s.getTipo().name().equals("LISTA_ESPERA")).toList();
            DefaultListModel<ListaEsperaPanel> model = new DefaultListModel<>();
            UIContasoc.listaEsperaLista.setModel(model);
            
            int index = 1; // Variable de índice
            
            // Utilizamos un bucle for para mantener un control directo sobre el índice
            for (Socios s : data.stream().sorted(Comparator.comparing(Socios::getFechaDeAlta)).toList()) {
                model.addElement(new ListaEsperaPanel(s, index));
                index++; // Incrementamos el índice en cada iteración
            }
        } catch (ConstraintViolationException e) {
            Contasoc.logger.error("Error", e);
        }
    }


    public static boolean isEmpty(String table) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int totalFilas = 0;
        try {
            connection = DriverManager.getConnection(DBURL, "contasoc_user", password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) AS total FROM " + table);
            if (resultSet.next()) {
                totalFilas = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return totalFilas == 0;
    }

    public static void removeSocioOnClose(int id) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(DBURL, "contasoc_user", password);
            statement = connection.createStatement();
            statement.executeQuery("DELETE FROM Socios WHERE idSocio = " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void removeIngresoOnClose(int id) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(DBURL, "contasoc_user", password);
            statement = connection.createStatement();
            statement.executeQuery("DELETE FROM Ingresos WHERE idIngreso = " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void removeGastoOnClose(int id) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(DBURL, "contasoc_user", password);
            statement = connection.createStatement();
            statement.executeQuery("DELETE FROM Gastos WHERE idGasto = " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateSocioOnClose(int numeroSocio, String[] params) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(DBURL, "contasoc_user", password);
            statement = connection.createStatement();
            String query = """
                    UPDATE Socios
                    SET numeroSocio = %s, numeroHuerto = %s, nombre = '%s', dni = '%s', telefono = %s, email = '%s', fechaDeAlta = '%s', fechaDeEntrega = '%s', fechaDeBaja = '%s', notas = '%s', tipo = '%s'
                    WHERE numeroSocio = %d
                    """.formatted(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7], params[8], params[9], params[10], numeroSocio)
                    .replace("'NULL'", "NULL");
            statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateIngresoOnClose(int numeroSocio, String concepto, String[] params) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(DBURL, "contasoc_user", password);
            statement = connection.createStatement();
            statement.executeQuery(
                    """
                    UPDATE Ingresos
                    SET numeroSocio = %s, fecha = '%s', concepto = '%s', cantidad = %s, tipo = '%s'
                    WHERE numeroSocio = %d AND concepto = '%s'
                    """.formatted(params[0], params[1], params[2], params[3], params[4], numeroSocio, concepto)
            );

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateGastoOnClose(String fecha, String proveedor, String factura, String[] params) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(DBURL, "contasoc_user", password);
            statement = connection.createStatement();
            String query = """
                    UPDATE Gastos
                    SET fecha = '%s', proveedor = '%s', concepto = '%s', cantidad = '%s', factura = '%s', tipo = '%s'
                    WHERE fecha = '%s' AND proveedor = '%s' AND factura = '%s'
                    """.formatted(params[0], params[1], params[2], params[3], params[4], params[5], fecha, proveedor, factura);
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void cerrarAnyo() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(DBURL, "contasoc_user", password);
            statement = connection.createStatement();
            statement.executeQuery("DELETE FROM Balance");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean connect(String password) {
        try {
            DriverManager.getConnection(DBURL, "contasoc_user", password);
            DBUtils.password = password;
            Contasoc.balance = DBUtils.isEmpty("Balance") ? null : (Balance) Contasoc.jpaBalanceDao.execute("SELECT e FROM Balance e", new String[]{});
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
