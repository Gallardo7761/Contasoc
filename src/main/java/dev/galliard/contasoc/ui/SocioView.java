/*
 * Created by JFormDesigner on Sat Feb 17 06:12:27 CET 2024
 */

package dev.galliard.contasoc.ui;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import com.github.lgooddatepicker.components.*;
import com.github.lgooddatepicker.optionalusertools.CalendarBorderProperties;
import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.common.Estado;
import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.common.TipoSocio;
import dev.galliard.contasoc.database.objects.Ingresos;
import dev.galliard.contasoc.database.objects.Socios;
import dev.galliard.contasoc.ui.models.IngresoPanelRenderer;
import dev.galliard.contasoc.util.Parsers;
import net.miginfocom.swing.*;

/**
 * @author jomaa
 */
public class SocioView extends JFrame {
    private static SocioView instance;
    protected static int tempNumeroSocio;
    private SocioView() {
        initComponents();
        setEditable(false);
        System.out.println(altaField.getText());
        System.out.println(entregaField.getText());
        System.out.println(bajaField.getText());
    }

    public static SocioView getInstance() {
        if (instance == null) {
            instance = new SocioView();
        }
        return instance;
    }

    protected void clear() {
        for (JTextField jtf : Arrays.asList(nombreField, telefonoField, dniField, emailField, socioField, huertoField)) {
            jtf.setText("");
        }
    }

    private void aceptarBtnActionPerformed(ActionEvent e) {
        Integer numeroSocio = socioField.getText().isEmpty() ?
                UIContasoc.sociosLista.getModel().getSize() == 0 ? 1 : (Integer) UIContasoc.sociosLista.getModel()
                        .getElementAt(UIContasoc.sociosLista.getVisibleRowCount() - 1).getSocio().getNumeroSocio() + 1
                : Integer.parseInt(socioField.getText());
        Integer numeroHuerto = Integer.parseInt(huertoField.getText());
        String nombre = nombreField.getText();
        String dni = dniField.getText();
        Integer telefono = Integer.valueOf(telefonoField.getText());
        String email = emailField.getText();
        Date alta = altaField.getText().isEmpty() ? null : Date.valueOf(Parsers.dashDateParserReversed(altaField.getText()));
        Date entrega = entregaField.getText().isEmpty() ? null : Date.valueOf(Parsers.dashDateParserReversed(entregaField.getText()));
        Date baja = bajaField.getText().isEmpty() ? null : Date.valueOf(Parsers.dashDateParserReversed(bajaField.getText()));
        String notas = notasField.getText();
        TipoSocio tipoSocio = TipoSocio.valueOf((String) tipoSocioComboBox.getSelectedItem());
        Estado estado = Estado.ACTIVO;

        Socios socios = new Socios(numeroSocio, numeroHuerto, nombre, dni, telefono, email, alta, entrega, baja, notas, tipoSocio, estado);
        DefaultListModel<SocioPanel> model = (DefaultListModel<SocioPanel>) UIContasoc.sociosLista.getModel();

        if(checkBox1.isSelected()) {
            model.setElementAt(new SocioPanel(socios), UIContasoc.sociosLista.getSelectedIndex());
            Contasoc.sqlMemory.socioEditado(socios);
            clear();
            this.dispose();
        } else {
            this.dispose();
        }
    }

