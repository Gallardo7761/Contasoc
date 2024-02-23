package dev.galliard.contasoc.database;

import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.database.objects.Balance;
import dev.galliard.contasoc.database.objects.Gastos;
import dev.galliard.contasoc.database.objects.Ingresos;
import dev.galliard.contasoc.database.objects.Socios;
import dev.galliard.contasoc.ui.*;
import dev.galliard.contasoc.util.Parsers;
import org.hibernate.exception.ConstraintViolationException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class DBUtils {
    public static String password;
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
            data.stream()
                    .sorted(Comparator.comparing(Socios::getFechaDeAlta))
                    .forEach(s -> model.addElement(
                            new ListaEsperaPanel(s, data.indexOf(s) + 1)
                    ));
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
            connection = DriverManager.getConnection(Contasoc.DBURL, "contasoc_user", password);
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
            connection = DriverManager.getConnection(Contasoc.DBURL, "contasoc_user", password);
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
            connection = DriverManager.getConnection(Contasoc.DBURL, "contasoc_user", password);
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
            connection = DriverManager.getConnection(Contasoc.DBURL, "contasoc_user", password);
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
            connection = DriverManager.getConnection(Contasoc.DBURL, "contasoc_user", password);
            statement = connection.createStatement();
            String query = """
                    UPDATE Socios
                    SET numeroSocio = %s, numeroHuerto = %s, nombre = '%s', dni = '%s', telefono = %s, email = '%s', fechaDeAlta = '%s', fechaDeEntrega = '%s', fechaDeBaja = '%s', notas = '%s', tipo = '%s'
                    WHERE numeroSocio = %d
                    """.formatted(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7], params[8], params[9], params[10], numeroSocio)
                    .replace("'NULL'", "NULL");
            System.out.println(query);
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
            connection = DriverManager.getConnection(Contasoc.DBURL, "contasoc_user", password);
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
            connection = DriverManager.getConnection(Contasoc.DBURL, "contasoc_user", password);
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
            connection = DriverManager.getConnection(Contasoc.DBURL, "contasoc_user", password);
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
            DriverManager.getConnection(Contasoc.DBURL, "contasoc_user", password);
            DBUtils.password = password;
            Contasoc.balance = DBUtils.isEmpty("Balance") ? null : (Balance) Contasoc.jpaBalanceDao.execute("SELECT e FROM Balance e", new String[]{});
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
