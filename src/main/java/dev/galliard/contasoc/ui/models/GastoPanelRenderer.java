package dev.galliard.contasoc.ui.models;

import dev.galliard.contasoc.ui.GastoPanel;
import dev.galliard.contasoc.ui.SocioPanel;

import javax.swing.*;
import java.awt.*;

public class GastoPanelRenderer implements ListCellRenderer<GastoPanel> {
    @Override
    public Component getListCellRendererComponent(JList<? extends GastoPanel> list, GastoPanel value, int index, boolean isSelected, boolean cellHasFocus) {
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

