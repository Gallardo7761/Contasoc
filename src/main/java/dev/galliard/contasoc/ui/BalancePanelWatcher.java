package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.util.ContasocLogger;

import java.sql.SQLException;

public class BalancePanelWatcher implements Runnable {
    @Override
    public void run() {
        double inicialBanco = 0;
        double inicialCaja = 0;
        try {
            inicialBanco = ContasocDAO.leerTabla("Balance").isEmpty() ? 0.0 : Double.parseDouble(ContasocDAO.select("Balance",new Object[] {"inicialBanco"}, ""));
            inicialCaja = ContasocDAO.leerTabla("Balance").isEmpty() ? 0.0 : Double.parseDouble(ContasocDAO.select("Balance",new Object[] {"inicialCaja"}, ""));
        } catch (SQLException e) {
            ContasocLogger.dispatchSQLException(e);
        }
        if(inicialBanco == 0.0 && inicialCaja == 0.0) {
            SaldoInicial saldoInicial = SaldoInicial.getInstance();
            saldoInicial.setVisible(true);
            saldoInicial.requestFocus();
        }
    }
}
