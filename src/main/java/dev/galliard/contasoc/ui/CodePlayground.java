package dev.galliard.contasoc.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CodePlayground {
    public static void main(String[] args) {
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}

