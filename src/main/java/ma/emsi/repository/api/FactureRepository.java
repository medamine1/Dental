package ma.emsi.repository.api;

import ma.emsi.entities.Facture;
import java.util.List;

public interface FactureRepository {
    Facture findById(Long id);
    List<Facture> findAll();
    void save(Facture facture);
    void delete(Long id);
}
