package ma.emsi.repository.api;

import ma.emsi.entities.Ordonnance;
import java.util.List;

public interface OrdonnanceRepository {
    Ordonnance findById(Long id);
    List<Ordonnance> findAll();
    void save(Ordonnance ordonnance);
    void delete(Long id);
}
