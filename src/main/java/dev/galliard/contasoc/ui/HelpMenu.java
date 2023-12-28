/*
 * Created by JFormDesigner on Tue Dec 26 07:14:59 CET 2023
 */

package dev.galliard.contasoc.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import net.miginfocom.swing.*;

/**
 * @author jomaa
 */
public class HelpMenu extends JFrame {
    private static HelpMenu instance = null;
    private HelpMenu() {
        initComponents();
        inicializarTree();
        agregarElementoAlTree("Atajos teclado");
        agregarElementoAlTree("Socios");
        agregarElementoAlTree("Ingresos");
        agregarElementoAlTree("Gastos");
        agregarElementoAlTree("Balance");
        agregarElementoAlTree("Email");
    }

    public static HelpMenu getInstance() {
        if (instance == null) {
            instance = new HelpMenu();
        }
        return instance;
    }

    private void inicializarTree() {
        DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Guía de ayuda"); // Nodo raíz

        treeModel.setRoot(root);
        treeModel.nodeStructureChanged(root);
        treeModel.reload();
    }

    private void agregarElementoAlTree(String nombreElemento) {
        DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();

        DefaultMutableTreeNode nuevoElemento = new DefaultMutableTreeNode(nombreElemento);
        root.add(nuevoElemento);

        treeModel.nodeStructureChanged(root);
        treeModel.reload();
    }

