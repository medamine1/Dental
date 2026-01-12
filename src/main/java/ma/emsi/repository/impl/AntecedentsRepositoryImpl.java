package ma.emsi.repository.impl;

import ma.emsi.repository.api.AntecedentsRepository;
import ma.emsi.entities.Antecedents;
import java.util.*;

public class AntecedentsRepositoryImpl implements AntecedentsRepository {
    private final Map<Long, Antecedents> db = new HashMap<>();
    @Override
    public Antecedents findById(Long id) { return db.get(id); }
    @Override
    public List<Antecedents> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Antecedents antecedents) { db.put(antecedents.getId(), antecedents); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
