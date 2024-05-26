/*
 * Created by JFormDesigner on Sun Feb 25 00:45:26 CET 2024
 */

package dev.gallardo.contasoc.ui;

import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author jomaa
 */
@SuppressWarnings("all")
public class FilterIngresos extends JDialog {
    private static FilterIngresos instance;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private FilterIngresos() {
        initComponents();
        buttonGroup.add(socioRadio);
        buttonGroup.add(fechaRadio);
        buttonGroup.setSelected(socioRadio.getModel(), true);
    }

    public static FilterIngresos getInstance() {
        if(instance == null) {
            instance = new FilterIngresos();
        }
        return instance;
    }

    private String getSelectedRadio() {
        if (socioRadio.isSelected()) {
            return "socio";
        } else if (fechaRadio.isSelected()) {
            return "fecha";
        }
        return null;
    }

    private void ascDescBtn(ActionEvent e) {
        if (ascDescBtn.isSelected()) {
            ascDescBtn.setText("Descendente");
            GUIManager.filterIngresos(getSelectedRadio());
        } else {
            ascDescBtn.setText("Ascendente");
            GUIManager.filterIngresos(getSelectedRadio());
        }
    }

    private void socioRadio(ActionEvent e) {
        GUIManager.filterIngresos("socio");
    }

    private void fechaRadio(ActionEvent e) {
        GUIManager.filterIngresos("fecha");
    }

    private void thisWindowLostFocus(WindowEvent e) {
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        checBoxesPanel = new JPanel();
        socioRadio = new JRadioButton();
        fechaRadio = new JRadioButton();
        ascDescBtn = new JToggleButton();

        //======== this ========
        setPreferredSize(new Dimension(135, 105));
        setMinimumSize(new Dimension(135, 105));
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

            //---- socioRadio ----
            socioRadio.setText("N\u00ba de socio");
            socioRadio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            socioRadio.setFocusable(false);
            socioRadio.setName("socio");
            socioRadio.addActionListener(e -> socioRadio(e));
            checBoxesPanel.add(socioRadio, "cell 0 0");

            //---- fechaRadio ----
            fechaRadio.setText("Fecha");
            fechaRadio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            fechaRadio.setFocusable(false);
            fechaRadio.setName("fecha");
            fechaRadio.addActionListener(e -> fechaRadio(e));
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
    protected static JRadioButton socioRadio;
    protected static JRadioButton fechaRadio;
    protected static JToggleButton ascDescBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
