/*
 * Created by JFormDesigner on Wed Dec 20 20:59:08 CET 2023
 */

package dev.galliard.contasoc.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author jomaa
 */
public class ContextMenu extends JPopupMenu {
    public ContextMenu() {
        initComponents();
    }

    private void copyBtnActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void viewBtnActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void modifyBtnActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void removeBtnActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    public JButton getCopyBtn() {
        return copyBtn;
    }

    public JButton getViewBtn() {
        return viewBtn;
    }

    public JButton getModifyBtn() {
        return modifyBtn;
    }

    public JButton getRemoveBtn() {
        return removeBtn;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        copyBtn = new JButton();
        viewBtn = new JButton();
        modifyBtn = new JButton();
        removeBtn = new JButton();

        //======== this ========
        setBackground(new Color(0x1e1e1e));

        //---- copyBtn ----
        copyBtn.setText("Copiar");
        copyBtn.setFocusable(false);
        copyBtn.setMaximumSize(new Dimension(85, 26));
        copyBtn.setBackground(new Color(0x1e1e1e));
        copyBtn.addActionListener(e -> copyBtnActionPerformed(e));
        add(copyBtn);

        //---- viewBtn ----
        viewBtn.setText("Ver");
        viewBtn.setFocusable(false);
        viewBtn.setMaximumSize(new Dimension(85, 26));
        viewBtn.setBackground(new Color(0x1e1e1e));
        viewBtn.addActionListener(e -> viewBtnActionPerformed(e));
        add(viewBtn);

        //---- modifyBtn ----
        modifyBtn.setText("Modificar");
        modifyBtn.setFocusable(false);
        modifyBtn.setBackground(new Color(0x1e1e1e));
        modifyBtn.addActionListener(e -> modifyBtnActionPerformed(e));
        add(modifyBtn);

        //---- removeBtn ----
        removeBtn.setText("Eliminar");
        removeBtn.setFocusable(false);
        removeBtn.setMaximumSize(new Dimension(85, 26));
        removeBtn.setBackground(new Color(0x1e1e1e));
        removeBtn.addActionListener(e -> removeBtnActionPerformed(e));
        add(removeBtn);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    private JButton copyBtn;
    private JButton viewBtn;
    private JButton modifyBtn;
    private JButton removeBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
