package dev.galliard.contasoc.util;

import jakarta.activation.FileDataSource;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.email.EmailPopulatingBuilder;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import org.apache.commons.io.FileUtils;

public class EmailSender2 {
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;
    private static final String SMTP_PASSWORD = "hjlmtekuogfpsjzw";
    private static final String SENDER_EMAIL = "huertoslasaludbellavista@gmail.com";
    public static File adjunto;

    public static void crearBorrador(String destinatario, String asunto, String cuerpo) {
        EmailPopulatingBuilder emailBuilder = EmailBuilder.startingBlank()
                .from(SENDER_EMAIL)
                .to(destinatario)
                .withSubject(asunto)
                .withHTMLText(cuerpo);
        Email borrador = emailBuilder.buildEmail();
        guardarHTMLenEscritorio(borrador.getHTMLText(), destinatario, asunto);
    }

    private static void guardarHTMLenEscritorio(String html, String destinatario, String asunto) {
        String fileName = "Borrador_" + destinatario + "_" + asunto + "_" + LocalDate.now() + ".html";
        Path filePath = Paths.get(System.getProperty("user.home"), "Desktop", fileName);
        try {
            Files.write(filePath, html.getBytes());
            ErrorHandler.borradorGuardado();
        } catch (IOException e) {
            ContasocLogger.error(e.getMessage(), e);
        }
    }

    public void sendEmail(String destinatario, String asunto, String cuerpo) {
        Email email = buildEmail(destinatario, asunto, cuerpo);

        Mailer mailer = MailerBuilder
                .withSMTPServer(SMTP_SERVER, SMTP_PORT, SENDER_EMAIL, SMTP_PASSWORD)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer();

        mailer.sendMail(email);

       ErrorHandler.mailEnviado();
    }

    public void sendEmail(String destinatario, String asunto, String cuerpo, File adjunto) {
        Email email = buildEmail(destinatario, asunto, cuerpo, adjunto);

        Mailer mailer = MailerBuilder
                .withSMTPServer(SMTP_SERVER, SMTP_PORT, SENDER_EMAIL, SMTP_PASSWORD)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer();

        mailer.sendMail(email);

        ErrorHandler.mailEnviado();
    }

    private Email buildEmail(String destinatario, String asunto, String cuerpo) {
        return EmailBuilder.startingBlank()
                .from(SENDER_EMAIL)
                .to(destinatario)
                .withSubject(asunto)
                .withHTMLText(cuerpo)
                .buildEmail();
    }

    private Email buildEmail(String destinatario, String asunto, String cuerpo, File adjunto) {
        try {
            byte[] adjuntoBytes = FileUtils.readFileToByteArray(adjunto);

            return EmailBuilder.startingBlank()
                    .from(SENDER_EMAIL)
                    .to(destinatario)
                    .withSubject(asunto)
                    .withHTMLText(cuerpo)
                    .withAttachment(adjunto.getName(), new FileDataSource(EmailSender2.adjunto))
                    .buildEmail();
        } catch (IOException e) {
            ContasocLogger.error(e.getMessage(), e); // Manejo de errores, puedes personalizar esto según tus necesidades
            return null; // O lanzar una excepción, dependiendo de tus requisitos
        }
    }

