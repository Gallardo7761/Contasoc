package dev.gallardo.contasoc.database.objects;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

import dev.gallardo.contasoc.common.TipoPago;
import dev.gallardo.contasoc.util.Parsers;

@Entity
@Table(name = "Ingresos")
public class Ingresos implements Comparable<Ingresos> {
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
    private String tipo;

    public Ingresos(Integer numeroSocio, Date fecha, String concepto, Double cantidad, TipoPago tipo) {
        super();
        this.numeroSocio = numeroSocio;
        this.fecha = fecha;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.tipo = tipo.name();
    }

    public Ingresos(String s) {
        super();
        String[] t = s.split(";");
        Integer socio = Integer.valueOf(t[0].trim());
        Date fecha = Date.valueOf(t[1]);
        String concepto = t[2];
        Double cantidad = Double.valueOf(t[3].trim());
        TipoPago tipo = TipoPago.valueOf(t[4]);

        this.numeroSocio = socio;
        this.fecha = fecha;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.tipo = tipo.name();
    }
    
    public static Ingresos parseSQLDump(String data) {
        String[] values = data.split(";");
        Integer numeroSocio = Integer.parseInt(values[0]);
        String fechaStr = values[1];
        Date fecha = Date.valueOf(fechaStr);
        String concepto = values[3].replaceAll("'", "");
        Double cantidad = Double.parseDouble(values[4]);
        TipoPago tipo = TipoPago.valueOf(values[5].replaceAll("'", ""));
        
        return new Ingresos(numeroSocio, fecha, concepto, cantidad, tipo);
    }

    public Ingresos() {

    }

    public Integer getId() {
        return idIngreso;
    }

    public void setId(Integer id) {
        this.idIngreso = id;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
        Ingresos other = (Ingresos) obj;
        return Objects.equals(fecha, other.fecha);
    }

    @Override
    public int compareTo(Ingresos p) {
        // TODO Auto-generated method stub
        int res = this.numeroSocio.compareTo(p.getNumeroSocio());
        if (res == 0) {
            res = fecha.compareTo(p.fecha);
        }
        return res;
    }

    @Override
    public String toString() {
        return numeroSocio + ";" + Parsers.dateParser(fecha) + ";" + concepto + ";" + cantidad + ";" + tipo;
    }

}
