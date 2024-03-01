package dev.galliard.contasoc;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import com.formdev.flatlaf.util.SystemInfo;
import dev.galliard.contasoc.database.*;
import dev.galliard.contasoc.database.objects.Balance;
import dev.galliard.contasoc.database.objects.Gastos;
import dev.galliard.contasoc.database.objects.Ingresos;
import dev.galliard.contasoc.database.objects.Socios;
import dev.galliard.contasoc.ui.PasswordDialog;
import dev.galliard.contasoc.ui.UIContasoc;
import dev.galliard.contasoc.ui.UpdateChecker;
import dev.galliard.contasoc.util.SQLMemory;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class Contasoc {
    public static final String ESCRITORIO = System.getProperty("os.name").toLowerCase().contains("win") ?
            "C:/Users/" + System.getProperty("user.name") + "/Desktop" :
            "/home/" + System.getProperty("user.home") + "/Escritorio";
    private static final String CONFIG_DIR = System.getProperty("user.home") + "/.contasoc/";
    private static final String CONFIG_FILE = CONFIG_DIR + "config.properties";
    public static final Properties properties = new Properties();
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
    public static final String VERSION = "6.4.4";
    public static CountDownLatch latch = new CountDownLatch(1);

    private static final String GREEN = "#549159";
    private static final String DARK_GREEN = "#3B663F";

    private static final String BG = "#F7F8FA";
    private static final String GRAY_BG = "#D1D7E2";
    private static final String LIGHT_GREEN = "#C8E8CA";
    private static final String GRAY_BORDER = "#7E7F87";
    private static final String LIGHT_GRAY_BORDER = "#A0A0A0";

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException {
        genFiles();

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

    private static void genFiles() {
    try {
        if(!Files.exists(Paths.get(CONFIG_DIR))) {
            Files.createDirectories(Paths.get(CONFIG_DIR));
        }
        if(!Files.exists(Paths.get(CONFIG_FILE))) {
            URI defaultConfigURI = Contasoc.class.getResource("/defaultconfig/config.properties").toURI();
            String defaultConfigPath = defaultConfigURI.getPath();
            if (defaultConfigPath.startsWith("/")) {
                defaultConfigPath = defaultConfigPath.substring(1);
            }
            Path defaultConfig = Path.of(defaultConfigPath);
            Files.copy(defaultConfig, Paths.get(CONFIG_FILE));
        }
        properties.load(new FileReader(CONFIG_FILE));
    } catch (IOException | URISyntaxException ex) {
        throw new RuntimeException(ex);
    }
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


