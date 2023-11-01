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

    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
    }

    public JPanel getSociosPanel() {
        return sociosPanel;
    }

    public JScrollPane getSociosTablaPanel() {
        return sociosTablaPanel;
    }

    public JPanel getIngresosPanel() {
        return ingresosPanel;
    }

    public JPanel getGastosPanel() {
        return gastosPanel;
    }

    public JPanel getBalancePanel() {
        return balancePanel;
    }

    public JPanel getListaEsperaPanel() {
        return listaEsperaPanel;
    }

    public JPanel getImprimirPanel() {
        return imprimirPanel;
    }

    public JPanel getEmailPanel() {
        return emailPanel;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public JLabel getVersionLabel() {
        return versionLabel;
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        tabbedPane1 = new JTabbedPane();
        sociosPanel = new JPanel();
        sociosTablaPanel = new JScrollPane();
        sociosTabla = new JTable();
        buscarPanel = new JPanel();
        buscarLabel = new JLabel();
        buscarField = new JTextField();
        ingresosPanel = new JPanel();
        gastosPanel = new JPanel();
        balancePanel = new JPanel();
        listaEsperaPanel = new JPanel();
        imprimirPanel = new JPanel();
        emailPanel = new JPanel();
        toolBar = new JToolBar();
        nuevoBtn = new JButton();
        editarBtn = new JButton();
        eliminarBtn = new JButton();
        separator1 = new JSeparator();
        importarBtn = new JButton();
        exportarBtn = new JButton();
        versionLabel = new JLabel();

        //======== this ========
        setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        setTitle("Huertos la Salud Bellavista");
        setIconImage(new ImageIcon(getClass().getResource("/images/newlogo_small.png")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(960, 600));
        var contentPane = getContentPane();

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(tabbedPane1.getFont().deriveFont(tabbedPane1.getFont().getSize() + 6f));
            tabbedPane1.setTabPlacement(SwingConstants.LEFT);

            //======== sociosPanel ========
            {
                sociosPanel.setPreferredSize(new Dimension(960, 600));

                //======== sociosTablaPanel ========
                {
                    sociosTablaPanel.setBorder(null);

                    //---- sociosTabla ----
                    sociosTabla.setModel(new SociosTablaModel());
                    sociosTablaPanel.setViewportView(sociosTabla);
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
                                    .addComponent(buscarLabel)
                                    .addComponent(buscarField, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }

                GroupLayout sociosPanelLayout = new GroupLayout(sociosPanel);
                sociosPanel.setLayout(sociosPanelLayout);
                sociosPanelLayout.setHorizontalGroup(
                    sociosPanelLayout.createParallelGroup()
                        .addComponent(buscarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sociosTablaPanel)
                );
                sociosPanelLayout.setVerticalGroup(
                    sociosPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, sociosPanelLayout.createSequentialGroup()
                            .addComponent(buscarPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sociosTablaPanel, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE))
                );
            }
            tabbedPane1.addTab("Socios", sociosPanel);

            //======== ingresosPanel ========
            {

                GroupLayout ingresosPanelLayout = new GroupLayout(ingresosPanel);
                ingresosPanel.setLayout(ingresosPanelLayout);
                ingresosPanelLayout.setHorizontalGroup(
                    ingresosPanelLayout.createParallelGroup()
                        .addGap(0, 760, Short.MAX_VALUE)
                );
                ingresosPanelLayout.setVerticalGroup(
                    ingresosPanelLayout.createParallelGroup()
                        .addGap(0, 529, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Ingresos", ingresosPanel);

            //======== gastosPanel ========
            {

                GroupLayout gastosPanelLayout = new GroupLayout(gastosPanel);
                gastosPanel.setLayout(gastosPanelLayout);
                gastosPanelLayout.setHorizontalGroup(
                    gastosPanelLayout.createParallelGroup()
                        .addGap(0, 760, Short.MAX_VALUE)
                );
                gastosPanelLayout.setVerticalGroup(
                    gastosPanelLayout.createParallelGroup()
                        .addGap(0, 529, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Gastos", gastosPanel);

            //======== balancePanel ========
            {

                GroupLayout balancePanelLayout = new GroupLayout(balancePanel);
                balancePanel.setLayout(balancePanelLayout);
                balancePanelLayout.setHorizontalGroup(
                    balancePanelLayout.createParallelGroup()
                        .addGap(0, 760, Short.MAX_VALUE)
                );
                balancePanelLayout.setVerticalGroup(
                    balancePanelLayout.createParallelGroup()
                        .addGap(0, 529, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Balance", balancePanel);

            //======== listaEsperaPanel ========
            {

                GroupLayout listaEsperaPanelLayout = new GroupLayout(listaEsperaPanel);
                listaEsperaPanel.setLayout(listaEsperaPanelLayout);
                listaEsperaPanelLayout.setHorizontalGroup(
                    listaEsperaPanelLayout.createParallelGroup()
                        .addGap(0, 760, Short.MAX_VALUE)
                );
                listaEsperaPanelLayout.setVerticalGroup(
                    listaEsperaPanelLayout.createParallelGroup()
                        .addGap(0, 529, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Lista de espera", listaEsperaPanel);

            //======== imprimirPanel ========
            {

                GroupLayout imprimirPanelLayout = new GroupLayout(imprimirPanel);
                imprimirPanel.setLayout(imprimirPanelLayout);
                imprimirPanelLayout.setHorizontalGroup(
                    imprimirPanelLayout.createParallelGroup()
                        .addGap(0, 760, Short.MAX_VALUE)
                );
                imprimirPanelLayout.setVerticalGroup(
                    imprimirPanelLayout.createParallelGroup()
                        .addGap(0, 529, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Imprimir", imprimirPanel);

            //======== emailPanel ========
            {

                GroupLayout emailPanelLayout = new GroupLayout(emailPanel);
                emailPanel.setLayout(emailPanelLayout);
                emailPanelLayout.setHorizontalGroup(
                    emailPanelLayout.createParallelGroup()
                        .addGap(0, 760, Short.MAX_VALUE)
                );
                emailPanelLayout.setVerticalGroup(
                    emailPanelLayout.createParallelGroup()
                        .addGap(0, 529, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Email", emailPanel);
        }

        //======== toolBar ========
        {

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

            //---- separator1 ----
            separator1.setBackground(new Color(0xf7f8fa));
            separator1.setForeground(new Color(0xf7f8fa));
            toolBar.add(separator1);

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

        //---- versionLabel ----
        versionLabel.setText("v5.1.1");
        versionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getSize() + 2f));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(versionLabel, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(versionLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
        );
        setSize(900, 600);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected JTabbedPane tabbedPane1;
    protected JPanel sociosPanel;
    protected JScrollPane sociosTablaPanel;
    private JTable sociosTabla;
    private JPanel buscarPanel;
    private JLabel buscarLabel;
    private JTextField buscarField;
    protected JPanel ingresosPanel;
    protected JPanel gastosPanel;
    protected JPanel balancePanel;
    protected JPanel listaEsperaPanel;
    protected JPanel imprimirPanel;
    protected JPanel emailPanel;
    protected JToolBar toolBar;
    private JButton nuevoBtn;
    private JButton editarBtn;
    private JButton eliminarBtn;
    private JSeparator separator1;
    private JButton importarBtn;
    private JButton exportarBtn;
    protected JLabel versionLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
