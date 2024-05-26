package dev.gallardo.contasoc.ui.models;

import javax.swing.*;

import dev.gallardo.contasoc.ui.IngresoPanel;

import java.awt.*;

public class IngresoPanelRenderer implements ListCellRenderer<IngresoPanel> {
    @Override
    public Component getListCellRendererComponent(JList<? extends IngresoPanel> list, IngresoPanel value, int index, boolean isSelected, boolean cellHasFocus) {
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

