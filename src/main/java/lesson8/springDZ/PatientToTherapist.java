package lesson8.springDZ;

import java.io.RandomAccessFile;
import java.util.Random;

public class PatientToTherapist implements IPatient {
    private Random random = new Random();

    int id = random.nextInt();

    @Override
    public void healing() {
        System.out.println("Пациент " + id + " успешно посетил терапевта!");
    }
}
