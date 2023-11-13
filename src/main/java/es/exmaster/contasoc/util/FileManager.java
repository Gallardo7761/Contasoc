package es.exmaster.contasoc.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    public static void createFile(String name, String dir) {
        try {
            Files.createFile(Path.of(dir+"/"+name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
