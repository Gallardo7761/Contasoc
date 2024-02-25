/*
 * Created by JFormDesigner on Sun Feb 25 00:53:59 CET 2024
 */

package dev.galliard.contasoc.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.*;

/**
 * @author jomaa
 */
public class FilterGastos extends JDialog {
    private static FilterGastos instance;
    private ButtonGroup buttonGroup = new ButtonGroup();

    private FilterGastos() {
        initComponents();
        buttonGroup.add(proveedorCheckBox);
        buttonGroup.add(fechaCheckBox);
    }

    public static FilterGastos getInstance() {
        if(instance == null) {
            instance = new FilterGastos();
        }
        return instance;
    }

    private String getSelectedRadio() {
        if (proveedorCheckBox.isSelected()) {
            return "proveedor";
        } else if (fechaCheckBox.isSelected()) {
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
        proveedorCheckBox = new JRadioButton();
        fechaCheckBox = new JRadioButton();
        ascDescBtn = new JToggleButton();

        //======== this ========
        setMinimumSize(new Dimension(135, 105));
        setPreferredSize(new Dimension(135, 105));
        setUndecorated(true);
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

            //---- proveedorCheckBox ----
            proveedorCheckBox.setText("Proveedor");
            proveedorCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            proveedorCheckBox.setFocusable(false);
            proveedorCheckBox.addActionListener(e -> proveedorCheckBox(e));
            checBoxesPanel.add(proveedorCheckBox, "cell 0 0");

            //---- fechaCheckBox ----
            fechaCheckBox.setText("Fecha");
            fechaCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            fechaCheckBox.setFocusable(false);
            fechaCheckBox.addActionListener(e -> fechaCheckBox(e));
            checBoxesPanel.add(fechaCheckBox, "cell 0 1");

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
    protected static JRadioButton proveedorCheckBox;
    protected static JRadioButton fechaCheckBox;
    protected static JToggleButton ascDescBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
