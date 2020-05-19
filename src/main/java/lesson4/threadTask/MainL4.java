package lesson4.threadTask;

import java.util.ArrayList;
import java.util.Arrays;

public class MainL4 {
    public static void main(String[] args) {
        final int CICLES_COUNT = 10;

        ArrayList<String> dict = new ArrayList<>(Arrays.asList("Артем ", "КРАСА", "ВА "));
        Monitor monitor = new Monitor(dict, CICLES_COUNT);

        for (String s : dict) {
            new Thread(new MyThread(s, monitor, CICLES_COUNT)).start();
        }

    }

}
