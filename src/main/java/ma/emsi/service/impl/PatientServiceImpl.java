package ma.emsi.service.impl;

import ma.emsi.service.api.PatientService;
import ma.emsi.repository.api.PatientRepository;
import ma.emsi.entities.Patient;
import java.util.List;

public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Override
    public Patient getPatient(Long id) { return patientRepository.findById(id); }
    @Override
    public List<Patient> getAllPatients() { return patientRepository.findAll(); }
    @Override
    public void createOrUpdatePatient(Patient patient) { patientRepository.save(patient); }
    @Override
    public void deletePatient(Long id) { patientRepository.delete(id); }
}
