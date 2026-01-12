package ma.emsi.service.api;

import ma.emsi.entities.Utilisateur;
import java.util.List;

public interface UtilisateurService {
    Utilisateur getUtilisateur(Long id);
    List<Utilisateur> getAllUtilisateurs();
    void createOrUpdateUtilisateur(Utilisateur utilisateur);
    void deleteUtilisateur(Long id);
}
