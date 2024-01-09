package dev.galliard.contasoc.database.sqltypes;

import dev.galliard.contasoc.common.Estado;
import dev.galliard.contasoc.common.TipoSocio;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Socio implements Comparable<Socio> {
	private Integer idSocio;
	private Integer numeroSocio;

	private Integer numeroHuerto;

	private String nombre;

	private String dni;

	private Integer telefono;

	private String email;

	private LocalDate fechaDeAlta;

	private LocalDate fechaDeEntrega;

	private LocalDate fechaDeBaja;

	private String notas;

	private String tipo;

	private String estado;
	
	public Socio(Integer numeroSocio, Integer numeroHuerto, String nombre, String dni, Integer telefono, String email, LocalDate fechaDeAlta, LocalDate fechaDeEntrega, LocalDate fechaDeBaja,
				 String notas, TipoSocio tipo, Estado estado) {
		this.numeroSocio = numeroSocio;
		this.numeroHuerto = numeroHuerto;
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
		this.fechaDeAlta = fechaDeAlta;
		this.fechaDeEntrega = fechaDeEntrega;
		this.fechaDeBaja = fechaDeBaja;
		this.notas = notas;
		this.tipo = tipo.name();
		this.estado = estado.name();
	}
	
	public Socio(String s) {
		String[] t = s.split(";");
		String nombre = t[2];
		String dni = t[3];
		Integer telefono = Integer.valueOf(t[4]);
		String correo = t[5];
		String numeroSocio = t[1];
		Integer numeroHuerto = Integer.valueOf(t[0]);
		String fechaDeAltaStr = t[6];
		String fechaDeEntregaStr = t[7];
		String fechaDeBajaStr = t[8];
		String notas = t[9];
		String estado = t[11];
		String tipo = t[10];

		this.numeroSocio = Integer.valueOf(numeroSocio);
		this.numeroHuerto = numeroHuerto;
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.email = correo;
		String[] fechaDeAltaArr = fechaDeAltaStr.split("/");
		LocalDate fechaDeAlta = LocalDate.of(Integer.valueOf(fechaDeAltaArr[2]), Integer.valueOf(fechaDeAltaArr[1]), Integer.valueOf(fechaDeAltaArr[0]));
		String[] fechaDeEntregaArr = fechaDeEntregaStr.split("/");
		LocalDate fechaDeEntrega = null;
		String[] fechaDeBajaArr = fechaDeBajaStr.split("/");
		LocalDate fechaDeBaja = null;
		if(fechaDeEntregaArr.length == 3) {
			fechaDeEntrega = LocalDate.of(Integer.valueOf(fechaDeEntregaArr[2]), Integer.valueOf(fechaDeEntregaArr[1]), Integer.valueOf(fechaDeEntregaArr[0]));
		} else {
			fechaDeEntrega = null;
		}
		if(fechaDeBajaArr.length == 3) {
			fechaDeBaja = LocalDate.of(Integer.valueOf(fechaDeBajaArr[2]), Integer.valueOf(fechaDeBajaArr[1]), Integer.valueOf(fechaDeBajaArr[0]));
		} else {
			fechaDeBaja = null;
		}
		this.fechaDeAlta = fechaDeAlta;
		this.fechaDeEntrega = fechaDeEntrega;
		this.fechaDeBaja = fechaDeBaja;
		this.estado = estado;
		this.tipo = tipo;
		this.notas = notas;
	}

	public Socio() {

	}

	public Integer getNumeroSocio() {
		return numeroSocio;
	}

	public void setNumeroSocio(Integer numeroSocio) {
		this.numeroSocio = numeroSocio;
	}

	public Integer getNumeroHuerto() {
		return numeroHuerto;
	}

	public void setNumeroHuerto(Integer numeroHuerto) {
		this.numeroHuerto = numeroHuerto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaDeAlta() {
		return fechaDeAlta;
	}

	public void setFechaDeAlta(LocalDate fechaDeAlta) {
		this.fechaDeAlta = fechaDeAlta;
	}

	public LocalDate getFechaDeEntrega() {
		return fechaDeEntrega;
	}

	public void setFechaDeEntrega(LocalDate fechaDeEntrega) {
		this.fechaDeEntrega = fechaDeEntrega;
	}

	public LocalDate getFechaDeBaja() {
		return fechaDeBaja;
	}

	public void setFechaDeBaja(LocalDate fechaDeBaja) {
		this.fechaDeBaja = fechaDeBaja;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public TipoSocio getTipo() {
		return TipoSocio.valueOf(tipo);
	}

	public void setTipo(TipoSocio tipo) {
		this.tipo = tipo.name();
	}

	public Estado getEstado() {
		return Estado.valueOf(estado);
	}

	public void setEstado(Estado estado) {
		this.estado = estado.name();
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroSocio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Socio other = (Socio) obj;
		return Objects.equals(numeroSocio, other.numeroSocio);
	}

	@Override
	public int compareTo(Socio h) {
		// TODO Auto-generated method stub
		return numeroSocio.compareTo(h.getNumeroSocio());
	}

	@Override
	public String toString() {
		return numeroHuerto+";"+numeroSocio+";"+nombre+";"+dni+";"+telefono+";"+
				email+";"+fechaDeAlta+";"+fechaDeEntrega+";"+fechaDeBaja+";"+notas+";"+tipo+";"+estado;
	}
	
}
