package ma.emsi.repository.api;

import ma.emsi.entities.Medecin;
import java.util.List;

public interface MedecinRepository {
    Medecin findById(Long id);
    List<Medecin> findAll();
    void save(Medecin medecin);
    void delete(Long id);
}
