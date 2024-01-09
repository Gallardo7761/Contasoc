package dev.galliard.contasoc;

import dev.galliard.contasoc.ui.lookandfeel.ContasocLaf;
import dev.galliard.contasoc.ui.UIContasoc;
import dev.galliard.contasoc.util.FileManager;
import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.util.ErrorHandler;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

public class Contasoc {
    public static final String ESCRITORIO = System.getProperty("os.name").toLowerCase().contains("win") ?
            "C:/Users/" + System.getProperty("user.name") + "/Desktop" :
            "/home/"+System.getProperty("user.home") + "/Escritorio";
    public static final String BASEDIR = System.getProperty("os.name").toLowerCase().contains("win") ?
            "C:/Contasoc/" :
            System.getProperty("user.home") + "/Contasoc/";
    public static final String VERSION = "6.2.0-beta";
    public static void main(String[] args) throws SQLException {
        ContasocLaf.setup();
        try {
            if(!Files.exists(Path.of(BASEDIR))) {
                Files.createDirectory(Path.of(BASEDIR));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ContasocDAO.createTablesAndTriggers();
        SwingUtilities.invokeLater(() -> {
            new UIContasoc().setVisible(true);
        });
    }


}
