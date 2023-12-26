package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.util.Parsers;

import java.util.List;

public class PopulateEmailsThread implements Runnable {

    @Override
    public void run() {
        List<String> l = ContasocDAO.leerTabla("Socios").stream()
                .map(Parsers::socioParser)
                .map(socio -> socio.getPersona().getCorreo().trim()+" ("+socio.getSocio()+")")
                .map(string -> string.contains("null") ? string.replace(string, "") : string)
                .filter(string -> !string.isBlank())
                .toList();
        for(String s:l) {
            UIContasoc.destinatarioComboBox.addItem(s);
        }
    }
}
