/*
 * Created by JFormDesigner on Mon Oct 30 02:46:37 CET 2023
 */

package es.exmaster.contasoc.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author jomaa
 */
public class UIContasoc extends JFrame {
    public UIContasoc() {
        initComponents();
        GUIManager.populateGUITables();
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

    private void imprimirTestActionPerformed(ActionEvent e) {
        GUIManager.printContent();
    }

    private void printBtnActionPerformed(ActionEvent e) {
        if(sociosPanel.isVisible()) {
            GUIManager.valor = "Socios";
        } else if(ingresosPanel.isVisible()) {
            GUIManager.valor = "Ingresos";
        } else if(gastosPanel.isVisible()) {
            GUIManager.valor = "Gastos";
        } else if(listaEsperaPanel.isVisible()) {
            GUIManager.valor = "ListaEspera";
        }
        GUIManager.printContent();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        tabbedPane1 = new JTabbedPane();
        sociosPanel = new JPanel();
        sociosTablaPanel = new JScrollPane();
        sociosTabla = new JTable();
        ingresosPanel = new JPanel();
        ingresosTablaPanel = new JScrollPane();
        ingresosTabla = new JTable();
        gastosPanel = new JPanel();
        balancePanel = new JPanel();
        listaEsperaPanel = new JPanel();
        emailPanel = new JPanel();
        toolBar1 = new JToolBar();
        nuevoBtn = new JButton();
        editarBtn = new JButton();
        eliminarBtn = new JButton();
        printBtn = new JButton();
        importarBtn = new JButton();
        exportarBtn = new JButton();
        buscarPanel = new JPanel();
        buscarField = new JTextField();
        label1 = new JLabel();

        //======== this ========
        setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        setIconImage(new ImageIcon(getClass().getResource("/resources/images/logohuerto_small.png")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        setMinimumSize(new Dimension(900, 600));
        setTitle("Contasoc");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(tabbedPane1.getFont().deriveFont(tabbedPane1.getFont().getSize() + 6f));
            tabbedPane1.setTabPlacement(SwingConstants.LEFT);
            tabbedPane1.putClientProperty("JTabbedPane.tabHeight", 50);

            //======== sociosPanel ========
            {

                //======== sociosTablaPanel ========
                {
                    sociosTablaPanel.setBorder(null);
                    sociosTablaPanel.putClientProperty("JComponent.outline",Color.decode("#549159"));

                    //---- sociosTabla ----
                    sociosTabla.setFillsViewportHeight(true);
                    sociosTabla.setFont(sociosTabla.getFont().deriveFont(sociosTabla.getFont().getSize() + 4f));
                    sociosTabla.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    sociosTabla.setShowHorizontalLines(true);
                    sociosTabla.setModel(new SociosTablaModel());
                    sociosTabla.getTableHeader().setReorderingAllowed(false);
                    sociosTabla.getTableHeader().setResizingAllowed(false);
                    GUIManager.setColumnWidths(sociosTabla,
                        new int[] {55,55,350,100,100,320,100,100,100,400,100,100});
                    sociosTabla.setRowHeight(50);
                    sociosTablaPanel.setViewportView(sociosTabla);
                }

                GroupLayout sociosPanelLayout = new GroupLayout(sociosPanel);
                sociosPanel.setLayout(sociosPanelLayout);
                sociosPanelLayout.setHorizontalGroup(
                    sociosPanelLayout.createParallelGroup()
                        .addGroup(sociosPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(sociosTablaPanel, GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                            .addContainerGap())
                );
                sociosPanelLayout.setVerticalGroup(
                    sociosPanelLayout.createParallelGroup()
                        .addGroup(sociosPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(sociosTablaPanel, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }
            tabbedPane1.addTab("SOCIOS", sociosPanel);

            //======== ingresosPanel ========
            {

                //======== ingresosTablaPanel ========
                {

                    //---- ingresosTabla ----
                    ingresosTabla.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    ingresosTabla.setBorder(null);
                    ingresosTabla.setFillsViewportHeight(true);
                    ingresosTabla.setModel(new IngresosTablaModel());
                    ingresosTabla.getTableHeader().setReorderingAllowed(false);
                    ingresosTabla.getTableHeader().setResizingAllowed(false);
                    ingresosTablaPanel.setViewportView(ingresosTabla);
                }

                GroupLayout ingresosPanelLayout = new GroupLayout(ingresosPanel);
                ingresosPanel.setLayout(ingresosPanelLayout);
                ingresosPanelLayout.setHorizontalGroup(
                    ingresosPanelLayout.createParallelGroup()
                        .addGroup(ingresosPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(ingresosTablaPanel, GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                            .addContainerGap())
                );
                ingresosPanelLayout.setVerticalGroup(
                    ingresosPanelLayout.createParallelGroup()
                        .addGroup(ingresosPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(ingresosTablaPanel, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }
            tabbedPane1.addTab("INGRESOS", ingresosPanel);

            //======== gastosPanel ========
            {

                GroupLayout gastosPanelLayout = new GroupLayout(gastosPanel);
                gastosPanel.setLayout(gastosPanelLayout);
                gastosPanelLayout.setHorizontalGroup(
                    gastosPanelLayout.createParallelGroup()
                        .addGap(0, 850, Short.MAX_VALUE)
                );
                gastosPanelLayout.setVerticalGroup(
                    gastosPanelLayout.createParallelGroup()
                        .addGap(0, 603, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("GASTOS", gastosPanel);

            //======== balancePanel ========
            {

                GroupLayout balancePanelLayout = new GroupLayout(balancePanel);
                balancePanel.setLayout(balancePanelLayout);
                balancePanelLayout.setHorizontalGroup(
                    balancePanelLayout.createParallelGroup()
                        .addGap(0, 850, Short.MAX_VALUE)
                );
                balancePanelLayout.setVerticalGroup(
                    balancePanelLayout.createParallelGroup()
                        .addGap(0, 603, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("BALANCE", balancePanel);

            //======== listaEsperaPanel ========
            {

                GroupLayout listaEsperaPanelLayout = new GroupLayout(listaEsperaPanel);
                listaEsperaPanel.setLayout(listaEsperaPanelLayout);
                listaEsperaPanelLayout.setHorizontalGroup(
                    listaEsperaPanelLayout.createParallelGroup()
                        .addGap(0, 850, Short.MAX_VALUE)
                );
                listaEsperaPanelLayout.setVerticalGroup(
                    listaEsperaPanelLayout.createParallelGroup()
                        .addGap(0, 603, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("LISTA DE ESPERA", listaEsperaPanel);

            //======== emailPanel ========
            {

                GroupLayout emailPanelLayout = new GroupLayout(emailPanel);
                emailPanel.setLayout(emailPanelLayout);
                emailPanelLayout.setHorizontalGroup(
                    emailPanelLayout.createParallelGroup()
                        .addGap(0, 850, Short.MAX_VALUE)
                );
                emailPanelLayout.setVerticalGroup(
                    emailPanelLayout.createParallelGroup()
                        .addGap(0, 603, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("EMAIL", emailPanel);
        }
        contentPane.add(tabbedPane1, BorderLayout.CENTER);

        //======== toolBar1 ========
        {

            //---- nuevoBtn ----
            nuevoBtn.setText("Nuevo");
            nuevoBtn.setFont(nuevoBtn.getFont().deriveFont(nuevoBtn.getFont().getSize() + 4f));
            nuevoBtn.setIcon(new ImageIcon(getClass().getResource("/images/newpass_small_black.png")));
            nuevoBtn.setIconTextGap(6);
            nuevoBtn.setMargin(new Insets(2, 2, 2, 6));
            toolBar1.add(nuevoBtn);

            //---- editarBtn ----
            editarBtn.setText("Editar");
            editarBtn.setFont(editarBtn.getFont().deriveFont(editarBtn.getFont().getSize() + 4f));
            editarBtn.setIcon(new ImageIcon(getClass().getResource("/images/edit_small_black.png")));
            editarBtn.setIconTextGap(6);
            editarBtn.setMargin(new Insets(2, 2, 2, 6));
            toolBar1.add(editarBtn);

            //---- eliminarBtn ----
            eliminarBtn.setText("Eliminar");
            eliminarBtn.setFont(eliminarBtn.getFont().deriveFont(eliminarBtn.getFont().getSize() + 4f));
            eliminarBtn.setIcon(new ImageIcon(getClass().getResource("/images/bin_small_black.png")));
            eliminarBtn.setIconTextGap(6);
            eliminarBtn.setMargin(new Insets(2, 2, 2, 6));
            toolBar1.add(eliminarBtn);

            //---- printBtn ----
            printBtn.setText("Imprimir");
            printBtn.setFont(printBtn.getFont().deriveFont(printBtn.getFont().getSize() + 4f));
            printBtn.setIconTextGap(6);
            printBtn.setMargin(new Insets(2, 2, 2, 6));
            printBtn.setIcon(new ImageIcon(getClass().getResource("/resources/images/printer.png")));
            printBtn.addActionListener(e -> printBtnActionPerformed(e));
            toolBar1.add(printBtn);

            //---- importarBtn ----
            importarBtn.setText("Importar");
            importarBtn.setFont(importarBtn.getFont().deriveFont(importarBtn.getFont().getSize() + 4f));
            importarBtn.setIcon(new ImageIcon(getClass().getResource("/images/import_small_black.png")));
            importarBtn.setIconTextGap(6);
            importarBtn.setMargin(new Insets(2, 2, 2, 6));
            toolBar1.add(importarBtn);

            //---- exportarBtn ----
            exportarBtn.setText("Exportar");
            exportarBtn.setFont(exportarBtn.getFont().deriveFont(exportarBtn.getFont().getSize() + 4f));
            exportarBtn.setIcon(new ImageIcon(getClass().getResource("/images/export_small_black.png")));
            exportarBtn.setIconTextGap(6);
            exportarBtn.setMargin(new Insets(2, 2, 2, 6));
            toolBar1.add(exportarBtn);
            toolBar1.addSeparator();

            //======== buscarPanel ========
            {
                buscarPanel.setPreferredSize(new Dimension(100, 32));

                //---- buscarField ----
                buscarField.setFont(buscarField.getFont().deriveFont(buscarField.getFont().getSize() + 4f));
                buscarField.setMinimumSize(new Dimension(68, 28));
                buscarField.setPreferredSize(new Dimension(68, 28));
                buscarField.putClientProperty("JTextField.placeholderText", "Buscar socios...");
                buscarField.putClientProperty("JTextField.leadingIcon", new ImageIcon(Objects.requireNonNull(UIContasoc.class.getResource("/images/search_medium_black.png"))));

                //---- label1 ----
                label1.setText("Ver. 6.0");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 2f));
                label1.setHorizontalAlignment(SwingConstants.RIGHT);

                GroupLayout buscarPanelLayout = new GroupLayout(buscarPanel);
                buscarPanel.setLayout(buscarPanelLayout);
                buscarPanelLayout.setHorizontalGroup(
                    buscarPanelLayout.createParallelGroup()
                        .addGroup(buscarPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(buscarField, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                );
                buscarPanelLayout.setVerticalGroup(
                    buscarPanelLayout.createParallelGroup()
                        .addGroup(buscarPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, buscarPanelLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(buscarField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                );
            }
            toolBar1.add(buscarPanel);
        }
        contentPane.add(toolBar1, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JTabbedPane tabbedPane1;
    protected static JPanel sociosPanel;
    protected static JScrollPane sociosTablaPanel;
    protected static JTable sociosTabla;
    protected static JPanel ingresosPanel;
    protected static JScrollPane ingresosTablaPanel;
    protected static JTable ingresosTabla;
    protected static JPanel gastosPanel;
    protected static JPanel balancePanel;
    protected static JPanel listaEsperaPanel;
    protected static JPanel emailPanel;
    private JToolBar toolBar1;
    protected static JButton nuevoBtn;
    protected static JButton editarBtn;
    protected static JButton eliminarBtn;
    private JButton printBtn;
    protected static JButton importarBtn;
    protected static JButton exportarBtn;
    private JPanel buscarPanel;
    protected static JTextField buscarField;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
