package dev.galliard.contasoc.util;

import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.database.objects.Gastos;
import dev.galliard.contasoc.database.objects.Ingresos;
import dev.galliard.contasoc.database.objects.Socios;

import java.util.ArrayList;
import java.util.List;

public class SQLMemory {
    public static boolean dataModified() {
        return !Contasoc.sqlMemory.getSociosAgregados().isEmpty() ||
                !Contasoc.sqlMemory.getSociosEliminados().isEmpty() ||
                !Contasoc.sqlMemory.getIngresosAgregados().isEmpty() ||
                !Contasoc.sqlMemory.getIngresosEliminados().isEmpty() ||
                !Contasoc.sqlMemory.getGastosAgregados().isEmpty() ||
                !Contasoc.sqlMemory.getGastosEliminados().isEmpty() ||
                !Contasoc.sqlMemory.getSociosEditados().isEmpty() ||
                !Contasoc.sqlMemory.getIngresosEditados().isEmpty() ||
                !Contasoc.sqlMemory.getGastosEditados().isEmpty();
    }
    private List<Socios> sociosAgregados = new ArrayList<>();
    private List<Socios> sociosEditados = new ArrayList<>();
    private List<Socios> sociosEliminados = new ArrayList<>();
    private List<Ingresos> ingresosAgregados = new ArrayList<>();
    private List<Ingresos> ingresosEditados = new ArrayList<>();
    private List<Ingresos> ingresosEliminados = new ArrayList<>();
    private List<Gastos> gastosAgregados = new ArrayList<>();
    private List<Gastos> gastosEditados = new ArrayList<>();
    private List<Gastos> gastosEliminados = new ArrayList<>();

    public List<Socios> getSociosAgregados() {
        return sociosAgregados;
    }

    public List<Socios> getSociosEditados() {
        return sociosEditados;
    }

    public List<Socios> getSociosEliminados() {
        return sociosEliminados;
    }

    public List<Ingresos> getIngresosAgregados() {
        return ingresosAgregados;
    }

    public List<Ingresos> getIngresosEditados() {
        return ingresosEditados;
    }

    public List<Ingresos> getIngresosEliminados() {
        return ingresosEliminados;
    }

    public List<Gastos> getGastosAgregados() {
        return gastosAgregados;
    }

    public List<Gastos> getGastosEditados() {
        return gastosEditados;
    }

    public List<Gastos> getGastosEliminados() {
        return gastosEliminados;
    }

    public void socioAgregado(Socios socio) {
        sociosAgregados.add(socio);
    }

    public void socioEditado(Socios socio) {
        sociosEditados.add(socio);
    }

    public void socioEliminado(Socios socio) {
        sociosAgregados.remove(socio);
        sociosEliminados.add(socio);
    }

    public void ingresoAgregado(Ingresos ingreso) {
        ingresosAgregados.add(ingreso);
    }

    public void ingresoEditado(Ingresos ingreso) {
        ingresosEditados.add(ingreso);
    }

    public void ingresoEliminado(Ingresos ingreso) {
        ingresosAgregados.remove(ingreso);
        ingresosEliminados.add(ingreso);
    }

    public void gastoAgregado(Gastos gasto) {
        gastosAgregados.add(gasto);
    }

    public void gastoEditado(Gastos gasto) {
        gastosEditados.add(gasto);
    }

    public void gastoEliminado(Gastos gasto) {
        gastosAgregados.remove(gasto);
        gastosEliminados.add(gasto);
    }

}
