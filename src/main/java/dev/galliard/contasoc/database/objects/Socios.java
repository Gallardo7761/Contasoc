package dev.galliard.contasoc.database.objects;

import dev.galliard.contasoc.common.Estado;
import dev.galliard.contasoc.common.TipoSocio;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Socios")
public class Socios implements Comparable<Socios> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Socios_SEQ")
    @SequenceGenerator(name = "Socios_SEQ", sequenceName = "Socios_SEQ", allocationSize = 1)
    @Column(name = "idSocio")
    private Integer idSocio;

    @Column(name = "numeroSocio")
    private Integer numeroSocio;

    @Column(name = "numeroHuerto")
    private Integer numeroHuerto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "dni")
    private String dni;

    @Column(name = "telefono")
    private Integer telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "fechaDeAlta")
    private Date fechaDeAlta;

    @Column(name = "fechaDeEntrega")
    private Date fechaDeEntrega;

    @Column(name = "fechaDeBaja")
    private Date fechaDeBaja;

    @Column(name = "notas")
    private String notas;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "estado")
    private String estado;

    public Socios(Integer numeroSocio, Integer numeroHuerto, String nombre, String dni,
                  Integer telefono, String email, Date fechaDeAlta, Date fechaDeEntrega,
                  Date fechaDeBaja, String notas, TipoSocio tipo, Estado estado) {
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

    public Socios(String s) {
        String[] t = s.split(";");
        String nombre = t[2];
        String dni = t[3];
        String telefono = t[4];
        String correo = t[5];
        String numeroSocio = t[1];
        String numeroHuerto = t[0];
        String fechaDeAltaStr = t[6];
        String fechaDeEntregaStr = t[7];
        String fechaDeBajaStr = t[8];
        String notas = t[9];
        String estado = t[11];
        String tipo = t[10];

        this.numeroSocio = Integer.valueOf(numeroSocio);
        this.numeroHuerto = Integer.valueOf(numeroHuerto);
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = Integer.valueOf(telefono);
        this.email = correo;
        this.fechaDeAlta = Date.valueOf(fechaDeAltaStr);
        this.fechaDeEntrega = Date.valueOf(fechaDeEntregaStr);
        this.fechaDeBaja = Date.valueOf(fechaDeBajaStr);
        this.estado = estado;
        this.tipo = tipo;
        this.notas = notas;
    }
    
    public static Socios parseSQLDump(String data) {
        String[] values = data.split(";");
        Integer numeroHuerto = Integer.parseInt(values[0]);
        Integer numeroSocio = Integer.parseInt(values[1]);
        String nombre = values[3].replaceAll("'", "");
        String dni = values[4].replaceAll("'", "");
        Integer telefono = Integer.parseInt(values[5].replaceAll("'", ""));
        String email = values[6].replaceAll("'", "");
        String fechaDeAltaStr = values[7].replaceAll("'", "");
        String fechaDeEntregaStr = values[8].replaceAll("'", "");
        String fechaDeBajaStr = values[9].replaceAll("'", "");
        String notas = values[10].replaceAll("'", "");
        TipoSocio tipo = TipoSocio.valueOf(values[11].replaceAll("'", ""));
        Estado estado = Estado.valueOf(values[12].replaceAll("'", ""));
        
        return new Socios(numeroSocio, numeroHuerto, nombre, dni, telefono, email,
                fechaDeAltaStr.isEmpty() ? null : Date.valueOf(fechaDeAltaStr), 
        		fechaDeEntregaStr.isEmpty() ? null : Date.valueOf(fechaDeEntregaStr), 
				fechaDeBajaStr.isEmpty() ? null : Date.valueOf(fechaDeBajaStr),
                notas, tipo, estado);
    }

    public Socios() {

    }

    public Integer getId() {
        return idSocio;
    }

    public void setId(Integer id) {
        this.idSocio = id;
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

    public Date getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(Date fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public Date getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(Date fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public Date getFechaDeBaja() {
        return fechaDeBaja;
    }

    public void setFechaDeBaja(Date fechaDeBaja) {
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
        Socios other = (Socios) obj;
        return Objects.equals(numeroSocio, other.numeroSocio);
    }

    @Override
    public int compareTo(Socios h) {
        // TODO Auto-generated method stub
        return numeroSocio.compareTo(h.getNumeroSocio());
    }

    @Override
    public String toString() {
        return numeroHuerto + ";" + numeroSocio + ";" + nombre + ";" + dni + ";" + telefono + ";" +
                email + ";" + fechaDeAlta + ";" + fechaDeEntrega + ";" + fechaDeBaja + ";" + notas + ";" + tipo + ";" + estado;
    }

}
