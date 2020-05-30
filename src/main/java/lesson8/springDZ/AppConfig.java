package lesson8.springDZ;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("lesson8.springDZ")
public class AppConfig {

    @Bean(name = "doctorSurgeon")
    public IDoctor doctorSurgeon(){
        Surgeon surgeon = new Surgeon();
        surgeon.setPatient(new PatientToSurgeon());
        return surgeon;
    }

    @Bean(name = "doctorTherapist")
    public IDoctor doctorTherapist(){
        Therapist therapist = new Therapist();
        therapist.setPatient(new PatientToTherapist());
        return therapist;
    }
//
//       @Bean(name = "patientToTerapist")
//    public IPatient patientToTerapist(){
//        return new PatientToTherapist();
//    }
//
//    @Bean(name = "patientToSurgeon")
//    public IPatient patientToSurgeon(){
//        return new PatientToSurgeon();
//    }
}
