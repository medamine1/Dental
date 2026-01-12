package ma.emsi.repository.api;

import ma.emsi.entities.Acte;
import java.util.List;

public interface ActeRepository {
    Acte findById(Long id);
    List<Acte> findAll();
    void save(Acte acte);
    void delete(Long id);
}
