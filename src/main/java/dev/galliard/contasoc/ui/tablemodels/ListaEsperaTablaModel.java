package dev.galliard.contasoc.ui.tablemodels;

import javax.swing.table.DefaultTableModel;

public class ListaEsperaTablaModel extends DefaultTableModel {

    private static final String[] columnNames = { "Nº socio", "Nombre", "Teléfono", "Email", "Fecha de Alta" };

    private static final Class<?>[] columnTypes = { Integer.class, String.class, Integer.class,
            String.class, String.class };

    private static final boolean[] canEdit = { false, false, false, false, false };

    public ListaEsperaTablaModel() {
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
