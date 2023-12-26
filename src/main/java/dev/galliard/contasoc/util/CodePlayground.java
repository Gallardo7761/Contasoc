package dev.galliard.contasoc.util;

import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.ui.UIContasoc;

import java.util.List;

public class CodePlayground {

    public static void main(String[] args) {
        System.out.println(ContasocDAO.leerTabla("Socios").get(0));
    }
}