package ma.emsi.mvc.controllers;

import ma.emsi.service.api.PatientService;
import ma.emsi.entities.Patient;
import java.util.List;

public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    public Patient getPatient(Long id) { return patientService.getPatient(id); }
    public List<Patient> getAllPatients() { return patientService.getAllPatients(); }
    public void createOrUpdatePatient(Patient patient) { patientService.createOrUpdatePatient(patient); }
    public void deletePatient(Long id) { patientService.deletePatient(id); }
}
