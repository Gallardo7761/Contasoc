package es.exmaster.contasoc.util;

import javax.swing.*;
import java.awt.*;

public class ErrorHandler {
	JOptionPane jop = new JOptionPane();
	public static void camposObligatoriosVacios() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Hay campos obligatorios vacíos", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void socioNoExiste() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "No existe un socio con ese número de socio", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void socioYaExiste() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Ya existe un socio con ese número de socio", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void socioAgregado(Integer num) {
		JOptionPane.showMessageDialog(null, "Se ha agregado el socio nº " + num, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void socioModificado(Integer num) {
		JOptionPane.showMessageDialog(null, "Se ha modificado el socio nº " + num, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void socioEliminado(Integer num) {
		JOptionPane.showMessageDialog(null, "Se ha eliminado el socio nº " + num, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void tipoSocioNoAdecuado() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "El tipo de socio no es el adecuado", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void numeroHuertoVacio() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Introducir número de huerto", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void ingresoNoExiste() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "No hay ingresos registrados de ese socio o ese socio no existe", "Error", JOptionPane.ERROR_MESSAGE);		
	}
	public static void ingresoAgregado(Integer num) {
		JOptionPane.showMessageDialog(null, "Se ha agregado el ingreso al socio nº " + num, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void ingresoModificado(Integer num) {
		JOptionPane.showMessageDialog(null, "Se ha modificado el ingreso al socio nº " + num, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void ingresoEliminado(Integer num) {
		JOptionPane.showMessageDialog(null, "Se ha eliminado el ingreso del socio nº " + num, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void pagoAgregado(String fac) {
		JOptionPane.showMessageDialog(null, "Se ha agregado el pago con factura: " + fac, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void pagoModificado() {
		JOptionPane.showMessageDialog(null, "Se ha modificado el pago", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void pagoEliminado() {
		JOptionPane.showMessageDialog(null, "Se ha eliminado el pago", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void errorAlCrearDirectorio() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Hubo un error al crear una carpeta necesaria para la aplicación", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void errorAlCrearBDD() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Hubo un error al crear la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void BDDCreada() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Se ha creado la base de datos", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void errorAlLeerBDD(String bdd) throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Hubo un error al leer la BDD de " + bdd, "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void errorAlEscribirBDD(String bdd) throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Hubo un error al escribir en la BDD de " + bdd, "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void errorAlLeerFecha(String fecha) throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Hay un error en el formato de la fecha de " + fecha, "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void errorAlLeerTlf() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Hay un error en el formato del número de teléfono", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void errorAlLeerDNI() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Hay un error en el formato del DNI", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void errorAlLeerCorreo() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Hay un error en el formato del correo", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void errorAlLeerTipoSocio() throws HeadlessException {
		JOptionPane.showMessageDialog(null, "Hay un error en el formato del tipo de socio", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void errorAlLeerCantidadNumerica(String cantidad) {
		JOptionPane.showMessageDialog(null, "Hay un error en el formato de " + cantidad, "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void errorAlLeerTipoRetr() {
		JOptionPane.showMessageDialog(null, "Hay un error en el formato del tipo de ingreso/pago", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void pdfCreado() {
		JOptionPane.showMessageDialog(null, "Se ha generado el PDF y se ha guardado en el Escritorio", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void errorNumeroColumnas() {
		JOptionPane.showMessageDialog(null, "El número de columnas no coincide con el número de nuevos valores", "Error", JOptionPane.ERROR_MESSAGE);
	}
	public static void mailEnviado() {
		JOptionPane.showMessageDialog(null, "Se ha enviado el email destinatario especificado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void borradorGuardado() {
		JOptionPane.showMessageDialog(null, "Se ha guardado el borrador en el Escritorio", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void error() {
		JOptionPane.showMessageDialog(null, "Se ha producido un error desconocido", "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void error(String msg) {
		JOptionPane.showMessageDialog(null, "ERROR: " + msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
