package dev.galliard.contasoc.ui;

import dev.galliard.contasoc.database.ContasocDAO;

public class BalanceCalculatorThread implements Runnable {
    @Override
    public void run() {
        while(true) {
            if(UIContasoc.balancePanel.isVisible()) {
                GUIManager.calcularBalance();
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
