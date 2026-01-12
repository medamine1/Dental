package ma.emsi.service.api;

import ma.emsi.entities.Ordonnance;
import java.util.List;

public interface OrdonnanceService {
    Ordonnance getOrdonnance(Long id);
    List<Ordonnance> getAllOrdonnances();
    void createOrUpdateOrdonnance(Ordonnance ordonnance);
    void deleteOrdonnance(Long id);
}