    private void treeMouseClicked(MouseEvent e) {
        TreePath selectedPath = tree.getSelectionPath();

        if (selectedPath != null) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();

            if (selectedNode.isRoot()) {
                CardLayout cl = (CardLayout) (helpWrapper.getLayout());
                cl.show(helpWrapper, "guide");
            } else {
                if (selectedNode.toString().equals("Atajos teclado")) {
                    CardLayout cl = (CardLayout) (helpWrapper.getLayout());
                    cl.show(helpWrapper, "shortcuts");
                } else if (selectedNode.toString().equals("Socios")) {
                    CardLayout cl = (CardLayout) (helpWrapper.getLayout());
                    cl.show(helpWrapper, "socios");
                } else if (selectedNode.toString().equals("Ingresos")) {
                    CardLayout cl = (CardLayout) (helpWrapper.getLayout());
                    cl.show(helpWrapper, "ingresos");
                } else if (selectedNode.toString().equals("Gastos")) {
                    CardLayout cl = (CardLayout) (helpWrapper.getLayout());
                    cl.show(helpWrapper, "gastos");
                } else if (selectedNode.toString().equals("Balance")) {
                    CardLayout cl = (CardLayout) (helpWrapper.getLayout());
                    cl.show(helpWrapper, "balance");
                } else if (selectedNode.toString().equals("Email")) {
                    CardLayout cl = (CardLayout) (helpWrapper.getLayout());
                    cl.show(helpWrapper, "email");
                }
            }
        } else {

        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        wrapper = new JPanel();
        treePanel = new JScrollPane();
        tree = new JTree();
        separator1 = new JSeparator();
        helpWrapper = new JPanel();
        cardGuide = new JPanel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        cardKeyShortcuts = new JPanel();
        nuevoDato = new JPanel();
        Ctrl = new JLabel();
        N = new JLabel();
        CtrlNLabel = new JLabel();
        editarDato = new JPanel();
        Ctrl2 = new JLabel();
        E = new JLabel();
        CtrlE = new JLabel();
        eliminarDato = new JPanel();
        Ctrl3 = new JLabel();
        D = new JLabel();
        CtrlD = new JLabel();
        imprimirTabla = new JPanel();
        Ctrl4 = new JLabel();
        P = new JLabel();
        CtrlP = new JLabel();
        menuAyuda = new JPanel();
        F1 = new JLabel();
        F1Label = new JLabel();
        atajosTitle = new JLabel();
        cardSocios = new JPanel();
        sociosLabel = new JLabel();
        sociosPanel = new JPanel();
        cardIngresos = new JPanel();
        ingresosLabel = new JLabel();
        ingresosPanel = new JPanel();
        cardGastos = new JPanel();
        gastosLabel = new JLabel();
        gastosPanel = new JPanel();
        cardBalance = new JPanel();
        balanceLabel = new JLabel();
        balancePanel = new JPanel();
        cardEmail = new JPanel();
        emailLabel = new JLabel();
        emailPanel = new JPanel();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/images/logohuerto_small.png")).getImage());
        setTitle("Men\u00fa de ayuda de Contasoc");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();

        //======== wrapper ========
        {
            wrapper.setLayout(new MigLayout(
                "insets 0,hidemode 3,gap 0 0",
                // columns
                "[grow,fill]" +
                "[fill]" +
                "[fill]" +
                "[grow,fill]" +
                "[grow,fill]",
                // rows
                "[grow,fill]"));

            //======== treePanel ========
            {
                treePanel.setPreferredSize(new Dimension(120, 0));
                treePanel.setBorder(null);
                treePanel.setMinimumSize(new Dimension(120, 15));

                //---- tree ----
                tree.setFont(tree.getFont().deriveFont(tree.getFont().getSize() + 4f));
                tree.setFocusable(false);
                tree.setBorder(null);
                tree.setExpandsSelectedPaths(false);
                tree.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        treeMouseClicked(e);
                    }
                });
                tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
                treePanel.setViewportView(tree);
            }
            wrapper.add(treePanel, "cell 0 0,width 140:140:140");

            //---- separator1 ----
            separator1.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
            wrapper.add(separator1, "pad 0,cell 1 0,gapx 6 6");

            //======== helpWrapper ========
            {
                helpWrapper.setPreferredSize(new Dimension(0, 0));
                helpWrapper.setLayout(new CardLayout());

                //======== cardGuide ========
                {
                    cardGuide.setLayout(new MigLayout(
                        "insets 0,hidemode 3",
                        // columns
                        "[grow,fill]",
                        // rows
                        "[grow,fill]" +
                        "[grow,fill]"));

                    //---- label1 ----
                    label1.setIcon(new ImageIcon(getClass().getResource("/images/logohuerto_256.png")));
                    label1.setHorizontalAlignment(SwingConstants.CENTER);
                    cardGuide.add(label1, "cell 0 0");

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setBorder(null);

                        //---- textArea1 ----
                        textArea1.setEditable(false);
                        textArea1.setFont(textArea1.getFont().deriveFont(textArea1.getFont().getSize() + 3f));
                        textArea1.setTabSize(4);
                        textArea1.setBorder(null);
                        textArea1.setText("Bienvenido a la p\u00e1gina de ayuda de Contasoc.  Si est\u00e1s aqu\u00ed es porque necesitas ayuda con alguna de las caracter\u00edsticas de la app. Bien, pues aqu\u00ed podr\u00e1s encontrar desde los atajos de tecla para realizar diversas funciones en los distintos apartados hasta que hace cada apartado detalladamente explicado y hasta con im\u00e1genes. Espero que esta p\u00e1gina te sirva de ayuda. ");
                        textArea1.setLineWrap(true);
                        textArea1.setWrapStyleWord(true);
                        textArea1.setFocusable(false);
                        textArea1.setRequestFocusEnabled(false);
                        scrollPane1.setViewportView(textArea1);
                    }
                    cardGuide.add(scrollPane1, "pad 10 10 -10 -10,cell 0 1");
                }
                helpWrapper.add(cardGuide, "guide");

                //======== cardKeyShortcuts ========
                {
                    cardKeyShortcuts.setLayout(new MigLayout(
                        "insets 0,hidemode 3,gap 0 5",
                        // columns
                        "[fill]",
                        // rows
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]"));

                    //======== nuevoDato ========
                    {
                        nuevoDato.setLayout(new MigLayout(
                            "insets 0,hidemode 3",
                            // columns
                            "[grow,fill]" +
                            "[grow,fill]" +
                            "[grow,fill]",
                            // rows
                            "[grow,fill]"));

                        //---- Ctrl ----
                        Ctrl.setIcon(new ImageIcon(getClass().getResource("/images/help/ctrlBtn_icon.png")));
                        nuevoDato.add(Ctrl, "cell 0 0,width 32:32:32");

                        //---- N ----
                        N.setIcon(new ImageIcon(getClass().getResource("/images/help/nBtn_icon.png")));
                        N.setText(":");
                        N.setFont(N.getFont().deriveFont(N.getFont().getStyle() | Font.BOLD));
                        nuevoDato.add(N, "cell 1 0,width 30:30:30");

                        //---- CtrlNLabel ----
                        CtrlNLabel.setText("A\u00f1adir nuevo dato");
                        CtrlNLabel.setFont(CtrlNLabel.getFont().deriveFont(CtrlNLabel.getFont().getSize() + 4f));
                        nuevoDato.add(CtrlNLabel, "cell 2 0,width 208:208:208");
                    }
                    cardKeyShortcuts.add(nuevoDato, "pad 0 5 0 0,cell 0 1");

                    //======== editarDato ========
                    {
                        editarDato.setLayout(new MigLayout(
                            "insets 0,hidemode 3",
                            // columns
                            "[grow,fill]" +
                            "[grow,fill]" +
                            "[grow,fill]",
                            // rows
                            "[grow,fill]"));

                        //---- Ctrl2 ----
                        Ctrl2.setIcon(new ImageIcon(getClass().getResource("/images/help/ctrlBtn_icon.png")));
                        editarDato.add(Ctrl2, "cell 0 0,width 32:32:32");

                        //---- E ----
                        E.setIcon(new ImageIcon(getClass().getResource("/images/help/eBtn_icon.png")));
                        E.setText(":");
                        E.setFont(E.getFont().deriveFont(E.getFont().getStyle() | Font.BOLD));
                        editarDato.add(E, "cell 1 0,width 30:30:30");

                        //---- CtrlE ----
                        CtrlE.setText("Editar un dato");
                        CtrlE.setFont(CtrlE.getFont().deriveFont(CtrlE.getFont().getSize() + 4f));
                        editarDato.add(CtrlE, "cell 2 0,width 208:208:208");
                    }
                    cardKeyShortcuts.add(editarDato, "pad 0 5 0 0,cell 0 2");

                    //======== eliminarDato ========
                    {
                        eliminarDato.setLayout(new MigLayout(
                            "insets 0,hidemode 3",
                            // columns
                            "[grow,fill]" +
                            "[grow,fill]" +
                            "[grow,fill]",
                            // rows
                            "[grow,fill]"));

                        //---- Ctrl3 ----
                        Ctrl3.setIcon(new ImageIcon(getClass().getResource("/images/help/ctrlBtn_icon.png")));
                        eliminarDato.add(Ctrl3, "cell 0 0,width 32:32:32");

                        //---- D ----
                        D.setIcon(new ImageIcon(getClass().getResource("/images/help/dBtn_icon.png")));
                        D.setText(":");
                        D.setFont(D.getFont().deriveFont(D.getFont().getStyle() | Font.BOLD));
                        eliminarDato.add(D, "cell 1 0,width 30:30:30");

                        //---- CtrlD ----
                        CtrlD.setText("Eliminar un dato");
                        CtrlD.setFont(CtrlD.getFont().deriveFont(CtrlD.getFont().getSize() + 4f));
                        eliminarDato.add(CtrlD, "cell 2 0,width 208:208:208");
                    }
                    cardKeyShortcuts.add(eliminarDato, "pad 0 5 0 0,cell 0 3");

                    //======== imprimirTabla ========
                    {
                        imprimirTabla.setLayout(new MigLayout(
                            "insets 0,hidemode 3",
                            // columns
                            "[grow,fill]" +
                            "[grow,fill]" +
                            "[grow,fill]",
                            // rows
                            "[grow,fill]"));

                        //---- Ctrl4 ----
                        Ctrl4.setIcon(new ImageIcon(getClass().getResource("/images/help/ctrlBtn_icon.png")));
                        imprimirTabla.add(Ctrl4, "cell 0 0,width 32:32:32");

                        //---- P ----
                        P.setIcon(new ImageIcon(getClass().getResource("/images/help/pBtn_icon.png")));
                        P.setText(":");
                        P.setFont(P.getFont().deriveFont(P.getFont().getStyle() | Font.BOLD));
                        imprimirTabla.add(P, "cell 1 0,width 30:30:30");

                        //---- CtrlP ----
                        CtrlP.setText("Imprimir la tabla");
                        CtrlP.setFont(CtrlP.getFont().deriveFont(CtrlP.getFont().getSize() + 4f));
                        imprimirTabla.add(CtrlP, "cell 2 0,width 208:208:208");
                    }
                    cardKeyShortcuts.add(imprimirTabla, "pad 0 5 0 0,cell 0 4");

                    //======== menuAyuda ========
                    {
                        menuAyuda.setLayout(new MigLayout(
                            "insets 0,hidemode 3",
                            // columns
                            "[grow,fill]" +
                            "[grow,fill]",
                            // rows
                            "[grow,fill]"));

                        //---- F1 ----
                        F1.setIcon(new ImageIcon(getClass().getResource("/images/help/f1Btn_icon.png")));
                        F1.setText(":");
                        F1.setFont(F1.getFont().deriveFont(F1.getFont().getStyle() | Font.BOLD));
                        menuAyuda.add(F1, "cell 0 0,width 30:30:30");

                        //---- F1Label ----
                        F1Label.setText("Abrir este men\u00fa de ayuda");
                        F1Label.setFont(F1Label.getFont().deriveFont(F1Label.getFont().getSize() + 4f));
                        menuAyuda.add(F1Label, "cell 0 0");
                    }
                    cardKeyShortcuts.add(menuAyuda, "pad 0 5 0 0,cell 0 5");

                    //---- atajosTitle ----
                    atajosTitle.setText("ATAJOS");
                    atajosTitle.setVerticalAlignment(SwingConstants.TOP);
                    atajosTitle.setFont(atajosTitle.getFont().deriveFont(atajosTitle.getFont().getStyle() | Font.BOLD, atajosTitle.getFont().getSize() + 24f));
                    cardKeyShortcuts.add(atajosTitle, "pad 10 10 -10 -10,cell 0 0,aligny top,growy 0,height 70:70:70");
                }
                helpWrapper.add(cardKeyShortcuts, "shortcuts");

                //======== cardSocios ========
                {
                    cardSocios.setLayout(new MigLayout(
                        "insets 0,hidemode 3",
                        // columns
                        "[grow,fill]",
                        // rows
                        "[grow,fill]" +
                        "[grow,fill]"));

                    //---- sociosLabel ----
                    sociosLabel.setText("SOCIOS");
                    sociosLabel.setVerticalAlignment(SwingConstants.TOP);
                    sociosLabel.setFont(sociosLabel.getFont().deriveFont(sociosLabel.getFont().getStyle() | Font.BOLD, sociosLabel.getFont().getSize() + 24f));
                    cardSocios.add(sociosLabel, "pad 10 10 -10 -10,cell 0 0,aligny top,growy 0,height 70:70:70");

                    //======== sociosPanel ========
                    {
                        sociosPanel.setLayout(new BorderLayout());
                    }
                    cardSocios.add(sociosPanel, "cell 0 1,height 480:480:480");
                }
                helpWrapper.add(cardSocios, "socios");

                //======== cardIngresos ========
                {
                    cardIngresos.setLayout(new MigLayout(
                        "insets 0,hidemode 3",
                        // columns
                        "[grow,fill]",
                        // rows
                        "[grow,fill]" +
                        "[grow,fill]"));

                    //---- ingresosLabel ----
                    ingresosLabel.setText("INGRESOS");
                    ingresosLabel.setVerticalAlignment(SwingConstants.TOP);
                    ingresosLabel.setFont(ingresosLabel.getFont().deriveFont(ingresosLabel.getFont().getStyle() | Font.BOLD, ingresosLabel.getFont().getSize() + 24f));
                    cardIngresos.add(ingresosLabel, "pad 10 10 -10 -10,cell 0 0,aligny top,growy 0,height 70:70:70");

                    //======== ingresosPanel ========
                    {
                        ingresosPanel.setLayout(new BorderLayout());
                    }
                    cardIngresos.add(ingresosPanel, "cell 0 1,height 480:480:480");
                }
                helpWrapper.add(cardIngresos, "ingresos");

                //======== cardGastos ========
                {
                    cardGastos.setLayout(new MigLayout(
                        "insets 0,hidemode 3",
                        // columns
                        "[grow,fill]",
                        // rows
                        "[grow,fill]" +
                        "[grow,fill]"));

                    //---- gastosLabel ----
                    gastosLabel.setText("GASTOS");
                    gastosLabel.setVerticalAlignment(SwingConstants.TOP);
                    gastosLabel.setFont(gastosLabel.getFont().deriveFont(gastosLabel.getFont().getStyle() | Font.BOLD, gastosLabel.getFont().getSize() + 24f));
                    cardGastos.add(gastosLabel, "pad 10 10 -10 -10,cell 0 0,aligny top,growy 0,height 70:70:70");

                    //======== gastosPanel ========
                    {
                        gastosPanel.setLayout(new BorderLayout());
                    }
                    cardGastos.add(gastosPanel, "cell 0 1,height 480:480:480");
                }
                helpWrapper.add(cardGastos, "gastos");

                //======== cardBalance ========
                {
                    cardBalance.setLayout(new MigLayout(
                        "insets 0,hidemode 3",
                        // columns
                        "[grow,fill]",
                        // rows
                        "[grow,fill]" +
                        "[grow,fill]"));

                    //---- balanceLabel ----
                    balanceLabel.setText("BALANCE");
                    balanceLabel.setVerticalAlignment(SwingConstants.TOP);
                    balanceLabel.setFont(balanceLabel.getFont().deriveFont(balanceLabel.getFont().getStyle() | Font.BOLD, balanceLabel.getFont().getSize() + 24f));
                    cardBalance.add(balanceLabel, "pad 10 10 -10 -10,cell 0 0,aligny top,growy 0,height 70:70:70");

                    //======== balancePanel ========
                    {
                        balancePanel.setLayout(new BorderLayout());
                    }
                    cardBalance.add(balancePanel, "cell 0 1,height 480:480:480");
                }
                helpWrapper.add(cardBalance, "balance");

                //======== cardEmail ========
                {
                    cardEmail.setLayout(new MigLayout(
                        "insets 0,hidemode 3",
                        // columns
                        "[grow,fill]",
                        // rows
                        "[grow,fill]" +
                        "[grow,fill]"));

                    //---- emailLabel ----
                    emailLabel.setText("EMAIL");
                    emailLabel.setVerticalAlignment(SwingConstants.TOP);
                    emailLabel.setFont(emailLabel.getFont().deriveFont(emailLabel.getFont().getStyle() | Font.BOLD, emailLabel.getFont().getSize() + 24f));
                    cardEmail.add(emailLabel, "pad 10 10 -10 -10,cell 0 0,aligny top,growy 0,height 70:70:70");

                    //======== emailPanel ========
                    {
                        emailPanel.setLayout(new BorderLayout());
                    }
                    cardEmail.add(emailPanel, "cell 0 1,height 480:480:480");
                }
                helpWrapper.add(cardEmail, "email");
            }
            wrapper.add(helpWrapper, "cell 3 0,width 283:283:283");
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(wrapper, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(wrapper, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        setSize(450, 600);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JPanel wrapper;
    protected static JScrollPane treePanel;
    protected static JTree tree;
    protected static JSeparator separator1;
    protected static JPanel helpWrapper;
    protected static JPanel cardGuide;
    protected static JLabel label1;
    protected static JScrollPane scrollPane1;
    protected static JTextArea textArea1;
    protected static JPanel cardKeyShortcuts;
    protected static JPanel nuevoDato;
    protected static JLabel Ctrl;
    protected static JLabel N;
    protected static JLabel CtrlNLabel;
    protected static JPanel editarDato;
    protected static JLabel Ctrl2;
    protected static JLabel E;
    protected static JLabel CtrlE;
    protected static JPanel eliminarDato;
    protected static JLabel Ctrl3;
    protected static JLabel D;
    protected static JLabel CtrlD;
    protected static JPanel imprimirTabla;
    protected static JLabel Ctrl4;
    protected static JLabel P;
    protected static JLabel CtrlP;
    protected static JPanel menuAyuda;
    protected static JLabel F1;
    protected static JLabel F1Label;
    protected static JLabel atajosTitle;
    protected static JPanel cardSocios;
    protected static JLabel sociosLabel;
    protected static JPanel sociosPanel;
    protected static JPanel cardIngresos;
    protected static JLabel ingresosLabel;
    protected static JPanel ingresosPanel;
    protected static JPanel cardGastos;
    protected static JLabel gastosLabel;
    protected static JPanel gastosPanel;
    protected static JPanel cardBalance;
    protected static JLabel balanceLabel;
    protected static JPanel balancePanel;
    protected static JPanel cardEmail;
    protected static JLabel emailLabel;
    protected static JPanel emailPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
