package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.util.EmailSender2;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HTMLRendererThread implements  Runnable {
    @Override
    public void run() {
        while(true) {
            if(UIContasoc.destinatarioComboBox.getSelectedItem() != null) {
                switch(UIContasoc.tipoEmailComboBox.getSelectedItem().toString()) {
                    case "NORMAL":
                        UIContasoc.rendered = EmailSender2.NORMAL_EMAIL
                                .replace("{nombre}",
                                        ContasocDAO.select(
                                                "Socios",
                                                new Object[] {"nombre"},
                                                "email = '"+
                                                        UIContasoc.destinatarioComboBox.getSelectedItem().toString().replaceAll("\\(\\d+\\)", "").trim()+"'"))
                                .replace("{mensaje}",
                                        UIContasoc.bodyTextArea.getText());
                        break;
                    case "AVISO IMPAGO":
                        UIContasoc.rendered = EmailSender2.UNPAID_EMAIL
                                .replace("{nombre}",ContasocDAO.select("Socios", new Object[] {"nombre"}, "email = '"+UIContasoc.destinatarioComboBox.getSelectedItem().toString().replaceAll("\\(\\d+\\)", "").trim()+"'"));
                        break;
                    case "AVISO ABANDONO":
                        UIContasoc.rendered = EmailSender2.WARNING_EMAIL
                                .replace("{nombre}",ContasocDAO.select("Socios", new Object[] {"nombre"}, "email = '"+UIContasoc.destinatarioComboBox.getSelectedItem().toString().replaceAll("\\(\\d+\\)", "").trim()+"'"));
                        break;
                    case "MAL COMPORTAMIENTO":
                        UIContasoc.rendered = EmailSender2.MISBEHAVE_EMAIL
                                .replace("{nombre}",ContasocDAO.select("Socios", new Object[] {"nombre"}, "email = '"+UIContasoc.destinatarioComboBox.getSelectedItem().toString().replaceAll("\\(\\d+\\)", "").trim()+"'"));
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
                e.printStackTrace();
            }
        }
    }
}
