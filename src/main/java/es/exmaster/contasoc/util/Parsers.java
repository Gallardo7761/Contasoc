package es.exmaster.contasoc.util;

import es.exmaster.contasoc.common.Estado;
import es.exmaster.contasoc.common.TipoSocio;
import es.exmaster.contasoc.common.TipoPago;
import es.exmaster.contasoc.ingreso.Ingreso;
import es.exmaster.contasoc.pago.Pago;
import es.exmaster.contasoc.persona.Persona;
import es.exmaster.contasoc.persona.Socio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parsers {
	public static String dateParser(LocalDate date) {
		if(date==null) {
			return null;
		}
		return date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();
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
		String[] t = s.split(";");
		Integer socio = Integer.valueOf(t[0].trim());
		String[] fechaArr = t[1].split("/");
		LocalDate fecha = LocalDate.of(Integer.valueOf(fechaArr[2]),Integer.valueOf(fechaArr[1]),Integer.valueOf(fechaArr[0]));
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
		LocalDate fecha = LocalDate.parse(t[0],DateTimeFormatter.ofPattern("d/M/yyyy"));
		String proveedor = t[1];
		String concepto = t[2];
		Double cantidad = Double.parseDouble(t[3].trim());
		String factura = t[4];
		TipoPago tipo = TipoPago.valueOf(t[5]);
		return new Pago(fecha,proveedor,concepto,cantidad,factura,tipo);
	}
}
