package dev.galliard.contasoc;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
import dev.galliard.contasoc.util.ConfigManager;
import dev.galliard.contasoc.util.SQLMemory;
import javafx.application.Platform;

@SuppressWarnings("unused")
public class Contasoc {
	// Path del escritorio según S.O.
    public static final String ESCRITORIO = System.getProperty("os.name").toLowerCase().contains("win") ?
            "C:/Users/" + System.getProperty("user.name") + "/Desktop" :
            "/home/" + System.getProperty("user.home") + "/Escritorio";
    
    // Objetos referentes al mapeo objeto-relacional
    public static Dao<Socios> jpaSocioDao;
    public static Dao<Ingresos> jpaIngresoDao;
    public static Dao<Gastos> jpaGastoDao;
    public static Dao<Balance> jpaBalanceDao;
    public static SQLMemory sqlMemory = new SQLMemory();
    public static List<Socios> socios;
    public static List<Ingresos> ingresos;
    public static List<Gastos> gastos;
    public static Balance balance;
    
    // Logger y Properties
    public static final Logger logger = LoggerFactory.getLogger(Contasoc.class);
    private static File cfg;
    public static final ConfigManager cfgManager = new ConfigManager();
    
    // Latch usado para el panel de contraseña BDD
    public static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException, URISyntaxException {
    	initDao();
    	makeFiles();
    	cfgManager.loadConfig();
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

    private static void makeFiles() {
    	String mainDir = System.getProperty("user.home") + File.separator + ".contasoc";
    	String backupDir = mainDir + File.separator + "backups";
    	new File(mainDir).mkdir();
    	new File(backupDir).mkdir();
    	InputStream is = Contasoc.class.getClassLoader().getResourceAsStream("default.properties");
    	try {
			Files.copy(is, Path.of(mainDir+File.separator+"contasoc.properties"), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			logger.error("Error al crear archivos de configuración");
		}
	}

	public static void initDao() {
    	jpaSocioDao = new JpaSocioDao();
    	jpaIngresoDao = new JpaIngresoDao();
    	jpaGastoDao = new JpaGastoDao();
    	jpaBalanceDao = new JpaBalanceDao();
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
        UIManager.put("Component.borderColor", Color.decode(cfgManager.getProperty("GRAY_BORDER")));
        UIManager.put("TabbedPane.contentAreaColor", Color.decode(cfgManager.getProperty("GRAY_BORDER")));

        UIManager.put("ScrollBar.width", 10);
        UIManager.put("Button.hoverBackground", Color.decode("#D1D7E2"));

        UIManager.put("PasswordField.showRevealButton", true);

        if (SystemInfo.isLinux) {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        }
    }
}


