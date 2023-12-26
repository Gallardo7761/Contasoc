package dev.galliard.contasoc.util;

import java.awt.*;

public class ResourceTest {
    public static void main(String[] args) {
        java.awt.Image awtImg = Toolkit.getDefaultToolkit()
            .getImage(ClassLoader.getSystemResource("resources/images/logohuerto_pdf.png"));
        if (awtImg == null) {
            System.out.println("not found.");
        } else {
            System.out.println("found.");
        }
    }
}

