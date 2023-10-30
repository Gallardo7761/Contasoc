package es.exmaster.contasoc;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;
import es.exmaster.contasoc.ui.UIContasoc;

import javax.swing.*;

public class Contasoc {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatGitHubIJTheme());

        SwingUtilities.invokeLater(() -> {
            new UIContasoc().setVisible(true);
        });
    }
}
