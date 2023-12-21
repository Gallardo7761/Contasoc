package es.exmaster.contasoc.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;
import es.exmaster.contasoc.Contasoc;

import java.io.FileOutputStream;

public class ReciboPrinter {
	public static void generarRecibo(Integer numero, Integer numSocio, String ciudad, Integer dia, String mes, Integer anyo,
			String nombre, Double cantidad, String concepto) {
		Document document = new Document(new Rectangle(453.0f, 283.5f)); // Tamaño en puntos (1 cm = 28.35 puntos)

		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(Contasoc.ESCRITORIO + "/recibo_No"+numero+"_Soc"+numSocio+".pdf"));
			document.open();

			// Configurar fuente y tamaño
			float fontSize = 12;
			Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA, fontSize + 8, Font.BOLD);

			// Agregar imagen de marca de agua
			PdfContentByte content = writer.getDirectContentUnder();
			Image img = Image.getInstance(ReciboPrinter.class.getClassLoader().getResource("imagenes/logohuerto_sinletras.png"));
			img.scaleAbsolute(353.0f, 133.5f);
			img.setAbsolutePosition((document.getPageSize().getWidth() - img.getScaledWidth()) / 2,
					(document.getPageSize().getHeight() - img.getScaledHeight()) / 2); // Centrar la imagen
			// Configurar la transparencia de la imagen de marca de agua
			PdfGState gs1 = new PdfGState();
			gs1.setFillOpacity(0.3f); // Cambia la transparencia (0.0f a 1.0f)
			content.setGState(gs1);
			content.addImage(img);

			// Crear bordes
			Rectangle rect = new Rectangle(20, 20, 433.0f, 263.5f);
			rect.setBorder(Rectangle.BOX);
			rect.setBorderWidth(2);
			document.add(rect);

			// Encabezado del recibo
			Paragraph encabezado = new Paragraph();
			encabezado.setFont(tituloFont);
			encabezado.add("RECIBO ");
			encabezado.add(new Paragraph("Nº" + numero, FontFactory.getFont(FontFactory.HELVETICA, fontSize))); 
			encabezado.setAlignment(Paragraph.ALIGN_LEFT);
			encabezado.setSpacingBefore(10);
			document.add(encabezado);

			// Fecha
			Paragraph fechaParag = new Paragraph("En " + ciudad + " a " + dia + " de " + mes + " de " + anyo,
					FontFactory.getFont(FontFactory.HELVETICA, fontSize)); // Poner la fecha en negrita
			fechaParag.setAlignment(Paragraph.ALIGN_LEFT);
			fechaParag.setSpacingBefore(10);
			document.add(fechaParag);

			// Recibido de
			Paragraph recibidoDe = new Paragraph("Recibí de " + nombre + " con Nº de Socio " + numSocio
					+ " la cantidad de " + cantidad + " euros (€)",
					FontFactory.getFont(FontFactory.HELVETICA, fontSize)); // Poner los valores en negrita
			recibidoDe.setSpacingBefore(10);
			document.add(recibidoDe);

			// Concepto
			Paragraph conceptoParrafo = new Paragraph("Por concepto de " + concepto,
					FontFactory.getFont(FontFactory.HELVETICA, fontSize)); // Poner el concepto en negrita y cursiva
			conceptoParrafo.setSpacingBefore(10);
			document.add(conceptoParrafo);

			document.close();
			System.out.println("Recibo personalizado creado exitosamente.");
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
