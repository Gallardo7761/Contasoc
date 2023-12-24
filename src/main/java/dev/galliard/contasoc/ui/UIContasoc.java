/*
 * Created by JFormDesigner on Mon Oct 30 02:46:37 CET 2023
 */

package dev.galliard.contasoc.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author jomaa
 */
public class UIContasoc extends JFrame {
    public UIContasoc() {
        initComponents();
        GUIManager.populateGUITables();
        BalancePanelWatcher watcher = new BalancePanelWatcher();
        Thread thread = new Thread(watcher);
        thread.start();
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
        if(cardSociosPanel.isVisible()) {
            GUIManager.valor = "Socios";
        } else if(ingresosPanel.isVisible()) {
            GUIManager.valor = "Ingresos";
        } else if(gastosPanel.isVisible()) {
            GUIManager.valor = "Gastos";
        } else if(cardListaEsperaPanel.isVisible()) {
            GUIManager.valor = "ListaEspera";
        }
        GUIManager.printContent();
    }

    private void toListaEsperaBtnActionPerformed(ActionEvent e) {
        ((CardLayout) sociosPanel.getLayout()).show(sociosPanel, "cardListaEspera");
    }

    private void toSociosBtnActionPerformed(ActionEvent e) {
        ((CardLayout) sociosPanel.getLayout()).show(sociosPanel, "cardSocios");
    }

