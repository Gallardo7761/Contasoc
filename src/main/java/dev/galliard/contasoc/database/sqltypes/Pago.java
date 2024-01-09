package dev.galliard.contasoc.database.sqltypes;

import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.util.Checkers;
import dev.galliard.contasoc.util.Parsers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Pago implements Comparable<Pago> {
	private LocalDate fecha;
	private String proveedor;
	private String concepto;
	private Double cantidad;
	private String factura;
	private TipoPago tipo;
	
	public Pago(LocalDate fecha, String proveedor, String concepto, Double cantidad, String factura, TipoPago tipo) {
		super();
		Checkers.checkNoNull(fecha,proveedor,concepto,cantidad,factura,tipo);
		this.fecha = fecha;
		this.proveedor = proveedor;
		this.concepto = concepto;
		this.cantidad = cantidad;
		this.factura = factura;
		this.tipo = tipo;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
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
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public TipoPago getTipo() {
		return tipo;
	}
	public void setTipo(TipoPago tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(factura);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pago other = (Pago) obj;
		return Objects.equals(factura, other.factura);
	}

	@Override
	public int compareTo(Pago o) {
		int res = proveedor.compareTo(o.getProveedor());
		if(res==0) {
			res = fecha.compareTo(o.getFecha());
		}
		return res;
	}

	@Override
	public String toString() {
		return Parsers.dateParser(fecha)+";"+proveedor+";"+concepto+";"+cantidad+";"+factura+";"+tipo;
	}
}
