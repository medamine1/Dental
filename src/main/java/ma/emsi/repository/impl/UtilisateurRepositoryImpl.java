package ma.emsi.repository.impl;

import ma.emsi.repository.api.UtilisateurRepository;
import ma.emsi.entities.Utilisateur;
import java.util.*;

public class UtilisateurRepositoryImpl implements UtilisateurRepository {
    private final Map<Long, Utilisateur> db = new HashMap<>();
    @Override
    public Utilisateur findById(Long id) { return db.get(id); }
    @Override
    public List<Utilisateur> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Utilisateur utilisateur) { db.put(utilisateur.getId(), utilisateur); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
