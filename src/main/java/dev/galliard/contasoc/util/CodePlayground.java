package dev.galliard.contasoc.util;

import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.ui.UIContasoc;

import java.io.File;
import java.util.List;

public class CodePlayground {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home") + File.separator + "Contasoc" + File.separator + "contasoc2.db");

    }
}