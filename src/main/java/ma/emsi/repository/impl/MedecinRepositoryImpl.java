package ma.emsi.repository.impl;

import ma.emsi.repository.api.MedecinRepository;
import ma.emsi.entities.Medecin;
import java.util.*;

public class MedecinRepositoryImpl implements MedecinRepository {
    private final Map<Long, Medecin> db = new HashMap<>();
    @Override
    public Medecin findById(Long id) { return db.get(id); }
    @Override
    public List<Medecin> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Medecin medecin) { db.put(medecin.getId(), medecin); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
