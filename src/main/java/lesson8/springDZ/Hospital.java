package lesson8.springDZ;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class Hospital {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        IDoctor surgeon = context.getBean("doctorSurgeon", IDoctor.class);
        IDoctor therapist = context.getBean("doctorTherapist", IDoctor.class);

        surgeon.acceptPatient();
        therapist.acceptPatient();
    }
}
