package dev.galliard.contasoc;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.formdev.flatlaf.util.SystemInfo;

import dev.galliard.contasoc.database.Dao;
import dev.galliard.contasoc.database.JpaBalanceDao;
import dev.galliard.contasoc.database.JpaGastoDao;
import dev.galliard.contasoc.database.JpaIngresoDao;
import dev.galliard.contasoc.database.JpaSocioDao;
import dev.galliard.contasoc.database.objects.Balance;
import dev.galliard.contasoc.database.objects.Gastos;
import dev.galliard.contasoc.database.objects.Ingresos;
import dev.galliard.contasoc.database.objects.Socios;
import dev.galliard.contasoc.ui.PasswordDialog;
import dev.galliard.contasoc.ui.UIContasoc;
import dev.galliard.contasoc.ui.UpdateChecker;
import dev.galliard.contasoc.util.SQLMemory;
import javafx.application.Platform;

public class Contasoc {
    public static final String ESCRITORIO = System.getProperty("os.name").toLowerCase().contains("win") ?
            "C:/Users/" + System.getProperty("user.name") + "/Desktop" :
            "/home/" + System.getProperty("user.home") + "/Escritorio";
    public static final String DBURL = "jdbc:mariadb://157.90.72.14:3306/contasoc";
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
    public static final String VERSION = "6.4.5";
    public static CountDownLatch latch = new CountDownLatch(1);

    private static final String GREEN = "#549159";
    private static final String DARK_GREEN = "#3B663F";

    private static final String BG = "#F7F8FA";
    private static final String GRAY_BG = "#D1D7E2";
    private static final String LIGHT_GREEN = "#C8E8CA";
    private static final String GRAY_BORDER = "#7E7F87";
    private static final String LIGHT_GRAY_BORDER = "#A0A0A0";

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException {
        Platform.startup(() -> {});
        UIManager.setLookAndFeel(new FlatGitHubIJTheme());
        setProperties();

        SwingUtilities.invokeLater(() -> {
            PasswordDialog passwordDialog = new PasswordDialog();
            passwordDialog.setVisible(true);
            passwordDialog.requestFocus();
        });

        latch.await();

        load();

        SwingUtilities.invokeLater(() -> {
            new UIContasoc().setVisible(true);
        });

        new Thread(new UpdateChecker()).start();
    }

    public static void load() {
        socios = jpaSocioDao.getAll();
        ingresos = jpaIngresoDao.getAll();
        gastos = jpaGastoDao.getAll();
    }

    private static void setProperties() {
        UIManager.put("TitlePane.unifiedBackground", true);
        UIManager.put("TitlePane.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Component.focusWidth", 1);
        UIManager.put("Component.innerFocusWidth", 1);
        UIManager.put("Component.borderColor", Color.decode(GRAY_BORDER));
        UIManager.put("TabbedPane.contentAreaColor", Color.decode(LIGHT_GRAY_BORDER));

        UIManager.put("ScrollBar.width", 10);
        UIManager.put("Button.hoverBackground", Color.decode("#D1D7E2"));

        UIManager.put("PasswordField.showRevealButton", true);

        if (SystemInfo.isLinux) {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        }
    }
}


