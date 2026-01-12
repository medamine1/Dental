package ma.emsi.repository.impl;

import ma.emsi.repository.api.SecretaireRepository;
import ma.emsi.entities.Secretaire;
import java.util.*;

public class SecretaireRepositoryImpl implements SecretaireRepository {
    private final Map<Long, Secretaire> db = new HashMap<>();
    @Override
    public Secretaire findById(Long id) { return db.get(id); }
    @Override
    public List<Secretaire> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Secretaire secretaire) { db.put(secretaire.getId(), secretaire); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
