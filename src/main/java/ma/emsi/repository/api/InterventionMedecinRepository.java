package ma.emsi.repository.api;

import ma.emsi.entities.InterventionMedecin;
import java.util.List;

public interface InterventionMedecinRepository {
    InterventionMedecin findById(Long id);
    List<InterventionMedecin> findAll();
    void save(InterventionMedecin interventionMedecin);
    void delete(Long id);
}
