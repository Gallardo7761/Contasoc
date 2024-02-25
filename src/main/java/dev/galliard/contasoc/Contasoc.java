package dev.galliard.contasoc;

import dev.galliard.contasoc.database.*;
import dev.galliard.contasoc.database.objects.Balance;
import dev.galliard.contasoc.database.objects.Gastos;
import dev.galliard.contasoc.database.objects.Ingresos;
import dev.galliard.contasoc.database.objects.Socios;
import dev.galliard.contasoc.ui.PasswordDialog;
import dev.galliard.contasoc.ui.UIContasoc;
import dev.galliard.contasoc.ui.UpdateChecker;
import dev.galliard.contasoc.ui.theme.ContasocLaf;
import dev.galliard.contasoc.util.SQLMemory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Contasoc {
    public static final String ESCRITORIO = System.getProperty("os.name").toLowerCase().contains("win") ?
            "C:/Users/" + System.getProperty("user.name") + "/Desktop" :
            "/home/" + System.getProperty("user.home") + "/Escritorio";
    public static final String DBURL = "jdbc:mariadb://212.227.230.89:3306/contasoc";
    public static final Dao<Socios> jpaSocioDao = new JpaSocioDao();
    public static final Dao<Ingresos> jpaIngresoDao = new JpaIngresoDao();
    public static final Dao<Gastos> jpaGastoDao = new JpaGastoDao();
    public static final Dao<Balance> jpaBalanceDao = new JpaBalanceDao();
    public static SQLMemory sqlMemory = new SQLMemory();
    public static List<Socios> socios;
    public static List<Ingresos> ingresos;
    public static List<Gastos> gastos;
    public static Balance balance;

    public static final Logger logger = LoggerFactory.getLogger(Contasoc.class);
    public static final String VERSION = "6.4.1-beta";
    public static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws SQLException {
        ContasocLaf.setup();
        SwingUtilities.invokeLater(() -> {
            new PasswordDialog().setVisible(true);
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Error en el hilo principal", e);
        }
        socios = jpaSocioDao.getAll();
        ingresos = jpaIngresoDao.getAll();
        gastos = jpaGastoDao.getAll();
        SwingUtilities.invokeLater(() -> {
            new UIContasoc().setVisible(true);
        });
        new Thread(new UpdateChecker()).start();
    }
}
