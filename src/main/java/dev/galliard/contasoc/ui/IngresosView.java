/*
 * Created by JFormDesigner on Mon Dec 25 08:16:54 CET 2023
 */

package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.ui.tablemodels.IngresosTablaModel;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author jomaa
 */
public class IngresosView extends JFrame {
    public IngresosView() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        ingresosTablaPanel = new JScrollPane();
        ingresosTabla = new JTable();

        //======== this ========
        setTitle("Ingresos de {socio}");
        setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/images/logohuerto_small.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== ingresosTablaPanel ========
        {
            ingresosTablaPanel.setBorder(null);

            //---- ingresosTabla ----
            ingresosTabla.setFont(ingresosTabla.getFont().deriveFont(ingresosTabla.getFont().getSize() + 4f));
            ingresosTabla.setBorder(null);
            ingresosTabla.setGridColor(new Color(0x999999));
            ingresosTabla.setShowHorizontalLines(true);
            ingresosTabla.setModel(new IngresosTablaModel());
            ingresosTabla.getTableHeader().setReorderingAllowed(false);
            ingresosTabla.getTableHeader().setResizingAllowed(false);
            ingresosTabla.setRowHeight(50);
            GUIManager.setColumnWidths(ingresosTabla, 
                new int[] {70,120,250,80,70});
            ingresosTabla.setDefaultRenderer(String.class, new DateCellRenderer());
            ingresosTablaPanel.setViewportView(ingresosTabla);
        }
        contentPane.add(ingresosTablaPanel, BorderLayout.CENTER);
        setSize(480, 400);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JScrollPane ingresosTablaPanel;
    protected static JTable ingresosTabla;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
