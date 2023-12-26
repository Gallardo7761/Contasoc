package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.database.ContasocDAO;

public class BalancePanelWatcher implements Runnable {
    @Override
    public void run() {
        double inicialBanco = ContasocDAO.leerTabla("Balance").isEmpty() ? 0.0 : Double.parseDouble(ContasocDAO.select("Balance",new Object[] {"inicialBanco"}, ""));
        double inicialCaja = ContasocDAO.leerTabla("Balance").isEmpty() ? 0.0 : Double.parseDouble(ContasocDAO.select("Balance",new Object[] {"inicialCaja"}, ""));
        if(inicialBanco == 0.0 && inicialCaja == 0.0) {
            SaldoInicial saldoInicial = new SaldoInicial();
            saldoInicial.setVisible(true);
            saldoInicial.requestFocus();
        }
    }
}
