package dev.galliard.contasoc.util;

import dev.galliard.contasoc.common.Estado;
import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.common.TipoSocio;
import dev.galliard.contasoc.ingreso.Ingreso;
import dev.galliard.contasoc.persona.Persona;
import dev.galliard.contasoc.persona.Socio;
import dev.galliard.contasoc.pago.Pago;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parsers {
	public static String dateParser(LocalDate date) {
		if(date==null) {
			return null;
		}
		return date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();
	}

	public static String dashDateParser(String date) {
		SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatoNuevo = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatoNuevo.format(formatoOriginal.parse(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

	public static String dashDateParserReversed(String date) {
		SimpleDateFormat formatoOriginal = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatoNuevo = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatoNuevo.format(formatoOriginal.parse(date));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String decimalSymbolParser(String cantidad) {
		if(cantidad.contains(",")) {
    		cantidad.replace(",", ".");
    	} else if(cantidad.contains("'")) {
    		cantidad.replace("'", ".");
    	}
		return cantidad;
	}
	
	public static String tipoHortelanoParser(String tipo) {
		if(tipo.equals("LISTA DE ESPERA")) {
			tipo = "LISTA_ESPERA";
		} else if(tipo.equals("HORTELANO + INV")) {
			tipo = "HORTELANO_INVERNADERO";
		}
		return tipo;
	}
	
	public static Ingreso ingresoParser(String s) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String[] t = s.split(";");
		Integer socio = Integer.valueOf(t[0].trim());
		LocalDate fecha = LocalDate.parse(t[1],formatter);
		String concepto = t[2];
		Double cantidad = Double.valueOf(t[3].trim());
		TipoPago tipo = TipoPago.valueOf(t[4]);
		return new Ingreso(socio,fecha,concepto,cantidad,tipo);
	}
	
	public static Socio socioParser(String s) {
		String[] t = s.split(";");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Integer socio = Integer.valueOf(t[0].trim());
		String huerto = t[1];
		String nombre = t[2];
		String dni = t[3];
		String telefono = t[4];
		String correo = t[5];
		LocalDate alta = LocalDate.parse(t[6],formatter);
		LocalDate entrega = t[7].equals("null") ? null : LocalDate.parse(t[7],formatter);
		LocalDate baja = t[8].equals("null") ? null : LocalDate.parse(t[8],formatter);
		String notas = t[9];
		TipoSocio tipo = TipoSocio.valueOf(t[10]);
		Estado estado = Estado.valueOf(t[11]);
	
		Socio res = new Socio(huerto, socio, new Persona(nombre,dni,telefono,correo),
				alta,entrega,baja,notas,tipo,estado);
		
		return res;
	}
	
	public static Pago pagoParser(String s) {
		String[] t = s.split(";");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha = LocalDate.parse(t[0],formatter);
		String proveedor = t[1];
		String concepto = t[2];
		Double cantidad = Double.parseDouble(t[3].trim());
		String factura = t[4];
		TipoPago tipo = TipoPago.valueOf(t[5]);
		return new Pago(fecha,proveedor,concepto,cantidad,factura,tipo);
	}
}
