package ma.emsi.repository.impl;

import ma.emsi.repository.api.PatientRepository;
import ma.emsi.entities.Patient;
import java.util.*;

public class PatientRepositoryImpl implements PatientRepository {
    private final Map<Long, Patient> db = new HashMap<>();
    @Override
    public Patient findById(Long id) { return db.get(id); }
    @Override
    public List<Patient> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Patient patient) { db.put(patient.getId(), patient); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
