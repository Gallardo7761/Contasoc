package es.exmaster.contasoc.persona;

import es.exmaster.contasoc.common.Estado;
import es.exmaster.contasoc.common.TipoSocio;
import es.exmaster.contasoc.util.Checkers;

import java.time.LocalDate;
import java.util.Objects;

public class Socio implements Comparable<Socio> {
	private String huerto;
	private Integer socio;
	private Persona persona;
	private LocalDate alta;
	private LocalDate entrega;
	private LocalDate baja;
	private String notas;
	private TipoSocio tipo;
	private Estado estado;
	
	public Socio(String huerto, Integer socio, Persona persona, LocalDate alta, LocalDate entrega, LocalDate baja,
				 String notas, TipoSocio tipo, Estado estado) {
		super();
		Checkers.checkNoNull(persona,socio,alta,estado,tipo);
		this.huerto = huerto;
		this.socio = socio;
		this.persona = persona;
		this.alta = alta;
		this.entrega = entrega;
		this.baja = baja;
		this.notas = notas;
		this.tipo = tipo;
		this.estado = estado;
	}
	
	public Socio(String s) {
		String[] t = s.split(";");
		String nombre = t[2];
		String dni = t[3];
		String telefono = t[4];
		String correo = t[5];
		String socio = t[1];
		String huerto = t[0];
		String altaStr = t[6];
		String entregaStr = t[7];
		String bajaStr = t[8];
		String notas = t[9];
		String estado = t[11];
		String tipo = t[10];
		
		Persona p = new Persona(nombre,dni,telefono,correo);
		
		this.persona = p;
		this.socio = Integer.valueOf(socio);
		this.huerto = huerto;
		String[] altaArr = altaStr.split("/");
		LocalDate alta = LocalDate.of(Integer.valueOf(altaArr[2]),Integer.valueOf(altaArr[1]),Integer.valueOf(altaArr[0]));
		String[] entregaArr = entregaStr.split("/");
		LocalDate entrega = null;
		String[] bajaArr = bajaStr.split("/");
		LocalDate baja = null;
		if(entregaArr.length == 3) {
			entrega = LocalDate.of(Integer.valueOf(entregaArr[2]),Integer.valueOf(entregaArr[1]),Integer.valueOf(entregaArr[0]));
		} else {
			entrega = null;
		}
		if(bajaArr.length == 3) {
			baja = LocalDate.of(Integer.valueOf(bajaArr[2]),Integer.valueOf(bajaArr[1]),Integer.valueOf(bajaArr[0]));
		} else {
			baja = null;
		}
		this.alta = alta;
		this.entrega = entrega;
		this.baja = baja;
		this.estado = Estado.valueOf(estado);
		this.tipo = TipoSocio.valueOf(tipo);
		this.notas = notas;
	}
	
	public Integer getSocio() {
		return socio;
	}

	public void setSocio(Integer socio) {
		this.socio = socio;
	}

	public String getHuerto() {
		return huerto;
	}

	public void setHuerto(String huerto) {
		this.huerto = huerto;
	}

	public LocalDate getAlta() {
		return alta;
	}

	public void setAlta(LocalDate alta) {
		this.alta = alta;
	}

	public LocalDate getBaja() {
		return baja;
	}

	public void setBaja(LocalDate baja) {
		this.baja = baja;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public TipoSocio getTipo() {
		return tipo;
	}

	public void setTipo(TipoSocio tipo) {
		this.tipo = tipo;
	}

	public Persona getPersona() {
		return persona;
	}
	
	public LocalDate getEntrega() {
		return entrega;
	}

	public void setEntrega(LocalDate entrega) {
		this.entrega = entrega;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(socio);
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
		return Objects.equals(socio, other.socio);
	}

	@Override
	public int compareTo(Socio h) {
		// TODO Auto-generated method stub
		return socio.compareTo(h.getSocio());
	}

	@Override
	public String toString() {
		return huerto+";"+socio+";"+persona.getNombre()+";"+persona.getDni()+";"+persona.getTelefono()+";"+
				persona.getCorreo()+";"+alta+";"+entrega+";"+baja+";"+notas+";"+tipo+";"+estado;
	}
	
}
