/*
 * Created by JFormDesigner on Thu Feb 22 23:44:53 CET 2024
 */

package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.database.DBUtils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author jomaa
 */
public class PasswordDialog extends JFrame {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PasswordDialog() {
        initComponents();
        setActions();
    }

    private void aceptarBtn(ActionEvent e) {
        value = new String(passwordField.getPassword());
        if(DBUtils.connect(value)) {
            Contasoc.latch.countDown();
            dispose();
        }
    }

    protected void setActions() {
        javax.swing.Action nuevoAction = new AbstractAction("Enter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                aceptarBtn(e);
            }
        };
        JPanel contentPane = (JPanel) this.getContentPane();
        KeyStroke nuevoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(nuevoKeyStroke, "Enter");
        contentPane.getActionMap().put("Enter", nuevoAction);
    }

    private void thisWindowClosing(WindowEvent e) {
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        passwordField = new JPasswordField();
        buttonBar = new JPanel();
        aceptarBtn = new JButton();

        //======== this ========
        setTitle("Contrase\u00f1a SQL");
        setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/images/contasoc_small.png")).getImage());
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets dialog,hidemode 3",
                    // columns
                    "[grow,fill]",
                    // rows
                    "[grow,fill]"));

                //---- passwordField ----
                passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                passwordField.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0x549159)));
                passwordField.setBackground(new Color(0xf7f8fa));
                contentPanel.add(passwordField, "cell 0 0");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[button,fill]",
                    // rows
                    null));

                //---- aceptarBtn ----
                aceptarBtn.setText("Aceptar");
                aceptarBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                aceptarBtn.addActionListener(e -> aceptarBtn(e));
                buttonBar.add(aceptarBtn, "cell 0 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(270, 150);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPasswordField passwordField;
    private JPanel buttonBar;
    private JButton aceptarBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
