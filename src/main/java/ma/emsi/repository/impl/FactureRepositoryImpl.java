package ma.emsi.repository.impl;

import ma.emsi.repository.api.FactureRepository;
import ma.emsi.entities.Facture;
import java.util.*;

public class FactureRepositoryImpl implements FactureRepository {
    private final Map<Long, Facture> db = new HashMap<>();
    @Override
    public Facture findById(Long id) { return db.get(id); }
    @Override
    public List<Facture> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Facture facture) { db.put(facture.getId(), facture); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
