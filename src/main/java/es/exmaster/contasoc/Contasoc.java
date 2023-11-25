package es.exmaster.contasoc;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import es.exmaster.contasoc.database.ContasocDAO;
import es.exmaster.contasoc.ui.ContasocLaf;
import es.exmaster.contasoc.ui.UIContasoc;
import es.exmaster.contasoc.util.FileManager;

import javax.swing.*;
import java.awt.*;

public class Contasoc {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        ContasocLaf.setup();
        FileManager.createFile("contasoc2.db","C:/Contasoc/");
        ContasocDAO.createTables();
        System.out.println(ContasocDAO.select("Socios",new Object[] {"nombre","dni"}, "(socioId = 2)"));
        SwingUtilities.invokeLater(() -> {
            new UIContasoc().setVisible(true);
        });
    }
}
