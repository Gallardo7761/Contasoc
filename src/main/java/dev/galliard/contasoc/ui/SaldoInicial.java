/*
 * Created by JFormDesigner on Sun Dec 24 05:15:00 CET 2023
 */

package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.ui.GUIManager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author jomaa
 */
public class SaldoInicial extends JFrame {
    public SaldoInicial() {
        initComponents();
    }

    private void saldoInicialGuardarBtnActionPerformed(ActionEvent e) {
        GUIManager.inicialBanco = Double.parseDouble(inicialBancoField.getText());
        GUIManager.inicialCaja = Double.parseDouble(inicialCajaField.getText());
        ContasocDAO.insert("Balance", new String[] {"inicialBanco","inicialCaja"},
                new String[] {inicialBancoField.getText(),inicialCajaField.getText()});
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        inicialBancoPanel = new JPanel();
        inicialBancoLabel = new JLabel();
        inicialBancoField = new JTextField();
        inicialCajaPanel = new JPanel();
        inicialCajaLabel = new JLabel();
        inicialCajaField = new JTextField();
        saldoInicialGuardarPanel = new JPanel();
        saldoInicialGuardarBtn = new JButton();

        //======== this ========
        setTitle("Saldos iniciales");
        setIconImage(new ImageIcon(getClass().getResource("/images/logohuerto_small.png")).getImage());
        setResizable(false);
        setAlwaysOnTop(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));

        //======== inicialBancoPanel ========
        {

            //---- inicialBancoLabel ----
            inicialBancoLabel.setText("Inicial Banco:");

            GroupLayout inicialBancoPanelLayout = new GroupLayout(inicialBancoPanel);
            inicialBancoPanel.setLayout(inicialBancoPanelLayout);
            inicialBancoPanelLayout.setHorizontalGroup(
                inicialBancoPanelLayout.createParallelGroup()
                    .addGroup(inicialBancoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(inicialBancoLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inicialBancoField, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addContainerGap())
            );
            inicialBancoPanelLayout.setVerticalGroup(
                inicialBancoPanelLayout.createParallelGroup()
                    .addGroup(inicialBancoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(inicialBancoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(inicialBancoLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
            );
        }
        contentPane.add(inicialBancoPanel);

        //======== inicialCajaPanel ========
        {

            //---- inicialCajaLabel ----
            inicialCajaLabel.setText("Inicial Caja:");

            GroupLayout inicialCajaPanelLayout = new GroupLayout(inicialCajaPanel);
            inicialCajaPanel.setLayout(inicialCajaPanelLayout);
            inicialCajaPanelLayout.setHorizontalGroup(
                inicialCajaPanelLayout.createParallelGroup()
                    .addGroup(inicialCajaPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(inicialCajaLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inicialCajaField, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addContainerGap())
            );
            inicialCajaPanelLayout.setVerticalGroup(
                inicialCajaPanelLayout.createParallelGroup()
                    .addGroup(inicialCajaPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(inicialCajaLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                        .addComponent(inicialCajaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            );
        }
        contentPane.add(inicialCajaPanel);

        //======== saldoInicialGuardarPanel ========
        {

            //---- saldoInicialGuardarBtn ----
            saldoInicialGuardarBtn.setText("GUARDAR");
            saldoInicialGuardarBtn.addActionListener(e -> saldoInicialGuardarBtnActionPerformed(e));

            GroupLayout saldoInicialGuardarPanelLayout = new GroupLayout(saldoInicialGuardarPanel);
            saldoInicialGuardarPanel.setLayout(saldoInicialGuardarPanelLayout);
            saldoInicialGuardarPanelLayout.setHorizontalGroup(
                saldoInicialGuardarPanelLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, saldoInicialGuardarPanelLayout.createSequentialGroup()
                        .addContainerGap(164, Short.MAX_VALUE)
                        .addComponent(saldoInicialGuardarBtn)
                        .addContainerGap())
            );
            saldoInicialGuardarPanelLayout.setVerticalGroup(
                saldoInicialGuardarPanelLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, saldoInicialGuardarPanelLayout.createSequentialGroup()
                        .addContainerGap(7, Short.MAX_VALUE)
                        .addComponent(saldoInicialGuardarBtn)
                        .addContainerGap())
            );
        }
        contentPane.add(saldoInicialGuardarPanel);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JPanel inicialBancoPanel;
    protected static JLabel inicialBancoLabel;
    protected static JTextField inicialBancoField;
    protected static JPanel inicialCajaPanel;
    protected static JLabel inicialCajaLabel;
    protected static JTextField inicialCajaField;
    protected static JPanel saldoInicialGuardarPanel;
    protected static JButton saldoInicialGuardarBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