    public static String NORMAL_EMAIL = """
           <!DOCTYPE html>
           <html>
           
           <head>
               <style>
                   /* Inline CSS */
                   .container {
                       border: 1px solid #dddddd;
                       border-radius: 5px;
                       padding: 20px;
                       max-width: 400px;
                       margin: 0 auto;
                   }
           
                   .logo {
                       display: block;
                       margin: 0 auto;
                       max-width: 200px;
                   }
           
                   .title {
                       text-align: center;
                       font-size: 24px;
                       color: #333333;
                       margin-top: 20px;
                   }
           
                   .body {
                       margin-top: 20px;
                       color: #555555;
                       font-size: 16px;
                   }
           
                   .contact-info {
                       margin-top: 20px;
                       font-size: 14px;
                       color: #777777;
                       text-align: center;
                   }
               </style>
           </head>
           
           <body>
               <div class="container">
                   <img src="https://raw.githubusercontent.com/GalliardDev/ContasocRemake/master/src/main/resources/images/logohuerto.png" width="150" class="logo">
                   <h4 class="title">Huertos la Salud Bellavista</h4>
                   <p class="body">Estimado/a {nombre},</p>
                   <p class="body">{mensaje}</p>
                   <p class="body">Atte. La Directiva</p>
                   <div class="contact-info">
                       <p>Direcci&oacute;n: C/ Cronos SN, Bellavista, Sevilla, 41014</p>
                       <p>Email: huertoslasaludbellavista@gmail.com</p>
                   </div>
               </div>
           </body>
           
           </html>
            """;
    public static String WARNING_EMAIL = """
           <!DOCTYPE html>
           <html>
           
           <head>
               <style>
                   /* Inline CSS */
                   .container {
                       border: 1px solid #ff7b00;
                       border-radius: 5px;
                       padding: 20px;
                       max-width: 400px;
                       margin: 0 auto;
                   }
           
                   .logo {
                       display: block;
                       margin: 0 auto;
                       max-width: 200px;
                   }
           
                   .title {
                       text-align: center;
                       font-size: 24px;
                       color: #333333;
                       margin-top: 20px;
                       margin-bottom: 10px;
                   }
           
                   .subtitle {
                       text-align: center;
                       font-size: 18px;
                       font-family: 'Segoe UI';
                       color: #1e1e1e;
                       margin-top: 0px;
                       border: #ff9100 solid 2px;
                       background-color: #f3d9b8;
                       border-radius: 9px;
                       padding: 5px;  \s
                       width: 200px;    \s
                   }
           
                   .body {
                       margin-top: 20px;
                       color: #555555;
                       font-size: 16px;
                   }
           
                   .contact-info {
                       margin-top: 20px;
                       font-size: 14px;
                       color: #777777;
                       text-align: center;
                   }
               </style>
           </head>
           
           <body>
               <div class="container">
                   <img src="https://raw.githubusercontent.com/GalliardDev/ContasocRemake/master/src/main/resources/images/logohuerto.png" width="150" class="logo">
                   <h4 class="title">Huertos la Salud Bellavista</h4>
                   <center><h5 class="subtitle">AVISO DE ABANDONO</h5></center>
                   <p class="body">Estimado/a {nombre},</p>
                   <p class="body">Nos ponemos en contacto con usted para comunicarle que\s
                       su huerto se encuentra en estado de abandono. Tiene un plazo de 15\s
                       d&iacute;as para realizar labores de cultivo.<br><br> Rogamos tenga en cuenta
                       el documento adjunto para conocer m&aacute;s detalles.</p>
                   <p class="body">Atte. La Directiva</p>
                   <div class="contact-info">
                       <p>Direcci&oacute;n: C/ Cronos SN, Bellavista, Sevilla, 41014</p>
                       <p>Email: huertoslasaludbellavista@gmail.com</p>
                   </div>
               </div>
           </body>
           
           </html>
            """;
    public static String UNPAID_EMAIL = """
           <!DOCTYPE html>
           <html>
           
           <head>
               <style>
                   /* Inline CSS */
                   .container {
                       border: 1px solid #ff0000;
                       border-radius: 5px;
                       padding: 20px;
                       max-width: 400px;
                       margin: 0 auto;
                   }
           
                   .logo {
                       display: block;
                       margin: 0 auto;
                       max-width: 200px;
                   }
           
                   .title {
                       text-align: center;
                       font-size: 24px;
                       color: #333333;
                       margin-top: 20px;
                       margin-bottom: 10px;
                   }
           
                   .subtitle {
                       text-align: center;
                       font-size: 18px;
                       font-family: 'Segoe UI';
                       color: #1e1e1e;
                       margin-top: 0px;
                       border: #ff0000 solid 2px;
                       background-color: #f3b8b8;
                       border-radius: 9px;
                       padding: 5px;  \s
                       width: 200px;    \s
                   }
           
                   .body {
                       margin-top: 20px;
                       color: #555555;
                       font-size: 16px;
                   }
           
                   .contact-info {
                       margin-top: 20px;
                       font-size: 14px;
                       color: #777777;
                       text-align: center;
                   }
               </style>
           </head>
           
           <body>
               <div class="container">
                   <img src="https://raw.githubusercontent.com/GalliardDev/ContasocRemake/master/src/main/resources/images/logohuerto.png" width="150" class="logo">
                   <h4 class="title">Huertos la Salud Bellavista</h4>
                   <center><h5 class="subtitle">AVISO DE IMPAGO</h5></center>
                   <p class="body">Estimado/a {nombre},</p>
                   <p class="body">Nos ponemos en contacto con usted para informarle que tiene
                       cuotas pendientes de pago.<br><br>
                       Rogamos tenga en cuenta el documento adjunto para conocer m&aacute;s detalles.
                   </p>
                   <p class="body">Atte. La Directiva</p>
                   <div class="contact-info">
                       <p>Direcci&oacute;n: C/ Cronos SN, Bellavista, Sevilla, 41014</p>
                       <p>Email: huertoslasaludbellavista@gmail.com</p>
                   </div>
               </div>
           </body>
           
           </html>
            """;
    public static String MISBEHAVE_EMAIL = """
            <!DOCTYPE html>
            <html>
                        
            <head>
                <style>
                    /* Inline CSS */
                    .container {
                        border: 1px solid #5e0786;
                        border-radius: 5px;
                        padding: 20px;
                        max-width: 400px;
                        margin: 0 auto;
                    }
                        
                    .logo {
                        display: block;
                        margin: 0 auto;
                        max-width: 200px;
                    }
                        
                    .title {
                        text-align: center;
                        font-size: 24px;
                        color: #333333;
                        margin-top: 20px;
                        margin-bottom: 10px;
                    }
                        
                    .subtitle {
                        text-align: center;
                        font-size: 18px;
                        font-family: 'Segoe UI';
                        color: #1e1e1e;
                        margin-top: 0px;
                        border: #5e0786 solid 2px;
                        background-color: #d6b8f3;
                        border-radius: 9px;
                        padding: 5px;
                        width: 220px;
                    }
                        
                    .body {
                        margin-top: 20px;
                        color: #555555;
                        font-size: 16px;
                    }
                        
                    .contact-info {
                        margin-top: 20px;
                        font-size: 14px;
                        color: #777777;
                        text-align: center;
                    }
                </style>
            </head>
                        
            <body>
                <div class="container">
                    <img src="https://raw.githubusercontent.com/GalliardDev/ContasocRemake/master/src/main/resources/images/logohuerto.png" width="150" class="logo">
                    <h4 class="title">Huertos la Salud Bellavista</h4>
                    <center><h5 class="subtitle">LLAMADA DE ATENCI&Oacute;N</h5></center>
                    <p class="body">Estimado/a {nombre},</p>
                    <p class="body">Nos ponemos en contacto con usted para comunicarle que
                        debido a que su comportamiento con el resto de socios no ha sido el
                        adecuado. Si desea realizar una apelaci&oacute;n p&oacute;ngase en contacto con
                        la directiva a trav&eacute;s de este mismo email o presencialmente.<br><br>
                        Rogamos tenga en cuenta el documento adjunto para conocer m&aacute;s detalles.
                    </p>
                    <p class="body">Atte. La Directiva</p>
                    <div class="contact-info">
                        <p>Direcci&oacute;n: C/ Cronos SN, Bellavista, Sevilla, 41014</p>
                        <p>Email: huertoslasaludbellavista@gmail.com</p>
                    </div>
                </div>
            </body>
                        
            </html>
            """;
}

