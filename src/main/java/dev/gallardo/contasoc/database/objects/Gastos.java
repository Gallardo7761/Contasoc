package dev.gallardo.contasoc.database.objects;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

import dev.gallardo.contasoc.common.TipoPago;
import dev.gallardo.contasoc.util.Parsers;

@Entity
@Table(name = "Gastos")
public class Gastos implements Comparable<Gastos> {
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

    public Gastos(Date fecha, String proveedor, String concepto, Double cantidad, String factura, TipoPago tipo) {
        super();
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.factura = factura;
        this.tipo = tipo.name();
    }

    public Gastos(String s) {
        super();
        String[] t = s.split(";");
        String proveedor = t[1];
        Date fecha = Date.valueOf(t[0]);
        String concepto = t[2];
        Double cantidad = Double.valueOf(t[3].trim());
        String factura = t[4];
        TipoPago tipo = TipoPago.valueOf(t[5]);

        this.fecha = fecha;
        this.proveedor = proveedor;
        this.concepto = concepto;
        this.cantidad = cantidad;
        this.factura = factura;
        this.tipo = tipo.name();
    }
    
    public static Gastos parseSQLDump(String data) {
        String[] values = data.split(";");
        String fechaStr = values[0];
        Date fecha = Date.valueOf(fechaStr);
        String proveedor = values[1].replaceAll("'", "");
        String concepto = values[2].replaceAll("'", "");
        Double cantidad = Double.parseDouble(values[3]);
        String factura = values[4].replaceAll("'", "");
        TipoPago tipo = TipoPago.valueOf(values[5].replaceAll("'", ""));
        
        return new Gastos(fecha, proveedor, concepto, cantidad, factura, tipo);
    }

    public Gastos() {

    }

    public Integer getId() {
        return idGasto;
    }

    public void setId(Integer id) {
        this.idGasto = id;
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
        Gastos other = (Gastos) obj;
        return Objects.equals(factura, other.factura);
    }

    @Override
    public int compareTo(Gastos o) {
        int res = proveedor.compareTo(o.getProveedor());
        if (res == 0) {
            res = fecha.compareTo(o.getFecha());
        }
        return res;
    }

    @Override
    public String toString() {
        return Parsers.dateParser(fecha) + ";" + proveedor + ";" + concepto + ";" + cantidad + ";" + factura + ";" + tipo;
    }
}
