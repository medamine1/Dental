package ma.emsi.service.api;

import ma.emsi.entities.InterventionMedecin;
import java.util.List;

public interface InterventionMedecinService {
    InterventionMedecin getInterventionMedecin(Long id);
    List<InterventionMedecin> getAllInterventionMedecins();
    void createOrUpdateInterventionMedecin(InterventionMedecin interventionMedecin);
    void deleteInterventionMedecin(Long id);
}
