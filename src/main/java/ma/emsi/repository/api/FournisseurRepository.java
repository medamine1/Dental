package ma.emsi.repository.api;

import ma.emsi.entities.Fournisseur;
import java.util.List;

public interface FournisseurRepository {
    Fournisseur findById(Long id);
    List<Fournisseur> findAll();
    void save(Fournisseur fournisseur);
    void delete(Long id);
}
