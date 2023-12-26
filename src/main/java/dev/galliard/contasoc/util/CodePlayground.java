package dev.galliard.contasoc.util;

import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.ui.HelpMenu;
import dev.galliard.contasoc.ui.UIContasoc;
import dev.galliard.contasoc.ui.lookandfeel.ContasocLaf;

import java.io.File;
import java.util.List;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CodePlayground {
    public static void main(String[] args) {
        ContasocLaf.setup();
        SwingUtilities.invokeLater(() -> {
            new HelpMenu().setVisible(true);
        });

    }
}