    private void setDatePickerProperties(DatePicker picker) {
        picker.getSettings().setFormatForDatesCommonEra("d MMM yyyy");
        picker.getSettings().setFontValidDate(new Font("Segoe UI", Font.PLAIN, 18));
        picker.getSettings().setFontInvalidDate(new Font("Segoe UI", Font.PLAIN, 18));
        picker.getSettings().setColor(DatePickerSettings.DateArea.BackgroundOverallCalendarPanel, Color.decode("#EDF1F5"));
        picker.getSettings().setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearMenuLabels, Color.decode("#EDF1F5"));
        picker.getSettings().setColor(DatePickerSettings.DateArea.BackgroundTodayLabel, Color.decode("#EDF1F5"));
        picker.getSettings().setColor(DatePickerSettings.DateArea.BackgroundClearLabel, Color.decode("#EDF1F5"));
        picker.getSettings().setColor(DatePickerSettings.DateArea.DatePickerTextValidDate, Color.decode("#5B6168"));
        picker.getSettings().setFontClearLabel(new Font("Segoe UI", Font.PLAIN, 18));
        picker.getSettings().setFontMonthAndYearMenuLabels(new Font("Segoe UI", Font.PLAIN, 18));
        picker.getSettings().setFontTodayLabel(new Font("Segoe UI", Font.PLAIN, 18));
        picker.getSettings().setFontCalendarDateLabels(new Font("Segoe UI", Font.PLAIN, 14));
        picker.getSettings().setFontCalendarWeekdayLabels(new Font("Segoe UI", Font.PLAIN, 14));
        picker.getSettings().setColor(DatePickerSettings.DateArea.BackgroundCalendarPanelLabelsOnHover, Color.decode("#C8E8CA"));
        picker.getSettings().setColor(DatePickerSettings.DateArea.CalendarBorderSelectedDate, Color.decode("#5B6168"));
        picker.getSettings().setBorderCalendarPopup(BorderFactory.createLineBorder(Color.decode("#B3B3B4")));
        picker.getSettings().setColorBackgroundWeekdayLabels(Color.decode("#549159"), true);
        picker.getSettings().setColor(DatePickerSettings.DateArea.CalendarTextWeekdays, Color.WHITE);
        ArrayList<CalendarBorderProperties> borderProperties = new ArrayList();
        borderProperties.add(new CalendarBorderProperties(new Point(1, 1), new Point(5, 5), Color.decode("#B3B3B4"), 1));
        borderProperties.add(new CalendarBorderProperties(new Point(4, 1), new Point(4, 1), Color.decode("#B3B3B4"), 1));
        borderProperties.add(new CalendarBorderProperties(new Point(3, 3), new Point(5, 5), Color.decode("#B3B3B4"), 1));
        borderProperties.add(new CalendarBorderProperties(new Point(3, 3), new Point(3, 3), Color.decode("#B3B3B4"), 1));
        borderProperties.add(new CalendarBorderProperties(new Point(5, 3), new Point(5, 3), Color.decode("#B3B3B4"), 1));
        picker.getSettings().setBorderPropertiesList(borderProperties);
    }

    private void setEditable(boolean editable) {
        nombreField.setEditable(editable);
        telefonoField.setEditable(editable);
        dniField.setEditable(editable);
        emailField.setEditable(editable);
        socioField.setEditable(editable);
        altaField.setEnabled(editable);
        huertoField.setEditable(editable);
        entregaField.setEnabled(editable);
        notasField.setEditable(editable);
        bajaField.setEnabled(editable);
        tipoSocioComboBox.setEnabled(editable);
    }

    private void checkBox1StateChanged(ChangeEvent e) {
        if (checkBox1.isSelected()) {
            setEditable(true);
            aceptarBtn.setText("GUARDAR");
        } else {
            setEditable(false);
            aceptarBtn.setText("ACEPTAR");
        }
    }

    private void tabbedPane1StateChanged(ChangeEvent e) {
        if (tabbedPane1.getSelectedComponent().equals(infoPanel)) {
            this.setSize(635, 600);
        } else if (tabbedPane1.getSelectedComponent().equals(ingresosPanel)) {
            this.setSize(850, 600);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        tabbedPane1 = new JTabbedPane();
        infoPanel = new JPanel();
        personalPanel = new JPanel();
        nombreLabel = new JLabel();
        nombreField = new JTextField();
        telefonoLabel = new JLabel();
        telefonoField = new JTextField();
        dniLabel = new JLabel();
        dniField = new JTextField();
        emailLabel = new JLabel();
        emailField = new JTextField();
        otrosPanel = new JPanel();
        socioLabel = new JLabel();
        socioField = new JTextField();
        altaLabel = new JLabel();
        altaField = new DatePicker();
        huertoLabel = new JLabel();
        huertoField = new JTextField();
        entregaLabel = new JLabel();
        entregaField = new DatePicker();
        tipoLabel = new JLabel();
        tipoSocioComboBox = new JComboBox<>();
        bajaLabel = new JLabel();
        bajaField = new DatePicker();
        notasPanel = new JScrollPane();
        notasField = new JTextArea();
        checkBox1 = new JCheckBox();
        aceptarBtn = new JButton();
        ingresosPanel = new JPanel();
        ingresosListaPanel = new JScrollPane();
        ingresosLista = new JList<>();

        //======== this ========
        setResizable(false);
        setTitle("Datos extendidos");
        setIconImage(new ImageIcon(getClass().getResource("/images/contasoc_small.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[grow,fill]",
            // rows
            "[grow,fill]"));

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            tabbedPane1.addChangeListener(e -> tabbedPane1StateChanged(e));

            //======== infoPanel ========
            {
                infoPanel.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[grow,fill]",
                    // rows
                    "[fill]" +
                    "[fill]" +
                    "[grow,fill]" +
                    "[fill]"));

                //======== personalPanel ========
                {
                    personalPanel.setBorder(new TitledBorder(null, "Datos personales", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                        new Font("Segoe UI", Font.PLAIN, 14)));
                    personalPanel.setLayout(new MigLayout(
                        "insets 6",
                        // columns
                        "[grow,fill]" +
                        "[grow,fill]",
                        // rows
                        "[grow,fill]" +
                        "[grow,fill]"));

                    //---- nombreLabel ----
                    nombreLabel.setText("Nombre:");
                    nombreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    nombreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    personalPanel.add(nombreLabel, "cell 0 0,width 80:80:80,height 32:32:32");

                    //---- nombreField ----
                    nombreField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    nombreField.setNextFocusableComponent(dniField);
                    personalPanel.add(nombreField, "cell 0 0,width 200:200:200,height 32:32:32");

                    //---- telefonoLabel ----
                    telefonoLabel.setText("Tel\u00e9fono:");
                    telefonoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    telefonoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    personalPanel.add(telefonoLabel, "cell 1 0,width 80:80:80,height 32:32:32");

                    //---- telefonoField ----
                    telefonoField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    telefonoField.setNextFocusableComponent(emailField);
                    personalPanel.add(telefonoField, "cell 1 0,width 200:200:200,height 32:32:32");

                    //---- dniLabel ----
                    dniLabel.setText("DNI/NIE:");
                    dniLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    dniLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    personalPanel.add(dniLabel, "cell 0 1,width 80:80:80,height 32:32:32");

                    //---- dniField ----
                    dniField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    dniField.setNextFocusableComponent(telefonoField);
                    personalPanel.add(dniField, "cell 0 1,width 200:200:200,height 32:32:32");

                    //---- emailLabel ----
                    emailLabel.setText("Email:");
                    emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    personalPanel.add(emailLabel, "cell 1 1,width 80:80:80,height 32:32:32");

                    //---- emailField ----
                    emailField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    emailField.setNextFocusableComponent(socioField);
                    personalPanel.add(emailField, "cell 1 1,width 200:200:200,height 32:32:32");
                }
                infoPanel.add(personalPanel, "cell 0 0");

                //======== otrosPanel ========
                {
                    otrosPanel.setBorder(new TitledBorder(null, "Datos de socio", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                        new Font("Segoe UI", Font.PLAIN, 14)));
                    otrosPanel.setLayout(new MigLayout(
                        "insets 6",
                        // columns
                        "[grow,fill]" +
                        "[grow,fill]",
                        // rows
                        "[grow,fill]" +
                        "[grow,fill]" +
                        "[grow,fill]"));

                    //---- socioLabel ----
                    socioLabel.setText("Socio:");
                    socioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    socioLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    otrosPanel.add(socioLabel, "cell 0 0,width 80:80:80,height 32:32:32");

                    //---- socioField ----
                    socioField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    socioField.setToolTipText("Si no se pone se autoincrementa");
                    socioField.setNextFocusableComponent(huertoField);
                    otrosPanel.add(socioField, "cell 0 0,width 200:200:200,height 32:32:32");

                    //---- altaLabel ----
                    altaLabel.setText("Alta:");
                    altaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    altaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    otrosPanel.add(altaLabel, "cell 1 0,width 80:80:80,height 32:32:32");

                    //---- altaField ----
                    altaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    altaField.setNextFocusableComponent(entregaField);
                    setDatePickerProperties(altaField);
                    setDatePickerProperties(entregaField);
                    setDatePickerProperties(bajaField);
                    otrosPanel.add(altaField, "cell 1 0,width 200:200:200,height 32:32:32");

                    //---- huertoLabel ----
                    huertoLabel.setText("Huerto:");
                    huertoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    huertoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    otrosPanel.add(huertoLabel, "cell 0 1,width 80:80:80,height 32:32:32");

                    //---- huertoField ----
                    huertoField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    otrosPanel.add(huertoField, "cell 0 1,width 200:200:200,height 32:32:32");

                    //---- entregaLabel ----
                    entregaLabel.setText("Entrega:");
                    entregaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    entregaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    otrosPanel.add(entregaLabel, "cell 1 1,width 80:80:80,height 32:32:32");

                    //---- entregaField ----
                    entregaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    entregaField.setNextFocusableComponent(bajaField);
                    entregaField.getSettings().setFormatForDatesCommonEra("d MMM yyyy");
                    otrosPanel.add(entregaField, "cell 1 1,width 200:200:200,height 32:32:32");

                    //---- tipoLabel ----
                    tipoLabel.setText("Tipo:");
                    tipoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    tipoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    otrosPanel.add(tipoLabel, "cell 0 2,width 80:80:80,height 32:32:32");

                    //---- tipoSocioComboBox ----
                    tipoSocioComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    tipoSocioComboBox.setSelectedItem("HORTELANO");
                    tipoSocioComboBox.addItem("HORTELANO");
                    tipoSocioComboBox.addItem("HORTELANO_INVERNADERO");
                    tipoSocioComboBox.addItem("COLABORADOR");
                    tipoSocioComboBox.addItem("LISTA_ESPERA");
                    otrosPanel.add(tipoSocioComboBox, "cell 0 2,width 200:200:200,height 32:32:32");

                    //---- bajaLabel ----
                    bajaLabel.setText("Baja:");
                    bajaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    bajaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    otrosPanel.add(bajaLabel, "cell 1 2,width 80:80:80,height 32:32:32");

                    //---- bajaField ----
                    bajaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    bajaField.setNextFocusableComponent(nombreField);
                    bajaField.getSettings().setFormatForDatesCommonEra("d MMM yyyy");
                    otrosPanel.add(bajaField, "cell 1 2,width 200:200:200,height 32:32:32");
                }
                infoPanel.add(otrosPanel, "cell 0 1");

                //======== notasPanel ========
                {
                    notasPanel.setBorder(new TitledBorder(null, "Notas", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                        new Font("Segoe UI", Font.PLAIN, 14)));

                    //---- notasField ----
                    notasField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    notasPanel.setViewportView(notasField);
                }
                infoPanel.add(notasPanel, "cell 0 2");

                //---- checkBox1 ----
                checkBox1.setText("Modo edici\u00f3n");
                checkBox1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                checkBox1.setIconTextGap(6);
                checkBox1.addChangeListener(e -> checkBox1StateChanged(e));
                infoPanel.add(checkBox1, "cell 0 3");

                //---- aceptarBtn ----
                aceptarBtn.setText("ACEPTAR");
                aceptarBtn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                aceptarBtn.setFocusable(false);
                aceptarBtn.addActionListener(e -> aceptarBtnActionPerformed(e));
                infoPanel.add(aceptarBtn, "cell 0 3,alignx right,growx 0,width 200:200:200,height 32:32:32");
            }
            tabbedPane1.addTab("Informaci\u00f3n", infoPanel);

            //======== ingresosPanel ========
            {
                ingresosPanel.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[grow,fill]",
                    // rows
                    "[grow,fill]"));

                //======== ingresosListaPanel ========
                {

                    //---- ingresosLista ----
                    ingresosLista.setCellRenderer(new IngresoPanelRenderer());
                    ingresosListaPanel.setViewportView(ingresosLista);
                }
                ingresosPanel.add(ingresosListaPanel, "cell 0 0");
            }
            tabbedPane1.addTab("Ingresos", ingresosPanel);
        }
        contentPane.add(tabbedPane1, "cell 0 0");
        setSize(635, 600);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JTabbedPane tabbedPane1;
    protected static JPanel infoPanel;
    protected static JPanel personalPanel;
    protected static JLabel nombreLabel;
    protected static JTextField nombreField;
    protected static JLabel telefonoLabel;
    protected static JTextField telefonoField;
    protected static JLabel dniLabel;
    protected static JTextField dniField;
    protected static JLabel emailLabel;
    protected static JTextField emailField;
    protected static JPanel otrosPanel;
    protected static JLabel socioLabel;
    protected static JTextField socioField;
    protected static JLabel altaLabel;
    protected static DatePicker altaField;
    protected static JLabel huertoLabel;
    protected static JTextField huertoField;
    protected static JLabel entregaLabel;
    protected static DatePicker entregaField;
    protected static JLabel tipoLabel;
    protected static JComboBox<String> tipoSocioComboBox;
    protected static JLabel bajaLabel;
    protected static DatePicker bajaField;
    protected static JScrollPane notasPanel;
    protected static JTextArea notasField;
    protected static JCheckBox checkBox1;
    protected static JButton aceptarBtn;
    protected static JPanel ingresosPanel;
    protected static JScrollPane ingresosListaPanel;
    protected static JList<IngresoPanel> ingresosLista;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
