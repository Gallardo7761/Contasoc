package dev.galliard.contasoc.persona;

import dev.galliard.contasoc.util.Checkers;

import java.util.Objects;

public class Persona {
	private String nombre;
	private String dni;
	private String telefono;
	private String correo;
	
	public Persona(String nombre, String dni, String telefono, String correo) {
		super();
		Checkers.checkNoNull(nombre,dni);
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.correo = correo;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni);
	}	
}
