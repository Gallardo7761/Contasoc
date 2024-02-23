package dev.galliard.contasoc.util;

import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import dev.galliard.contasoc.Contasoc;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
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
            addTitle(document, tituloTabla);
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

    private static void addTitle(Document document, String tituloTabla) {
        Paragraph title = new Paragraph(tituloTabla);
        title.setFontSize(18).setBold();
        title.setTextAlignment(TextAlignment.CENTER);
        document.add(title);
    }

    private static void addTable(Document document, List<String> inputs, int numColumns, float[] columnWidths, boolean mostrarTitulos, String[] columnTitles, int tamañoFuente) {
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        // Configuración de la fuente Helvetica tamaño 10
        PdfFont font = null;
        try {
            font = PdfFontFactory.createFont("Helvetica");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mostrarTitulos) {
            for (String columnTitle : columnTitles) {
                table.addCell(createCell(columnTitle, true, font, tamañoFuente)); // Aplicar la fuente al título de la tabla
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

    private static com.itextpdf.layout.element.Cell createCell(String content, boolean isHeader, PdfFont font, int tamañoFuente) {
        com.itextpdf.layout.element.Cell cell = new com.itextpdf.layout.element.Cell().add(new Paragraph(content).setTextAlignment(TextAlignment.LEFT));
        cell.setBackgroundColor(isHeader ? DeviceGray.makeLighter(DeviceGray.GRAY) : DeviceRgb.WHITE);
        cell.setFont(font).setFontSize(tamañoFuente); // Aplicar la fuente y tamaño a la celda
        return cell;
    }

}
