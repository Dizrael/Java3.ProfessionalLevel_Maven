package lesson5.models;

import lesson5.MainL5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Road extends Stage {
    CyclicBarrier finishBar;
    public static Lock lock = new ReentrantLock();
    private static int winCounter = 0;
    public Road(int length, CyclicBarrier finishBar) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
        this.finishBar = finishBar;
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            if(length == MainL5.LAST_ROAD_LENGTH && winCounter == 0){
                lock.lock();
                finishBar.await();
                System.out.println(c.getName() + " WIN!!!");
                winCounter++;
                lock.unlock();
            }

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}


//if(i == race.lastStage){
////                    cb.await();
//        System.out.println(this.getName() + " WIN!!!");
//        }