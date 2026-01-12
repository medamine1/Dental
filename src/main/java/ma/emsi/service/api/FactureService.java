package ma.emsi.service.api;

import ma.emsi.entities.Facture;
import java.util.List;

public interface FactureService {
    Facture getFacture(Long id);
    List<Facture> getAllFactures();
    void createOrUpdateFacture(Facture facture);
    void deleteFacture(Long id);
}
