package dev.galliard.contasoc.util;

import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.ui.HelpMenu;
import dev.galliard.contasoc.ui.UIContasoc;
import dev.galliard.contasoc.ui.lookandfeel.ContasocLaf;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

class CodePlayground extends JFrame {
    private JFXPanel jfxPanel;
    private WebView webView;
    private WebEngine webEngine;

    public CodePlayground() {
        initJavaFX();
    }

    private void initJavaFX() {
        jfxPanel = new JFXPanel();
        add(jfxPanel, BorderLayout.CENTER);
        setSize(900,600);
        Platform.runLater(() -> {
            webView = new WebView();
            webEngine = webView.getEngine();
            Scene scene = new Scene(webView);
            jfxPanel.setScene(scene);
            webEngine.load("https://contasoc.galliard.dev/socios.html");
        });
    }
    public static void main(String[] args) {
//        ContasocLaf.setup();
//        SwingUtilities.invokeLater(() -> {
//            HelpMenu.getInstance().setVisible(true);
//        });
        new CodePlayground().setVisible(true);

    }
}

