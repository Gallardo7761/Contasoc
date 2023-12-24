package dev.galliard.contasoc.util;

import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.ingreso.Ingreso;
import dev.galliard.contasoc.pago.Pago;

import java.awt.*;
import javax.swing.*;

public class CodePlayground {

    public static void main(String[] args) {
        for (Ingreso i : ContasocDAO.leerTabla("Ingresos").stream().map(Parsers::ingresoParser).toList()) {
            String[] ingArr = i.toString().split(";");
            System.out.println(
                    ingArr[0] + ";" + ContasocDAO.select("Socios", new Object[] {"nombre"}, "WHERE Socios.numeroSocio = Ingresos.numeroSocio")
                            + ";" + ingArr[1] + ";" + ingArr[2] + ";" + ingArr[3] + ";" + ingArr[4]);
        }
        System.out.println("\n\n\n");
        for (Pago p : ContasocDAO.leerTabla("Gastos").stream().map(Parsers::pagoParser).toList()) {
            System.out.println(p.toString());
        }
    }
}