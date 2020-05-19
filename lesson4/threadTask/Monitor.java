package lesson4.threadTask;

import java.util.ArrayList;

public class Monitor {

    ArrayList<String> myArr;
    int currentIndex = 0;
    int ciclesCount;
    int stopper;

    public Monitor(ArrayList myArr, int ciclesCount){
        this.myArr = myArr;
        this.ciclesCount = ciclesCount;
    }


    public synchronized void greenLight(String litera) {
        boolean notYourTurn = true;
        while(notYourTurn){
            if (!litera.equals(myArr.get(currentIndex))){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                notYourTurn = false;
            }
        }
    }

    public synchronized void addPrintLetera(String litera) {
        stopper++;
        currentIndex = (currentIndex+1) % myArr.size();
        try {
            notifyAll();
            if(stopper != ciclesCount*myArr.size()){
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
