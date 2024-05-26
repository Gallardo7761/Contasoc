/*
 * Created by JFormDesigner on Mon Dec 25 07:56:18 CET 2023
 */

package dev.gallardo.contasoc.ui;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.CalendarBorderProperties;

import dev.gallardo.contasoc.Contasoc;
import dev.gallardo.contasoc.common.Action;
import dev.gallardo.contasoc.common.FormatterType;
import dev.gallardo.contasoc.common.TipoPago;
import dev.gallardo.contasoc.database.objects.Ingresos;
import dev.gallardo.contasoc.util.Parsers;
import dev.gallardo.contasoc.util.UpperCaseFilter;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jomaa
 */
@SuppressWarnings("all")
public class AddModifyIngresos extends JFrame {
    protected static Action accion;
    protected static String tempSocio;
    protected static String tempConcepto;
    private static AddModifyIngresos instance;

    private static boolean sqlExceptionOcurred = false;

    private AddModifyIngresos() {
        initComponents();
        setActions();
        setFilters();
        addFormatterFactories();
    }

    private void addFormatterFactories() {
        try {
            conceptoField.putClientProperty("JTextField.placeholderText", "Concepto del pago");
            GUIManager.addFormatterFactory(socioField, FormatterType.ID);
            GUIManager.addFormatterFactory(cantidadField, FormatterType.DECIMAL);
        } catch (ParseException e) {
            Contasoc.logger.error("Error", e);
        }
    }

    protected void clear() {
        for (JTextField jtf : Arrays.asList(socioField, conceptoField, cantidadField)) {
            jtf.setText("");
        }
    }

    public static AddModifyIngresos getInstance() {
        if (instance == null) {
            instance = new AddModifyIngresos();
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
        java.util.List<JTextField> lista = List.of(conceptoField);
        for (JTextField jtp : lista) {
            AbstractDocument doc = (AbstractDocument) jtp.getDocument();
            doc.setDocumentFilter(new UpperCaseFilter());
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

    private void aceptarBtnActionPerformed(ActionEvent e) {
        Integer numeroSocio = Integer.valueOf(socioField.getText());
        Date fecha = Date.valueOf(Parsers.dashDateParserReversed(fechaField.getText()));
        String concepto = conceptoField.getText();
        Double cantidad = Double.valueOf(cantidadField.getText().contains(",") ? cantidadField.getText().replace(",", ".") : cantidadField.getText());
        TipoPago tipo = TipoPago.valueOf((String) tipoPagoComboBox.getSelectedItem());

        DefaultListModel<IngresoPanel> model = (DefaultListModel<IngresoPanel>) UIContasoc.ingresosLista.getModel();
        Ingresos ingreso = new Ingresos(numeroSocio, fecha, concepto, cantidad, tipo);

        switch (accion) {
            case ADD:
                model.addElement(new IngresoPanel(ingreso));
                Contasoc.sqlMemory.ingresoAgregado(ingreso);
                clear();
                this.dispose();
                break;
            case MODIFY:
                model.setElementAt(new IngresoPanel(ingreso), UIContasoc.ingresosLista.getSelectedIndex());
                Contasoc.sqlMemory.ingresoEditado(ingreso);
                clear();
                this.dispose();
                break;
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
        nombreLabel = new JLabel();
        socioField = new JTextField();
        fechalLabel = new JLabel();
        fechaField = new DatePicker();
        conceptoLabel = new JLabel();
        conceptoField = new JTextField();
        cantidadLabel = new JLabel();
        cantidadField = new JTextField();
        tipoLabel = new JLabel();
        tipoPagoComboBox = new JComboBox<>();
        aceptarBtn = new JButton();

        //======== this ========
        setTitle("{accion} ingreso");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/images/contasoc_small.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 12 12 12 12,gap 10 10",
            // columns
            "[grow,fill]",
            // rows
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]" +
            "[grow,fill]"));

        //---- nombreLabel ----
        nombreLabel.setText("Socio:");
        nombreLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nombreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(nombreLabel, "cell 0 0,width 100:100:100,height 32:32:32");

        //---- socioField ----
        socioField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        socioField.setNextFocusableComponent(fechaField);
        contentPane.add(socioField, "cell 0 0,height 32:32:32");

        //---- fechalLabel ----
        fechalLabel.setText("Fecha:");
        fechalLabel.setHorizontalAlignment(SwingConstants.LEFT);
        fechalLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(fechalLabel, "cell 0 1,width 100:100:100,height 32:32:32");

        //---- fechaField ----
        fechaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fechaField.setNextFocusableComponent(conceptoField);
        setDatePickerProperties(fechaField);
        contentPane.add(fechaField, "cell 0 1,height 32:32:32");

        //---- conceptoLabel ----
        conceptoLabel.setText("Concepto:");
        conceptoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        conceptoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(conceptoLabel, "cell 0 2,width 100:100:100,height 32:32:32");

        //---- conceptoField ----
        conceptoField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        conceptoField.setNextFocusableComponent(cantidadField);
        contentPane.add(conceptoField, "cell 0 2,height 32:32:32");

        //---- cantidadLabel ----
        cantidadLabel.setText("Cantidad:");
        cantidadLabel.setHorizontalAlignment(SwingConstants.LEFT);
        cantidadLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(cantidadLabel, "cell 0 3,width 100:100:100,height 32:32:32");

        //---- cantidadField ----
        cantidadField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        cantidadField.setNextFocusableComponent(socioField);
        contentPane.add(cantidadField, "cell 0 3");

        //---- tipoLabel ----
        tipoLabel.setText("Tipo:");
        tipoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        tipoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentPane.add(tipoLabel, "cell 0 4,width 100:100:100,height 32:32:32");

        //---- tipoPagoComboBox ----
        tipoPagoComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tipoPagoComboBox.setSelectedItem("BANCO");
        tipoPagoComboBox.addItem("BANCO");
        tipoPagoComboBox.addItem("CAJA");
        contentPane.add(tipoPagoComboBox, "cell 0 4");

        //---- aceptarBtn ----
        aceptarBtn.setText("ACEPTAR");
        aceptarBtn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        aceptarBtn.setFocusable(false);
        aceptarBtn.addActionListener(e -> aceptarBtnActionPerformed(e));
        contentPane.add(aceptarBtn, "cell 0 5,alignx right,growx 0,width 128:128:128,height 32:32:32");
        setSize(400, 300);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - José Manuel Amador Gallardo (José Manuel Amador)
    protected static JLabel nombreLabel;
    protected static JTextField socioField;
    protected static JLabel fechalLabel;
    protected static DatePicker fechaField;
    protected static JLabel conceptoLabel;
    protected static JTextField conceptoField;
    protected static JLabel cantidadLabel;
    protected static JTextField cantidadField;
    protected static JLabel tipoLabel;
    protected static JComboBox<String> tipoPagoComboBox;
    protected static JButton aceptarBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
