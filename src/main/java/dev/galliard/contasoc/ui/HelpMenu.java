/*
 * Created by JFormDesigner on Tue Dec 26 07:14:59 CET 2023
 */

package dev.galliard.contasoc.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import net.miginfocom.swing.*;

/**
 * @author jomaa
 */
public class HelpMenu extends JFrame {
    public HelpMenu() {
        initComponents();
        inicializarTree();
        agregarElementoAlTree("Atajos de teclado");
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        wrapper = new JPanel();
        treePanel = new JScrollPane();
        tree = new JTree();
        separator1 = new JSeparator();
        helpWrapper = new JPanel();
        cardKeyShortcuts = new JPanel();
        NuevoDatoPanel = new JPanel();
        Ctrl = new JLabel();
        N = new JLabel();
        CtrlNLabel = new JLabel();
        NuevoDatoPanel2 = new JPanel();
        Ctrl2 = new JLabel();
        N2 = new JLabel();
        CtrlNLabel2 = new JLabel();
        NuevoDatoPanel3 = new JPanel();
        Ctrl3 = new JLabel();
        N3 = new JLabel();
        CtrlNLabel3 = new JLabel();
        NuevoDatoPanel4 = new JPanel();
        Ctrl4 = new JLabel();
        N4 = new JLabel();
        CtrlNLabel4 = new JLabel();
        NuevoDatoPanel5 = new JPanel();
        N5 = new JLabel();
        CtrlNLabel5 = new JLabel();
        cardSocios = new JPanel();
        cardIngresos = new JPanel();
        cardGastos = new JPanel();
        cardBalance = new JPanel();
        cardEmail = new JPanel();

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
                treePanel.setViewportView(tree);
            }
            wrapper.add(treePanel, "cell 0 0,width 145:145:145");

            //---- separator1 ----
            separator1.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
            wrapper.add(separator1, "pad 0,cell 1 0,gapx 6 6");

