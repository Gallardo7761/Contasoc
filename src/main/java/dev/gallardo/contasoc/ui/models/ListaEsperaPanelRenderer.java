package dev.gallardo.contasoc.ui.models;

import javax.swing.*;

import dev.gallardo.contasoc.ui.ListaEsperaPanel;

import java.awt.*;

public class ListaEsperaPanelRenderer implements ListCellRenderer<ListaEsperaPanel> {
    @Override
    public Component getListCellRendererComponent(JList<? extends ListaEsperaPanel> list, ListaEsperaPanel value, int index, boolean isSelected, boolean cellHasFocus) {
        // Cambiar el aspecto del panel seleccionado
        if (isSelected) {
            value.setBackground(list.getSelectionBackground());
            value.setForeground(list.getSelectionForeground());
        } else {
            value.setBackground(list.getBackground());
            value.setForeground(list.getForeground());
        }
        return value;
    }
}
