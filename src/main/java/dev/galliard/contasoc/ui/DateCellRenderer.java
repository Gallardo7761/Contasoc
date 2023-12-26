package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.util.Parsers;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class DateCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if(!(value==null) && (value.toString().matches("\\d{4}-\\d{2}-\\d{2}"))) {
            value = Parsers.dashDateParser((String) value);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}