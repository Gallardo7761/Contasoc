/*
 * Created by JFormDesigner on Mon Dec 25 08:08:03 CET 2023
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
public class AddModifyGastos extends JFrame {
    protected static Action accion;
    protected static String tempFecha;
    protected static String tempProveedor;

    private static AddModifyGastos instance;

    private static boolean sqlExceptionOcurred = false;
    private AddModifyGastos() {
        initComponents();
        setActions();
        setFilters();
        addFormatterFactories();
    }

    private void addFormatterFactories() {
        try {
            GUIManager.addFormatterFactory(fechaField, FormatterType.DATE);
            GUIManager.addFormatterFactory(cantidadField, FormatterType.DECIMAL);
        } catch (ParseException e) {
            ContasocLogger.error("Error",e);
        }
    }

    protected void clear() {
        for(JTextField jtf : Arrays.asList(fechaField, proveedorField, conceptoField, cantidadField, facturaField)) {
            jtf.setText("");
        }
    }

    public static AddModifyGastos getInstance() {
        if (instance == null) {
            instance = new AddModifyGastos();
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
        java.util.List<JTextField> lista = List.of(proveedorField,conceptoField);
        for (JTextField jtp : lista) {
            AbstractDocument doc = (AbstractDocument) jtp.getDocument();
            doc.setDocumentFilter(new UpperCaseFilter());
        }
    }

    private void aceptarBtnActionPerformed(ActionEvent e) {
        switch(accion.name()) {
            case "ADD":
                java.util.List<String> ins = new ArrayList<>();
                ins.add(Parsers.dashDateParserReversed(fechaField.getText()));
                ins.add(proveedorField.getText());
                ins.add(conceptoField.getText());
                ins.add(cantidadField.getText().contains(",") ? cantidadField.getText().replace(",",".") : cantidadField.getText());
                ins.add(facturaField.getText());
                ins.add((String) tipoPagoComboBox.getSelectedItem());

                try {
                    sqlExceptionOcurred = false;
                    ContasocDAO.insert("Gastos", new String[] {"fecha", "proveedor", "concepto", "cantidad", "factura", "tipo"},
                            ins.toArray(String[]::new));
                } catch (SQLException ex) {
                    sqlExceptionOcurred = true;
                    ContasocLogger.dispatchSQLException(ex);
                }
                if(!sqlExceptionOcurred) {
                    GUIManager.populateGUITables();
                    for(JTextField jtf : List.of(fechaField, proveedorField, conceptoField, cantidadField, facturaField)) {
                        jtf.setText("");
                    }
                    this.dispose();
                }
                break;
            case "MODIFY":
                java.util.List<String> upd = new ArrayList<>();
                upd.add(Parsers.dashDateParserReversed(fechaField.getText()));
                upd.add(proveedorField.getText());
                upd.add(conceptoField.getText());
                upd.add(cantidadField.getText().contains(",") ? cantidadField.getText().replace(",",".") : cantidadField.getText());
                upd.add(facturaField.getText());
                upd.add((String) tipoPagoComboBox.getSelectedItem());
                try {
                    sqlExceptionOcurred = false;
                    ContasocDAO.update("Gastos", new String[] {"fecha", "proveedor", "concepto", "cantidad", "factura", "tipo"},
                            upd.toArray(String[]::new),
                            new String[] {
                                    "fecha =" + Parsers.dashDateParserReversed(tempFecha),
                                    "proveedor =" + tempProveedor
                            });
                } catch (SQLException ex) {
                    sqlExceptionOcurred = true;
                    ContasocLogger.dispatchSQLException(ex);
                }
                if(!sqlExceptionOcurred) {
                    GUIManager.populateGUITables();
                    for(JTextField jtf : List.of(fechaField, proveedorField, conceptoField, cantidadField, facturaField)) {
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
        fechaLabel = new JLabel();
        fechaField = new JFormattedTextField();
        proveedorLabel = new JLabel();
        proveedorField = new JTextField();
        conceptoLabel = new JLabel();
        conceptoField = new JTextField();
        cantidadLabel = new JLabel();
        cantidadField = new JFormattedTextField();
        facturaLabel = new JLabel();
        facturaField = new JTextField();
        tipoLabel = new JLabel();
        tipoPagoComboBox = new JComboBox<>();
        aceptarBtn = new JButton();

        //======== this ========
        setTitle("{accion} gasto");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/images/contasoc_small.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 12,gap 10 10",
            // columns
            "[grow,fill]",
            // rows
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]"));

        //---- fechaLabel ----
        fechaLabel.setText("Fecha:");
        fechaLabel.setHorizontalAlignment(SwingConstants.LEFT);
        fechaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(fechaLabel, "cell 0 0,width 90:90:90,height 32:32:32");

        //---- fechaField ----
        fechaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fechaField.setNextFocusableComponent(proveedorField);
        contentPane.add(fechaField, "cell 0 0,height 32:32:32");

        //---- proveedorLabel ----
        proveedorLabel.setText("Proveedor:");
        proveedorLabel.setHorizontalAlignment(SwingConstants.LEFT);
        proveedorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(proveedorLabel, "cell 0 1,width 90:90:90,height 32:32:32");

        //---- proveedorField ----
        proveedorField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        proveedorField.setNextFocusableComponent(conceptoField);
        contentPane.add(proveedorField, "cell 0 1,height 32:32:32");

        //---- conceptoLabel ----
        conceptoLabel.setText("Concepto:");
        conceptoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        conceptoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(conceptoLabel, "cell 0 2,width 90:90:90,height 32:32:32");

        //---- conceptoField ----
        conceptoField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        conceptoField.setNextFocusableComponent(cantidadField);
        contentPane.add(conceptoField, "cell 0 2,height 32:32:32");

        //---- cantidadLabel ----
        cantidadLabel.setText("Cantidad:");
        cantidadLabel.setHorizontalAlignment(SwingConstants.LEFT);
        cantidadLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(cantidadLabel, "cell 0 3,width 90:90:90,height 32:32:32");

        //---- cantidadField ----
        cantidadField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        cantidadField.setNextFocusableComponent(facturaField);
        contentPane.add(cantidadField, "cell 0 3,height 32:32:32");

        //---- facturaLabel ----
        facturaLabel.setText("Factura:");
        facturaLabel.setHorizontalAlignment(SwingConstants.LEFT);
        facturaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(facturaLabel, "cell 0 4,width 90:90:90,height 32:32:32");

        //---- facturaField ----
        facturaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        facturaField.setNextFocusableComponent(fechaField);
        contentPane.add(facturaField, "cell 0 4,height 32:32:32");

        //---- tipoLabel ----
        tipoLabel.setText("Tipo:");
        tipoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        tipoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(tipoLabel, "cell 0 5,width 90:90:90,height 32:32:32");

        //---- tipoPagoComboBox ----
        tipoPagoComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tipoPagoComboBox.setSelectedItem("BANCO");
        tipoPagoComboBox.addItem("BANCO");
        tipoPagoComboBox.addItem("CAJA");
        contentPane.add(tipoPagoComboBox, "cell 0 5,height 32:32:32");

        //---- aceptarBtn ----
        aceptarBtn.setText("ACEPTAR");
        aceptarBtn.setFont(aceptarBtn.getFont().deriveFont(aceptarBtn.getFont().getSize() + 4f));
        aceptarBtn.setFocusable(false);
        aceptarBtn.addActionListener(e -> aceptarBtnActionPerformed(e));
        contentPane.add(aceptarBtn, "cell 0 6,alignx right,growx 0,width 128:128:128,height 32:32:32");
        setSize(400, 350);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JLabel fechaLabel;
    protected static JFormattedTextField fechaField;
    protected static JLabel proveedorLabel;
    protected static JTextField proveedorField;
    protected static JLabel conceptoLabel;
    protected static JTextField conceptoField;
    protected static JLabel cantidadLabel;
    protected static JFormattedTextField cantidadField;
    protected static JLabel facturaLabel;
    protected static JTextField facturaField;
    protected static JLabel tipoLabel;
    protected static JComboBox<String> tipoPagoComboBox;
    protected static JButton aceptarBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
