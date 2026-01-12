package ma.emsi.repository.impl;

import ma.emsi.repository.api.FournisseurRepository;
import ma.emsi.entities.Fournisseur;
import java.util.*;

public class FournisseurRepositoryImpl implements FournisseurRepository {
    private final Map<Long, Fournisseur> db = new HashMap<>();
    @Override
    public Fournisseur findById(Long id) { return db.get(id); }
    @Override
    public List<Fournisseur> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Fournisseur fournisseur) { db.put(fournisseur.getId(), fournisseur); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
