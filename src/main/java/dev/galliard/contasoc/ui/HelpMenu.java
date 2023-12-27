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
    public HelpMenu() {
        initComponents();
        inicializarTree();
        agregarElementoAlTree("Atajos teclado");
        agregarElementoAlTree("Socios");
        agregarElementoAlTree("Ingresos");
        agregarElementoAlTree("Gastos");
        agregarElementoAlTree("Balance");
        agregarElementoAlTree("Email");
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
        label2 = new JLabel();
        panel1 = new JPanel();
        cardIngresos = new JPanel();
        label3 = new JLabel();
        panel2 = new JPanel();
        cardGastos = new JPanel();
        label4 = new JLabel();
        panel3 = new JPanel();
        cardBalance = new JPanel();
        label5 = new JLabel();
        panel4 = new JPanel();
        cardEmail = new JPanel();
        label6 = new JLabel();
        panel5 = new JPanel();

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

                    //---- atajosTitle ----
                    atajosTitle.setText("ATAJOS");
                    atajosTitle.setVerticalAlignment(SwingConstants.TOP);
                    atajosTitle.setFont(atajosTitle.getFont().deriveFont(atajosTitle.getFont().getStyle() | Font.BOLD, atajosTitle.getFont().getSize() + 24f));

                    GroupLayout cardKeyShortcutsLayout = new GroupLayout(cardKeyShortcuts);
                    cardKeyShortcuts.setLayout(cardKeyShortcutsLayout);
                    cardKeyShortcutsLayout.setHorizontalGroup(
                        cardKeyShortcutsLayout.createParallelGroup()
                            .addGroup(cardKeyShortcutsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(cardKeyShortcutsLayout.createParallelGroup()
                                    .addComponent(atajosTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(GroupLayout.Alignment.TRAILING, cardKeyShortcutsLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(cardKeyShortcutsLayout.createParallelGroup()
                                            .addGroup(cardKeyShortcutsLayout.createParallelGroup()
                                                .addComponent(nuevoDato, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(editarDato, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(cardKeyShortcutsLayout.createParallelGroup()
                                                .addComponent(eliminarDato, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(imprimirTabla, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(menuAyuda, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap())
                    );
                    cardKeyShortcutsLayout.setVerticalGroup(
                        cardKeyShortcutsLayout.createParallelGroup()
                            .addGroup(cardKeyShortcutsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(atajosTitle, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nuevoDato, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editarDato, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eliminarDato, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(imprimirTabla, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(menuAyuda, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 311, Short.MAX_VALUE))
                    );
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

                    //---- label2 ----
                    label2.setText("SOCIOS");
                    label2.setVerticalAlignment(SwingConstants.TOP);
                    label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 24f));
                    cardSocios.add(label2, "pad 10 10 -10 -10,cell 0 0,aligny top,growy 0,height 70:70:70");

                    //======== panel1 ========
                    {

                        GroupLayout panel1Layout = new GroupLayout(panel1);
                        panel1.setLayout(panel1Layout);
                        panel1Layout.setHorizontalGroup(
                            panel1Layout.createParallelGroup()
                                .addGap(0, 283, Short.MAX_VALUE)
                        );
                        panel1Layout.setVerticalGroup(
                            panel1Layout.createParallelGroup()
                                .addGap(0, 480, Short.MAX_VALUE)
                        );
                    }
                    cardSocios.add(panel1, "cell 0 1,height 480:480:480");
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

                    //---- label3 ----
                    label3.setText("INGRESOS");
                    label3.setVerticalAlignment(SwingConstants.TOP);
                    label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 24f));
                    cardIngresos.add(label3, "pad 10 10 -10 -10,cell 0 0,aligny top,growy 0,height 70:70:70");

                    //======== panel2 ========
                    {

                        GroupLayout panel2Layout = new GroupLayout(panel2);
                        panel2.setLayout(panel2Layout);
                        panel2Layout.setHorizontalGroup(
                            panel2Layout.createParallelGroup()
                                .addGap(0, 283, Short.MAX_VALUE)
                        );
                        panel2Layout.setVerticalGroup(
                            panel2Layout.createParallelGroup()
                                .addGap(0, 480, Short.MAX_VALUE)
                        );
                    }
                    cardIngresos.add(panel2, "cell 0 1,height 480:480:480");
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

                    //---- label4 ----
                    label4.setText("GASTOS");
                    label4.setVerticalAlignment(SwingConstants.TOP);
                    label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD, label4.getFont().getSize() + 24f));
                    cardGastos.add(label4, "pad 10 10 -10 -10,cell 0 0,aligny top,growy 0,height 70:70:70");

                    //======== panel3 ========
                    {

                        GroupLayout panel3Layout = new GroupLayout(panel3);
                        panel3.setLayout(panel3Layout);
                        panel3Layout.setHorizontalGroup(
                            panel3Layout.createParallelGroup()
                                .addGap(0, 283, Short.MAX_VALUE)
                        );
                        panel3Layout.setVerticalGroup(
                            panel3Layout.createParallelGroup()
                                .addGap(0, 480, Short.MAX_VALUE)
                        );
                    }
                    cardGastos.add(panel3, "cell 0 1,height 480:480:480");
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

                    //---- label5 ----
                    label5.setText("BALANCE");
                    label5.setVerticalAlignment(SwingConstants.TOP);
                    label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.BOLD, label5.getFont().getSize() + 24f));
                    cardBalance.add(label5, "pad 10 10 -10 -10,cell 0 0,aligny top,growy 0,height 70:70:70");

                    //======== panel4 ========
                    {

                        GroupLayout panel4Layout = new GroupLayout(panel4);
                        panel4.setLayout(panel4Layout);
                        panel4Layout.setHorizontalGroup(
                            panel4Layout.createParallelGroup()
                                .addGap(0, 283, Short.MAX_VALUE)
                        );
                        panel4Layout.setVerticalGroup(
                            panel4Layout.createParallelGroup()
                                .addGap(0, 480, Short.MAX_VALUE)
                        );
                    }
                    cardBalance.add(panel4, "cell 0 1,height 480:480:480");
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

                    //---- label6 ----
                    label6.setText("EMAIL");
                    label6.setVerticalAlignment(SwingConstants.TOP);
                    label6.setFont(label6.getFont().deriveFont(label6.getFont().getStyle() | Font.BOLD, label6.getFont().getSize() + 24f));
                    cardEmail.add(label6, "pad 10 10 -10 -10,cell 0 0,aligny top,growy 0,height 70:70:70");

                    //======== panel5 ========
                    {

                        GroupLayout panel5Layout = new GroupLayout(panel5);
                        panel5.setLayout(panel5Layout);
                        panel5Layout.setHorizontalGroup(
                            panel5Layout.createParallelGroup()
                                .addGap(0, 283, Short.MAX_VALUE)
                        );
                        panel5Layout.setVerticalGroup(
                            panel5Layout.createParallelGroup()
                                .addGap(0, 480, Short.MAX_VALUE)
                        );
                    }
                    cardEmail.add(panel5, "cell 0 1,height 480:480:480");
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
    protected static JLabel label2;
    protected static JPanel panel1;
    protected static JPanel cardIngresos;
    protected static JLabel label3;
    protected static JPanel panel2;
    protected static JPanel cardGastos;
    protected static JLabel label4;
    protected static JPanel panel3;
    protected static JPanel cardBalance;
    protected static JLabel label5;
    protected static JPanel panel4;
    protected static JPanel cardEmail;
    protected static JLabel label6;
    protected static JPanel panel5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
