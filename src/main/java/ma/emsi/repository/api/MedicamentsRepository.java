package ma.emsi.repository.api;

import ma.emsi.entities.Medicaments;
import java.util.List;

public interface MedicamentsRepository {
    Medicaments findById(Long id);
    List<Medicaments> findAll();
    void save(Medicaments medicaments);
    void delete(Long id);
}
