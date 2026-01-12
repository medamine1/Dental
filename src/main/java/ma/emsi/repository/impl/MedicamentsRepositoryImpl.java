package ma.emsi.repository.impl;

import ma.emsi.repository.api.MedicamentsRepository;
import ma.emsi.entities.Medicaments;
import java.util.*;

public class MedicamentsRepositoryImpl implements MedicamentsRepository {
    private final Map<Long, Medicaments> db = new HashMap<>();
    @Override
    public Medicaments findById(Long id) { return db.get(id); }
    @Override
    public List<Medicaments> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Medicaments medicaments) { db.put(medicaments.getId(), medicaments); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
