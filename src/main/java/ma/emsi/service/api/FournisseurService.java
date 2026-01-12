package ma.emsi.service.api;

import ma.emsi.entities.Fournisseur;
import java.util.List;

public interface FournisseurService {
    Fournisseur getFournisseur(Long id);
    List<Fournisseur> getAllFournisseurs();
    void createOrUpdateFournisseur(Fournisseur fournisseur);
    void deleteFournisseur(Long id);
}
