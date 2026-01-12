package ma.emsi.repository.impl;

import ma.emsi.repository.api.OrdonnanceRepository;
import ma.emsi.entities.Ordonnance;
import java.util.*;

public class OrdonnanceRepositoryImpl implements OrdonnanceRepository {
    private final Map<Long, Ordonnance> db = new HashMap<>();
    @Override
    public Ordonnance findById(Long id) { return db.get(id); }
    @Override
    public List<Ordonnance> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Ordonnance ordonnance) { db.put(ordonnance.getId(), ordonnance); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
