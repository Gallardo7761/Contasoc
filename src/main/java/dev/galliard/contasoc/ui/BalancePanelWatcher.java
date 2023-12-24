package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.database.ContasocDAO;
import dev.galliard.contasoc.ui.SaldoInicial;
import dev.galliard.contasoc.ui.UIContasoc;

public class BalancePanelWatcher implements Runnable {
    @Override
    public void run() {
        while(!UIContasoc.balancePanel.isVisible()) {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        double inicialBanco = ContasocDAO.leerTabla("Balance").isEmpty() ? 0.0 : Double.parseDouble(ContasocDAO.select("Balance",new Object[] {"inicialBanco"}, ""));
        double inicialCaja = ContasocDAO.leerTabla("Balance").isEmpty() ? 0.0 : Double.parseDouble(ContasocDAO.select("Balance",new Object[] {"inicialCaja"}, ""));
        if(inicialBanco == 0.0 && inicialCaja == 0.0) {
            SaldoInicial saldoInicial = new SaldoInicial();
            saldoInicial.setVisible(true);
            saldoInicial.requestFocus();
        }
    }
}
