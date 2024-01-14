package dev.galliard.contasoc.ui.tablemodels;

import javax.swing.table.DefaultTableModel;

public class IngresosTablaModel extends DefaultTableModel {
    private static final String[] columnNames = { "Nº Socio", "Fecha", "Concepto", "Cantidad", "Tipo" };

    private static final Class<?>[] columnTypes = { Integer.class, String.class, String.class,
            Double.class, String.class };

    private static final boolean[] canEdit = { false, false, false, false, false };

    public IngresosTablaModel() {
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
