package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.util.Parsers;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        this.setHorizontalAlignment(JLabel.LEFT);
         if(value != null && value.toString().matches("\\d{4}-\\d{2}-\\d{2}")) {
             value = Parsers.dashDateParser(value.toString());
         }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}