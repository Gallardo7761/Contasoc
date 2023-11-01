package es.exmaster.contasoc.ui;

import javax.swing.table.DefaultTableModel;

public class SociosTablaModel extends DefaultTableModel {
    private static final String[] columnNames = { "Nº huerto", "Nº socio", "Nombre", "DNI", "Teléfono", "Correo",
            "F. Alta", "F. Entrega", "F. Baja", "Notas", "Tipo", "Estado" };

    private static final Class<?>[] columnTypes = { String.class, String.class, String.class, String.class,
            String.class, String.class, String.class, String.class,
            String.class, String.class, String.class, String.class };

    private static final boolean[] canEdit = { false, false, false, false, false, false, false, false, false,
            false, false, false };

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
