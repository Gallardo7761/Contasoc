package es.exmaster.contasoc;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import es.exmaster.contasoc.database.ContasocDAO;
import es.exmaster.contasoc.ui.ContasocLaf;
import es.exmaster.contasoc.ui.UIContasoc;
import es.exmaster.contasoc.util.ErrorHandler;
import es.exmaster.contasoc.util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

public class Contasoc {
    public static final String ESCRITORIO = "C:/Users/" + System.getProperty("user.name") + "/Desktop";
    public static final String BASEDIR = "C:/Contasoc/";
    public static final String VERSION = "6.0";
    public static void main(String[] args) throws SQLException {
        ContasocLaf.setup();
        if(Files.notExists(Path.of(BASEDIR+"contasoc2.db"))) {
            try {
                FileManager.createFile("contasoc2.db", BASEDIR);
                //ContasocDAO.tablesFromScript("createTables.sql");
                //ContasocDAO.triggersFromScript("createTriggers.sql");
                ContasocDAO.createTablesAndTriggers();
            } catch (Exception e) {
                ErrorHandler.errorAlCrearBDD();
            }
        }
        SwingUtilities.invokeLater(() -> {
            new UIContasoc().setVisible(true);
        });
    }
}
