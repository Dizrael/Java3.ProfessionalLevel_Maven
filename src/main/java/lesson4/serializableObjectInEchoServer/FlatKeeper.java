package lesson4.serializableObjectInEchoServer;

import java.io.Serializable;

public class FlatKeeper implements Serializable {
    private int angry;
    private String name;

    public FlatKeeper(int angry, String name){
        this.angry = angry;
        this.name = name;
    }

    public void info(){
        System.out.println(name + " is angry on " + angry + "% !!!" +
                "\nYou must pay him for flat.");
    }
}
