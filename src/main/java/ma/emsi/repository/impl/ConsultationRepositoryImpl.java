package ma.emsi.repository.impl;

import ma.emsi.repository.api.ConsultationRepository;
import ma.emsi.entities.Consultation;
import java.util.*;

public class ConsultationRepositoryImpl implements ConsultationRepository {
    private final Map<Long, Consultation> db = new HashMap<>();
    @Override
    public Consultation findById(Long id) { return db.get(id); }
    @Override
    public List<Consultation> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Consultation consultation) { db.put(consultation.getId(), consultation); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
