package es.exmaster.contasoc.util;

import java.awt.*;

public class ResourceTest {
    public static void main(String[] args) {
        java.awt.Image awtImg = Toolkit.getDefaultToolkit()
            .getImage(ClassLoader.getSystemResource("assets/createTables.sql"));
        if (awtImg == null) {
            System.out.println("not found.");
        } else {
            System.out.println("found.");
        }
    }
}

