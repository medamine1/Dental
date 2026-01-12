package ma.emsi.mvc.ui;

import ma.emsi.entities.Patient;
import java.util.List;

public class PatientView {
    public void displayPatient(Patient patient) {
        System.out.println("Patient: " + patient.getNom() + " " + patient.getPrenom());
    }
    public void displayPatients(List<Patient> patients) {
        for (Patient p : patients) {
            displayPatient(p);
        }
    }
}
