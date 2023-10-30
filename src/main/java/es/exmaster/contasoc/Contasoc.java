package es.exmaster.contasoc;

import es.exmaster.contasoc.ui.UIContasoc;

import javax.swing.*;

public class Contasoc {
    public static void main(String[] args) {
        UIManager.setLookAndFeel(new );

        SwingUtilities.invokeLater(() -> {
            new UIContasoc().setVisible(true);
        });
    }
}
