package br.com.five.thread.model;


import br.com.five.thread.service.CaminhaoTamque;

public class Fabrica{

    private int combustivel;

    public Fabrica() {
    }

    public Fabrica(int combustivel) {
        this.combustivel = combustivel;
    }

    public void abastecerFabrica(int combustivelFabrica) {
        this.combustivel = combustivelFabrica;
//        combustivel += 50;
    }

    public void abastecerCar(Car car) {

        synchronized (this) {
            if (combustivel > 50) {
                car.setCombustivel(50);
                combustivel -= 20;
            } else {
                System.out.println("Fabrica sem combustível");
            }
        }
    }

    public int getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(int combustivel) {
        this.combustivel = combustivel;
    }
}
