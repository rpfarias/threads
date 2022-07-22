package br.com.five.thread.service;

import br.com.five.thread.model.Car;

public class CarroEmMovimento implements Runnable{

    private Car car;

    public CarroEmMovimento(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (car.getCombustivel() > 0) {
                    System.out.println("Carro " + car.getId() + " em movimento... "+ "Com " + car.getCombustivel() + " litros de combustível.");
                    car.mover();
                    Thread.sleep(5000);
                    car.abastecer(5);
                    System.out.println("Enchendo tanque do carro... " + car.getCombustivel() + " litros.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
