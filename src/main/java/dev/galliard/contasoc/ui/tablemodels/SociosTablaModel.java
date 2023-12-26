package dev.galliard.contasoc.ui.tablemodels;

import dev.galliard.contasoc.util.Parsers;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SociosTablaModel extends DefaultTableModel {
    public static final String[] columnNames = { "Socio", "Huerto", "Nombre", "DNI", "Teléfono", "Correo",
            "F. Alta", "F. Entrega", "F. Baja", "Notas", "Tipo", "Estado" };

    private static final Class<?>[] columnTypes = { String.class, String.class, String.class, String.class,
            String.class, String.class, String.class, String.class,
            String.class, String.class, String.class, String.class };

    public SociosTablaModel() {
        super(null, columnNames);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }



}
