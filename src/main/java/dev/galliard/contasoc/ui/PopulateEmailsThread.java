package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.util.ContasocLogger;
import dev.galliard.contasoc.util.Parsers;

import java.sql.SQLException;
import java.util.List;

public class PopulateEmailsThread implements Runnable {

    @Override
    public void run() {
        List<String> l = null;
        try {
            l = ContasocDAO.leerTabla("Socios").stream()
                    .map(Parsers::socioParser)
                    .map(socio -> socio.getEmail().trim()+" ("+socio.getNumeroSocio()+")")
                    .map(string -> string.contains("null") ? string.replace(string, "") : string)
                    .filter(string -> !string.isBlank())
                    .toList();
        } catch (SQLException e) {
            ContasocLogger.dispatchSQLException(e);
        }
        for(String s:l) {
            UIContasoc.destinatarioComboBox.addItem(s);
        }
    }
}
