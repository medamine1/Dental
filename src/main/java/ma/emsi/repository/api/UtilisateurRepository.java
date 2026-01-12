package ma.emsi.repository.api;

import ma.emsi.entities.Utilisateur;
import java.util.List;

public interface UtilisateurRepository {
    Utilisateur findById(Long id);
    List<Utilisateur> findAll();
    void save(Utilisateur utilisateur);
    void delete(Long id);
}
