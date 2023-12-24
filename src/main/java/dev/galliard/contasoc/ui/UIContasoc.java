/*
 * Created by JFormDesigner on Mon Oct 30 02:46:37 CET 2023
 */

package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.ui.tablemodels.GastosTablaModel;
import dev.galliard.contasoc.ui.tablemodels.IngresosTablaModel;
import dev.galliard.contasoc.ui.tablemodels.ListaEsperaTablaModel;
import dev.galliard.contasoc.ui.tablemodels.SociosTablaModel;

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

    private void printBtnActionPerformed(ActionEvent e) {
        Component selected = UIContasoc.tabbedPane1.getSelectedComponent();
        if (selected.equals(sociosPanel)) {
            if (cardSociosPanel.isVisible()) {
                GUIManager.valor = "Socios";
            } else if (cardListaEsperaPanel.isVisible()) {
                GUIManager.valor = "ListaEspera";
            }
        } else if (selected.equals(ingresosPanel)) {
            GUIManager.valor = "Ingresos";
        } else if (selected.equals(gastosPanel)) {
            GUIManager.valor = "Gastos";
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

    private void bodyTextAreaFocusGained(FocusEvent e) {
        // TODO add your code here
        if(bodyTextArea.getText().equals("Escriba su mensaje...")) {
            bodyTextArea.setText("");
        }
    }

    private void bodyTextAreaFocusLost(FocusEvent e) {
        // TODO add your code here
        if(bodyTextArea.getText().isEmpty()) {
            bodyTextArea.setText("Escriba su mensaje...");
        }
    }

    private void nuevoBtnActionPerformed(ActionEvent e) {
        // TODO add your code here
        /*for(Component component : tabbedPane1.getComponents()) {
            if(component.isVisible()) {
                if(component.equals(sociosPanel)) {
                    GUIManager.nuevoSocio();
                } else if(component.equals(ingresosPanel)) {
                    GUIManager.nuevoIngreso();
                } else if(component.equals(gastosPanel)) {
                    GUIManager.nuevoGasto();
                }
            }
        }*/
    }

    private void editarBtnActionPerformed(ActionEvent e) {
        // TODO add your code here
        /*for(Component component : tabbedPane1.getComponents()) {
            if(component.isVisible()) {
                if(component.equals(sociosPanel)) {
                    GUIManager.editarSocio();
                } else if(component.equals(ingresosPanel)) {
                    GUIManager.editarIngreso();
                } else if(component.equals(gastosPanel)) {
                    GUIManager.editarGasto();
                }
            }
        }*/
    }

    private void eliminarBtnActionPerformed(ActionEvent e) {
        // TODO add your code here
        /*for(Component component : tabbedPane1.getComponents()) {
            if(component.isVisible()) {
                if(component.equals(sociosPanel)) {
                    GUIManager.eliminarSocio();
                } else if(component.equals(ingresosPanel)) {
                    GUIManager.eliminarIngreso();
                } else if(component.equals(gastosPanel)) {
                    GUIManager.eliminarGasto();
                }
            }
        }*/
    }

    private void importarBtnActionPerformed(ActionEvent e) {
        // TODO add your code here
        GUIManager.importBDD();
    }

    private void exportarBtnActionPerformed(ActionEvent e) {
        // TODO add your code here
        GUIManager.exportBDD();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        searchAndButtonsWrapper = new JPanel();
        buscarField = new JTextField();
        nuevoBtn = new JButton();
        editarBtn = new JButton();
        eliminarBtn = new JButton();
        printBtn2 = new JButton();
        importarBtn = new JButton();
        exportarBtn = new JButton();
        label1 = new JLabel();
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
        destinatarioLabel = new JLabel();
        asuntoLabel = new JLabel();
        asuntoField = new JTextField();
        destinatarioComboBox = new JComboBox();
        emailBodyPanel = new JPanel();
        emailBodyWrapper = new JPanel();
        bodyScrollPane = new JScrollPane();
        bodyTextArea = new JTextArea();
        emailBtnsPanel = new JPanel();
        emailBtnsWrapper = new JPanel();
        enviarBtn = new JButton();
        borradorBtn = new JButton();
        comboBox1 = new JComboBox();

        //======== this ========
        setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        setIconImage(new ImageIcon(getClass().getResource("/images/logohuerto_small.png")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        setMinimumSize(new Dimension(900, 600));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== searchAndButtonsWrapper ========
        {
            searchAndButtonsWrapper.setPreferredSize(new Dimension(1008, 78));
            searchAndButtonsWrapper.setMinimumSize(new Dimension(673, 78));

            //---- buscarField ----
            buscarField.setFont(buscarField.getFont().deriveFont(buscarField.getFont().getSize() + 6f));
            buscarField.setMinimumSize(new Dimension(68, 38));
            buscarField.setPreferredSize(new Dimension(68, 38));
            buscarField.setCaretColor(new Color(0x549159));
            buscarField.putClientProperty("JTextField.placeholderText", "Buscar socios...");
            buscarField.putClientProperty("JTextField.leadingIcon", new ImageIcon(Objects.requireNonNull(UIContasoc.class.getResource("/images/search_medium_black.png"))));

            //---- nuevoBtn ----
            nuevoBtn.setText("Nuevo");
            nuevoBtn.setFont(nuevoBtn.getFont().deriveFont(nuevoBtn.getFont().getSize() + 4f));
            nuevoBtn.setIconTextGap(5);
            nuevoBtn.setMargin(new Insets(0, 0, 0, 12));
            nuevoBtn.setBackground(new Color(0xf7f8fa));
            nuevoBtn.setForeground(Color.black);
            nuevoBtn.setBorderPainted(false);
            nuevoBtn.setIcon(new ImageIcon(getClass().getResource("/images/newpass_small_black.png")));
            nuevoBtn.addActionListener(e -> nuevoBtnActionPerformed(e));

            //---- editarBtn ----
            editarBtn.setText("Editar");
            editarBtn.setFont(editarBtn.getFont().deriveFont(editarBtn.getFont().getSize() + 4f));
            editarBtn.setIconTextGap(5);
            editarBtn.setMargin(new Insets(0, 0, 0, 12));
            editarBtn.setBackground(new Color(0xf7f8fa));
            editarBtn.setForeground(Color.black);
            editarBtn.setBorderPainted(false);
            editarBtn.setIcon(new ImageIcon(getClass().getResource("/images/edit_small_black.png")));
            editarBtn.addActionListener(e -> editarBtnActionPerformed(e));

            //---- eliminarBtn ----
            eliminarBtn.setText("Eliminar");
            eliminarBtn.setFont(eliminarBtn.getFont().deriveFont(eliminarBtn.getFont().getSize() + 4f));
            eliminarBtn.setIconTextGap(5);
            eliminarBtn.setMargin(new Insets(0, 0, 0, 12));
            eliminarBtn.setBackground(new Color(0xf7f8fa));
            eliminarBtn.setForeground(Color.black);
            eliminarBtn.setBorderPainted(false);
            eliminarBtn.setIcon(new ImageIcon(getClass().getResource("/images/bin_small_black.png")));
            eliminarBtn.addActionListener(e -> eliminarBtnActionPerformed(e));

            //---- printBtn2 ----
            printBtn2.setText("Imprimir");
            printBtn2.setFont(printBtn2.getFont().deriveFont(printBtn2.getFont().getSize() + 4f));
            printBtn2.setIconTextGap(5);
            printBtn2.setMargin(new Insets(0, 0, 0, 12));
            printBtn2.setBackground(new Color(0xf7f8fa));
            printBtn2.setForeground(Color.black);
            printBtn2.setBorderPainted(false);
            printBtn2.setIcon(new ImageIcon(getClass().getResource("/images/printer.png")));
            printBtn2.addActionListener(e -> {
			printBtnActionPerformed(e);
			printBtnActionPerformed(e);
		});

            //---- importarBtn ----
            importarBtn.setText("Importar");
            importarBtn.setFont(importarBtn.getFont().deriveFont(importarBtn.getFont().getSize() + 4f));
            importarBtn.setIconTextGap(5);
            importarBtn.setMargin(new Insets(0, 0, 0, 12));
            importarBtn.setBackground(new Color(0xf7f8fa));
            importarBtn.setForeground(Color.black);
            importarBtn.setBorderPainted(false);
            importarBtn.setIcon(new ImageIcon(getClass().getResource("/images/import_small_black.png")));
            importarBtn.addActionListener(e -> importarBtnActionPerformed(e));

            //---- exportarBtn ----
            exportarBtn.setText("Exportar");
            exportarBtn.setFont(exportarBtn.getFont().deriveFont(exportarBtn.getFont().getSize() + 4f));
            exportarBtn.setIconTextGap(5);
            exportarBtn.setMargin(new Insets(0, 0, 0, 12));
            exportarBtn.setBackground(new Color(0xf7f8fa));
            exportarBtn.setForeground(Color.black);
            exportarBtn.setBorderPainted(false);
            exportarBtn.setIcon(new ImageIcon(getClass().getResource("/images/export_small_black.png")));
            exportarBtn.addActionListener(e -> exportarBtnActionPerformed(e));

            //---- label1 ----
            label1.setText("Ver. 6.0.0");
            label1.setFocusable(false);

            GroupLayout searchAndButtonsWrapperLayout = new GroupLayout(searchAndButtonsWrapper);
            searchAndButtonsWrapper.setLayout(searchAndButtonsWrapperLayout);
            searchAndButtonsWrapperLayout.setHorizontalGroup(
                searchAndButtonsWrapperLayout.createParallelGroup()
                    .addGroup(searchAndButtonsWrapperLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(searchAndButtonsWrapperLayout.createParallelGroup()
                            .addComponent(buscarField, GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
                            .addGroup(searchAndButtonsWrapperLayout.createSequentialGroup()
                                .addComponent(nuevoBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editarBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eliminarBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(printBtn2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(importarBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exportarBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 358, Short.MAX_VALUE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
            );
            searchAndButtonsWrapperLayout.setVerticalGroup(
                searchAndButtonsWrapperLayout.createParallelGroup()
                    .addGroup(searchAndButtonsWrapperLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(searchAndButtonsWrapperLayout.createParallelGroup()
                            .addComponent(exportarBtn, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuevoBtn)
                            .addComponent(editarBtn, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                            .addComponent(eliminarBtn, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                            .addComponent(printBtn2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                            .addComponent(importarBtn, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
        }
        contentPane.add(searchAndButtonsWrapper, BorderLayout.NORTH);

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
                            sociosTabla.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                            sociosTabla.setFont(sociosTabla.getFont().deriveFont(sociosTabla.getFont().getSize() + 4f));
                            sociosTabla.setSelectionBackground(new Color(0xc8e8ca));
                            sociosTabla.setGridColor(new Color(0x999999));
                            sociosTabla.setShowHorizontalLines(true);
                            sociosTabla.setFillsViewportHeight(true);
                            sociosTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
                                    .addComponent(sociosTablaPanel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
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
                                    .addComponent(toListaEsperaBtn, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
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
                                    .addComponent(toSociosBtn, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
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
                            listaEsperaTabla.setGridColor(new Color(0x999999));
                            listaEsperaTabla.setSelectionBackground(new Color(0xc8e8ca));
                            listaEsperaTabla.setShowHorizontalLines(true);
                            listaEsperaTabla.setFillsViewportHeight(true);
                            listaEsperaTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
                                    .addComponent(listaEsperaTablaPanel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
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
                    ingresosTabla.setFont(ingresosTabla.getFont().deriveFont(ingresosTabla.getFont().getSize() + 4f));
                    ingresosTabla.setSelectionBackground(new Color(0xc8e8ca));
                    ingresosTabla.setGridColor(new Color(0x999999));
                    ingresosTabla.setShowHorizontalLines(true);
                    ingresosTabla.setFillsViewportHeight(true);
                    ingresosTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    ingresosTabla.setModel(new IngresosTablaModel());
                    ingresosTabla.getTableHeader().setReorderingAllowed(false);
                    ingresosTabla.getTableHeader().setResizingAllowed(false);
                    ingresosTabla.setRowHeight(50);
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
                            .addComponent(ingresosTablaPanel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
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
                    gastosTabla.setFont(gastosTabla.getFont().deriveFont(gastosTabla.getFont().getSize() + 4f));
                    gastosTabla.setSelectionBackground(new Color(0xc8e8ca));
                    gastosTabla.setGridColor(new Color(0x999999));
                    gastosTabla.setShowHorizontalLines(true);
                    gastosTabla.setFillsViewportHeight(true);
                    gastosTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    gastosTabla.setModel(new GastosTablaModel());
                    gastosTabla.getTableHeader().setReorderingAllowed(false);
                    gastosTabla.getTableHeader().setResizingAllowed(false);
                    gastosTabla.setRowHeight(50);
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
                            .addComponent(gastosTablaPanel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
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

                //======== balanceCantidadesPanel ========
                {
                    balanceCantidadesPanel.setLayout(new GridLayout(4, 2));

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

                GroupLayout balancePanelLayout = new GroupLayout(balancePanel);
                balancePanel.setLayout(balancePanelLayout);
                balancePanelLayout.setHorizontalGroup(
                    balancePanelLayout.createParallelGroup()
                        .addComponent(balanceCantidadesPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
                );
                balancePanelLayout.setVerticalGroup(
                    balancePanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, balancePanelLayout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addComponent(balanceCantidadesPanel, GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE))
                );
            }
            tabbedPane1.addTab("BALANCE", balancePanel);

            //======== emailPanel ========
            {
                emailPanel.setLayout(new BorderLayout());

                //======== emailDataPanel ========
                {

                    //---- destinatarioLabel ----
                    destinatarioLabel.setText("Destinatario:");
                    destinatarioLabel.setFont(destinatarioLabel.getFont().deriveFont(destinatarioLabel.getFont().getSize() + 6f));

                    //---- asuntoLabel ----
                    asuntoLabel.setText("Asunto:");
                    asuntoLabel.setFont(asuntoLabel.getFont().deriveFont(asuntoLabel.getFont().getSize() + 6f));

                    GroupLayout emailDataPanelLayout = new GroupLayout(emailDataPanel);
                    emailDataPanel.setLayout(emailDataPanelLayout);
                    emailDataPanelLayout.setHorizontalGroup(
                        emailDataPanelLayout.createParallelGroup()
                            .addGroup(emailDataPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(destinatarioLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(destinatarioComboBox, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(asuntoLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(asuntoField, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                    emailDataPanelLayout.setVerticalGroup(
                        emailDataPanelLayout.createParallelGroup()
                            .addGroup(emailDataPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(asuntoLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addComponent(asuntoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(destinatarioLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addComponent(destinatarioComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    );
                }
                emailPanel.add(emailDataPanel, BorderLayout.NORTH);

                //======== emailBodyPanel ========
                {
                    emailBodyPanel.setLayout(new GridLayout());

                    //======== emailBodyWrapper ========
                    {

                        //======== bodyScrollPane ========
                        {

                            //---- bodyTextArea ----
                            bodyTextArea.setFont(bodyTextArea.getFont().deriveFont(bodyTextArea.getFont().getSize() + 6f));
                            bodyTextArea.setLineWrap(true);
                            bodyTextArea.setTabSize(4);
                            bodyTextArea.setWrapStyleWord(true);
                            bodyTextArea.addFocusListener(new FocusAdapter() {
                                @Override
                                public void focusGained(FocusEvent e) {
                                    bodyTextAreaFocusGained(e);
                                }
                                @Override
                                public void focusLost(FocusEvent e) {
                                    bodyTextAreaFocusLost(e);
                                }
                            });
                            bodyTextArea.setText("Escriba su mensaje...");
                            bodyScrollPane.setViewportView(bodyTextArea);
                        }

                        GroupLayout emailBodyWrapperLayout = new GroupLayout(emailBodyWrapper);
                        emailBodyWrapper.setLayout(emailBodyWrapperLayout);
                        emailBodyWrapperLayout.setHorizontalGroup(
                            emailBodyWrapperLayout.createParallelGroup()
                                .addGroup(emailBodyWrapperLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(bodyScrollPane)
                                    .addContainerGap())
                        );
                        emailBodyWrapperLayout.setVerticalGroup(
                            emailBodyWrapperLayout.createParallelGroup()
                                .addGroup(emailBodyWrapperLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(bodyScrollPane, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                    }
                    emailBodyPanel.add(emailBodyWrapper);
                }
                emailPanel.add(emailBodyPanel, BorderLayout.CENTER);

                //======== emailBtnsPanel ========
                {
                    emailBtnsPanel.setLayout(new GridLayout(1, 3));

                    //======== emailBtnsWrapper ========
                    {

                        //---- enviarBtn ----
                        enviarBtn.setText("Enviar");
                        enviarBtn.setFont(enviarBtn.getFont().deriveFont(enviarBtn.getFont().getSize() + 6f));

                        //---- borradorBtn ----
                        borradorBtn.setText("Guardar borrador");
                        borradorBtn.setFont(borradorBtn.getFont().deriveFont(borradorBtn.getFont().getSize() + 6f));

                        GroupLayout emailBtnsWrapperLayout = new GroupLayout(emailBtnsWrapper);
                        emailBtnsWrapper.setLayout(emailBtnsWrapperLayout);
                        emailBtnsWrapperLayout.setHorizontalGroup(
                            emailBtnsWrapperLayout.createParallelGroup()
                                .addGroup(emailBtnsWrapperLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(enviarBtn)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(borradorBtn)
                                    .addContainerGap(471, Short.MAX_VALUE))
                        );
                        emailBtnsWrapperLayout.setVerticalGroup(
                            emailBtnsWrapperLayout.createParallelGroup()
                                .addGroup(emailBtnsWrapperLayout.createSequentialGroup()
                                    .addGroup(emailBtnsWrapperLayout.createParallelGroup()
                                        .addComponent(enviarBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(borradorBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(comboBox1))
                                    .addContainerGap())
                        );
                    }
                    emailBtnsPanel.add(emailBtnsWrapper);
                }
                emailPanel.add(emailBtnsPanel, BorderLayout.SOUTH);
            }
            tabbedPane1.addTab("EMAIL", emailPanel);
        }
        contentPane.add(tabbedPane1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    private JPanel searchAndButtonsWrapper;
    protected static JTextField buscarField;
    protected static JButton nuevoBtn;
    protected static JButton editarBtn;
    protected static JButton eliminarBtn;
    protected static JButton printBtn2;
    protected static JButton importarBtn;
    protected static JButton exportarBtn;
    private JLabel label1;
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
    private JLabel destinatarioLabel;
    private JLabel asuntoLabel;
    private JTextField asuntoField;
    private JComboBox destinatarioComboBox;
    private JPanel emailBodyPanel;
    private JPanel emailBodyWrapper;
    private JScrollPane bodyScrollPane;
    private JTextArea bodyTextArea;
    private JPanel emailBtnsPanel;
    private JPanel emailBtnsWrapper;
    private JButton enviarBtn;
    private JButton borradorBtn;
    private JComboBox comboBox1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
