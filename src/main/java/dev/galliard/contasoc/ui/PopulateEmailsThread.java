package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.Contasoc;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class PopulateEmailsThread implements Runnable {

    @Override
    public void run() {
        try {
            GUIManager.getSocios().stream()
                    .filter(s -> s.getEmail() != null && !s.getEmail().isEmpty()) // Filtrar los socios con email válido
                    .map(s -> s.getEmail() + "(" + s.getNumeroSocio() + ")")
                    .forEach(UIContasoc.destinatarioComboBox::addItem);
        } catch (ConstraintViolationException e) {
            Contasoc.logger.error("Error SQL", e);
        }
    }
}
