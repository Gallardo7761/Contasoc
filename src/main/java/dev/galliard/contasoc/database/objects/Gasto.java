package dev.galliard.contasoc.database.objects;

import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.util.Checkers;
import dev.galliard.contasoc.util.Parsers;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Gastos")
public class Gasto implements Comparable<Gasto> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Gastos_SEQ")
	@SequenceGenerator(name = "Gastos_SEQ", sequenceName = "Gastos_SEQ", allocationSize = 1)
	@Column(name = "idGasto")
	private Integer idGasto;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "proveedor")
	private String proveedor;

	@Column(name = "concepto")
	private String concepto;

	@Column(name = "cantidad")
	private Double cantidad;

	@Column(name = "factura")
	private String factura;

	@Column(name = "tipo")
	private String tipo;
	
	public Gasto(Date fecha, String proveedor, String concepto, Double cantidad, String factura, TipoPago tipo) {
		super();
		this.fecha = fecha;
		this.proveedor = proveedor;
		this.concepto = concepto;
		this.cantidad = cantidad;
		this.factura = factura;
		this.tipo = tipo.name();
	}

	public Gasto() {

	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
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
		Gasto other = (Gasto) obj;
		return Objects.equals(factura, other.factura);
	}

	@Override
	public int compareTo(Gasto o) {
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
