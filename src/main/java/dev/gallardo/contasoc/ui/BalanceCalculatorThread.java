package dev.gallardo.contasoc.ui;

import java.util.List;

import dev.gallardo.contasoc.Contasoc;
import dev.gallardo.contasoc.database.objects.Gastos;
import dev.gallardo.contasoc.database.objects.Ingresos;

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
