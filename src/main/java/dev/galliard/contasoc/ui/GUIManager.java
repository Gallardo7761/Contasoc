package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.common.*;
import dev.galliard.contasoc.database.DBUtils;
import dev.galliard.contasoc.database.objects.Balance;
import dev.galliard.contasoc.database.objects.Gastos;
import dev.galliard.contasoc.database.objects.Ingresos;
import dev.galliard.contasoc.database.objects.Socios;
import dev.galliard.contasoc.util.ErrorHandler;
import dev.galliard.contasoc.util.PDFPrinter;
import dev.galliard.contasoc.util.Parsers;
import org.hibernate.exception.ConstraintViolationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GUIManager {
    protected static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static PrintAction valor;

    protected static void populateGUITables() {
        try {
            DBUtils.fillSocios();
            DBUtils.fillIngresos();
            DBUtils.fillGastos();
            DBUtils.fillListaEspera();
        } catch (ConstraintViolationException e) {
            Contasoc.logger.error("Error SQL", e);
        }
    }

    protected static void calcularBalance(Balance balance, List<Ingresos> ingresos, List<Gastos> gastos) {
        Double banco = balance.getInicialBanco();
        Double caja = balance.getInicialCaja();
        Double totalIngresosBanco = ingresos.stream().filter(x -> x.getTipo().equals(TipoPago.BANCO.name())).mapToDouble(Ingresos::getCantidad).sum();
        Double totalIngresosCaja = ingresos.stream().filter(x -> x.getTipo().equals(TipoPago.CAJA.name())).mapToDouble(Ingresos::getCantidad).sum();
        double totalPagosBanco = gastos.stream().filter(x -> x.getTipo().equals(TipoPago.BANCO.name())).mapToDouble(Gastos::getCantidad).sum();
        double totalPagosCaja = gastos.stream().filter(x -> x.getTipo().equals(TipoPago.CAJA.name())).mapToDouble(Gastos::getCantidad).sum();

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        String saldoBanco = df.format((banco + totalIngresosBanco - totalPagosBanco));
        String saldoCaja = df.format((caja + totalIngresosCaja - totalPagosCaja));

        UIContasoc.tBancoIngresosValue.setText(df.format(totalIngresosBanco) + " €");
        UIContasoc.tBancoGastosValue.setText(df.format(totalPagosBanco) + " €");
        UIContasoc.tCajaIngresosValue.setText(df.format(totalIngresosCaja) + " €");
        UIContasoc.tCajaGastosValue.setText(df.format(totalPagosCaja) + " €");
        UIContasoc.saldoBancoValue.setText(saldoBanco + " €");
        UIContasoc.saldoCajaValue.setText(saldoCaja + " €");
    }

    protected static void printContent() {
        List<String> socios = new ArrayList<>();
        List<String> listaEspera = new ArrayList<>();
        List<String> ingresos = new ArrayList<>();
        List<String> gastos = new ArrayList<>();

        switch (valor) {
            case SOCIOS -> {
                getSocios().stream()
                        .filter(s -> s.getEstado() == Estado.ACTIVO)
                        .forEach(s -> {
                            String nSocio = s.getNumeroSocio().toString();
                            String nHuerto = s.getNumeroHuerto().toString();
                            String nombre = s.getNombre();
                            String dni = s.getDni();
                            String telefono = s.getTelefono().toString();
                            String correo = s.getEmail();
                            String alta = s.getFechaDeAlta() == null ? "" : Parsers.dashDateParser(s.getFechaDeAlta().toString());
                            String entrega = s.getFechaDeEntrega() == null ? "" : Parsers.dashDateParser(s.getFechaDeEntrega().toString());
                            String baja = s.getFechaDeBaja() == null ? "" : Parsers.dashDateParser(s.getFechaDeBaja().toString());
                            String tipo = s.getTipo().toString();
                            String estado = s.getEstado().toString();
                            socios.add(nSocio + ";" + nHuerto + ";" + nombre + ";" + dni + ";" + telefono + ";" + correo + ";" + alta + ";" + entrega + ";" + baja + ";" +tipo + ";" + estado);
                        });
                PDFPrinter.printStringToPDF(socios.stream().map(s -> s.replace("null", "")).toList(), 11,
                        new float[]{35f, 35f, 250f, 85f, 80f, 140f, 85f, 85f, 85f, 110f, 50f},
                        "logohuerto_pdf.png", "Listado de socios", true, new String[]{"Soc", "Hue", "Nombre", "DNI",
                                "Teléfono", "Correo", "Alta", "Entrega", "Baja", "Tipo", "Estado"},
                        true, 8, Contasoc.ESCRITORIO + "/socios.pdf");
                ErrorHandler.pdfCreado();
            }
            case INGRESOS -> {
                getIngresos().stream()
                        .forEach(i -> {
                            String nSocio = i.getNumeroSocio().toString();
                            String nombre = GUIManager.getSocios().stream()
                                    .filter(x -> x.getNumeroSocio().equals(i.getNumeroSocio()) )
                                    .map(Socios::getNombre).findFirst().orElse("null");
                            String fecha = Parsers.dashDateParser(i.getFecha().toString());
                            String concepto = i.getConcepto();
                            String cantidad = i.getCantidad().toString() + " €";
                            String tipo = i.getTipo();
                            ingresos.add(nSocio + ";" + nombre + ";" + fecha + ";" + concepto + ";" + cantidad + ";" + tipo);
                        });
                PDFPrinter.printStringToPDF(ingresos, 6, new float[]{35f, 160f, 85f, 250f, 45f, 60f},
                        "logohuerto_pdf.png", "Listado de ingresos", true,
                        new String[]{"Nº socio", "Nombre y apellidos", "Fecha", "Concepto", "Cantidad", "Tipo"}, true,
                        10, Contasoc.ESCRITORIO + "/ingresos.pdf");
                ErrorHandler.pdfCreado();
            }
            case GASTOS -> {
                getGastos().stream()
                        .forEach(g -> {
                            String fecha = Parsers.dashDateParser(g.getFecha().toString());
                            String proveedor = g.getProveedor();
                            String concepto = g.getConcepto();
                            String cantidad = g.getCantidad().toString() + " €";
                            String factura = g.getFactura();
                            String tipo = g.getTipo();
                            gastos.add(fecha + ";" + proveedor + ";" + concepto + ";" + cantidad + ";" + factura + ";" + tipo);
                        });
                PDFPrinter.printStringToPDF(gastos, 6, new float[]{55f, 90f, 150f, 45f, 75f, 90f},
                        "logohuerto_pdf.png", "Listado de gastos", true,
                        new String[]{"Fecha", "Proveedor", "Concepto", "Cantidad", "Nº Factura", "Tipo"}, true, 10,
                        Contasoc.ESCRITORIO + "/gastos.pdf");
                ErrorHandler.pdfCreado();

            }
            case LISTA_ESPERA -> {
                int i = 1;
                for (Socios s : getSocios()) {
                    if (s.getEstado() != Estado.INACTIVO && s.getTipo().equals(TipoSocio.LISTA_ESPERA)) {
                        String socio =
                                i + ";" +
                                s.getNumeroSocio() + ";" +
                                        s.getNombre() + ";" +
                                        s.getTelefono() + ";" +
                                        s.getEmail() + ";" +
                                        Parsers.dashDateParser(s.getFechaDeAlta().toString()).replace("null", "");
                        listaEspera.add(socio);
                        i++;
                    }
                }
                System.out.println(listaEspera);
                PDFPrinter.printStringToPDF(listaEspera, 6, new float[]{35f, 35f, 200f, 70f, 145f, 75f},
                        "logohuerto_pdf.png", "Lista de espera", true,
                        new String[]{"Pos", "Soc", "Nombre y apellidos", "Teléfono", "Correo", "F. Alta"}, false, 10,
                        Contasoc.ESCRITORIO + "/lista_de_espera.pdf");
                ErrorHandler.pdfCreado();
            }
        }
    }

    protected static List<Socios> getSocios() {
        DefaultListModel<SocioPanel> model = (DefaultListModel<SocioPanel>) UIContasoc.sociosLista.getModel();
        return Stream.iterate(0, i -> i + 1)
                .limit(model.getSize())
                .map(i -> model.get(i).getSocio())
                .collect(Collectors.toList());
    }

    public static List<Ingresos> getIngresos() {
        DefaultListModel<IngresoPanel> model = (DefaultListModel<IngresoPanel>) UIContasoc.ingresosLista.getModel();
        return Stream.iterate(0, i -> i + 1)
                .limit(model.getSize())
                .map(i -> model.get(i).getIngreso())
                .collect(Collectors.toList());
    }

    protected static List<Gastos> getGastos() {
        DefaultListModel<GastoPanel> model = (DefaultListModel<GastoPanel>) UIContasoc.gastosLista.getModel();
        return Stream.iterate(0, i -> i + 1)
                .limit(model.getSize())
                .map(i -> model.get(i).getGasto())
                .collect(Collectors.toList());
    }

    public static void addFormatterFactory(JTextField textField, FormatterType formatterType) throws ParseException {
        switch (formatterType) {
            case DATE:
                textField.putClientProperty("JTextField.placeholderText", "d mes aaaa");
                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        boolean isNotDate = !((c >= '0') && (c <= '9') ||
                                (c >= 'a') && (c <= 'z') ||
                                (c == KeyEvent.VK_BACK_SPACE) ||
                                (c == KeyEvent.VK_SPACE) ||
                                (c == KeyEvent.VK_DELETE) ||
                                (c == KeyEvent.VK_ENTER));
                        if (textField.getText().length() >= 11 || isNotDate) {
                            toolkit.beep();
                            e.consume();
                        }
                    }
                });
                break;
            case ID:
                textField.putClientProperty("JTextField.placeholderText", "NNN");
                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        boolean isNotNumber = !((c >= '0') && (c <= '9') ||
                                (c == KeyEvent.VK_BACK_SPACE) ||
                                (c == KeyEvent.VK_DELETE) ||
                                (c == KeyEvent.VK_ENTER));
                        if (isNotNumber) {
                            toolkit.beep();
                            e.consume();
                        }
                    }
                });
                break;
            case DNI:
                textField.putClientProperty("JTextField.placeholderText", "12345678A");
                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        boolean isNotDni = !((c >= '0' && c <= '9') || Character.isAlphabetic(c) ||
                                c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_ENTER);
                        if (textField.getText().length() >= 9 || isNotDni) {
                            Toolkit.getDefaultToolkit().beep();
                            e.consume();
                        }
                    }
                });
                break;

            case PHONE:
                textField.putClientProperty("JTextField.placeholderText", "600700800");
                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        boolean isNotNumber = !((c >= '0') && (c <= '9') ||
                                (c == KeyEvent.VK_BACK_SPACE) ||
                                (c == KeyEvent.VK_DELETE) ||
                                (c == KeyEvent.VK_ENTER));
                        if (textField.getText().length() >= 9 || isNotNumber) {
                            toolkit.beep();
                            e.consume();
                        }
                    }
                });
                break;
            case EMAIL:
                textField.putClientProperty("JTextField.placeholderText", "usuario@sitio.dominio");
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
                textField.putClientProperty("JTextField.placeholderText", "NN,NN");
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

    protected static void addListenerToSearchBar(JList<SocioPanel> listaSocios, DefaultListModel<SocioPanel> modeloOriginal) {
        JTextField searchBar = UIContasoc.buscarField; // Suponiendo que buscarField es tu campo de búsqueda
        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String text = searchBar.getText().toLowerCase(); // Convertimos el texto a minúsculas para una comparación más fácil
                DefaultListModel<SocioPanel> model = (DefaultListModel<SocioPanel>) listaSocios.getModel();
                DefaultListModel<SocioPanel> filteredModel = new DefaultListModel<>();

                if (text.isEmpty()) {
                    // Si el texto está vacío, restablecemos el modelo original
                    listaSocios.setModel(modeloOriginal);
                    return;
                }

                // Iteramos sobre los elementos de la lista para filtrar
                for (int i = 0; i < model.getSize(); i++) {
                    SocioPanel panel = model.getElementAt(i);
                    String socioInfo = panel.getSocio().toString().toLowerCase(); // Suponiendo que SocioPanel tiene un método toString() representativo
                    if (socioInfo.contains(text)) {
                        filteredModel.addElement(panel);
                    }
                }

                // Actualizamos la lista con los elementos filtrados
                listaSocios.setModel(filteredModel);
            }
        });
    }


}
