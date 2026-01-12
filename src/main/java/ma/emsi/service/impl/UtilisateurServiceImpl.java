package ma.emsi.service.impl;

import ma.emsi.service.api.UtilisateurService;
import ma.emsi.repository.api.UtilisateurRepository;
import ma.emsi.entities.Utilisateur;
import java.util.List;

public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
    @Override
    public Utilisateur getUtilisateur(Long id) { return utilisateurRepository.findById(id); }
    @Override
    public List<Utilisateur> getAllUtilisateurs() { return utilisateurRepository.findAll(); }
    @Override
    public void createOrUpdateUtilisateur(Utilisateur utilisateur) { utilisateurRepository.save(utilisateur); }
    @Override
    public void deleteUtilisateur(Long id) { utilisateurRepository.delete(id); }
}
