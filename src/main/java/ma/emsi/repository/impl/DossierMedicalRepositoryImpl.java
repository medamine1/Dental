package ma.emsi.repository.impl;

import ma.emsi.repository.api.DossierMedicalRepository;
import ma.emsi.entities.DossierMedical;
import java.util.*;

public class DossierMedicalRepositoryImpl implements DossierMedicalRepository {
    private final Map<Long, DossierMedical> db = new HashMap<>();
    @Override
    public DossierMedical findById(Long id) { return db.get(id); }
    @Override
    public List<DossierMedical> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(DossierMedical dossierMedical) { db.put(dossierMedical.getId(), dossierMedical); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
