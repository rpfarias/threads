package br.com.five.thread.service;

import br.com.five.thread.model.Fabrica;

public class CaminhaoTamque implements Runnable{

    private Fabrica fabrica;

    private int quantidadeGasolina;

    public CaminhaoTamque(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (getQuantidadeGasolina() >= 0 ) {
                    System.out.println("Total de litros na Fábrica: " + fabrica.getCombustivel() + " litros.");
                    fabrica.abastecerFabrica(50);
                    Thread.sleep(3000);
                } else {
                    System.out.println("Sem gasolina!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getQuantidadeGasolina() {
        return quantidadeGasolina;
    }
}
