/*
 * Created by JFormDesigner on Sat Feb 24 23:43:03 CET 2024
 */

package dev.galliard.contasoc.ui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author jomaa
 */
public class FilterSocios extends JDialog {
    private static FilterSocios instance;
    protected static ButtonGroup buttonGroup = new ButtonGroup();
    private FilterSocios() {
        initComponents();
        buttonGroup.add(socioRadio);
        buttonGroup.add(huertoRadio);
        buttonGroup.add(nombreRadio);
        buttonGroup.add(fechaDeAltaRadio);
        buttonGroup.setSelected(socioRadio.getModel(), true);
    }

    public static FilterSocios getInstance() {
        if(instance == null) {
            instance = new FilterSocios();
        }
        return instance;
    }

    private String getSelectedRadio() {
        if (socioRadio.isSelected()) {
            return "socio";
        } else if (huertoRadio.isSelected()) {
            return "huerto";
        } else if (nombreRadio.isSelected()) {
            return "nombre";
        } else if (fechaDeAltaRadio.isSelected()) {
            return "fechaAlta";
        }
        return null;
    }

    private void ascDescBtn(ActionEvent e) {
        if (ascDescBtn.isSelected()) {
            ascDescBtn.setText("Descendente");
            GUIManager.filterSocios(getSelectedRadio());
        } else {
            ascDescBtn.setText("Ascendente");
            GUIManager.filterSocios(getSelectedRadio());
        }
    }

    private void socioRadio(ActionEvent e) {
        GUIManager.filterSocios("socio");
    }

    private void huertoRadio(ActionEvent e) {
        GUIManager.filterSocios("huerto");
    }

    private void nombreRadio(ActionEvent e) {
        GUIManager.filterSocios("nombre");
    }

    private void fechaDeAltaRadio(ActionEvent e) {
        GUIManager.filterSocios("fechaAlta");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        checBoxesPanel = new JPanel();
        socioRadio = new JRadioButton();
        huertoRadio = new JRadioButton();
        nombreRadio = new JRadioButton();
        fechaDeAltaRadio = new JRadioButton();
        ascDescBtn = new JToggleButton();

        //======== this ========
        setPreferredSize(new Dimension(135, 155));
        setMinimumSize(new Dimension(135, 155));
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

            //---- huertoRadio ----
            huertoRadio.setText("N\u00ba de huerto");
            huertoRadio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            huertoRadio.setFocusable(false);
            huertoRadio.setName("huerto");
            huertoRadio.addActionListener(e -> huertoRadio(e));
            checBoxesPanel.add(huertoRadio, "cell 0 1");

            //---- nombreRadio ----
            nombreRadio.setText("Nombre");
            nombreRadio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            nombreRadio.setFocusable(false);
            nombreRadio.setName("nombre");
            nombreRadio.addActionListener(e -> nombreRadio(e));
            checBoxesPanel.add(nombreRadio, "cell 0 2");

            //---- fechaDeAltaRadio ----
            fechaDeAltaRadio.setText("Fecha de alta");
            fechaDeAltaRadio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            fechaDeAltaRadio.setFocusable(false);
            fechaDeAltaRadio.setName("fechaAlta");
            fechaDeAltaRadio.addActionListener(e -> fechaDeAltaRadio(e));
            checBoxesPanel.add(fechaDeAltaRadio, "cell 0 3");

            //---- ascDescBtn ----
            ascDescBtn.setText("Ascendente");
            ascDescBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            ascDescBtn.setIcon(UIManager.getIcon("Table.ascendingSortIcon"));
            ascDescBtn.setSelectedIcon(UIManager.getIcon("Table.descendingSortIcon"));
            ascDescBtn.setMargin(new Insets(2, 2, 2, 2));
            ascDescBtn.setFocusable(false);
            ascDescBtn.addActionListener(e -> ascDescBtn(e));
            checBoxesPanel.add(ascDescBtn, "cell 0 4");
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
    protected static JRadioButton huertoRadio;
    protected static JRadioButton nombreRadio;
    protected static JRadioButton fechaDeAltaRadio;
    protected static JToggleButton ascDescBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