            //======== helpWrapper ========
            {
                helpWrapper.setPreferredSize(new Dimension(0, 0));
                helpWrapper.setLayout(new CardLayout());

                //======== cardKeyShortcuts ========
                {

                    //======== NuevoDatoPanel ========
                    {
                        NuevoDatoPanel.setLayout(new MigLayout(
                            "insets 0,hidemode 3",
                            // columns
                            "[grow,fill]" +
                            "[grow,fill]" +
                            "[grow,fill]",
                            // rows
                            "[grow,fill]"));

                        //---- Ctrl ----
                        Ctrl.setIcon(new ImageIcon(getClass().getResource("/images/help/ctrlBtn_icon.png")));
                        NuevoDatoPanel.add(Ctrl, "cell 0 0,width 32:32:32");

                        //---- N ----
                        N.setIcon(new ImageIcon(getClass().getResource("/images/help/nBtn_icon.png")));
                        N.setText(":");
                        N.setFont(N.getFont().deriveFont(N.getFont().getStyle() | Font.BOLD));
                        NuevoDatoPanel.add(N, "cell 1 0,width 30:30:30");

                        //---- CtrlNLabel ----
                        CtrlNLabel.setText("A\u00f1adir nuevo dato");
                        CtrlNLabel.setFont(CtrlNLabel.getFont().deriveFont(CtrlNLabel.getFont().getSize() + 4f));
                        NuevoDatoPanel.add(CtrlNLabel, "cell 2 0,width 208:208:208");
                    }

                    //======== NuevoDatoPanel2 ========
                    {
                        NuevoDatoPanel2.setLayout(new MigLayout(
                            "insets 0,hidemode 3",
                            // columns
                            "[grow,fill]" +
                            "[grow,fill]" +
                            "[grow,fill]",
                            // rows
                            "[grow,fill]"));

                        //---- Ctrl2 ----
                        Ctrl2.setIcon(new ImageIcon(getClass().getResource("/images/help/ctrlBtn_icon.png")));
                        NuevoDatoPanel2.add(Ctrl2, "cell 0 0,width 32:32:32");

                        //---- N2 ----
                        N2.setIcon(new ImageIcon(getClass().getResource("/images/help/eBtn_icon.png")));
                        N2.setText(":");
                        N2.setFont(N2.getFont().deriveFont(N2.getFont().getStyle() | Font.BOLD));
                        NuevoDatoPanel2.add(N2, "cell 1 0,width 30:30:30");

                        //---- CtrlNLabel2 ----
                        CtrlNLabel2.setText("Editar un dato");
                        CtrlNLabel2.setFont(CtrlNLabel2.getFont().deriveFont(CtrlNLabel2.getFont().getSize() + 4f));
                        NuevoDatoPanel2.add(CtrlNLabel2, "cell 2 0,width 208:208:208");
                    }

                    //======== NuevoDatoPanel3 ========
                    {
                        NuevoDatoPanel3.setLayout(new MigLayout(
                            "insets 0,hidemode 3",
                            // columns
                            "[grow,fill]" +
                            "[grow,fill]" +
                            "[grow,fill]",
                            // rows
                            "[grow,fill]"));

                        //---- Ctrl3 ----
                        Ctrl3.setIcon(new ImageIcon(getClass().getResource("/images/help/ctrlBtn_icon.png")));
                        NuevoDatoPanel3.add(Ctrl3, "cell 0 0,width 32:32:32");

                        //---- N3 ----
                        N3.setIcon(new ImageIcon(getClass().getResource("/images/help/dBtn_icon.png")));
                        N3.setText(":");
                        N3.setFont(N3.getFont().deriveFont(N3.getFont().getStyle() | Font.BOLD));
                        NuevoDatoPanel3.add(N3, "cell 1 0,width 30:30:30");

                        //---- CtrlNLabel3 ----
                        CtrlNLabel3.setText("Eliminar un dato");
                        CtrlNLabel3.setFont(CtrlNLabel3.getFont().deriveFont(CtrlNLabel3.getFont().getSize() + 4f));
                        NuevoDatoPanel3.add(CtrlNLabel3, "cell 2 0,width 208:208:208");
                    }

                    //======== NuevoDatoPanel4 ========
                    {
                        NuevoDatoPanel4.setLayout(new MigLayout(
                            "insets 0,hidemode 3",
                            // columns
                            "[grow,fill]" +
                            "[grow,fill]" +
                            "[grow,fill]",
                            // rows
                            "[grow,fill]"));

                        //---- Ctrl4 ----
                        Ctrl4.setIcon(new ImageIcon(getClass().getResource("/images/help/ctrlBtn_icon.png")));
                        NuevoDatoPanel4.add(Ctrl4, "cell 0 0,width 32:32:32");

                        //---- N4 ----
                        N4.setIcon(new ImageIcon(getClass().getResource("/images/help/pBtn_icon.png")));
                        N4.setText(":");
                        N4.setFont(N4.getFont().deriveFont(N4.getFont().getStyle() | Font.BOLD));
                        NuevoDatoPanel4.add(N4, "cell 1 0,width 30:30:30");

                        //---- CtrlNLabel4 ----
                        CtrlNLabel4.setText("Imprimir la tabla");
                        CtrlNLabel4.setFont(CtrlNLabel4.getFont().deriveFont(CtrlNLabel4.getFont().getSize() + 4f));
                        NuevoDatoPanel4.add(CtrlNLabel4, "cell 2 0,width 208:208:208");
                    }

                    //======== NuevoDatoPanel5 ========
                    {
                        NuevoDatoPanel5.setLayout(new MigLayout(
                            "insets 0,hidemode 3",
                            // columns
                            "[grow,fill]" +
                            "[grow,fill]",
                            // rows
                            "[grow,fill]"));

                        //---- N5 ----
                        N5.setIcon(new ImageIcon(getClass().getResource("/images/help/f1Btn_icon.png")));
                        N5.setText(":");
                        N5.setFont(N5.getFont().deriveFont(N5.getFont().getStyle() | Font.BOLD));
                        NuevoDatoPanel5.add(N5, "cell 0 0,width 30:30:30");

                        //---- CtrlNLabel5 ----
                        CtrlNLabel5.setText("Abrir este men\u00fa de ayuda");
                        CtrlNLabel5.setFont(CtrlNLabel5.getFont().deriveFont(CtrlNLabel5.getFont().getSize() + 4f));
                        NuevoDatoPanel5.add(CtrlNLabel5, "cell 0 0");
                    }

                    GroupLayout cardKeyShortcutsLayout = new GroupLayout(cardKeyShortcuts);
                    cardKeyShortcuts.setLayout(cardKeyShortcutsLayout);
                    cardKeyShortcutsLayout.setHorizontalGroup(
                        cardKeyShortcutsLayout.createParallelGroup()
                            .addGroup(cardKeyShortcutsLayout.createSequentialGroup()
                                .addGroup(cardKeyShortcutsLayout.createParallelGroup()
                                    .addComponent(NuevoDatoPanel, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NuevoDatoPanel2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4))
                            .addGroup(GroupLayout.Alignment.TRAILING, cardKeyShortcutsLayout.createSequentialGroup()
                                .addGroup(cardKeyShortcutsLayout.createParallelGroup()
                                    .addComponent(NuevoDatoPanel3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NuevoDatoPanel4, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NuevoDatoPanel5, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                    );
                    cardKeyShortcutsLayout.setVerticalGroup(
                        cardKeyShortcutsLayout.createParallelGroup()
                            .addGroup(cardKeyShortcutsLayout.createSequentialGroup()
                                .addComponent(NuevoDatoPanel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NuevoDatoPanel2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NuevoDatoPanel3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NuevoDatoPanel4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NuevoDatoPanel5, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 373, Short.MAX_VALUE))
                    );
                }
                helpWrapper.add(cardKeyShortcuts, "card1");

                //======== cardSocios ========
                {

                    GroupLayout cardSociosLayout = new GroupLayout(cardSocios);
                    cardSocios.setLayout(cardSociosLayout);
                    cardSociosLayout.setHorizontalGroup(
                        cardSociosLayout.createParallelGroup()
                            .addGap(0, 278, Short.MAX_VALUE)
                    );
                    cardSociosLayout.setVerticalGroup(
                        cardSociosLayout.createParallelGroup()
                            .addGap(0, 557, Short.MAX_VALUE)
                    );
                }
                helpWrapper.add(cardSocios, "card2");

                //======== cardIngresos ========
                {

                    GroupLayout cardIngresosLayout = new GroupLayout(cardIngresos);
                    cardIngresos.setLayout(cardIngresosLayout);
                    cardIngresosLayout.setHorizontalGroup(
                        cardIngresosLayout.createParallelGroup()
                            .addGap(0, 278, Short.MAX_VALUE)
                    );
                    cardIngresosLayout.setVerticalGroup(
                        cardIngresosLayout.createParallelGroup()
                            .addGap(0, 557, Short.MAX_VALUE)
                    );
                }
                helpWrapper.add(cardIngresos, "card3");

                //======== cardGastos ========
                {

                    GroupLayout cardGastosLayout = new GroupLayout(cardGastos);
                    cardGastos.setLayout(cardGastosLayout);
                    cardGastosLayout.setHorizontalGroup(
                        cardGastosLayout.createParallelGroup()
                            .addGap(0, 278, Short.MAX_VALUE)
                    );
                    cardGastosLayout.setVerticalGroup(
                        cardGastosLayout.createParallelGroup()
                            .addGap(0, 557, Short.MAX_VALUE)
                    );
                }
                helpWrapper.add(cardGastos, "card4");

                //======== cardBalance ========
                {

                    GroupLayout cardBalanceLayout = new GroupLayout(cardBalance);
                    cardBalance.setLayout(cardBalanceLayout);
                    cardBalanceLayout.setHorizontalGroup(
                        cardBalanceLayout.createParallelGroup()
                            .addGap(0, 278, Short.MAX_VALUE)
                    );
                    cardBalanceLayout.setVerticalGroup(
                        cardBalanceLayout.createParallelGroup()
                            .addGap(0, 557, Short.MAX_VALUE)
                    );
                }
                helpWrapper.add(cardBalance, "card5");

                //======== cardEmail ========
                {

                    GroupLayout cardEmailLayout = new GroupLayout(cardEmail);
                    cardEmail.setLayout(cardEmailLayout);
                    cardEmailLayout.setHorizontalGroup(
                        cardEmailLayout.createParallelGroup()
                            .addGap(0, 278, Short.MAX_VALUE)
                    );
                    cardEmailLayout.setVerticalGroup(
                        cardEmailLayout.createParallelGroup()
                            .addGap(0, 557, Short.MAX_VALUE)
                    );
                }
                helpWrapper.add(cardEmail, "card6");
            }
            wrapper.add(helpWrapper, "cell 3 0,width 278:278:278");
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
    protected static JPanel cardKeyShortcuts;
    protected static JPanel NuevoDatoPanel;
    protected static JLabel Ctrl;
    protected static JLabel N;
    protected static JLabel CtrlNLabel;
    protected static JPanel NuevoDatoPanel2;
    protected static JLabel Ctrl2;
    protected static JLabel N2;
    protected static JLabel CtrlNLabel2;
    protected static JPanel NuevoDatoPanel3;
    protected static JLabel Ctrl3;
    protected static JLabel N3;
    protected static JLabel CtrlNLabel3;
    protected static JPanel NuevoDatoPanel4;
    protected static JLabel Ctrl4;
    protected static JLabel N4;
    protected static JLabel CtrlNLabel4;
    protected static JPanel NuevoDatoPanel5;
    protected static JLabel N5;
    protected static JLabel CtrlNLabel5;
    protected static JPanel cardSocios;
    protected static JPanel cardIngresos;
    protected static JPanel cardGastos;
    protected static JPanel cardBalance;
    protected static JPanel cardEmail;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
