/*
 * Created by JFormDesigner on Mon Dec 25 07:56:18 CET 2023
 */

package dev.galliard.contasoc.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

import dev.galliard.contasoc.common.Action;
import dev.galliard.contasoc.database.ContasocDAO;
import net.miginfocom.swing.*;

/**
 * @author jomaa
 */
public class AddModifyIngresos extends JFrame {
    protected static Action accion;
    public AddModifyIngresos() {
        initComponents();
    }

    private void aceptarBtnActionPerformed(ActionEvent e) {
        switch(accion.name()) {
            case "ADD":
                java.util.List<String> ins = new ArrayList<>();
                ins.add(socioField.getText());
                ins.add(fechaField.getText());
                ins.add(conceptoField.getText());
                ins.add(cantidadField.getText());
                ins.add((String) tipoPagoComboBox.getSelectedItem());
                System.out.println(ins);
                ContasocDAO.insert("Ingresos", new String[] {"numeroSocio", "fecha", "concepto", "cantidad", "tipo"},
                        ins.toArray(String[]::new));
                GUIManager.populateGUITables();
                this.dispose();
                break;
            case "MODIFY":
                java.util.List<String> upd = new ArrayList<>();
                upd.add(socioField.getText());
                upd.add(fechaField.getText());
                upd.add(conceptoField.getText());
                upd.add(cantidadField.getText());
                upd.add((String) tipoPagoComboBox.getSelectedItem());
                ContasocDAO.update("Ingresos", new String[] {"numeroSocio", "fecha", "concepto", "cantidad", "tipo"},
                        upd.toArray(String[]::new),
                        "numeroSocio = '" + socioField.getText() + "'" +
                        " AND concepto = '" + conceptoField.getText() + "'");
                GUIManager.populateGUITables();
                break;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        nombreLabel = new JLabel();
        socioField = new JTextField();
        fechalLabel = new JLabel();
        fechaField = new JTextField();
        conceptoLabel = new JLabel();
        conceptoField = new JTextField();
        cantidadLabel = new JLabel();
        cantidadField = new JTextField();
        tipoLabel = new JLabel();
        tipoPagoComboBox = new JComboBox<>();
        separator2 = new JSeparator();
        aceptarBtn = new JButton();

        //======== this ========
        setTitle("{accion} ingreso");
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 12 24 12 26,gap 10 10",
            // columns
            "[grow,fill]" +
            "[grow,fill]",
            // rows
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]"));

        //---- nombreLabel ----
        nombreLabel.setText("Socio:");
        nombreLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        nombreLabel.setFont(nombreLabel.getFont().deriveFont(nombreLabel.getFont().getSize() + 4f));
        contentPane.add(nombreLabel, "cell 0 0,width 64:64:64,height 32:32:32");

        //---- socioField ----
        socioField.setFont(socioField.getFont().deriveFont(socioField.getFont().getSize() + 4f));
        contentPane.add(socioField, "cell 0 0,height 24:24:24");

        //---- fechalLabel ----
        fechalLabel.setText("Fecha:");
        fechalLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        fechalLabel.setFont(fechalLabel.getFont().deriveFont(fechalLabel.getFont().getSize() + 4f));
        contentPane.add(fechalLabel, "cell 0 1,width 64:64:64,height 32:32:32");

        //---- fechaField ----
        fechaField.setFont(fechaField.getFont().deriveFont(fechaField.getFont().getSize() + 4f));
        contentPane.add(fechaField, "cell 0 1,height 24:24:24");

        //---- conceptoLabel ----
        conceptoLabel.setText("Concepto:");
        conceptoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        conceptoLabel.setFont(conceptoLabel.getFont().deriveFont(conceptoLabel.getFont().getSize() + 4f));
        contentPane.add(conceptoLabel, "cell 0 2,width 72:72:72,height 32:32:32");

        //---- conceptoField ----
        conceptoField.setFont(conceptoField.getFont().deriveFont(conceptoField.getFont().getSize() + 4f));
        contentPane.add(conceptoField, "cell 0 2,height 24:24:24");

        //---- cantidadLabel ----
        cantidadLabel.setText("Cantidad:");
        cantidadLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        cantidadLabel.setFont(cantidadLabel.getFont().deriveFont(cantidadLabel.getFont().getSize() + 4f));
        contentPane.add(cantidadLabel, "cell 1 0,width 72:72:72,height 32:32:32");

        //---- cantidadField ----
        cantidadField.setFont(cantidadField.getFont().deriveFont(cantidadField.getFont().getSize() + 4f));
        contentPane.add(cantidadField, "cell 1 0,height 24:24:24");

        //---- tipoLabel ----
        tipoLabel.setText("Tipo:");
        tipoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        tipoLabel.setFont(tipoLabel.getFont().deriveFont(tipoLabel.getFont().getSize() + 4f));
        contentPane.add(tipoLabel, "cell 1 1,width 64:64:64,height 32:32:32");

        //---- tipoPagoComboBox ----
        tipoPagoComboBox.setFont(tipoPagoComboBox.getFont().deriveFont(tipoPagoComboBox.getFont().getSize() + 4f));
        tipoPagoComboBox.setSelectedItem("BANCO");
        tipoPagoComboBox.addItem("BANCO");
        tipoPagoComboBox.addItem("CAJA");
        contentPane.add(tipoPagoComboBox, "cell 1 1,height 24:24:24");

        //---- separator2 ----
        separator2.setBackground(new Color(0xf7f8fa));
        separator2.setForeground(new Color(0xf7f8fa));
        contentPane.add(separator2, "cell 1 2");

        //---- aceptarBtn ----
        aceptarBtn.setText("ACEPTAR");
        aceptarBtn.setFont(aceptarBtn.getFont().deriveFont(aceptarBtn.getFont().getSize() + 4f));
        aceptarBtn.setFocusable(false);
        aceptarBtn.addActionListener(e -> aceptarBtnActionPerformed(e));
        contentPane.add(aceptarBtn, "cell 1 2,height 24:24:24");
        setSize(500, 250);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JLabel nombreLabel;
    protected static JTextField socioField;
    protected static JLabel fechalLabel;
    protected static JTextField fechaField;
    protected static JLabel conceptoLabel;
    protected static JTextField conceptoField;
    protected static JLabel cantidadLabel;
    protected static JTextField cantidadField;
    protected static JLabel tipoLabel;
    protected static JComboBox<String> tipoPagoComboBox;
    protected static JSeparator separator2;
    protected static JButton aceptarBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
