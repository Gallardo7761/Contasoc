package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.util.ContasocLogger;
import dev.galliard.contasoc.util.EmailSender2;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.sql.SQLException;

public class HTMLRendererThread implements  Runnable {
    @Override
    public void run() {
        while(true) {
            if(UIContasoc.destinatarioComboBox.getSelectedItem() != null) {
                switch(UIContasoc.tipoEmailComboBox.getSelectedItem().toString()) {
                    case "NORMAL":
                        try {
                            UIContasoc.rendered = EmailSender2.NORMAL_EMAIL
                                    .replace("{nombre}",
                                            ContasocDAO.select(
                                                    "Socios",
                                                    new Object[] {"nombre"},
                                                    "email = '"+
                                                            UIContasoc.destinatarioComboBox.getSelectedItem().toString().replaceAll("\\(\\d+\\)", "").trim()+"'"))
                                    .replace("{mensaje}",
                                            UIContasoc.htmlEditor.getHtmlText());
                        } catch (SQLException e) {
                            ContasocLogger.dispatchSQLException(e);
                        }
                        break;
                    case "AVISO IMPAGO":
                        try {
                            UIContasoc.rendered = EmailSender2.UNPAID_EMAIL
                                    .replace("{nombre}",ContasocDAO.select("Socios", new Object[] {"nombre"}, "email = '"+UIContasoc.destinatarioComboBox.getSelectedItem().toString().replaceAll("\\(\\d+\\)", "").trim()+"'"));
                        } catch (SQLException e) {
                            ContasocLogger.dispatchSQLException(e);
                        }
                        break;
                    case "AVISO ABANDONO":
                        try {
                            UIContasoc.rendered = EmailSender2.WARNING_EMAIL
                                    .replace("{nombre}",ContasocDAO.select("Socios", new Object[] {"nombre"}, "email = '"+UIContasoc.destinatarioComboBox.getSelectedItem().toString().replaceAll("\\(\\d+\\)", "").trim()+"'"));
                        } catch (SQLException e) {
                            ContasocLogger.dispatchSQLException(e);
                        }
                        break;
                    case "MAL COMPORTAMIENTO":
                        try {
                            UIContasoc.rendered = EmailSender2.MISBEHAVE_EMAIL
                                    .replace("{nombre}",ContasocDAO.select("Socios", new Object[] {"nombre"}, "email = '"+UIContasoc.destinatarioComboBox.getSelectedItem().toString().replaceAll("\\(\\d+\\)", "").trim()+"'"));
                        } catch (SQLException e) {
                            ContasocLogger.dispatchSQLException(e);
                        }
                        break;
                }
                Platform.runLater(() -> {
                    UIContasoc.webEngine.loadContent(UIContasoc.rendered);
                });
            } else {
                Platform.runLater(() -> {
                    UIContasoc.webEngine.loadContent("");
                });
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                ContasocLogger.error(e.getMessage(), e);
            }
        }
    }
}
