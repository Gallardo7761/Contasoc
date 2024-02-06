package dev.galliard.contasoc.database.objects;

import dev.galliard.contasoc.common.TipoPago;
import dev.galliard.contasoc.util.Checkers;
import dev.galliard.contasoc.util.Parsers;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Ingresos")
public class Ingreso implements Comparable<Ingreso> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Ingresos_SEQ")
	@SequenceGenerator(name = "Ingresos_SEQ", sequenceName = "Ingresos_SEQ", allocationSize = 1)
	@Column(name = "idIngreso")
	private Integer idIngreso;

	@Column(name = "numeroSocio")
	private Integer numeroSocio;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "concepto")
	private String concepto;

	@Column(name = "cantidad")
	private Double cantidad;

	@Column(name = "tipo")
	private TipoPago tipo;
	
	public Ingreso(Integer numeroSocio, Date fecha, String concepto, Double cantidad, TipoPago tipo) {
		super();
		this.numeroSocio = numeroSocio;
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
		Date fecha = Date.valueOf(LocalDate.of(Integer.valueOf(fechaArr[2]), Integer.valueOf(fechaArr[1]), Integer.valueOf(fechaArr[0])));
		String concepto = t[2];
		Double cantidad = Double.valueOf(t[3].trim());
		TipoPago tipo = TipoPago.valueOf(t[4]);
		
		this.numeroSocio = socio;
		this.fecha = fecha;
		this.concepto = concepto;
		this.cantidad = cantidad;
		this.tipo = tipo;
	}

	public Ingreso() {

	}

	public Integer getNumeroSocio() {
		return numeroSocio;
	}

	public void setNumeroSocio(Integer socio) {
		this.numeroSocio = socio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
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
		int res = this.numeroSocio.compareTo(p.getNumeroSocio());
		if(res==0) {
			res = fecha.compareTo(p.fecha);
		}
		return res;
	}

	@Override
	public String toString() {
		return numeroSocio +";"+Parsers.dateParser(fecha)+";"+concepto+";"+cantidad+";"+tipo;
	}
		
}
