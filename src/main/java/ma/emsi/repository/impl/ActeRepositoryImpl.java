package ma.emsi.repository.impl;

import ma.emsi.repository.api.ActeRepository;
import ma.emsi.entities.Acte;
import java.util.*;

public class ActeRepositoryImpl implements ActeRepository {
    private final Map<Long, Acte> db = new HashMap<>();
    @Override
    public Acte findById(Long id) { return db.get(id); }
    @Override
    public List<Acte> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Acte acte) { db.put(acte.getId(), acte); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
