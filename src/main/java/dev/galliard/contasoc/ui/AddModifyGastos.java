/*
 * Created by JFormDesigner on Mon Dec 25 08:08:03 CET 2023
 */

package dev.galliard.contasoc.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.text.AbstractDocument;

import dev.galliard.contasoc.common.Action;
import dev.galliard.contasoc.database.ContasocDAO;
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
    private AddModifyGastos() {
        initComponents();
        setActions();
        setFilters();
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
                ins.add(cantidadField.getText());
                ins.add(facturaField.getText());
                ins.add((String) tipoPagoComboBox.getSelectedItem());
                System.out.println(ins);
                ContasocDAO.insert("Gastos", new String[] {"fecha", "proveedor", "concepto", "cantidad", "factura", "tipo"},
                        ins.toArray(String[]::new));
                GUIManager.populateGUITables();
                for(JTextField jtf : List.of(fechaField, proveedorField, conceptoField, cantidadField, facturaField)) {
                    jtf.setText("");
                }
                this.dispose();
                break;
            case "MODIFY":
                java.util.List<String> upd = new ArrayList<>();
                upd.add(Parsers.dashDateParserReversed(fechaField.getText()));
                upd.add(proveedorField.getText());
                upd.add(conceptoField.getText());
                upd.add(cantidadField.getText());
                upd.add(facturaField.getText());
                upd.add((String) tipoPagoComboBox.getSelectedItem());
                ContasocDAO.update("Gastos", new String[] {"fecha", "proveedor", "concepto", "cantidad", "factura", "tipo"},
                        upd.toArray(String[]::new),
                        new String[] {
                                "fecha =" + tempFecha,
                                "proveedor =" + tempProveedor
                        });
                GUIManager.populateGUITables();
                for(JTextField jtf : List.of(fechaField, proveedorField, conceptoField, cantidadField, facturaField)) {
                    jtf.setText("");
                }
                this.dispose();
                break;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        fechaLabel = new JLabel();
        fechaField = new JTextField();
        proveedorLabel = new JLabel();
        proveedorField = new JTextField();
        conceptoLabel = new JLabel();
        conceptoField = new JTextField();
        cantidadLabel = new JLabel();
        cantidadField = new JTextField();
        facturaLabel = new JLabel();
        facturaField = new JTextField();
        tipoLabel = new JLabel();
        tipoPagoComboBox = new JComboBox<>();
        separator2 = new JSeparator();
        aceptarBtn = new JButton();

        //======== this ========
        setTitle("{accion} gasto");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/images/logohuerto_small.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 12 24 12 26,gap 10 10",
            // columns
            "[grow,fill]" +
            "[grow,fill]",
            // rows
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]"));

        //---- fechaLabel ----
        fechaLabel.setText("Fecha:");
        fechaLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        fechaLabel.setFont(fechaLabel.getFont().deriveFont(fechaLabel.getFont().getSize() + 4f));
        contentPane.add(fechaLabel, "cell 0 0,width 64:64:64,height 32:32:32");

        //---- fechaField ----
        fechaField.setFont(fechaField.getFont().deriveFont(fechaField.getFont().getSize() + 4f));
        fechaField.setNextFocusableComponent(proveedorField);
        contentPane.add(fechaField, "cell 0 0,height 24:24:24");

        //---- proveedorLabel ----
        proveedorLabel.setText("Proveedor:");
        proveedorLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        proveedorLabel.setFont(proveedorLabel.getFont().deriveFont(proveedorLabel.getFont().getSize() + 4f));
        contentPane.add(proveedorLabel, "cell 0 1,width 80:80:80,height 32:32:32");

        //---- proveedorField ----
        proveedorField.setFont(proveedorField.getFont().deriveFont(proveedorField.getFont().getSize() + 4f));
        proveedorField.setNextFocusableComponent(conceptoField);
        contentPane.add(proveedorField, "cell 0 1,height 24:24:24");

        //---- conceptoLabel ----
        conceptoLabel.setText("Concepto:");
        conceptoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        conceptoLabel.setFont(conceptoLabel.getFont().deriveFont(conceptoLabel.getFont().getSize() + 4f));
        contentPane.add(conceptoLabel, "cell 0 2,width 72:72:72,height 32:32:32");

        //---- conceptoField ----
        conceptoField.setFont(conceptoField.getFont().deriveFont(conceptoField.getFont().getSize() + 4f));
        conceptoField.setNextFocusableComponent(cantidadField);
        contentPane.add(conceptoField, "cell 0 2,height 24:24:24");

        //---- cantidadLabel ----
        cantidadLabel.setText("Cantidad:");
        cantidadLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        cantidadLabel.setFont(cantidadLabel.getFont().deriveFont(cantidadLabel.getFont().getSize() + 4f));
        contentPane.add(cantidadLabel, "cell 1 0,width 72:72:72,height 32:32:32");

        //---- cantidadField ----
        cantidadField.setFont(cantidadField.getFont().deriveFont(cantidadField.getFont().getSize() + 4f));
        cantidadField.setNextFocusableComponent(facturaField);
        contentPane.add(cantidadField, "cell 1 0,height 24:24:24");

        //---- facturaLabel ----
        facturaLabel.setText("Factura:");
        facturaLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        facturaLabel.setFont(facturaLabel.getFont().deriveFont(facturaLabel.getFont().getSize() + 4f));
        contentPane.add(facturaLabel, "cell 1 1,width 72:72:72,height 32:32:32");

        //---- facturaField ----
        facturaField.setFont(facturaField.getFont().deriveFont(facturaField.getFont().getSize() + 4f));
        facturaField.setNextFocusableComponent(fechaField);
        contentPane.add(facturaField, "cell 1 1,height 24:24:24");

        //---- tipoLabel ----
        tipoLabel.setText("Tipo:");
        tipoLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        tipoLabel.setFont(tipoLabel.getFont().deriveFont(tipoLabel.getFont().getSize() + 4f));
        contentPane.add(tipoLabel, "cell 1 2,width 64:64:64,height 32:32:32");

        //---- tipoPagoComboBox ----
        tipoPagoComboBox.setFont(tipoPagoComboBox.getFont().deriveFont(tipoPagoComboBox.getFont().getSize() + 4f));
        tipoPagoComboBox.setSelectedItem("BANCO");
        tipoPagoComboBox.addItem("BANCO");
        tipoPagoComboBox.addItem("CAJA");
        contentPane.add(tipoPagoComboBox, "cell 1 2,height 24:24:24");

        //---- separator2 ----
        separator2.setBackground(new Color(0xf7f8fa));
        separator2.setForeground(new Color(0xf7f8fa));
        contentPane.add(separator2, "cell 1 3");

        //---- aceptarBtn ----
        aceptarBtn.setText("ACEPTAR");
        aceptarBtn.setFont(aceptarBtn.getFont().deriveFont(aceptarBtn.getFont().getSize() + 4f));
        aceptarBtn.setFocusable(false);
        aceptarBtn.addActionListener(e -> aceptarBtnActionPerformed(e));
        contentPane.add(aceptarBtn, "cell 1 3,height 24:24:24");
        setSize(500, 250);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JLabel fechaLabel;
    protected static JTextField fechaField;
    protected static JLabel proveedorLabel;
    protected static JTextField proveedorField;
    protected static JLabel conceptoLabel;
    protected static JTextField conceptoField;
    protected static JLabel cantidadLabel;
    protected static JTextField cantidadField;
    protected static JLabel facturaLabel;
    protected static JTextField facturaField;
    protected static JLabel tipoLabel;
    protected static JComboBox<String> tipoPagoComboBox;
    protected static JSeparator separator2;
    protected static JButton aceptarBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
