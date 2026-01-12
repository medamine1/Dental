package ma.emsi.mvc.controllers;

import ma.emsi.service.api.UtilisateurService;
import ma.emsi.entities.Utilisateur;
import java.util.List;

public class UtilisateurController {
    private final UtilisateurService utilisateurService;
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
    public Utilisateur getUtilisateur(Long id) { return utilisateurService.getUtilisateur(id); }
    public List<Utilisateur> getAllUtilisateurs() { return utilisateurService.getAllUtilisateurs(); }
    public void createOrUpdateUtilisateur(Utilisateur utilisateur) { utilisateurService.createOrUpdateUtilisateur(utilisateur); }
    public void deleteUtilisateur(Long id) { utilisateurService.deleteUtilisateur(id); }
}
