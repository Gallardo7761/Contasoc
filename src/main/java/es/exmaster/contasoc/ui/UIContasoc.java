/*
 * Created by JFormDesigner on Mon Oct 30 02:46:37 CET 2023
 */

package es.exmaster.contasoc.ui;

import java.awt.*;
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        tabbedPane1 = new JTabbedPane();
        sociosPanel = new JPanel();
        sociosTablaScrollPanel = new JScrollPane();
        sociosTabla = new JTable();
        ingresosPanel = new JPanel();
        gastosPanel = new JPanel();
        balancePanel = new JPanel();
        listaEsperaPanel = new JPanel();
        imprimirPanel = new JPanel();
        emailPanel = new JPanel();
        toolBar1 = new JToolBar();
        nuevoBtn = new JButton();
        editarBtn = new JButton();
        eliminarBtn = new JButton();
        importarBtn = new JButton();
        exportarBtn = new JButton();
        label1 = new JLabel();

        //======== this ========
        setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        setTitle("Huertos la Salud Bellavista");
        setIconImage(new ImageIcon(getClass().getResource("/images/newlogo_small.png")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        var contentPane = getContentPane();

        //======== tabbedPane1 ========
        {
            tabbedPane1.setTabPlacement(SwingConstants.LEFT);
            tabbedPane1.setFont(tabbedPane1.getFont().deriveFont(tabbedPane1.getFont().getSize() + 6f));

            //======== sociosPanel ========
            {

                //======== sociosTablaScrollPanel ========
                {

                    //---- sociosTabla ----
                    sociosTabla.setModel(new SociosTablaModel());
                    sociosTablaScrollPanel.setViewportView(sociosTabla);
                }

                GroupLayout sociosPanelLayout = new GroupLayout(sociosPanel);
                sociosPanel.setLayout(sociosPanelLayout);
                sociosPanelLayout.setHorizontalGroup(
                    sociosPanelLayout.createParallelGroup()
                        .addGroup(sociosPanelLayout.createParallelGroup()
                            .addComponent(sociosTablaScrollPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE))
                        .addGap(0, 760, Short.MAX_VALUE)
                );
                sociosPanelLayout.setVerticalGroup(
                    sociosPanelLayout.createParallelGroup()
                        .addGroup(sociosPanelLayout.createParallelGroup()
                            .addComponent(sociosTablaScrollPanel, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
                        .addGap(0, 524, Short.MAX_VALUE)
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
                        .addGap(0, 524, Short.MAX_VALUE)
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
                        .addGap(0, 524, Short.MAX_VALUE)
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
                        .addGap(0, 524, Short.MAX_VALUE)
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
                        .addGap(0, 524, Short.MAX_VALUE)
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
                        .addGap(0, 524, Short.MAX_VALUE)
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
                        .addGap(0, 524, Short.MAX_VALUE)
                );
            }
            tabbedPane1.addTab("Email", emailPanel);
        }

        //======== toolBar1 ========
        {

            //---- nuevoBtn ----
            nuevoBtn.setText("Nuevo");
            nuevoBtn.setFont(nuevoBtn.getFont().deriveFont(nuevoBtn.getFont().getSize() + 4f));
            toolBar1.add(nuevoBtn);

            //---- editarBtn ----
            editarBtn.setText("Editar");
            editarBtn.setFont(editarBtn.getFont().deriveFont(editarBtn.getFont().getSize() + 4f));
            toolBar1.add(editarBtn);

            //---- eliminarBtn ----
            eliminarBtn.setText("Eliminar");
            eliminarBtn.setFont(eliminarBtn.getFont().deriveFont(eliminarBtn.getFont().getSize() + 4f));
            toolBar1.add(eliminarBtn);

            //---- importarBtn ----
            importarBtn.setText("Importar");
            importarBtn.setFont(importarBtn.getFont().deriveFont(importarBtn.getFont().getSize() + 4f));
            toolBar1.add(importarBtn);

            //---- exportarBtn ----
            exportarBtn.setText("Exportar");
            exportarBtn.setFont(exportarBtn.getFont().deriveFont(exportarBtn.getFont().getSize() + 4f));
            toolBar1.add(exportarBtn);
        }

        //---- label1 ----
        label1.setText("v5.1.1");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 2f));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(toolBar1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(toolBar1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(tabbedPane1))
        );
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    private JTabbedPane tabbedPane1;
    private JPanel sociosPanel;
    private JScrollPane sociosTablaScrollPanel;
    private JTable sociosTabla;
    private JPanel ingresosPanel;
    private JPanel gastosPanel;
    private JPanel balancePanel;
    private JPanel listaEsperaPanel;
    private JPanel imprimirPanel;
    private JPanel emailPanel;
    private JToolBar toolBar1;
    private JButton nuevoBtn;
    private JButton editarBtn;
    private JButton eliminarBtn;
    private JButton importarBtn;
    private JButton exportarBtn;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
