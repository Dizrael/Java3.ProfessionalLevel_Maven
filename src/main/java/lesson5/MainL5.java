package lesson5;

import lesson5.models.Car;
import lesson5.models.Race;
import lesson5.models.Road;
import lesson5.models.Tunnel;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MainL5 {

    public static final int CARS_COUNT = 4;
    public static final int FIRST_ROAD_LENGTH = 60;
    public static final int LAST_ROAD_LENGTH = 40;

    private static CyclicBarrier cb = new CyclicBarrier(CARS_COUNT+1);
    private static CyclicBarrier finishBar = new CyclicBarrier(2);

    public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(FIRST_ROAD_LENGTH, finishBar), new Tunnel(), new Road(LAST_ROAD_LENGTH, finishBar));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb);
        }
        for (Car car : cars) {
            new Thread(car).start();
        }
        try {
            cb.await();
            cb.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            finishBar.await();

            cb.await();
            cb.await();

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}