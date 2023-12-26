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
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
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

                    //---- label1 ----
                    label1.setText("text");

                    //---- label2 ----
                    label2.setText("text");

                    //---- label3 ----
                    label3.setText("text");

                    //---- label4 ----
                    label4.setText("text");

                    GroupLayout cardKeyShortcutsLayout = new GroupLayout(cardKeyShortcuts);
                    cardKeyShortcuts.setLayout(cardKeyShortcutsLayout);
                    cardKeyShortcutsLayout.setHorizontalGroup(
                        cardKeyShortcutsLayout.createParallelGroup()
                            .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(cardKeyShortcutsLayout.createSequentialGroup()
                                .addGroup(cardKeyShortcutsLayout.createParallelGroup()
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                    );
                    cardKeyShortcutsLayout.setVerticalGroup(
                        cardKeyShortcutsLayout.createParallelGroup()
                            .addGroup(cardKeyShortcutsLayout.createSequentialGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 411, Short.MAX_VALUE))
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
    protected static JLabel label1;
    protected static JLabel label2;
    protected static JLabel label3;
    protected static JLabel label4;
    protected static JPanel cardSocios;
    protected static JPanel cardIngresos;
    protected static JPanel cardGastos;
    protected static JPanel cardBalance;
    protected static JPanel cardEmail;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
