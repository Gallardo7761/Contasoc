package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.common.FormatterType;
import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.common.TipoSocio;
import dev.galliard.contasoc.database.objects.Ingreso;
import dev.galliard.contasoc.database.objects.Socio;
import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.common.Estado;
import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.database.objects.Pago;
import dev.galliard.contasoc.util.ContasocLogger;
import dev.galliard.contasoc.util.ErrorHandler;
import dev.galliard.contasoc.util.PDFPrinter;
import dev.galliard.contasoc.util.Parsers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GUIManager {
    protected static String valor = null;
    protected static Double inicialBanco = 0.0;
    protected static Double inicialCaja = 0.0;
    protected static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    protected static void populateGUITables () {
        try {
            ContasocDAO.fillTableFrom(UIContasoc.sociosTabla, "Socios");
            ContasocDAO.fillTableFrom(UIContasoc.ingresosTabla, "Ingresos");
            ContasocDAO.fillTableFrom(UIContasoc.gastosTabla, "Gastos");
            ContasocDAO.fillListaEspera(UIContasoc.listaEsperaTabla);
        } catch (SQLException e) {
            ContasocLogger.dispatchSQLException(e);
        }
    }

    protected static void importBDD() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona el archivo para importar");
        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            File destino = new File(Contasoc.BASEDIR + File.separator + "contasoc2.db");

            try {
                Files.copy(archivoSeleccionado.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(null, "Importación exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                populateGUITables();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al importar la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected static void exportBDD() {
        File origen = new File(Contasoc.BASEDIR + File.separator + "contasoc2.db");

        if (origen.exists()) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selecciona la ubicación para exportar");
            fileChooser.setSelectedFile(new File(Contasoc.ESCRITORIO + File.separator + "/contasoc2.db"));
            int seleccion = fileChooser.showSaveDialog(null);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File destino = fileChooser.getSelectedFile();

                try {
                    Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(null, "Exportación exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al exportar la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La base de datos no existe en la ubicación especificada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected static void setColumnWidths(JTable table, int[] widths) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < widths.length; i++) {
            if (i < columnModel.getColumnCount()) {
                columnModel.getColumn(i).setMaxWidth(widths[i]);
            }
            else break;
        }
    }

    protected static void calcularBalance() {
        Double banco = null;
        Double caja = null;
        List<Ingreso> ingresos = null;
        List<Pago> pagos = null;
        try {
            banco = !ContasocDAO.leerTabla("Balance").isEmpty()
                    ? Double.parseDouble(ContasocDAO.select("Balance",new Object[] {"inicialBanco"}, ""))
                    : inicialBanco;

            caja = !ContasocDAO.leerTabla("Balance").isEmpty()
                    ? Double.parseDouble(ContasocDAO.select("Balance",new Object[] {"inicialCaja"}, ""))
                    : inicialCaja;

            ingresos = ContasocDAO.leerTabla("ingresos").stream().map(Parsers::ingresoParser).toList();

            pagos = ContasocDAO.leerTabla("gastos").stream().map(Parsers::pagoParser).toList();
        } catch (SQLException e) {
            ContasocLogger.dispatchSQLException(e);
        }

        Double totalIngresosBanco = ingresos.stream().filter(x -> x.getTipo().equals(TipoPago.BANCO)).mapToDouble(Ingreso::getCantidad).sum();
        Double totalIngresosCaja = ingresos.stream().filter(x -> x.getTipo().equals(TipoPago.CAJA)).mapToDouble(Ingreso::getCantidad).sum();
        double totalPagosBanco = pagos.stream().filter(x -> x.getTipo().equals(TipoPago.BANCO)).mapToDouble(Pago::getCantidad).sum();
        double totalPagosCaja = pagos.stream().filter(x -> x.getTipo().equals(TipoPago.CAJA)).mapToDouble(Pago::getCantidad).sum();

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        String saldoBanco = df.format((banco + totalIngresosBanco - totalPagosBanco));
        String saldoCaja = df.format((caja + totalIngresosCaja - totalPagosCaja));

        UIContasoc.tBancoIngresosValue.setText(totalIngresosBanco +" €");
        UIContasoc.tBancoGastosValue.setText(totalPagosBanco +" €");
        UIContasoc.tCajaIngresosValue.setText(totalIngresosCaja +" €");
        UIContasoc.tCajaGastosValue.setText(totalPagosCaja +" €");
        UIContasoc.saldoBancoValue.setText(saldoBanco +" €");
        UIContasoc.saldoCajaValue.setText(saldoCaja +" €");
    }

    protected static void printContent() {
        List<String> socios = new ArrayList<>();
        List<String> listaEspera = new ArrayList<>();
        List<String> ingresos = new ArrayList<>();
        List<String> gastos = new ArrayList<>();

        switch (valor) {
            case "Socios" -> {
                try {
                    for (Socio s : ContasocDAO.leerTabla("Socios").stream().map(Parsers::socioParser).toList()) {
                        if (s.getEstado() != Estado.INACTIVO) {
                            String socio =
                                    s.getNumeroSocio() + ";" +
                                            s.getNumeroHuerto() + ";" +
                                            s.getNombre() + ";" +
                                            s.getDni() + ";" +
                                            s.getTelefono() + ";" +
                                            s.getEmail() + ";" +
                                            Parsers.dateParser(s.getFechaDeAlta()) + ";" +
                                            Parsers.dateParser(s.getFechaDeEntrega()) + ";" +
                                            Parsers.dateParser(s.getFechaDeBaja()) + ";" +
                                            s.getNotas() + ";" +
                                            s.getTipo().toString()
                                                    .replace("A_E", "A DE E")
                                                    .replace("O_INVERNADERO", "O + INV") + ";" +
                                            s.getEstado();

                            socios.add(Arrays.stream(socio.split(";"))
                                    .map(x -> x.equals("null") ? "" : x)
                                    .collect(Collectors.joining(";")));
                        }
                    }
                } catch (SQLException e) {
                    ContasocLogger.dispatchSQLException(e);
                }

                PDFPrinter.printStringToPDF(socios, 12,
                        new float[]{30f, 25f, 200f, 80f, 70f, 170f, 55f, 55f, 55f, 120f, 110f, 50f},
                        "logohuerto_pdf.png", "Listado de socios", true, new String[]{"S", "H", "Nombre", "DNI",
                                "Teléfono", "Correo", "Alta", "Entrega", "Baja", "Notas", "Tipo", "Estado"},
                        true, 8, Contasoc.ESCRITORIO + "/socios.pdf");
                ErrorHandler.pdfCreado();
            }
            case "Ingresos" -> {
                try {
                    for (Ingreso i : ContasocDAO.leerTabla("Ingresos").stream().map(Parsers::ingresoParser).toList()) {
                        String[] ingArr = i.toString().split(";");
                        ingresos.add(
                                ingArr[0] + ";" + ContasocDAO.select("Socios", new Object[] {"nombre"}, "numeroSocio = " + ingArr[0])
                                        + ";" + ingArr[1] + ";" + ingArr[2] + ";" + ingArr[3] + ";" + ingArr[4]);
                    }
                } catch (SQLException e) {
                    ContasocLogger.dispatchSQLException(e);
                }

                PDFPrinter.printStringToPDF(ingresos, 6, new float[]{35f, 170f, 50f, 120f, 45f, 60f},
                        "logohuerto_pdf.png", "Listado de ingresos", true,
                        new String[]{"Nº socio", "Nombre y apellidos", "Fecha", "Concepto", "Cantidad", "Tipo"}, false,
                        10, Contasoc.ESCRITORIO + "/ingresos.pdf");
                ErrorHandler.pdfCreado();

            }
            case "Gastos" -> {
                try {
                    for (Pago p : ContasocDAO.leerTabla("Gastos").stream().map(Parsers::pagoParser).toList()) {
                        gastos.add(p.toString());
                    }
                } catch (SQLException e) {
                    ContasocLogger.dispatchSQLException(e);
                }

                PDFPrinter.printStringToPDF(gastos, 6, new float[]{55f, 90f, 150f, 45f, 75f, 90f},
                        "logohuerto_pdf.png", "Listado de pagos", true,
                        new String[]{"Fecha", "Proveedor", "Concepto", "Cantidad", "Nº Factura", "Tipo"}, false, 10,
                        Contasoc.ESCRITORIO + "/gastos.pdf");
                ErrorHandler.pdfCreado();

            }
            case "ListaEspera" -> {
                try {
                    ContasocDAO.leerTabla("Socios").stream().map(Parsers::socioParser).toList().stream()
                            .filter(x->x.getTipo().equals(TipoSocio.LISTA_ESPERA))
                            .sorted(Comparator.comparing(Socio::getFechaDeAlta)).toList()
                            .forEach(x->listaEspera.add(
                                    x.getNumeroSocio() + ";" + x.getNombre() + ";" + x.getTelefono()
                                            + ";" + x.getEmail() + ";" + Parsers.dateParser(x.getFechaDeAlta())));
                } catch (SQLException e) {
                    ContasocLogger.dispatchSQLException(e);
                }

                PDFPrinter.printStringToPDF(listaEspera, 5, new float[]{40f, 170f, 65f, 145f, 45f},
                        "logohuerto_pdf.png", "Lista de espera", true,
                        new String[]{"Nº Socio", "Nombre y apellidos", "Teléfono", "Correo", "F. Alta"}, false, 10,
                        Contasoc.ESCRITORIO + "/lista_de_espera.pdf");
                ErrorHandler.pdfCreado();

            }
        }
    }

    protected static void addListenerToSearchBar() {
        UIContasoc.buscarField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String text = UIContasoc.buscarField.getText();
                if(!text.isEmpty()) {
                    fillTableFrom2DArray(
                            UIContasoc.sociosTabla,
                            search(text),
                            new String[]{"Socio", "Huerto", "Nombre", "DNI", "Teléfono", "Correo",
                                    "F. Alta", "F. Entrega", "F. Baja", "Notas", "Tipo", "Estado"}
                    );
                } else {
                    GUIManager.populateGUITables();
                }
            }
        });
    }

    protected static Object[][] search(String text) {
        DefaultTableModel model = (DefaultTableModel) UIContasoc.sociosTabla.getModel();
        int columnCount = model.getColumnCount();
        List<String> aux = null;
        try {
            aux = ContasocDAO.leerTabla("Socios").stream()
                    .filter(s -> s.split(";")[0].toLowerCase().contains(text.toLowerCase()) ||
                            s.split(";")[2].toLowerCase().contains(text.toLowerCase()))
                    .toList();
        } catch (SQLException e) {
            ContasocLogger.dispatchSQLException(e);
        }

        Object[][] result = new Object[aux.size()][columnCount];
        for (int i = 0; i < aux.size(); i++) {
            result[i] = aux.get(i).replace("null","").split(";");
        }
        return result;
    }

    protected static void fillTableFrom2DArray(JTable table, Object[][] data, String[] columnNames) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }

    public static void addFormatterFactory(JFormattedTextField textField, FormatterType formatterType) throws ParseException {
        switch (formatterType) {
            case DATE:
                textField.setFormatterFactory(new DefaultFormatterFactory(
                        new javax.swing.text.MaskFormatter("##/##/####")
                ));
                break;
            case ID:
                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        boolean isNumber = !((c >= '0') && (c <= '9') ||
                                (c == KeyEvent.VK_BACK_SPACE) ||
                                (c == KeyEvent.VK_DELETE) ||
                                (c == KeyEvent.VK_ENTER));
                        if(textField.getText().length() >= 3 || isNumber) {
                            toolkit.beep();
                            e.consume();
                        }
                    }
                });
                break;
            case DNI:
                textField.setFormatterFactory(new DefaultFormatterFactory(
                        new javax.swing.text.MaskFormatter("########U")
                ));
                break;
            case PHONE:
                textField.setFormatterFactory(new DefaultFormatterFactory(
                        new javax.swing.text.MaskFormatter("#########")
                ));
                break;
            case EMAIL:
                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!(Character.isLetterOrDigit(c) || c == '_' || c == '.' || c == '-' || c == '@' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_ENTER)) {
                            toolkit.beep();
                            e.consume();  // ignore the event
                        }
                    }
                });
                break;
            case DECIMAL:
                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!(Character.isDigit(c) || c == '.' || c == ',' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_ENTER)) {
                            toolkit.beep();
                            e.consume();  // ignore the event
                        }
                    }
                });
                break;

        }
    }
}
