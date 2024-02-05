/*
 * Created by JFormDesigner on Mon Dec 25 07:56:18 CET 2023
 */

package dev.galliard.contasoc.ui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.text.AbstractDocument;

import dev.galliard.contasoc.common.Action;
import dev.galliard.contasoc.common.FormatterType;
import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.util.ContasocLogger;
import dev.galliard.contasoc.util.Parsers;
import dev.galliard.contasoc.util.UpperCaseFilter;
import net.miginfocom.swing.*;

/**
 * @author jomaa
 */
public class AddModifyIngresos extends JFrame {
    protected static Action accion;
    protected static String tempFecha;
    protected static String tempSocio;
    protected static String tempConcepto;
    private static AddModifyIngresos instance;

    private static boolean sqlExceptionOcurred = false;

    private AddModifyIngresos() {
        initComponents();
        setActions();
        setFilters();
        addFormatterFactories();
    }

    private void addFormatterFactories() {
        try {
            GUIManager.addFormatterFactory(fechaField, FormatterType.DATE);
            GUIManager.addFormatterFactory(socioField, FormatterType.ID);
            GUIManager.addFormatterFactory(cantidadField, FormatterType.DECIMAL);
        } catch (ParseException e) {
            ContasocLogger.error("Error",e);
        }
    }

    public static AddModifyIngresos getInstance() {
        if (instance == null) {
            instance = new AddModifyIngresos();
        }
        return instance;
    }

