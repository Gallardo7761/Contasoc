package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.common.TipoSocio;
import dev.galliard.contasoc.ingreso.Ingreso;
import dev.galliard.contasoc.persona.Socio;
import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.common.Estado;
import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.pago.Pago;
import dev.galliard.contasoc.util.ErrorHandler;
import dev.galliard.contasoc.util.PDFPrinter;
import dev.galliard.contasoc.util.Parsers;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GUIManager {
    protected static String valor = null;
    protected static Double inicialBanco = 0.0;
    protected static Double inicialCaja = 0.0;
    protected static void populateGUITables () {
        ContasocDAO.fillTableFrom(UIContasoc.sociosTabla, "Socios");
        ContasocDAO.fillTableFrom(UIContasoc.ingresosTabla, "Ingresos");
        ContasocDAO.fillTableFrom(UIContasoc.gastosTabla, "Gastos");
        ContasocDAO.fillListaEspera(UIContasoc.listaEsperaTabla);
    }

    protected static void importBDD() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona el archivo para importar");
        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            File destino = new File("C:/Contasoc/contasoc2.db");

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
        File origen = new File("C:/Contasoc/contasoc2.db");

        if (origen.exists()) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selecciona la ubicación para exportar");
            fileChooser.setSelectedFile(new File(System.getProperty("user.home") + "/Desktop/contasoc2.db"));
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
        Double banco = !ContasocDAO.leerTabla("Balance").isEmpty()
                ? Double.parseDouble(ContasocDAO.select("Balance",new Object[] {"inicialBanco"}, ""))
                : inicialBanco;
        Double caja = !ContasocDAO.leerTabla("Balance").isEmpty()
                ? Double.parseDouble(ContasocDAO.select("Balance",new Object[] {"inicialCaja"}, ""))
                : inicialCaja;

        List<Ingreso> ingresos = ContasocDAO.leerTabla("ingresos").stream().map(Parsers::ingresoParser).toList();
        List<Pago> pagos = ContasocDAO.leerTabla("gastos").stream().map(Parsers::pagoParser).toList();

        Double totalIngresosBanco = ingresos.stream().filter(x -> x.getTipo().equals(TipoPago.BANCO)).mapToDouble(Ingreso::getCantidad).sum();
        Double totalIngresosCaja = ingresos.stream().filter(x -> x.getTipo().equals(TipoPago.CAJA)).mapToDouble(Ingreso::getCantidad).sum();
        double totalPagosBanco = pagos.stream().filter(x -> x.getTipo().equals(TipoPago.BANCO)).mapToDouble(Pago::getCantidad).sum();
        double totalPagosCaja = pagos.stream().filter(x -> x.getTipo().equals(TipoPago.CAJA)).mapToDouble(Pago::getCantidad).sum();

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        String saldoBanco = df.format((banco + totalIngresosBanco - totalPagosBanco));
        String saldoCaja = df.format((caja + totalIngresosCaja - totalPagosCaja));

        UIContasoc.tBancoIngresosValue.setText(String.valueOf(totalIngresosBanco)+" €");
        UIContasoc.tBancoGastosValue.setText(String.valueOf(totalPagosBanco)+" €");
        UIContasoc.tCajaIngresosValue.setText(String.valueOf(totalIngresosCaja)+" €");
        UIContasoc.tCajaGastosValue.setText(String.valueOf(totalPagosCaja)+" €");
        UIContasoc.saldoBancoValue.setText(String.valueOf(saldoBanco)+" €");
        UIContasoc.saldoCajaValue.setText(String.valueOf(saldoCaja)+" €");
    }

    protected static void printContent() {
        List<String> socios = new ArrayList<>();
        List<String> listaEspera = new ArrayList<>();
        List<String> ingresos = new ArrayList<>();
        List<String> gastos = new ArrayList<>();

        switch (valor) {
            case "Socios" -> {
                for (Socio s : ContasocDAO.leerTabla("Socios").stream().map(Parsers::socioParser).toList()) {
                    if (s.getEstado() != Estado.INACTIVO) {
                        String socio =
                                s.getSocio() + ";" +
                                        s.getHuerto() + ";" +
                                        s.getPersona().getNombre() + ";" +
                                        s.getPersona().getDni() + ";" +
                                        s.getPersona().getTelefono() + ";" +
                                        s.getPersona().getCorreo() + ";" +
                                        Parsers.dateParser(s.getAlta()) + ";" +
                                        Parsers.dateParser(s.getEntrega()) + ";" +
                                        Parsers.dateParser(s.getBaja()) + ";" +
                                        s.getNotas() + ";" +
                                        s.getTipo().toString()
                                                .replace("A_E", "A DE E")
                                                .replace("O_INVERNADERO", "O + INV") + ";" +
                                        s.getEstado();
                        System.out.println(socio);
                        socios.add(Arrays.stream(socio.split(";"))
                                .map(x -> x.equals("null") ? "" : x)
                                .collect(Collectors.joining(";")));
                    }
                }

                PDFPrinter.printStringToPDF(socios, 12,
                        new float[]{30f, 25f, 200f, 80f, 70f, 170f, 55f, 55f, 55f, 120f, 110f, 50f},
                        "logohuerto_pdf.png", "Listado de socios", true, new String[]{"S", "H", "Nombre", "DNI",
                                "Teléfono", "Correo", "Alta", "Entrega", "Baja", "Notas", "Tipo", "Estado"},
                        true, 8, Contasoc.ESCRITORIO + "/socios.pdf");
                ErrorHandler.pdfCreado();
            }
            case "Ingresos" -> {
                for (Ingreso i : ContasocDAO.leerTabla("Ingresos").stream().map(Parsers::ingresoParser).toList()) {
                    String[] ingArr = i.toString().split(";");
                    ingresos.add(
                            ingArr[0] + ";" + ContasocDAO.select("Socios", new Object[] {"nombre"}, "numeroSocio = " + ingArr[0])
                                    + ";" + ingArr[1] + ";" + ingArr[2] + ";" + ingArr[3] + ";" + ingArr[4]);
                }

                PDFPrinter.printStringToPDF(ingresos, 6, new float[]{35f, 170f, 50f, 120f, 45f, 60f},
                        "logohuerto_pdf.png", "Listado de ingresos", true,
                        new String[]{"Nº socio", "Nombre y apellidos", "Fecha", "Concepto", "Cantidad", "Tipo"}, false,
                        10, Contasoc.ESCRITORIO + "/ingresos.pdf");
                ErrorHandler.pdfCreado();
                System.out.println("Ingresos");
            }
            case "Gastos" -> {
                for (Pago p : ContasocDAO.leerTabla("Gastos").stream().map(Parsers::pagoParser).toList()) {
                    gastos.add(p.toString());
                }

                PDFPrinter.printStringToPDF(gastos, 6, new float[]{55f, 90f, 150f, 45f, 75f, 90f},
                        "logohuerto_pdf.png", "Listado de pagos", true,
                        new String[]{"Fecha", "Proveedor", "Concepto", "Cantidad", "Nº Factura", "Tipo"}, false, 10,
                        Contasoc.ESCRITORIO + "/gastos.pdf");
                ErrorHandler.pdfCreado();
                System.out.println("Gastos");
            }
            case "ListaEspera" -> {
                ContasocDAO.leerTabla("Socios").stream().map(Parsers::socioParser).toList().stream()
                        .filter(x->x.getTipo().equals(TipoSocio.LISTA_ESPERA))
                        .sorted(Comparator.comparing(Socio::getAlta)).toList()
                        .forEach(x->listaEspera.add(
                                x.getSocio() + ";" + x.getPersona().getNombre() + ";" + x.getPersona().getTelefono()
                                        + ";" + x.getPersona().getCorreo() + ";" + Parsers.dateParser(x.getAlta())));

                PDFPrinter.printStringToPDF(listaEspera, 5, new float[]{40f, 170f, 65f, 145f, 45f},
                        "logohuerto_pdf.png", "Lista de espera", true,
                        new String[]{"Nº Socio", "Nombre y apellidos", "Teléfono", "Correo", "F. Alta"}, false, 10,
                        Contasoc.ESCRITORIO + "/lista_de_espera.pdf");
                ErrorHandler.pdfCreado();
                System.out.println("ListaEspera");
            }
        }
    }
}
