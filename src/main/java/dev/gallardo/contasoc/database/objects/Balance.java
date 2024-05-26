package dev.gallardo.contasoc.database.objects;

import jakarta.persistence.*;

@Entity
@Table(name = "Balance")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Balance_SEQ")
    @SequenceGenerator(name = "Balance_SEQ", sequenceName = "Balance_SEQ", allocationSize = 1)
    @Column(name = "idBalance")
    private Integer idBalance;

    @Column(name = "inicialBanco")
    private double inicialBanco;

    @Column(name = "inicialCaja")
    private double inicialCaja;

    public Balance() {
    }

    public Balance(double inicialBanco, double inicialCaja) {
        this.inicialBanco = inicialBanco;
        this.inicialCaja = inicialCaja;
    }

    public Integer getId() {
        return idBalance;
    }

    public void setId(Integer id) {
        this.idBalance = id;
    }

    public double getInicialBanco() {
        return inicialBanco;
    }

    public void setInicialBanco(double inicialBanco) {
        this.inicialBanco = inicialBanco;
    }

    public double getInicialCaja() {
        return inicialCaja;
    }

    public void setInicialCaja(double inicialCaja) {
        this.inicialCaja = inicialCaja;
    }
}