    private void balancePanelComponentShown(ComponentEvent e) {
        BalanceCalculatorThread balanceCalc = new BalanceCalculatorThread();
        Thread thread = new Thread(balanceCalc);
        thread.start();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        tabbedPane1 = new JTabbedPane();
        sociosPanel = new JPanel();
        cardSociosPanel = new JPanel();
        sociosWrapper = new JPanel();
        sociosTablaPanel = new JScrollPane();
        sociosTabla = new JTable();
        toListaEsperaWrapper = new JPanel();
        toListaEsperaBtn = new JButton();
        cardListaEsperaPanel = new JPanel();
        toSociosWrapper = new JPanel();
        toSociosBtn = new JButton();
        listaEsperaWrapper = new JPanel();
        listaEsperaTablaPanel = new JScrollPane();
        listaEsperaTabla = new JTable();
        ingresosPanel = new JPanel();
        ingresosTablaPanel = new JScrollPane();
        ingresosTabla = new JTable();
        gastosPanel = new JPanel();
        gastosTablaPanel = new JScrollPane();
        gastosTabla = new JTable();
        balancePanel = new JPanel();
        balanceCantidadesPanel = new JPanel();
        tBancoIngresos = new JPanel();
        tBancoIngresosLabel = new JLabel();
        tBancoIngresosValue = new JLabel();
        tCajaIngresos = new JPanel();
        tCajaIngresosLabel = new JLabel();
        tCajaIngresosValue = new JLabel();
        tBancoGastos = new JPanel();
        tBancoGastosLabel = new JLabel();
        tBancoGastosValue = new JLabel();
        tCajaGastos = new JPanel();
        tCajaGastosLabel = new JLabel();
        tCajaGastosValue = new JLabel();
        saldoBanco = new JPanel();
        saldoBancoLabel = new JLabel();
        saldoBancoValue = new JLabel();
        saldoCaja = new JPanel();
        saldoCajaLabel = new JLabel();
        saldoCajaValue = new JLabel();
        emailPanel = new JPanel();
        emailDataPanel = new JPanel();
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
        setIconImage(new ImageIcon(getClass().getResource("/images/logohuerto_small.png")).getImage());
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
            tabbedPane1.putClientProperty("JTabbedPane.minimumTabWidth", 200);
            tabbedPane1.putClientProperty("JTabbedPane.maximumTabWidth", 200);

            //======== sociosPanel ========
            {
                sociosPanel.setLayout(new CardLayout());

                //======== cardSociosPanel ========
                {
                    cardSociosPanel.setLayout(new BorderLayout());

                    //======== sociosWrapper ========
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
                            sociosTabla.setGridColor(new Color(0xcccccc));
                            sociosTabla.setModel(new SociosTablaModel());
                            sociosTabla.getTableHeader().setReorderingAllowed(false);
                            sociosTabla.getTableHeader().setResizingAllowed(false);
                            GUIManager.setColumnWidths(sociosTabla, 
                                new int[] {55,55,350,100,100,320,100,100,100,400,100,100});
                            sociosTabla.setRowHeight(50);
                            sociosTablaPanel.setViewportView(sociosTabla);
                        }

                        GroupLayout sociosWrapperLayout = new GroupLayout(sociosWrapper);
                        sociosWrapper.setLayout(sociosWrapperLayout);
                        sociosWrapperLayout.setHorizontalGroup(
                            sociosWrapperLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, sociosWrapperLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(sociosTablaPanel, GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                        sociosWrapperLayout.setVerticalGroup(
                            sociosWrapperLayout.createParallelGroup()
                                .addGroup(sociosWrapperLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(sociosTablaPanel, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                    }
                    cardSociosPanel.add(sociosWrapper, BorderLayout.CENTER);

                    //======== toListaEsperaWrapper ========
                    {

                        //---- toListaEsperaBtn ----
                        toListaEsperaBtn.setText(">");
                        toListaEsperaBtn.setBackground(new Color(0x999999));
                        toListaEsperaBtn.setFont(toListaEsperaBtn.getFont().deriveFont(toListaEsperaBtn.getFont().getStyle() | Font.BOLD, toListaEsperaBtn.getFont().getSize() + 4f));
                        toListaEsperaBtn.setForeground(Color.black);
                        toListaEsperaBtn.setToolTipText("Ver lista de espera");
                        toListaEsperaBtn.addActionListener(e -> toListaEsperaBtnActionPerformed(e));

                        GroupLayout toListaEsperaWrapperLayout = new GroupLayout(toListaEsperaWrapper);
                        toListaEsperaWrapper.setLayout(toListaEsperaWrapperLayout);
                        toListaEsperaWrapperLayout.setHorizontalGroup(
                            toListaEsperaWrapperLayout.createParallelGroup()
                                .addGroup(toListaEsperaWrapperLayout.createSequentialGroup()
                                    .addComponent(toListaEsperaBtn)
                                    .addGap(0, 0, Short.MAX_VALUE))
                        );
                        toListaEsperaWrapperLayout.setVerticalGroup(
                            toListaEsperaWrapperLayout.createParallelGroup()
                                .addGroup(toListaEsperaWrapperLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(toListaEsperaBtn, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                    }
                    cardSociosPanel.add(toListaEsperaWrapper, BorderLayout.LINE_END);
                }
                sociosPanel.add(cardSociosPanel, "cardSocios");

                //======== cardListaEsperaPanel ========
                {
                    cardListaEsperaPanel.setLayout(new BorderLayout());

                    //======== toSociosWrapper ========
                    {

                        //---- toSociosBtn ----
                        toSociosBtn.setText("<");
                        toSociosBtn.setBackground(new Color(0x999999));
                        toSociosBtn.setFont(toSociosBtn.getFont().deriveFont(toSociosBtn.getFont().getStyle() | Font.BOLD, toSociosBtn.getFont().getSize() + 4f));
                        toSociosBtn.setForeground(Color.black);
                        toSociosBtn.setToolTipText("Ver socios");
                        toSociosBtn.addActionListener(e -> toSociosBtnActionPerformed(e));

                        GroupLayout toSociosWrapperLayout = new GroupLayout(toSociosWrapper);
                        toSociosWrapper.setLayout(toSociosWrapperLayout);
                        toSociosWrapperLayout.setHorizontalGroup(
                            toSociosWrapperLayout.createParallelGroup()
                                .addGroup(toSociosWrapperLayout.createSequentialGroup()
                                    .addComponent(toSociosBtn)
                                    .addGap(0, 0, Short.MAX_VALUE))
                        );
                        toSociosWrapperLayout.setVerticalGroup(
                            toSociosWrapperLayout.createParallelGroup()
                                .addGroup(toSociosWrapperLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(toSociosBtn, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                    }
                    cardListaEsperaPanel.add(toSociosWrapper, BorderLayout.WEST);

                    //======== listaEsperaWrapper ========
                    {

                        //======== listaEsperaTablaPanel ========
                        {
                            listaEsperaTablaPanel.setBorder(null);

                            //---- listaEsperaTabla ----
                            listaEsperaTabla.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                            listaEsperaTabla.setFont(listaEsperaTabla.getFont().deriveFont(listaEsperaTabla.getFont().getSize() + 4f));
                            listaEsperaTabla.setShowHorizontalLines(true);
                            listaEsperaTabla.setGridColor(new Color(0xcccccc));
                            listaEsperaTabla.setBorder(null);
                            listaEsperaTabla.setModel(new ListaEsperaTablaModel());
                            listaEsperaTabla.getTableHeader().setReorderingAllowed(false);
                            listaEsperaTabla.getTableHeader().setResizingAllowed(false);
                            listaEsperaTabla.setRowHeight(50);
                            listaEsperaTablaPanel.setViewportView(listaEsperaTabla);
                        }

                        GroupLayout listaEsperaWrapperLayout = new GroupLayout(listaEsperaWrapper);
                        listaEsperaWrapper.setLayout(listaEsperaWrapperLayout);
                        listaEsperaWrapperLayout.setHorizontalGroup(
                            listaEsperaWrapperLayout.createParallelGroup()
                                .addGroup(listaEsperaWrapperLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(listaEsperaTablaPanel, GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                        listaEsperaWrapperLayout.setVerticalGroup(
                            listaEsperaWrapperLayout.createParallelGroup()
                                .addGroup(listaEsperaWrapperLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(listaEsperaTablaPanel, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                    }
                    cardListaEsperaPanel.add(listaEsperaWrapper, BorderLayout.CENTER);
                }
                sociosPanel.add(cardListaEsperaPanel, "cardListaEspera");
            }
            tabbedPane1.addTab("SOCIOS", sociosPanel);

            //======== ingresosPanel ========
            {

                //======== ingresosTablaPanel ========
                {

                    //---- ingresosTabla ----
                    ingresosTabla.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    ingresosTabla.setFillsViewportHeight(true);
                    ingresosTabla.setFont(ingresosTabla.getFont().deriveFont(ingresosTabla.getFont().getSize() + 4f));
                    ingresosTabla.setModel(new IngresosTablaModel());
                    ingresosTabla.getTableHeader().setReorderingAllowed(false);
                    ingresosTabla.getTableHeader().setResizingAllowed(false);
                    ingresosTablaPanel.setViewportView(ingresosTabla);
                }

                GroupLayout ingresosPanelLayout = new GroupLayout(ingresosPanel);
                ingresosPanel.setLayout(ingresosPanelLayout);
                ingresosPanelLayout.setHorizontalGroup(
                    ingresosPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, ingresosPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(ingresosTablaPanel, GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
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

                //======== gastosTablaPanel ========
                {

                    //---- gastosTabla ----
                    gastosTabla.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                    gastosTabla.setFillsViewportHeight(true);
                    gastosTabla.setFont(gastosTabla.getFont().deriveFont(gastosTabla.getFont().getSize() + 4f));
                    gastosTabla.setModel(new GastosTablaModel());
                    gastosTabla.getTableHeader().setReorderingAllowed(false);
                    gastosTabla.getTableHeader().setResizingAllowed(false);
                    gastosTablaPanel.setViewportView(gastosTabla);
                }

                GroupLayout gastosPanelLayout = new GroupLayout(gastosPanel);
                gastosPanel.setLayout(gastosPanelLayout);
                gastosPanelLayout.setHorizontalGroup(
                    gastosPanelLayout.createParallelGroup()
                        .addGroup(gastosPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(gastosTablaPanel, GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
                            .addContainerGap())
                );
                gastosPanelLayout.setVerticalGroup(
                    gastosPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, gastosPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(gastosTablaPanel, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }
            tabbedPane1.addTab("GASTOS", gastosPanel);

            //======== balancePanel ========
            {
                balancePanel.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentShown(ComponentEvent e) {
                        balancePanelComponentShown(e);
                    }
                });
                balancePanel.setLayout(new BorderLayout());

                //======== balanceCantidadesPanel ========
                {
                    balanceCantidadesPanel.setLayout(new GridLayout(4, 2, 0, 50));

                    //======== tBancoIngresos ========
                    {
                        tBancoIngresos.setLayout(new BorderLayout());

                        //---- tBancoIngresosLabel ----
                        tBancoIngresosLabel.setText("TOTAL INGRESOS BANCO");
                        tBancoIngresosLabel.setFont(tBancoIngresosLabel.getFont().deriveFont(tBancoIngresosLabel.getFont().getSize() + 10f));
                        tBancoIngresosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        tBancoIngresos.add(tBancoIngresosLabel, BorderLayout.NORTH);

                        //---- tBancoIngresosValue ----
                        tBancoIngresosValue.setText("0,00 \u20ac");
                        tBancoIngresosValue.setHorizontalTextPosition(SwingConstants.CENTER);
                        tBancoIngresosValue.setHorizontalAlignment(SwingConstants.CENTER);
                        tBancoIngresosValue.setFont(new Font("Segoe UI", Font.ITALIC, 48));
                        tBancoIngresos.add(tBancoIngresosValue, BorderLayout.CENTER);
                    }
                    balanceCantidadesPanel.add(tBancoIngresos);

                    //======== tCajaIngresos ========
                    {
                        tCajaIngresos.setLayout(new BorderLayout());

                        //---- tCajaIngresosLabel ----
                        tCajaIngresosLabel.setText("TOTAL INGRESOS CAJA");
                        tCajaIngresosLabel.setFont(tCajaIngresosLabel.getFont().deriveFont(tCajaIngresosLabel.getFont().getSize() + 10f));
                        tCajaIngresosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        tCajaIngresos.add(tCajaIngresosLabel, BorderLayout.NORTH);

                        //---- tCajaIngresosValue ----
                        tCajaIngresosValue.setText("0,00  \u20ac");
                        tCajaIngresosValue.setHorizontalTextPosition(SwingConstants.CENTER);
                        tCajaIngresosValue.setHorizontalAlignment(SwingConstants.CENTER);
                        tCajaIngresosValue.setFont(new Font("Segoe UI", Font.ITALIC, 48));
                        tCajaIngresos.add(tCajaIngresosValue, BorderLayout.CENTER);
                    }
                    balanceCantidadesPanel.add(tCajaIngresos);

                    //======== tBancoGastos ========
                    {
                        tBancoGastos.setLayout(new BorderLayout());

                        //---- tBancoGastosLabel ----
                        tBancoGastosLabel.setText("TOTAL GASTOS BANCO");
                        tBancoGastosLabel.setFont(tBancoGastosLabel.getFont().deriveFont(tBancoGastosLabel.getFont().getSize() + 10f));
                        tBancoGastosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        tBancoGastos.add(tBancoGastosLabel, BorderLayout.NORTH);

                        //---- tBancoGastosValue ----
                        tBancoGastosValue.setText("0,00 \u20ac");
                        tBancoGastosValue.setHorizontalTextPosition(SwingConstants.CENTER);
                        tBancoGastosValue.setHorizontalAlignment(SwingConstants.CENTER);
                        tBancoGastosValue.setFont(new Font("Segoe UI", Font.ITALIC, 48));
                        tBancoGastos.add(tBancoGastosValue, BorderLayout.CENTER);
                    }
                    balanceCantidadesPanel.add(tBancoGastos);

                    //======== tCajaGastos ========
                    {
                        tCajaGastos.setLayout(new BorderLayout());

                        //---- tCajaGastosLabel ----
                        tCajaGastosLabel.setText("TOTAL GASTOS CAJA");
                        tCajaGastosLabel.setFont(tCajaGastosLabel.getFont().deriveFont(tCajaGastosLabel.getFont().getSize() + 10f));
                        tCajaGastosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        tCajaGastos.add(tCajaGastosLabel, BorderLayout.NORTH);

                        //---- tCajaGastosValue ----
                        tCajaGastosValue.setText("0,00 \u20ac");
                        tCajaGastosValue.setHorizontalTextPosition(SwingConstants.CENTER);
                        tCajaGastosValue.setHorizontalAlignment(SwingConstants.CENTER);
                        tCajaGastosValue.setFont(new Font("Segoe UI", Font.ITALIC, 48));
                        tCajaGastos.add(tCajaGastosValue, BorderLayout.CENTER);
                    }
                    balanceCantidadesPanel.add(tCajaGastos);

                    //======== saldoBanco ========
                    {
                        saldoBanco.setLayout(new BorderLayout());

                        //---- saldoBancoLabel ----
                        saldoBancoLabel.setText("SALDO ACTUAL BANCO");
                        saldoBancoLabel.setFont(saldoBancoLabel.getFont().deriveFont(saldoBancoLabel.getFont().getSize() + 10f));
                        saldoBancoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        saldoBanco.add(saldoBancoLabel, BorderLayout.NORTH);

                        //---- saldoBancoValue ----
                        saldoBancoValue.setText("0,00 \u20ac");
                        saldoBancoValue.setFont(new Font("Segoe UI", Font.ITALIC, 48));
                        saldoBancoValue.setHorizontalAlignment(SwingConstants.CENTER);
                        saldoBanco.add(saldoBancoValue, BorderLayout.CENTER);
                    }
                    balanceCantidadesPanel.add(saldoBanco);

                    //======== saldoCaja ========
                    {
                        saldoCaja.setLayout(new BorderLayout());

                        //---- saldoCajaLabel ----
                        saldoCajaLabel.setText("SALDO ACTUAL CAJA");
                        saldoCajaLabel.setFont(saldoCajaLabel.getFont().deriveFont(saldoCajaLabel.getFont().getSize() + 10f));
                        saldoCajaLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        saldoCaja.add(saldoCajaLabel, BorderLayout.NORTH);

                        //---- saldoCajaValue ----
                        saldoCajaValue.setText("0,00 \u20ac");
                        saldoCajaValue.setFont(new Font("Segoe UI", Font.ITALIC, 48));
                        saldoCajaValue.setHorizontalAlignment(SwingConstants.CENTER);
                        saldoCaja.add(saldoCajaValue, BorderLayout.CENTER);
                    }
                    balanceCantidadesPanel.add(saldoCaja);
                }
                balancePanel.add(balanceCantidadesPanel, BorderLayout.CENTER);
            }
            tabbedPane1.addTab("BALANCE", balancePanel);

            //======== emailPanel ========
            {
                emailPanel.setLayout(new BorderLayout());

                //======== emailDataPanel ========
                {
                    emailDataPanel.setLayout(new GridLayout());
                }
                emailPanel.add(emailDataPanel, BorderLayout.NORTH);
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
            printBtn.setIcon(new ImageIcon(getClass().getResource("/images/printer.png")));
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
                        .addComponent(buscarField, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    protected static JPanel cardSociosPanel;
    protected static JPanel sociosWrapper;
    protected static JScrollPane sociosTablaPanel;
    protected static JTable sociosTabla;
    private JPanel toListaEsperaWrapper;
    protected static JButton toListaEsperaBtn;
    protected static JPanel cardListaEsperaPanel;
    private JPanel toSociosWrapper;
    protected static JButton toSociosBtn;
    protected static JPanel listaEsperaWrapper;
    protected static JScrollPane listaEsperaTablaPanel;
    protected static JTable listaEsperaTabla;
    protected static JPanel ingresosPanel;
    protected static JScrollPane ingresosTablaPanel;
    protected static JTable ingresosTabla;
    protected static JPanel gastosPanel;
    protected static JScrollPane gastosTablaPanel;
    protected static JTable gastosTabla;
    protected static JPanel balancePanel;
    protected static JPanel balanceCantidadesPanel;
    protected static JPanel tBancoIngresos;
    protected static JLabel tBancoIngresosLabel;
    protected static JLabel tBancoIngresosValue;
    protected static JPanel tCajaIngresos;
    protected static JLabel tCajaIngresosLabel;
    protected static JLabel tCajaIngresosValue;
    protected static JPanel tBancoGastos;
    protected static JLabel tBancoGastosLabel;
    protected static JLabel tBancoGastosValue;
    protected static JPanel tCajaGastos;
    protected static JLabel tCajaGastosLabel;
    protected static JLabel tCajaGastosValue;
    protected static JPanel saldoBanco;
    protected static JLabel saldoBancoLabel;
    protected static JLabel saldoBancoValue;
    protected static JPanel saldoCaja;
    protected static JLabel saldoCajaLabel;
    protected static JLabel saldoCajaValue;
    protected static JPanel emailPanel;
    private JPanel emailDataPanel;
    protected static JToolBar toolBar1;
    protected static JButton nuevoBtn;
    protected static JButton editarBtn;
    protected static JButton eliminarBtn;
    protected static JButton printBtn;
    protected static JButton importarBtn;
    protected static JButton exportarBtn;
    protected static JPanel buscarPanel;
    protected static JTextField buscarField;
    protected static JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}