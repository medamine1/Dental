package ma.emsi.repository.api;

import ma.emsi.entities.Patient;
import java.util.List;

public interface PatientRepository {
    Patient findById(Long id);
    List<Patient> findAll();
    void save(Patient patient);
    void delete(Long id);
}