    private void setActions() {
        javax.swing.Action enterAction = new AbstractAction("Enter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                aceptarBtnActionPerformed(e);
            }
        };
        JPanel contentPane = (JPanel) this.getContentPane();
        KeyStroke nuevoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(nuevoKeyStroke, "Enter");
        contentPane.getActionMap().put("Enter", enterAction);
    }

    private void setFilters() {
        java.util.List<JTextField> lista = List.of(conceptoField);
        for (JTextField jtp : lista) {
            AbstractDocument doc = (AbstractDocument) jtp.getDocument();
            doc.setDocumentFilter(new UpperCaseFilter());
        }
    }

    private void aceptarBtnActionPerformed(ActionEvent e) {
        switch(accion.name()) {
            case "ADD":
                java.util.List<String> ins = new ArrayList<>();
                ins.add(socioField.getText());
                ins.add(Parsers.dashDateParserReversed(fechaField.getText()));
                ins.add(conceptoField.getText());
                ins.add(cantidadField.getText());
                ins.add((String) tipoPagoComboBox.getSelectedItem());

                try {
                    sqlExceptionOcurred = false;
                    ContasocDAO.insert("Ingresos", new String[] {"numeroSocio", "fecha", "concepto", "cantidad", "tipo"},
                            ins.toArray(String[]::new));
                } catch (SQLException ex) {
                    sqlExceptionOcurred = true;
                    ContasocLogger.dispatchSQLException(ex);
                }
                if(!sqlExceptionOcurred) {
                    GUIManager.populateGUITables();
                    for(JTextField jtf : Arrays.asList(socioField, fechaField, conceptoField, cantidadField)) {
                        jtf.setText("");
                    }
                    this.dispose();
                }
                break;
            case "MODIFY":
                java.util.List<String> upd = new ArrayList<>();
                upd.add(socioField.getText());
                upd.add(Parsers.dashDateParserReversed(fechaField.getText()));
                upd.add(conceptoField.getText());
                upd.add(cantidadField.getText());
                upd.add((String) tipoPagoComboBox.getSelectedItem());
                try {
                    sqlExceptionOcurred = false;
                    ContasocDAO.update("Ingresos", new String[] {"numeroSocio", "fecha", "concepto", "cantidad", "tipo"},
                            upd.toArray(String[]::new),
                            new String[] {
                                    "numeroSocio =" + tempSocio,
                                    "fecha =" + Parsers.dashDateParserReversed(tempFecha),
                                    "concepto =" + tempConcepto
                            });
                } catch (SQLException ex) {
                    sqlExceptionOcurred = true;
                    ContasocLogger.dispatchSQLException(ex);
                }
                if(!sqlExceptionOcurred) {
                    GUIManager.populateGUITables();
                    for(JTextField jtf : Arrays.asList(socioField, fechaField, conceptoField, cantidadField)) {
                        jtf.setText("");
                    }
                    this.dispose();
                }
                break;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        nombreLabel = new JLabel();
        socioField = new JFormattedTextField();
        fechalLabel = new JLabel();
        fechaField = new JFormattedTextField();
        conceptoLabel = new JLabel();
        conceptoField = new JTextField();
        cantidadLabel = new JLabel();
        cantidadField = new JFormattedTextField();
        tipoLabel = new JLabel();
        tipoPagoComboBox = new JComboBox<>();
        aceptarBtn = new JButton();

        //======== this ========
        setTitle("{accion} ingreso");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/images/contasoc_small.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 12 12 12 12,gap 10 10",
            // columns
            "[grow,fill]",
            // rows
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]"));

        //---- nombreLabel ----
        nombreLabel.setText("Socio:");
        nombreLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nombreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(nombreLabel, "cell 0 0,width 100:100:100,height 32:32:32");

        //---- socioField ----
        socioField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        socioField.setNextFocusableComponent(fechaField);
        contentPane.add(socioField, "cell 0 0,height 32:32:32");

        //---- fechalLabel ----
        fechalLabel.setText("Fecha:");
        fechalLabel.setHorizontalAlignment(SwingConstants.LEFT);
        fechalLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(fechalLabel, "cell 0 1,width 100:100:100,height 32:32:32");

        //---- fechaField ----
        fechaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fechaField.setNextFocusableComponent(conceptoField);
        contentPane.add(fechaField, "cell 0 1,height 32:32:32");

        //---- conceptoLabel ----
        conceptoLabel.setText("Concepto:");
        conceptoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        conceptoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(conceptoLabel, "cell 0 2,width 100:100:100,height 32:32:32");

        //---- conceptoField ----
        conceptoField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        conceptoField.setNextFocusableComponent(cantidadField);
        contentPane.add(conceptoField, "cell 0 2,height 32:32:32");

        //---- cantidadLabel ----
        cantidadLabel.setText("Cantidad:");
        cantidadLabel.setHorizontalAlignment(SwingConstants.LEFT);
        cantidadLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(cantidadLabel, "cell 0 3,width 100:100:100,height 32:32:32");

        //---- cantidadField ----
        cantidadField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        cantidadField.setNextFocusableComponent(socioField);
        contentPane.add(cantidadField, "cell 0 3");

        //---- tipoLabel ----
        tipoLabel.setText("Tipo:");
        tipoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        tipoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(tipoLabel, "cell 0 4,width 100:100:100,height 32:32:32");

        //---- tipoPagoComboBox ----
        tipoPagoComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tipoPagoComboBox.setSelectedItem("BANCO");
        tipoPagoComboBox.addItem("BANCO");
        tipoPagoComboBox.addItem("CAJA");
        contentPane.add(tipoPagoComboBox, "cell 0 4");

        //---- aceptarBtn ----
        aceptarBtn.setText("ACEPTAR");
        aceptarBtn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        aceptarBtn.setFocusable(false);
        aceptarBtn.addActionListener(e -> aceptarBtnActionPerformed(e));
        contentPane.add(aceptarBtn, "cell 0 5,alignx right,growx 0,width 128:128:128,height 32:32:32");
        setSize(400, 300);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JLabel nombreLabel;
    protected static JFormattedTextField socioField;
    protected static JLabel fechalLabel;
    protected static JFormattedTextField fechaField;
    protected static JLabel conceptoLabel;
    protected static JTextField conceptoField;
    protected static JLabel cantidadLabel;
    protected static JFormattedTextField cantidadField;
    protected static JLabel tipoLabel;
    protected static JComboBox<String> tipoPagoComboBox;
    protected static JButton aceptarBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
