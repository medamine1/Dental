package ma.emsi.service.api;

import ma.emsi.entities.Medecin;
import java.util.List;

public interface MedecinService {
    Medecin getMedecin(Long id);
    List<Medecin> getAllMedecins();
    void createOrUpdateMedecin(Medecin medecin);
    void deleteMedecin(Long id);
}
