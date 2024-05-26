package dev.gallardo.contasoc.util;

import javax.swing.*;

import com.formdev.flatlaf.util.SystemInfo;

import java.awt.*;

public class ErrorHandler {
    JOptionPane jop = new JOptionPane();

    public static void errorAlLeerDNI() throws HeadlessException {
        JOptionPane.showMessageDialog(null, "Hay un error en el formato del DNI", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void pdfCreado() {
        JOptionPane.showMessageDialog(null, "Se ha generado el PDF y se ha guardado en el Escritorio", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error() {
        JOptionPane.showMessageDialog(null, "Se ha producido un error desconocido", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void error(String msg) {
        JOptionPane.showMessageDialog(null, "ERROR: " + msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

	public static void backupDownloaded() {
		String ruta = SystemInfo.isWindows ? "C:/Users/" + System.getProperty("user.name") + "/.contasoc/backups" : 
			"/home/" + System.getProperty("user.name") + "/.contasoc/backups";
		JOptionPane.showMessageDialog(null, "Se ha descargado la copia de seguridad en " + ruta + ". Por favor envíasela a un administrador para proceder con la restauración a la fecha elegida.", "Copia descargada", JOptionPane.INFORMATION_MESSAGE);
	}
}
