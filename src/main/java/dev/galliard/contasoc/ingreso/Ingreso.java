package dev.galliard.contasoc.ingreso;

import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.util.Checkers;
import dev.galliard.contasoc.util.Parsers;

import java.time.LocalDate;
import java.util.Objects;

public class Ingreso implements Comparable<Ingreso> {
	private Integer socio;
	private LocalDate fecha;
	private String concepto;
	private Double cantidad;
	private TipoPago tipo;
	
	public Ingreso(Integer socio, LocalDate fecha, String concepto, Double cantidad, TipoPago tipo) {
		super();
		Checkers.checkNoNull(socio,fecha,concepto,cantidad,tipo);
		this.socio = socio;
		this.fecha = fecha;
		this.concepto = concepto;
		this.cantidad = cantidad;
		this.tipo = tipo;
	}
	
	public Ingreso(String s) {
		super();
		String[] t = s.split(";");
		Integer socio = Integer.valueOf(t[0].trim());
		String[] fechaArr = t[1].split("/");
		LocalDate fecha = LocalDate.of(Integer.valueOf(fechaArr[2]),Integer.valueOf(fechaArr[1]),Integer.valueOf(fechaArr[0]));
		String concepto = t[2];
		Double cantidad = Double.valueOf(t[3].trim());
		TipoPago tipo = TipoPago.valueOf(t[4]);
		
		this.socio = socio;
		this.fecha = fecha;
		this.concepto = concepto;
		this.cantidad = cantidad;
		this.tipo = tipo;
	}
	
	public Integer getSocio() {
		return socio;
	}

	public void setSocio(Integer socio) {
		this.socio = socio;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public TipoPago getTipo() {
		return tipo;
	}

	public void setTipo(TipoPago tipo) {
		this.tipo = tipo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingreso other = (Ingreso) obj;
		return Objects.equals(fecha, other.fecha);
	}

	@Override
	public int compareTo(Ingreso p) {
		// TODO Auto-generated method stub
		int res = this.socio.compareTo(p.getSocio());
		if(res==0) {
			res = fecha.compareTo(p.fecha);
		}
		return res;
	}

	@Override
	public String toString() {
		return socio+";"+Parsers.dateParser(fecha)+";"+concepto+";"+cantidad+";"+tipo;
	}
		
}
