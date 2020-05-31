package lesson8.springDZ;

public class Surgeon implements IDoctor {

    private PatientToSurgeon patient;

    public PatientToSurgeon getPatient() {
        return patient;
    }


    public void setPatient(PatientToSurgeon patient) {
        this.patient = patient;
    }

    @Override
    public void acceptPatient() {
        System.out.println("Врач принял пациента.");
        patient.healing();
    }
}
