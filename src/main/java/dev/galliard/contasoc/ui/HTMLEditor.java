/*
 * Created by JFormDesigner on Tue Feb 20 03:58:42 CET 2024
 */

package dev.galliard.contasoc.ui;

import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author jomaa
 */
public class HTMLEditor extends JPanel {
    public HTMLEditor() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        scrollPane = new JScrollPane();
        htmlPane = new JTextPane();

        //======== this ========
        setLayout(new MigLayout(
            "insets 0,hidemode 3,gapy 0",
            // columns
            "[grow,fill]",
            // rows
            "[grow,fill]"));

        //======== scrollPane ========
        {

            //---- htmlPane ----
            htmlPane.setBorder(null);
            scrollPane.setViewportView(htmlPane);
        }
        add(scrollPane, "cell 0 0");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    private JScrollPane scrollPane;
    private JTextPane htmlPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
