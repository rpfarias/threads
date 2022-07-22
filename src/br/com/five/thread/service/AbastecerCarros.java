package br.com.five.thread.service;

import br.com.five.thread.model.Car;
import br.com.five.thread.model.Fabrica;

public class AbastecerCarros implements Runnable{

    private Fabrica fabrica;
    private Car car;

    public AbastecerCarros(Fabrica fabrica, Car car) {
        this.fabrica = fabrica;
        this.car = car;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (car.getCombustivel() <= 0) {
                    System.out.println("Abastecendo...: " + car.getId());
                    Thread.sleep(5000);
                    fabrica.abastecerCar(car);
                    System.out.println(car.getId() + " abastecido");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
