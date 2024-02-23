package dev.galliard.contasoc.util;

import java.io.InputStream;

public class ResourceChecker {

    public static void main(String[] args) {
        String resourcePath = "/images/logohuerto_pdf.png";
        checkResource(resourcePath);
    }

    public static void checkResource(String resourcePath) {
        String resource = ResourceChecker.class.getResource(resourcePath).toExternalForm();
        if (resource != null) {
            System.out.println("El recurso '" + resourcePath + "' existe.");
        } else {
            System.out.println("El recurso '" + resourcePath + "' no se encuentra.");
        }
    }
}
