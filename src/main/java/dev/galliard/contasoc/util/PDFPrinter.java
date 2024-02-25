package dev.galliard.contasoc.util;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TabAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.List;

public class PDFPrinter {

    public static void printStringToPDF(
            List<String> inputs,
            int numColumns,
            float[] columnWidths,
            String logoPath,
            String tituloTabla,
            boolean mostrarTitulos,
            String[] columnTitles,
            boolean rotar,
            int tamañoFuente,
            String outFile) {
        PdfDocument pdfDoc = null;
        try {
            FileOutputStream outputStream = new FileOutputStream(outFile);
            pdfDoc = new PdfDocument(new PdfWriter(outputStream));
            Document document = new Document(pdfDoc, rotar ? PageSize.A4.rotate() : PageSize.A4);
            addHeader(document);
            addLogo(document, logoPath);
            addTitle(document, tituloTabla, 18);
            addTable(document, inputs, numColumns, columnWidths, mostrarTitulos, columnTitles, tamañoFuente);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pdfDoc != null) {
                pdfDoc.close();
            }
        }
    }

    public static void printBalance(
            String logoPath,
            String outFile,
            String[] ingresosBanco,
            String[] ingresosCaja,
            String[] gastosBanco,
            String[] gastosCaja,
            String saldoActualBanco,
            String saldoActualCaja,
            String inicialBanco,
            String inicialCaja) {
        PdfDocument pdfDoc = null;
        try {
            FileOutputStream outputStream = new FileOutputStream(outFile);
            pdfDoc = new PdfDocument(new PdfWriter(outputStream));
            Document document = new Document(pdfDoc, PageSize.A4);
            addHeader(document);
            addLogo(document, logoPath);
            addTitle(document, "Balance " + LocalDateTime.now().getYear(), 18);
            addParagraph(document, "Generado el " + LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " a las " + LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")) + " horas");
            addParagraph(document, "Saldo inicial banco: " + inicialBanco + ", Saldo inicial caja: " + inicialCaja);
            addBalanceTable(document, ingresosBanco, ingresosCaja, gastosBanco, gastosCaja, saldoActualBanco, saldoActualCaja, inicialBanco, inicialCaja);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pdfDoc != null) {
                pdfDoc.close();
            }
        }
    }

    private static void addBalanceTable(
            Document document,
            String[] ingresosBanco,
            String[] ingresosCaja,
            String[] gastosBanco,
            String[] gastosCaja,
            String saldoActualBanco,
            String saldoActualCaja,
            String inicialBanco,
            String inicialCaja) {
        float[] columnWidths = {50f, 50f};
        String[] columnTitles = {"Ingresos banco", "Ingresos caja"};

        addSubTable(document, columnTitles, ingresosBanco, ingresosCaja);

        String[] gastoColumnTitles = {"Gastos banco", "Gastos caja"};
        addSubTable(document, gastoColumnTitles, gastosBanco, gastosCaja);

        String[] saldoColumnTitles = {"Saldo actual banco", "Saldo actual caja"};
        addSubTable(document, saldoColumnTitles,
                    new String[]{"Inicial;"+inicialBanco, "Actual;"+saldoActualBanco},
                    new String[]{"Inicial;"+inicialCaja, "Actual;"+saldoActualCaja}
                );
    }

    private static void addSubTable(
            Document document,
            String[] columnTitles,
            String[] bancoData,
            String[] cajaData) {
        float[] columnWidths = {50f, 50f};
        boolean mostrarTitulos = true;
        int tamañoFuente = 10;

        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        PdfFont font = null;
        PdfFont fontBold = null;
        try {
            font = PdfFontFactory.createFont("Helvetica");
            fontBold = PdfFontFactory.createFont("Helvetica-Bold");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mostrarTitulos) {
            for (String columnTitle : columnTitles) {
                table.addCell(createCell(columnTitle, true, fontBold, 16));
            }
        }

        for (int i = 0; i < Math.max(bancoData.length, cajaData.length); i++) {
            String bancoValue = i < bancoData.length ? bancoData[i] : "";
            String cajaValue = i < cajaData.length ? cajaData[i] : "";

            String bContent1 = bancoValue.contains(";") ? bancoValue.split(";")[0] : "";
            String bContent2 = bancoValue.contains(";") ? bancoValue.split(";")[1] : "";

            String cContent1 = cajaValue.contains(";") ? cajaValue.split(";")[0] : "";
            String cContent2 = cajaValue.contains(";") ? cajaValue.split(";")[1] : "";

            table.addCell(createCellWithSpacePoints(bContent1, bContent2, font, tamañoFuente, table.getColumnWidth(0).getValue()));
            table.addCell(createCellWithSpacePoints(cContent1, cContent2, font, tamañoFuente, table.getColumnWidth(1).getValue()));
        }
        table.setBorderBottom(new SolidBorder(1));
        document.add(table);
    }

    private static void addHeader(Document document) {
        Paragraph header = new Paragraph();
        header.add("Asociación Huertos la Salud Bellavista\nC/ Cronos SN, Bellavista, 41014 Sevilla\nhuertoslasaludbellavista@gmail.com");
        header.setTextAlignment(TextAlignment.RIGHT);
        document.add(header);
    }

    private static void addLogo(Document document, String logoPath) throws MalformedURLException {
        String resourcePath = "/images/" + logoPath;
        Image logo = new Image(ImageDataFactory.create(PDFPrinter.class.getResource(resourcePath)));
        logo.setFixedPosition(35, document.getPdfDocument().getDefaultPageSize().getTop() - 115);
        document.add(logo);
    }

    private static void addWatermark(Document document, String logoPath) {
        String resourcePath = "/images/" + logoPath;
        Image logo = new Image(ImageDataFactory.create(PDFPrinter.class.getResource(resourcePath)));
        logo.setOpacity(0.3f); // Ajusta la opacidad del logo
        float documentWidth = document.getPdfDocument().getDefaultPageSize().getWidth();
        float documentHeight = document.getPdfDocument().getDefaultPageSize().getHeight();
        float logoWidth = logo.getImageScaledWidth();
        float logoHeight = logo.getImageScaledHeight();
        float x = (documentWidth - logoWidth) / 2;
        float y = (documentHeight - logoHeight) / 2;
        logo.setFixedPosition(x, y);
        document.add(logo);
    }

    private static void addTitle(Document document, String tituloTabla, int tamañoFuente) {
        Paragraph title = new Paragraph(tituloTabla);
        title.setFontSize(tamañoFuente).setBold();
        title.setTextAlignment(TextAlignment.CENTER);
        document.add(title);
    }

    private static void addParagraph(Document document, String paragraph) {
        Paragraph p = new Paragraph(paragraph);
        p.setFontSize(12);
        p.setTextAlignment(TextAlignment.CENTER);
        document.add(p);
    }

    private static void addTable(Document document, List<String> inputs, int numColumns, float[] columnWidths, boolean mostrarTitulos, String[] columnTitles, int tamañoFuente) {
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        // Configuración de la fuente Helvetica tamaño 10
        PdfFont font = null;
        PdfFont fontBold = null;
        try {
            font = PdfFontFactory.createFont("Helvetica");
            fontBold = PdfFontFactory.createFont("Helvetica-Bold");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mostrarTitulos) {
            for (String columnTitle : columnTitles) {
                table.addCell(createCell(columnTitle, true, fontBold, 16)); // Aplicar la fuente al título de la tabla
            }
        }

        for (String input : inputs) {
            String[] fields = input.split(";");
            if (fields.length != numColumns) {
                System.err.println("La cadena de entrada debe tener " + numColumns + " campos separados por ';'");
                return;
            }
            for (String field : fields) {
                table.addCell(createCell(field, false, font, tamañoFuente)); // Aplicar la fuente al contenido de la tabla
            }
        }

        document.add(table);
    }

    private static Cell createCell(String content, boolean isHeader, PdfFont font, int tamañoFuente) {
        Cell cell = new Cell().add(new Paragraph(content).setTextAlignment(TextAlignment.LEFT));
        cell.setBackgroundColor(isHeader ? DeviceGray.makeLighter(DeviceGray.GRAY) : DeviceRgb.WHITE);
        cell.setFont(font).setFontSize(tamañoFuente); // Aplicar la fuente y tamaño a la celda
        return cell;
    }

    private static Cell createCellWithSpacePoints(String content1, String content2, PdfFont font, int tamañoFuente, float ancho) {
        Cell cell = new Cell();

        Paragraph p = new Paragraph(content1);
        p.add(new Tab());
        p.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        p.add(content2);

        cell.setBackgroundColor(DeviceRgb.WHITE);
        cell.setFont(font).setFontSize(tamañoFuente); // Aplica la fuente y el tamaño a la celda
        cell.setBorderBottom(null);
        cell.setBorderTop(null);
        cell.add(p);
        return cell;
    }
}
