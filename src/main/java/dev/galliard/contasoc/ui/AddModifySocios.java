/*
 * Created by JFormDesigner on Mon Dec 25 05:45:33 CET 2023
 */

package dev.galliard.contasoc.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;

import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.common.Action;
import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.util.Pair;
import net.miginfocom.swing.*;

/**
 * @author jomaa
 */
public class AddModifySocios extends JFrame {
    protected static Action accion;
    public AddModifySocios() {
        initComponents();
    }

    private void aceptarBtnActionPerformed(ActionEvent e) {
        switch(accion.name()) {
            case "ADD":
                List<String> ins = new ArrayList<>();
                ins.add(nombreField.getText());
                ins.add(dniField.getText());
                ins.add(telefonoField.getText());
                ins.add(emailField.getText());
                ins.add(socioField.getText());
                ins.add(huertoField.getText());
                ins.add(altaField.getText());
                ins.add(entregaField.getText());
                ins.add(bajaField.getText());
                ins.add(notasField.getText());
                ins.add((String) tipoSocioComboBox.getSelectedItem());
                System.out.println(ins);
                ContasocDAO.insert("Socios", new String[] {"nombre", "dni", "telefono", "email", "numeroSocio",
                                "numeroHuerto", "fechaDeAlta", "fechaDeEntrega", "fechaDeBaja", "notas", "tipo"},
                        ins.toArray(String[]::new));
                GUIManager.populateGUITables();
                this.dispose();
                break;
            case "MODIFY":
                List<String> upd = new ArrayList<>();
                upd.add(nombreField.getText());
                upd.add(dniField.getText());
                upd.add(telefonoField.getText());
                upd.add(emailField.getText());
                upd.add(socioField.getText());
                upd.add(huertoField.getText());
                upd.add(altaField.getText());
                upd.add(entregaField.getText());
                upd.add(bajaField.getText());
                upd.add(notasField.getText());
                upd.add((String) tipoSocioComboBox.getSelectedItem());
                ContasocDAO.update("Socios", new String[] {"nombre", "dni", "telefono", "email", "numeroSocio",
                                "numeroHuerto", "fechaDeAlta", "fechaDeEntrega", "fechaDeBaja", "notas", "tipo"},
                        upd.toArray(String[]::new),
                        "numeroSocio = '" + socioField.getText() + "'");
                GUIManager.populateGUITables();
                break;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        nombreLabel = new JLabel();
        nombreField = new JTextField();
        dniLabel = new JLabel();
        dniField = new JTextField();
        telefonoLabel = new JLabel();
        telefonoField = new JTextField();
        emailLabel = new JLabel();
        emailField = new JTextField();
        socioLabel = new JLabel();
        socioField = new JTextField();
        huertoLabel = new JLabel();
        huertoField = new JTextField();
        altaLabel = new JLabel();
        altaField = new JTextField();
        entregaLabel = new JLabel();
        entregaField = new JTextField();
        bajaLabel = new JLabel();
        bajaField = new JTextField();
        notasLabel = new JLabel();
        notasField = new JTextField();
        estadoLabel = new JLabel();
        tipoSocioComboBox = new JComboBox<>();
        separator2 = new JSeparator();
        aceptarBtn = new JButton();

        //======== this ========
        setTitle("{accion} socio");
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 12 24 12 26",
            // columns
            "[grow,fill]" +
            "[grow,fill]",
            // rows
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]"));

        //---- nombreLabel ----
        nombreLabel.setText("Nombre:");
        nombreLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        nombreLabel.setFont(nombreLabel.getFont().deriveFont(nombreLabel.getFont().getSize() + 4f));
        contentPane.add(nombreLabel, "cell 0 0,width 64:64:64,height 32:32:32");

        //---- nombreField ----
        nombreField.setFont(nombreField.getFont().deriveFont(nombreField.getFont().getSize() + 4f));
        contentPane.add(nombreField, "cell 0 0,width 200:200:200,height 24:24:24");

        //---- dniLabel ----
        dniLabel.setText("DNI/NIE:");
        dniLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        dniLabel.setFont(dniLabel.getFont().deriveFont(dniLabel.getFont().getSize() + 4f));
        contentPane.add(dniLabel, "cell 0 1,width 64:64:64,height 32:32:32");

        //---- dniField ----
        dniField.setFont(dniField.getFont().deriveFont(dniField.getFont().getSize() + 4f));
        contentPane.add(dniField, "cell 0 1,width 200:200:200,height 24:24:24");

        //---- telefonoLabel ----
        telefonoLabel.setText("Tel\u00e9fono:");
        telefonoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        telefonoLabel.setFont(telefonoLabel.getFont().deriveFont(telefonoLabel.getFont().getSize() + 4f));
        contentPane.add(telefonoLabel, "cell 0 2,width 64:64:64,height 32:32:32");

        //---- telefonoField ----
        telefonoField.setFont(telefonoField.getFont().deriveFont(telefonoField.getFont().getSize() + 4f));
        contentPane.add(telefonoField, "cell 0 2,width 200:200:200,height 24:24:24");

        //---- emailLabel ----
        emailLabel.setText("Email:");
        emailLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        emailLabel.setFont(emailLabel.getFont().deriveFont(emailLabel.getFont().getSize() + 4f));
        contentPane.add(emailLabel, "cell 0 3,width 64:64:64,height 32:32:32");

        //---- emailField ----
        emailField.setFont(emailField.getFont().deriveFont(emailField.getFont().getSize() + 4f));
        contentPane.add(emailField, "cell 0 3,width 200:200:200,height 24:24:24");

        //---- socioLabel ----
        socioLabel.setText("Socio:");
        socioLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        socioLabel.setFont(socioLabel.getFont().deriveFont(socioLabel.getFont().getSize() + 4f));
        contentPane.add(socioLabel, "cell 0 4,width 64:64:64,height 32:32:32");

        //---- socioField ----
        socioField.setFont(socioField.getFont().deriveFont(socioField.getFont().getSize() + 4f));
        socioField.setToolTipText("Si no se pone se autoincrementa");
        contentPane.add(socioField, "cell 0 4,width 200:200:200,height 24:24:24");

        //---- huertoLabel ----
        huertoLabel.setText("Huerto:");
        huertoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        huertoLabel.setFont(huertoLabel.getFont().deriveFont(huertoLabel.getFont().getSize() + 4f));
        contentPane.add(huertoLabel, "cell 0 5,width 64:64:64,height 32:32:32");

        //---- huertoField ----
        huertoField.setFont(huertoField.getFont().deriveFont(huertoField.getFont().getSize() + 4f));
        contentPane.add(huertoField, "cell 0 5,width 200:200:200,height 24:24:24");

        //---- altaLabel ----
        altaLabel.setText("Alta:");
        altaLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        altaLabel.setFont(altaLabel.getFont().deriveFont(altaLabel.getFont().getSize() + 4f));
        contentPane.add(altaLabel, "cell 1 0,width 64:64:64,height 32:32:32");

        //---- altaField ----
        altaField.setFont(altaField.getFont().deriveFont(altaField.getFont().getSize() + 4f));
        contentPane.add(altaField, "cell 1 0,width 200:200:200,height 24:24:24");

        //---- entregaLabel ----
        entregaLabel.setText("Entrega:");
        entregaLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        entregaLabel.setFont(entregaLabel.getFont().deriveFont(entregaLabel.getFont().getSize() + 4f));
        contentPane.add(entregaLabel, "cell 1 1,width 64:64:64,height 32:32:32");

        //---- entregaField ----
        entregaField.setFont(entregaField.getFont().deriveFont(entregaField.getFont().getSize() + 4f));
        contentPane.add(entregaField, "cell 1 1,width 200:200:200,height 24:24:24");

        //---- bajaLabel ----
        bajaLabel.setText("Baja:");
        bajaLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        bajaLabel.setFont(bajaLabel.getFont().deriveFont(bajaLabel.getFont().getSize() + 4f));
        contentPane.add(bajaLabel, "cell 1 2,width 64:64:64,height 32:32:32");

        //---- bajaField ----
        bajaField.setFont(bajaField.getFont().deriveFont(bajaField.getFont().getSize() + 4f));
        contentPane.add(bajaField, "cell 1 2,width 200:200:200,height 24:24:24");

        //---- notasLabel ----
        notasLabel.setText("Notas:");
        notasLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        notasLabel.setFont(notasLabel.getFont().deriveFont(notasLabel.getFont().getSize() + 4f));
        contentPane.add(notasLabel, "cell 1 3,width 64:64:64,height 32:32:32");

        //---- notasField ----
        notasField.setFont(notasField.getFont().deriveFont(notasField.getFont().getSize() + 4f));
        contentPane.add(notasField, "cell 1 3,width 200:200:200,height 24:24:24");

        //---- estadoLabel ----
        estadoLabel.setText("Tipo:");
        estadoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        estadoLabel.setFont(estadoLabel.getFont().deriveFont(estadoLabel.getFont().getSize() + 4f));
        contentPane.add(estadoLabel, "cell 1 4,width 64:64:64,height 32:32:32");

        //---- tipoSocioComboBox ----
        tipoSocioComboBox.setFont(tipoSocioComboBox.getFont().deriveFont(tipoSocioComboBox.getFont().getSize() + 4f));
        tipoSocioComboBox.setSelectedItem("HORTELANO");
        tipoSocioComboBox.addItem("HORTELANO");
        tipoSocioComboBox.addItem("HORTELANO_INVERNADERO");
        tipoSocioComboBox.addItem("COLABORADOR");
        tipoSocioComboBox.addItem("LISTA_ESPERA");
        contentPane.add(tipoSocioComboBox, "cell 1 4,width 200:200:200,height 24:24:24");

        //---- separator2 ----
        separator2.setBackground(new Color(0xf7f8fa));
        separator2.setForeground(new Color(0xf7f8fa));
        contentPane.add(separator2, "cell 1 5,width 64:64:64");

        //---- aceptarBtn ----
        aceptarBtn.setText("ACEPTAR");
        aceptarBtn.setFont(aceptarBtn.getFont().deriveFont(aceptarBtn.getFont().getSize() + 4f));
        aceptarBtn.setFocusable(false);
        aceptarBtn.addActionListener(e -> aceptarBtnActionPerformed(e));
        contentPane.add(aceptarBtn, "cell 1 5,height 24:24:24");
        setSize(615, 350);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JLabel nombreLabel;
    protected static JTextField nombreField;
    protected static JLabel dniLabel;
    protected static JTextField dniField;
    protected static JLabel telefonoLabel;
    protected static JTextField telefonoField;
    protected static JLabel emailLabel;
    protected static JTextField emailField;
    protected static JLabel socioLabel;
    protected static JTextField socioField;
    protected static JLabel huertoLabel;
    protected static JTextField huertoField;
    protected static JLabel altaLabel;
    protected static JTextField altaField;
    protected static JLabel entregaLabel;
    protected static JTextField entregaField;
    protected static JLabel bajaLabel;
    protected static JTextField bajaField;
    protected static JLabel notasLabel;
    protected static JTextField notasField;
    protected static JLabel estadoLabel;
    protected static JComboBox<String> tipoSocioComboBox;
    protected static JSeparator separator2;
    protected static JButton aceptarBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
