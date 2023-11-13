package es.exmaster.contasoc;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import es.exmaster.contasoc.database.ContasocDAO;
import es.exmaster.contasoc.ui.ContasocLaf;
import es.exmaster.contasoc.ui.UIContasoc;

import javax.swing.*;
import java.awt.*;

public class Contasoc {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        ContasocLaf.setup();
        ContasocDAO.createTables();

        SwingUtilities.invokeLater(() -> {
            new UIContasoc().setVisible(true);
        });
    }
}
