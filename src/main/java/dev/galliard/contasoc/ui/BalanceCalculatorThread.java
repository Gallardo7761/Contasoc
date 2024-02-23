package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.database.objects.Gastos;
import dev.galliard.contasoc.database.objects.Ingresos;

import java.util.List;

public class BalanceCalculatorThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            List<Gastos> gastos = GUIManager.getGastos();
            List<Ingresos> ingresos = GUIManager.getIngresos();
            if (UIContasoc.balancePanel.isVisible()) {
                GUIManager.calcularBalance(Contasoc.balance, ingresos, gastos);
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
