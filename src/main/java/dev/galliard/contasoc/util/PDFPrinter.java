package dev.galliard.contasoc.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
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
    Document document = null;
    if(rotar){
        document = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 10f);
    } else{
        document = new Document(PageSize.A4, 10f, 10f, 10f, 10f);
    }
    try {
        PdfWriter.getInstance(document, new FileOutputStream(outFile));
        document.open();

        // Encabezado
        Paragraph header = new Paragraph();
        header.add(new Phrase("Asociación Huertos la Salud Bellavista\nC/ Cronos SN, Bellavista, 41014 Sevilla\nhuertoslasaludbellavista@gmail.com"));
        header.setAlignment(Element.ALIGN_RIGHT);
        document.add(header);

        // Logo
        String resourcePath;
        if (PDFPrinter.class.getResource("resources/images/" + logoPath) != null) {
            // Estás en un entorno de desarrollo (IDE)
            resourcePath = "resources/images/" + logoPath;
        } else {
            // Estás en un JAR
            resourcePath = "images/" + logoPath;
        }
        Image logo = Image.getInstance(PDFPrinter.class.getClassLoader().getResource(resourcePath));
        logo.setAbsolutePosition(10f, document.getPageSize().getHeight()-87f);
        document.add(logo);

        // Título de la tabla
        Paragraph title = new Paragraph(tituloTabla);
        title.setFont(new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Tabla
        PdfPTable table = new PdfPTable(numColumns);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        if(mostrarTitulos){
            // Títulos de las columnas
            for (String columnTitle : columnTitles) {
                PdfPCell cell = new PdfPCell(new Phrase(columnTitle,new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setBorderWidthTop(1f);
                cell.setBorderWidthBottom(1f);
                cell.setBorderWidthLeft(0f);
                cell.setBorderWidthRight(0f);
                table.addCell(cell);
            }
        }

        // Datos
        for (String input : inputs) {
            String[] fields = input.split(";");
            if (fields.length != numColumns) {
                System.out.println("La cadena de entrada debe tener " + numColumns + " campos separados por ';'");
                return;
            }
            for (String field : fields) {
                PdfPCell cell = new PdfPCell(new Phrase(field,getFont(tamañoFuente)));
                cell.setBorderWidthTop(0f);
                cell.setBorderWidthBottom(0.5f);
                cell.setBorderWidthLeft(0f);
                cell.setBorderWidthRight(0f);
                table.addCell(cell);
            }
        }

        table.setWidths(columnWidths);

        document.add(table);


    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        document.close();
    }
}

    public static void generarBalancePDF(String outFile) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outFile));
            document.open();

            PdfPTable table = new PdfPTable(2); // 2 columns
            PdfPCell cell;

            cell = new PdfPCell(new Phrase("BALANCE AÑO YYYY"));
            cell.setColspan(2);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            table.addCell("INGRESOS BANCO");
            table.addCell("INGRESOS CAJA");

            for (int i = 0; i < 10; i++) { // Add 10 rows of example data
                table.addCell("");
                table.addCell("");
            }

            cell = new PdfPCell(new Phrase("Total:"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(""));
            table.addCell(cell);

            // Similar cells can be added for GASTOS BANCO and GASTOS CAJA

            document.add(table);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }


    public static void printTableToPDF(JTable table, String filename, int tamañoFuente) {
        Document document = new Document(new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()).rotate());
        document.setMargins(0, 0, 10f, 10f);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
            float[] columnWidths = { 65f, 105f, 40f, 185f, 35f, 90f, 15f, 15f, 35f, 35f, 35f, 90f };
            pdfTable.setWidths(columnWidths);
            pdfTable.setTotalWidth(745f);
            pdfTable.setLockedWidth(true);
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    Object value = table.getValueAt(i, j);
                    PdfPCell cell = new PdfPCell(new Paragraph(value.toString(), getFont(tamañoFuente)));
                    pdfTable.addCell(cell);
                }
            }
            document.add(pdfTable);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el documento
            document.close();
        }
    }

    private static Font getFont(int tamaño) {
        Font font = new Font(Font.FontFamily.HELVETICA, tamaño);
        return font;
    }
}