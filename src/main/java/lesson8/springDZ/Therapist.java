package lesson8.springDZ;

public class Therapist implements IDoctor {

    private PatientToTherapist patient;

    public PatientToTherapist getPatient() {
        return patient;
    }

    public void setPatient(PatientToTherapist patient) {
        this.patient = patient;
    }
    @Override
    public void acceptPatient() {
        System.out.println("Пациент успешно посетил терапевта!");
        patient.healing();
    }
}
