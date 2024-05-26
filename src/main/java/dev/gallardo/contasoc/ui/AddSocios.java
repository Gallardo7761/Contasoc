/*
 * Created by JFormDesigner on Mon Dec 25 05:45:33 CET 2023
 */

package dev.gallardo.contasoc.ui;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.CalendarBorderProperties;

import dev.gallardo.contasoc.Contasoc;
import dev.gallardo.contasoc.common.Estado;
import dev.gallardo.contasoc.common.FormatterType;
import dev.gallardo.contasoc.common.TipoSocio;
import dev.gallardo.contasoc.database.objects.Socios;
import dev.gallardo.contasoc.util.DNIValidator;
import dev.gallardo.contasoc.util.ErrorHandler;
import dev.gallardo.contasoc.util.Parsers;
import dev.gallardo.contasoc.util.UpperCaseFilter;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jomaa
 */
@SuppressWarnings("all")
public class AddSocios extends JFrame {
    private static AddSocios instance;

    private static boolean sqlExceptionOcurred = false;

    private AddSocios() {
        initComponents();
        setActions();
        setFilters();
        addFormatterFactories();
    }

    public static AddSocios getInstance() {
        if (instance == null) {
            instance = new AddSocios();
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

    private void setFilters() {
        java.util.List<JTextField> lista = List.of(nombreField, dniField);
        for (JTextField jtp : lista) {
            AbstractDocument doc = (AbstractDocument) jtp.getDocument();
            doc.setDocumentFilter(new UpperCaseFilter());
        }
    }

    private void addFormatterFactories() {
        try {
            nombreField.putClientProperty("JTextField.placeholderText", "JUAN GARCÍA");
            notasField.putClientProperty("JTextField.placeholderText", "Nota o comentarios");
            GUIManager.addFormatterFactory(dniField, FormatterType.DNI);
            GUIManager.addFormatterFactory(telefonoField, FormatterType.PHONE);
            GUIManager.addFormatterFactory(socioField, FormatterType.ID);
            GUIManager.addFormatterFactory(huertoField, FormatterType.ID);
            GUIManager.addFormatterFactory(dniField, FormatterType.DNI);
            GUIManager.addFormatterFactory(emailField, FormatterType.EMAIL);
        } catch (Exception e) {
            Contasoc.logger.error("Error", e);
        }
    }

    protected void clear() {
        for (JTextField jtf : Arrays.asList(nombreField, dniField, telefonoField, emailField, socioField, huertoField,
                notasField, dniField)) {
            jtf.setText("");
        }
    }

    private void aceptarBtnActionPerformed(ActionEvent e) {
        int ultimo = UIContasoc.sociosLista.getModel().getSize() - 1;
        Integer numeroSocio = socioField.getText().isEmpty() ?
                UIContasoc.sociosLista.getModel().getSize() == 0 ? 1 : UIContasoc.sociosLista.getModel()
                        .getElementAt(ultimo).getSocio().getNumeroSocio() + 1
                : Integer.valueOf(socioField.getText());
        Integer numeroHuerto = Integer.valueOf(huertoField.getText());
        String nombre = nombreField.getText();
        String dni = dniField.getText();
        Integer telefono = Integer.valueOf(telefonoField.getText());
        String email = emailField.getText();
        Date alta = altaField.getText().isEmpty() ?
            Date.valueOf(LocalDate.now()) : Date.valueOf(Parsers.dashDateParserReversed(altaField.getText()));
        Date entrega = entregaField.getText().isEmpty() ? null : Date.valueOf(Parsers.dashDateParserReversed(entregaField.getText()));
        Date baja = bajaField.getText().isEmpty() ? null : Date.valueOf(Parsers.dashDateParserReversed(bajaField.getText()));
        String notas = notasField.getText();
        TipoSocio tipoSocio = TipoSocio.valueOf((String) tipoSocioComboBox.getSelectedItem());
        Estado estado = Estado.ACTIVO;

        JList<SocioPanel> tabla = UIContasoc.sociosLista;
        Socios socios = new Socios(numeroSocio, numeroHuerto, nombre, dni, telefono, email, alta, entrega, baja, notas, tipoSocio, estado);
        SocioPanel rowData = new SocioPanel(socios);
        rowData.telefonoLabel.setText(telefonoField.getText().isEmpty() ? "" : telefonoField.getText());

        if (DNIValidator.validarDNI(dniField.getText()) || DNIValidator.validarNIE(dniField.getText())) {
            ((DefaultListModel<SocioPanel>)UIContasoc.sociosLista.getModel()).addElement(rowData);
            Contasoc.sqlMemory.socioAgregado(socios);
            clear();
            this.dispose();
        } else {
            ErrorHandler.errorAlLeerDNI();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
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
        notasLabel = new JLabel();
        notasField = new JTextField();
        bajaLabel = new JLabel();
        bajaField = new DatePicker();
        estadoLabel = new JLabel();
        tipoSocioComboBox = new JComboBox<>();
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
            "[]"));

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
        contentPane.add(personalPanel, "cell 0 0 2 1");

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
            huertoField.setNextFocusableComponent(notasField);
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

            //---- notasLabel ----
            notasLabel.setText("Notas:");
            notasLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            notasLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            otrosPanel.add(notasLabel, "cell 0 2,width 80:80:80,height 32:32:32");

            //---- notasField ----
            notasField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            notasField.setNextFocusableComponent(altaField);
            otrosPanel.add(notasField, "cell 0 2,width 200:200:200,height 32:32:32");

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

            //---- estadoLabel ----
            estadoLabel.setText("Tipo:");
            estadoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            estadoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            otrosPanel.add(estadoLabel, "cell 0 3,width 80:80:80,height 32:32:32");

            //---- tipoSocioComboBox ----
            tipoSocioComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            tipoSocioComboBox.setSelectedItem("HORTELANO");
            tipoSocioComboBox.addItem("HORTELANO");
            tipoSocioComboBox.addItem("HORTELANO_INVERNADERO");
            tipoSocioComboBox.addItem("COLABORADOR");
            tipoSocioComboBox.addItem("LISTA_ESPERA");
            tipoSocioComboBox.addItem("SUBVENCION");
            otrosPanel.add(tipoSocioComboBox, "cell 0 3,width 200:200:200,height 32:32:32");
        }
        contentPane.add(otrosPanel, "cell 0 1 2 4");

        //---- aceptarBtn ----
        aceptarBtn.setText("ACEPTAR");
        aceptarBtn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        aceptarBtn.setFocusable(false);
        aceptarBtn.addActionListener(e -> aceptarBtnActionPerformed(e));
        contentPane.add(aceptarBtn, "cell 1 5,alignx right,growx 0,width 200:200:200,height 32:32:32");
        setSize(650, 400);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
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
    protected static JLabel notasLabel;
    protected static JTextField notasField;
    protected static JLabel bajaLabel;
    protected static DatePicker bajaField;
    protected static JLabel estadoLabel;
    protected static JComboBox<String> tipoSocioComboBox;
    protected static JButton aceptarBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
