package ma.emsi.service.api;

import ma.emsi.entities.Patient;
import java.util.List;

public interface PatientService {
    Patient getPatient(Long id);
    List<Patient> getAllPatients();
    void createOrUpdatePatient(Patient patient);
    void deletePatient(Long id);
}
