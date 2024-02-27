/*
 * Created by JFormDesigner on Sun Feb 25 00:53:59 CET 2024
 */

package dev.galliard.contasoc.ui;

import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author jomaa
 */
public class FilterGastos extends JDialog {
    private static FilterGastos instance;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    private FilterGastos() {
        initComponents();
        buttonGroup.add(proveedorRadio);
        buttonGroup.add(fechaRadio);
        buttonGroup.setSelected(fechaRadio.getModel(), true);
    }

    public static FilterGastos getInstance() {
        if(instance == null) {
            instance = new FilterGastos();
        }
        return instance;
    }

    private String getSelectedRadio() {
        if (proveedorRadio.isSelected()) {
            return "proveedor";
        } else if (fechaRadio.isSelected()) {
            return "fecha";
        }
        return null;
    }

    private void ascDescBtn(ActionEvent e) {
        if (ascDescBtn.isSelected()) {
            ascDescBtn.setText("Descendente");
            GUIManager.filterGastos(getSelectedRadio());
        } else {
            ascDescBtn.setText("Ascendente");
            GUIManager.filterGastos(getSelectedRadio());
        }
    }

    private void thisWindowLostFocus(WindowEvent e) {
        this.setVisible(false);
    }

    private void proveedorCheckBox(ActionEvent e) {
        GUIManager.filterGastos("proveedor");
    }

    private void fechaCheckBox(ActionEvent e) {
        GUIManager.filterGastos("fecha");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        checBoxesPanel = new JPanel();
        proveedorRadio = new JRadioButton();
        fechaRadio = new JRadioButton();
        ascDescBtn = new JToggleButton();

        //======== this ========
        setMinimumSize(new Dimension(135, 105));
        setPreferredSize(new Dimension(135, 105));
        setUndecorated(true);
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                thisWindowLostFocus(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 0,hidemode 3",
            // columns
            "[grow,fill]",
            // rows
            "[grow,fill]"));

        //======== checBoxesPanel ========
        {
            checBoxesPanel.setBorder(new MatteBorder(1, 1, 1, 1, SystemColor.controlShadow));
            checBoxesPanel.setLayout(new MigLayout(
                "insets panel,hidemode 3,gapy 0",
                // columns
                "[grow,fill]",
                // rows
                "[grow,fill]" +
                "[grow,fill]" +
                "[grow,fill]"));

            //---- proveedorRadio ----
            proveedorRadio.setText("Proveedor");
            proveedorRadio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            proveedorRadio.setFocusable(false);
            proveedorRadio.addActionListener(e -> proveedorCheckBox(e));
            checBoxesPanel.add(proveedorRadio, "cell 0 0");

            //---- fechaRadio ----
            fechaRadio.setText("Fecha");
            fechaRadio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            fechaRadio.setFocusable(false);
            fechaRadio.addActionListener(e -> fechaCheckBox(e));
            checBoxesPanel.add(fechaRadio, "cell 0 1");

            //---- ascDescBtn ----
            ascDescBtn.setText("Ascendente");
            ascDescBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            ascDescBtn.setIcon(UIManager.getIcon("Table.ascendingSortIcon"));
            ascDescBtn.setSelectedIcon(UIManager.getIcon("Table.descendingSortIcon"));
            ascDescBtn.setMargin(new Insets(2, 2, 2, 2));
            ascDescBtn.setFocusable(false);
            ascDescBtn.addActionListener(e -> ascDescBtn(e));
            checBoxesPanel.add(ascDescBtn, "cell 0 2");
        }
        contentPane.add(checBoxesPanel, "cell 0 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JPanel checBoxesPanel;
    protected static JRadioButton proveedorRadio;
    protected static JRadioButton fechaRadio;
    protected static JToggleButton ascDescBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
