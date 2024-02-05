/*
 * Created by JFormDesigner on Mon Dec 25 05:45:33 CET 2023
 */

package dev.galliard.contasoc.ui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.text.AbstractDocument;

import com.sun.media.jfxmedia.logging.Logger;
import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.common.Action;
import dev.galliard.contasoc.common.FormatterType;
import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.util.*;
import net.miginfocom.swing.*;

/**
 * @author jomaa
 */
public class AddModifySocios extends JFrame {
    protected static Action accion;
    protected static String tempNumeroSocio;
    private static AddModifySocios instance;

    private static boolean sqlExceptionOcurred = false;

    private AddModifySocios() {
        initComponents();
        setActions();
        setFilters();
        addFormatterFactories();
    }

    public static AddModifySocios getInstance() {
        if (instance == null) {
            instance = new AddModifySocios();
        }
        return instance;
    }

    private void setActions() {
        javax.swing.Action enterAction = new AbstractAction("Enter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                aceptarBtnActionPerformed(e);
            }
        };
        JPanel contentPane = (JPanel) this.getContentPane();
        KeyStroke nuevoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(nuevoKeyStroke, "Enter");
        contentPane.getActionMap().put("Enter", enterAction);
    }

    private void setFilters() {
        java.util.List<JTextField> lista = List.of(nombreField,dniField);
        for (JTextField jtp : lista) {
            AbstractDocument doc = (AbstractDocument) jtp.getDocument();
            doc.setDocumentFilter(new UpperCaseFilter());
        }
    }

    private void addFormatterFactories() {
        try {
            GUIManager.addFormatterFactory(telefonoField, FormatterType.PHONE);
            GUIManager.addFormatterFactory(socioField, FormatterType.ID);
            GUIManager.addFormatterFactory(huertoField, FormatterType.ID);
            GUIManager.addFormatterFactory(altaField, FormatterType.DATE);
            GUIManager.addFormatterFactory(entregaField, FormatterType.DATE);
            GUIManager.addFormatterFactory(bajaField, FormatterType.DATE);
            GUIManager.addFormatterFactory(dniField, FormatterType.DNI);
            GUIManager.addFormatterFactory(emailField, FormatterType.EMAIL);
        } catch (Exception e) {
            ContasocLogger.error("Error", e);
        }
    }

    private void aceptarBtnActionPerformed(ActionEvent e) {
        switch(accion.name()) {
            case "ADD":
                List<String> ins = new ArrayList<>();
                ins.add(nombreField.getText());
                ins.add(dniField.getText());
                ins.add(telefonoField.getText());
                ins.add(emailField.getText());
                ins.add(socioField.getText());
                ins.add(huertoField.getText());
                ins.add(Parsers.dashDateParserReversed(altaField.getText()));
                ins.add(Parsers.dashDateParserReversed(entregaField.getText()));
                ins.add(Parsers.dashDateParserReversed(bajaField.getText()));
                ins.add(notasField.getText());
                ins.add((String) tipoSocioComboBox.getSelectedItem());

                if(DNIValidator.validarDNI(dniField.getText()) || DNIValidator.validarNIE(dniField.getText())) {
                    try {
                        sqlExceptionOcurred = false;
                        ContasocDAO.insert("Socios", new String[] {"nombre", "dni", "telefono", "email", "numeroSocio",
                                        "numeroHuerto", "fechaDeAlta", "fechaDeEntrega", "fechaDeBaja", "notas", "tipo"},
                                ins.toArray(String[]::new));
                    } catch (SQLException ex) {
                        sqlExceptionOcurred = true;
                        ContasocLogger.dispatchSQLException(ex);
                    }
                    if(!sqlExceptionOcurred) {
                        GUIManager.populateGUITables();
                        for(JTextField jtf : List.of(nombreField, dniField, telefonoField, emailField, socioField, huertoField,
                                altaField, entregaField, bajaField, notasField)) {
                            jtf.setText("");
                        }
                        this.dispose();
                    }
                } else {
                    ErrorHandler.errorAlLeerDNI();
                }
                break;
            case "MODIFY":

                List<String> upd = new ArrayList<>();
                upd.add(nombreField.getText());
                upd.add(dniField.getText());
                upd.add(telefonoField.getText());
                upd.add(emailField.getText());
                upd.add(socioField.getText());
                upd.add(huertoField.getText());
                upd.add(Parsers.dashDateParserReversed(altaField.getText()));
                upd.add(Parsers.dashDateParserReversed(entregaField.getText()));
                upd.add(Parsers.dashDateParserReversed(bajaField.getText()));
                upd.add(notasField.getText());
                upd.add((String) tipoSocioComboBox.getSelectedItem());
                if(DNIValidator.validarDNI(dniField.getText()) || DNIValidator.validarNIE(dniField.getText())) {
                    try {
                        sqlExceptionOcurred = false;
                        ContasocDAO.update("Socios", new String[] {"nombre", "dni", "telefono", "email", "numeroSocio",
                                        "numeroHuerto", "fechaDeAlta", "fechaDeEntrega", "fechaDeBaja", "notas", "tipo"},
                                upd.toArray(String[]::new),
                                new String[] {"numeroSocio =" + tempNumeroSocio
                                });
                    } catch (SQLException ex) {
                        sqlExceptionOcurred = true;
                        ContasocLogger.dispatchSQLException(ex);
                    }
                    if(!sqlExceptionOcurred) {
                        GUIManager.populateGUITables();
                        for(JTextField jtf : List.of(nombreField, dniField, telefonoField, emailField, socioField, huertoField,
                                altaField, entregaField, bajaField, notasField)) {
                            jtf.setText("");
                        }
                        this.dispose();
                    }
                } else {
                    ErrorHandler.errorAlLeerDNI();
                }
                break;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        nombreLabel = new JLabel();
        nombreField = new JTextField();
        dniLabel = new JLabel();
        dniField = new JFormattedTextField();
        telefonoLabel = new JLabel();
        telefonoField = new JFormattedTextField();
        emailLabel = new JLabel();
        emailField = new JFormattedTextField();
        socioLabel = new JLabel();
        socioField = new JFormattedTextField();
        huertoLabel = new JLabel();
        huertoField = new JFormattedTextField();
        altaLabel = new JLabel();
        altaField = new JFormattedTextField();
        entregaLabel = new JLabel();
        entregaField = new JFormattedTextField();
        bajaLabel = new JLabel();
        bajaField = new JFormattedTextField();
        notasLabel = new JLabel();
        notasField = new JTextField();
        estadoLabel = new JLabel();
        tipoSocioComboBox = new JComboBox<>();
        separator2 = new JSeparator();
        aceptarBtn = new JButton();

        //======== this ========
        setTitle("{accion} socio");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/images/contasoc_small.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 12",
            // columns
            "[grow,fill]" +
            "[grow,fill]",
            // rows
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]"));

        //---- nombreLabel ----
        nombreLabel.setText("Nombre:");
        nombreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        nombreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(nombreLabel, "cell 0 0,width 80:80:80,height 32:32:32");

        //---- nombreField ----
        nombreField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        nombreField.setNextFocusableComponent(dniField);
        contentPane.add(nombreField, "cell 0 0,width 200:200:200,height 32:32:32");

        //---- dniLabel ----
        dniLabel.setText("DNI/NIE:");
        dniLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        dniLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(dniLabel, "cell 0 1,width 80:80:80,height 32:32:32");

        //---- dniField ----
        dniField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        dniField.setNextFocusableComponent(telefonoField);
        contentPane.add(dniField, "cell 0 1,width 200:200:200,height 32:32:32");

        //---- telefonoLabel ----
        telefonoLabel.setText("Tel\u00e9fono:");
        telefonoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        telefonoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(telefonoLabel, "cell 0 2,width 80:80:80,height 32:32:32");

        //---- telefonoField ----
        telefonoField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        telefonoField.setNextFocusableComponent(emailField);
        contentPane.add(telefonoField, "cell 0 2,width 200:200:200,height 32:32:32");

        //---- emailLabel ----
        emailLabel.setText("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(emailLabel, "cell 0 3,width 80:80:80,height 32:32:32");

        //---- emailField ----
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        emailField.setNextFocusableComponent(socioField);
        contentPane.add(emailField, "cell 0 3,width 200:200:200,height 32:32:32");

        //---- socioLabel ----
        socioLabel.setText("Socio:");
        socioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        socioLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(socioLabel, "cell 0 4,width 80:80:80,height 32:32:32");

        //---- socioField ----
        socioField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        socioField.setToolTipText("Si no se pone se autoincrementa");
        socioField.setNextFocusableComponent(huertoField);
        contentPane.add(socioField, "cell 0 4,width 200:200:200,height 32:32:32");

        //---- huertoLabel ----
        huertoLabel.setText("Huerto:");
        huertoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        huertoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(huertoLabel, "cell 0 5,width 80:80:80,height 32:32:32");

        //---- huertoField ----
        huertoField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        huertoField.setNextFocusableComponent(altaField);
        contentPane.add(huertoField, "cell 0 5,width 200:200:200,height 32:32:32");

        //---- altaLabel ----
        altaLabel.setText("Alta:");
        altaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        altaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(altaLabel, "cell 1 0,width 80:80:80,height 32:32:32");

        //---- altaField ----
        altaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        altaField.setNextFocusableComponent(entregaField);
        contentPane.add(altaField, "cell 1 0,width 200:200:200,height 32:32:32");

        //---- entregaLabel ----
        entregaLabel.setText("Entrega:");
        entregaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        entregaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(entregaLabel, "cell 1 1,width 80:80:80,height 32:32:32");

        //---- entregaField ----
        entregaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        entregaField.setNextFocusableComponent(bajaField);
        contentPane.add(entregaField, "cell 1 1,width 200:200:200,height 32:32:32");

        //---- bajaLabel ----
        bajaLabel.setText("Baja:");
        bajaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        bajaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(bajaLabel, "cell 1 2,width 80:80:80,height 32:32:32");

        //---- bajaField ----
        bajaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        bajaField.setNextFocusableComponent(notasField);
        contentPane.add(bajaField, "cell 1 2,width 200:200:200,height 32:32:32");

        //---- notasLabel ----
        notasLabel.setText("Notas:");
        notasLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        notasLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(notasLabel, "cell 1 3,width 80:80:80,height 32:32:32");

        //---- notasField ----
        notasField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        notasField.setNextFocusableComponent(nombreField);
        contentPane.add(notasField, "cell 1 3,width 200:200:200,height 32:32:32");

        //---- estadoLabel ----
        estadoLabel.setText("Tipo:");
        estadoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        estadoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(estadoLabel, "cell 1 4,width 80:80:80,height 32:32:32");

        //---- tipoSocioComboBox ----
        tipoSocioComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tipoSocioComboBox.setSelectedItem("HORTELANO");
        tipoSocioComboBox.addItem("HORTELANO");
        tipoSocioComboBox.addItem("HORTELANO_INVERNADERO");
        tipoSocioComboBox.addItem("COLABORADOR");
        tipoSocioComboBox.addItem("LISTA_ESPERA");
        contentPane.add(tipoSocioComboBox, "cell 1 4,width 200:200:200,height 32:32:32");

        //---- separator2 ----
        separator2.setBackground(new Color(0xf7f8fa));
        separator2.setForeground(new Color(0xf7f8fa));
        contentPane.add(separator2, "cell 1 5,width 64:64:64");

        //---- aceptarBtn ----
        aceptarBtn.setText("ACEPTAR");
        aceptarBtn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        aceptarBtn.setFocusable(false);
        aceptarBtn.addActionListener(e -> aceptarBtnActionPerformed(e));
        contentPane.add(aceptarBtn, "cell 1 5,width 200:200:200,height 32:32:32");
        setSize(630, 300);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JLabel nombreLabel;
    protected static JTextField nombreField;
    protected static JLabel dniLabel;
    protected static JFormattedTextField dniField;
    protected static JLabel telefonoLabel;
    protected static JFormattedTextField telefonoField;
    protected static JLabel emailLabel;
    protected static JFormattedTextField emailField;
    protected static JLabel socioLabel;
    protected static JFormattedTextField socioField;
    protected static JLabel huertoLabel;
    protected static JFormattedTextField huertoField;
    protected static JLabel altaLabel;
    protected static JFormattedTextField altaField;
    protected static JLabel entregaLabel;
    protected static JFormattedTextField entregaField;
    protected static JLabel bajaLabel;
    protected static JFormattedTextField bajaField;
    protected static JLabel notasLabel;
    protected static JTextField notasField;
    protected static JLabel estadoLabel;
    protected static JComboBox<String> tipoSocioComboBox;
    protected static JSeparator separator2;
    protected static JButton aceptarBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
