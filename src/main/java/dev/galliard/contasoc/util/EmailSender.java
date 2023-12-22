package dev.galliard.contasoc.util;

import dev.galliard.contasoc.Contasoc;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class EmailSender {
	public static String MSG_BEFORE = "<!DOCTYPE html>\r\n"
			+ "<html>\r\n"
			+ "\r\n"
			+ "<head>\r\n"
			+ "    <style>\r\n"
			+ "        /* Inline CSS */\r\n"
			+ "        .container {\r\n"
			+ "            border: 1px solid #dddddd;\r\n"
			+ "            border-radius: 5px;\r\n"
			+ "            padding: 20px;\r\n"
			+ "            max-width: 400px;\r\n"
			+ "            margin: 0 auto;\r\n"
			+ "        }\r\n"
			+ "\r\n"
			+ "        .logo {\r\n"
			+ "            display: block;\r\n"
			+ "            margin: 0 auto;\r\n"
			+ "            max-width: 200px;\r\n"
			+ "        }\r\n"
			+ "\r\n"
			+ "        .title {\r\n"
			+ "            text-align: center;\r\n"
			+ "            font-size: 24px;\r\n"
			+ "            color: #333333;\r\n"
			+ "            margin-top: 20px;\r\n"
			+ "        }\r\n"
			+ "\r\n"
			+ "        .body {\r\n"
			+ "            margin-top: 20px;\r\n"
			+ "            color: #555555;\r\n"
			+ "            font-size: 16px;\r\n"
			+ "        }\r\n"
			+ "\r\n"
			+ "        .contact-info {\r\n"
			+ "            margin-top: 20px;\r\n"
			+ "            font-size: 14px;\r\n"
			+ "            color: #777777;\r\n"
			+ "            text-align: center;\r\n"
			+ "        }\r\n"
			+ "    </style>\r\n"
			+ "</head>\r\n"
			+ "\r\n"
			+ "<body>\r\n"
			+ "    <div class=\"container\">\r\n"
			+ "        <img src=\"https://i.imgur.com/tKVGcuP.png\" class=\"logo\">\r\n"
			+ "        <h4 class=\"title\">Huertos la Salud Bellavista</h4>\r\n"
			+ "        <p class=\"body\">Estimado/s socio/s,</p>\r\n"
			+ "        <p class=\"body\">";
	public static String MSG_AFTER = "</p>\r\n"
			+ "        <p class=\"body\">Atte. La Directiva</p>\r\n"
			+ "        <div class=\"contact-info\">\r\n"
			+ "            <p>Direccion: C/ Cronos SN, Bellavista, Sevilla, 41014</p>\r\n"
			+ "            <p>Email: huertoslasaludbellavista@gmail.com</p>\r\n"
			+ "        </div>\r\n"
			+ "    </div>\r\n"
			+ "</body>\r\n"
			+ "\r\n"
			+ "</html>";
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;
    private static final String SMTP_USERNAME = "huertoslasaludbellavista@gmail.com";
    private static final String SMTP_PASSWORD = "ixdmevohktgjkoau";
    private static final String SENDER_EMAIL = "huertoslasaludbellavista@gmail.com";

    public static void sendEmail(String destinatario, String asunto, String cuerpo) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_SERVER);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setContent(cuerpo, "text/html");

            Transport.send(message);
            System.out.println("Correo enviado");
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
    
    public static void crearBorrador(String destinatario, String asunto, String cuerpo) {
    	String path = Contasoc.ESCRITORIO+"/borrador.html";
    	File borrador = new File(path);
    	try {
			borrador.createNewFile();
			ErrorHandler.borradorGuardado();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ErrorHandler.error();
		}
    	String msg = "<center><p>para: "+ destinatario +"</p></center>" +
    			"<center><p>asunto: "+ asunto +"</p></center>"+
    			MSG_BEFORE+cuerpo+MSG_AFTER;
    	try {
			Files.writeString(Path.of(path), msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ErrorHandler.error();
		}
    }
}

