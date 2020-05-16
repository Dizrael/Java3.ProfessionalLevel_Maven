package lesson4.threadTask;

public class MyThread implements Runnable {
    String litera;
    Monitor monitor;
    int ciclesCount;

    public MyThread(String litera, Monitor monitor, int ciclesCount){
        this.litera = litera;
        this.monitor = monitor;
        this.ciclesCount = ciclesCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < ciclesCount; i++) {
            monitor.greenLight(litera);
            System.out.print(litera);
            monitor.addPrintLetera(litera);
        }
    }
}
