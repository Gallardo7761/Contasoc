/*
 * Created by JFormDesigner on Mon Oct 30 02:46:37 CET 2023
 */

package es.exmaster.contasoc.ui;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author jomaa
 */
public class UIContasoc extends JFrame {
    public UIContasoc() {
        initComponents();
    }

    public JButton getNuevoBtn() {
        return nuevoBtn;
    }

    public JButton getEditarBtn() {
        return editarBtn;
    }

    public JButton getEliminarBtn() {
        return eliminarBtn;
    }

    public JButton getImportarBtn() {
        return importarBtn;
    }

    public JButton getExportarBtn() {
        return exportarBtn;
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        toolBar = new JMenuBar();
        nuevoBtn = new JButton();
        editarBtn = new JButton();
        eliminarBtn = new JButton();
        importarBtn = new JButton();
        exportarBtn = new JButton();
        tabbedPane1 = new JTabbedPane();
        sociosPanel = new JPanel();
        sociosTablaPanel = new JScrollPane();
        sociosTabla = new JTable();
        ingresosPanel = new JPanel();
        gastosPanel = new JPanel();
        balancePanel = new JPanel();
        listaEsperaPanel = new JPanel();
        imprimirPanel = new JPanel();
        emailPanel = new JPanel();
        buscarPanel = new JPanel();
        buscarLabel = new JLabel();
        buscarField = new JTextField();

        //======== this ========
        setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        setIconImage(new ImageIcon(getClass().getResource("/images/newlogo_small.png")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1100, 600));
        setMinimumSize(new Dimension(1100, 600));
        setTitle("{ver}");
        var contentPane = getContentPane();

        //======== toolBar ========
        {
            toolBar.setVisible(false);

            //---- nuevoBtn ----
            nuevoBtn.setText("Nuevo");
            nuevoBtn.setFont(nuevoBtn.getFont().deriveFont(nuevoBtn.getFont().getSize() + 4f));
            nuevoBtn.setIcon(new ImageIcon(getClass().getResource("/images/newpass_small_black.png")));
            nuevoBtn.setIconTextGap(6);
            toolBar.add(nuevoBtn);

            //---- editarBtn ----
            editarBtn.setText("Editar");
            editarBtn.setFont(editarBtn.getFont().deriveFont(editarBtn.getFont().getSize() + 4f));
            editarBtn.setIcon(new ImageIcon(getClass().getResource("/images/edit_small_black.png")));
            editarBtn.setIconTextGap(6);
            toolBar.add(editarBtn);

            //---- eliminarBtn ----
            eliminarBtn.setText("Eliminar");
            eliminarBtn.setFont(eliminarBtn.getFont().deriveFont(eliminarBtn.getFont().getSize() + 4f));
            eliminarBtn.setIconTextGap(6);
            eliminarBtn.setIcon(new ImageIcon(getClass().getResource("/images/bin_small_black.png")));
            toolBar.add(eliminarBtn);

            //---- importarBtn ----
            importarBtn.setText("Importar");
            importarBtn.setFont(importarBtn.getFont().deriveFont(importarBtn.getFont().getSize() + 4f));
            importarBtn.setIcon(new ImageIcon(getClass().getResource("/images/import_small_black.png")));
            importarBtn.setIconTextGap(6);
            toolBar.add(importarBtn);

            //---- exportarBtn ----
            exportarBtn.setText("Exportar");
            exportarBtn.setFont(exportarBtn.getFont().deriveFont(exportarBtn.getFont().getSize() + 4f));
            exportarBtn.setIcon(new ImageIcon(getClass().getResource("/images/export_small_black.png")));
            exportarBtn.setIconTextGap(6);
            toolBar.add(exportarBtn);
        }
        setJMenuBar(toolBar);

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(tabbedPane1.getFont().deriveFont(tabbedPane1.getFont().getSize() + 6f));
            tabbedPane1.setTabPlacement(SwingConstants.LEFT);
            tabbedPane1.putClientProperty("JTabbedPane.tabHeight", 50);

            //======== sociosPanel ========
            {
                sociosPanel.setPreferredSize(new Dimension(960, 600));

                //======== sociosTablaPanel ========
                {
                    sociosTablaPanel.setBorder(null);
                    sociosTablaPanel.putClientProperty("JComponent.outline",Color.decode("#549159"));

                    //---- sociosTabla ----
                    sociosTabla.setFillsViewportHeight(true);
                    sociosTabla.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    sociosTabla.setBorder(null);
                    sociosTabla.setModel(new SociosTablaModel());
                    sociosTabla.getTableHeader().setReorderingAllowed(false);
                    sociosTabla.getTableHeader().setResizingAllowed(false);
                    sociosTablaPanel.setViewportView(sociosTabla);
                }

                GroupLayout sociosPanelLayout = new GroupLayout(sociosPanel);
                sociosPanel.setLayout(sociosPanelLayout);
                sociosPanelLayout.setHorizontalGroup(
                    sociosPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, sociosPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(sociosTablaPanel, GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                            .addContainerGap())
                );
                sociosPanelLayout.setVerticalGroup(
                    sociosPanelLayout.createParallelGroup()
                        .addGroup(sociosPanelLayout.createSequentialGroup()
                            .addComponent(sociosTablaPanel, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }
            tabbedPane1.addTab("Socios", sociosPanel);

            //======== ingresosPanel ========
            {

                GroupLayout ingresosPanelLayout = new GroupLayout(ingresosPanel);
                ingresosPanel.setLayout(ingresosPanelLayout);
                ingresosPanelLayout.setHorizontalGroup(
                    ingresosPanelLayout.createParallelGroup()
                        .addGap(0, 882, Short.MAX_VALUE)
                );
                ingresosPanelLayout.setVerticalGroup(
                    ingresosPanelLayout.createParallelGroup()
                        .addGap(0, 642, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Ingresos", ingresosPanel);

            //======== gastosPanel ========
            {

                GroupLayout gastosPanelLayout = new GroupLayout(gastosPanel);
                gastosPanel.setLayout(gastosPanelLayout);
                gastosPanelLayout.setHorizontalGroup(
                    gastosPanelLayout.createParallelGroup()
                        .addGap(0, 882, Short.MAX_VALUE)
                );
                gastosPanelLayout.setVerticalGroup(
                    gastosPanelLayout.createParallelGroup()
                        .addGap(0, 642, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Gastos", gastosPanel);

            //======== balancePanel ========
            {

                GroupLayout balancePanelLayout = new GroupLayout(balancePanel);
                balancePanel.setLayout(balancePanelLayout);
                balancePanelLayout.setHorizontalGroup(
                    balancePanelLayout.createParallelGroup()
                        .addGap(0, 882, Short.MAX_VALUE)
                );
                balancePanelLayout.setVerticalGroup(
                    balancePanelLayout.createParallelGroup()
                        .addGap(0, 642, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Balance", balancePanel);

            //======== listaEsperaPanel ========
            {

                GroupLayout listaEsperaPanelLayout = new GroupLayout(listaEsperaPanel);
                listaEsperaPanel.setLayout(listaEsperaPanelLayout);
                listaEsperaPanelLayout.setHorizontalGroup(
                    listaEsperaPanelLayout.createParallelGroup()
                        .addGap(0, 882, Short.MAX_VALUE)
                );
                listaEsperaPanelLayout.setVerticalGroup(
                    listaEsperaPanelLayout.createParallelGroup()
                        .addGap(0, 642, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Lista de espera", listaEsperaPanel);

            //======== imprimirPanel ========
            {

                GroupLayout imprimirPanelLayout = new GroupLayout(imprimirPanel);
                imprimirPanel.setLayout(imprimirPanelLayout);
                imprimirPanelLayout.setHorizontalGroup(
                    imprimirPanelLayout.createParallelGroup()
                        .addGap(0, 882, Short.MAX_VALUE)
                );
                imprimirPanelLayout.setVerticalGroup(
                    imprimirPanelLayout.createParallelGroup()
                        .addGap(0, 642, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Imprimir", imprimirPanel);

            //======== emailPanel ========
            {

                GroupLayout emailPanelLayout = new GroupLayout(emailPanel);
                emailPanel.setLayout(emailPanelLayout);
                emailPanelLayout.setHorizontalGroup(
                    emailPanelLayout.createParallelGroup()
                        .addGap(0, 882, Short.MAX_VALUE)
                );
                emailPanelLayout.setVerticalGroup(
                    emailPanelLayout.createParallelGroup()
                        .addGap(0, 642, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Email", emailPanel);
        }

        //======== buscarPanel ========
        {

            //---- buscarLabel ----
            buscarLabel.setText("Buscar:");
            buscarLabel.setFont(buscarLabel.getFont().deriveFont(buscarLabel.getFont().getSize() + 6f));

            //---- buscarField ----
            buscarField.setFont(buscarField.getFont().deriveFont(buscarField.getFont().getSize() + 4f));

            GroupLayout buscarPanelLayout = new GroupLayout(buscarPanel);
            buscarPanel.setLayout(buscarPanelLayout);
            buscarPanelLayout.setHorizontalGroup(
                buscarPanelLayout.createParallelGroup()
                    .addGroup(buscarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buscarLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarField, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
            buscarPanelLayout.setVerticalGroup(
                buscarPanelLayout.createParallelGroup()
                    .addGroup(buscarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(buscarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(buscarField, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(buscarLabel)))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(buscarPanel, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 443, Short.MAX_VALUE))
                .addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addComponent(buscarPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(tabbedPane1))
        );
        setSize(1020, 720);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JMenuBar toolBar;
    protected static JButton nuevoBtn;
    protected static JButton editarBtn;
    protected static JButton eliminarBtn;
    protected static JButton importarBtn;
    protected static JButton exportarBtn;
    protected static JTabbedPane tabbedPane1;
    protected static JPanel sociosPanel;
    protected static JScrollPane sociosTablaPanel;
    protected static JTable sociosTabla;
    protected static JPanel ingresosPanel;
    protected static JPanel gastosPanel;
    protected static JPanel balancePanel;
    protected static JPanel listaEsperaPanel;
    protected static JPanel imprimirPanel;
    protected static JPanel emailPanel;
    protected static JPanel buscarPanel;
    protected static JLabel buscarLabel;
    protected static JTextField buscarField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
