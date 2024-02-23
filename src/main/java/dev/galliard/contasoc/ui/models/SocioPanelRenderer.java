package dev.galliard.contasoc.ui.models;

import dev.galliard.contasoc.ui.SocioPanel;

import javax.swing.*;
import java.awt.*;

public class SocioPanelRenderer implements ListCellRenderer<SocioPanel> {
  @Override
  public Component getListCellRendererComponent(JList<? extends SocioPanel> list, SocioPanel value, int index, boolean isSelected, boolean cellHasFocus) {
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
