package es.exmaster.contasoc.ui;

import javax.swing.table.DefaultTableModel;

public class GastosTablaModel extends DefaultTableModel {
    private static final String[] columnNames = { "Fecha", "Proveedor", "Concepto", "Cantidad", "Factura", "Tipo" };

    private static final Class<?>[] columnTypes = { String.class, String.class, String.class,
            String.class, String.class, String.class };

    private static final boolean[] canEdit = { false, false, false, false, false };

    public GastosTablaModel() {
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
