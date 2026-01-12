package ma.emsi.repository.impl;

import ma.emsi.repository.api.InterventionMedecinRepository;
import ma.emsi.entities.InterventionMedecin;
import java.util.*;

public class InterventionMedecinRepositoryImpl implements InterventionMedecinRepository {
    private final Map<Long, InterventionMedecin> db = new HashMap<>();
    @Override
    public InterventionMedecin findById(Long id) { return db.get(id); }
    @Override
    public List<InterventionMedecin> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(InterventionMedecin interventionMedecin) { db.put(interventionMedecin.getId(), interventionMedecin); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
