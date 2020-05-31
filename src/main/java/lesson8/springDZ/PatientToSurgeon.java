package lesson8.springDZ;

import java.util.Random;

public class PatientToSurgeon implements IPatient {
    private Random random = new Random();

    int id = random.nextInt();

    @Override
    public void healing() {
        System.out.println("Пациент " + id + " успешно посетил хирурга!");
    }
}
