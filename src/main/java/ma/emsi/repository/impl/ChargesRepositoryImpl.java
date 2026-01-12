package ma.emsi.repository.impl;

import ma.emsi.repository.api.ChargesRepository;
import ma.emsi.entities.Charges;
import java.util.*;

public class ChargesRepositoryImpl implements ChargesRepository {
    private final Map<Long, Charges> db = new HashMap<>();
    @Override
    public Charges findById(Long id) { return db.get(id); }
    @Override
    public List<Charges> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Charges charges) { db.put(charges.getId(), charges); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
