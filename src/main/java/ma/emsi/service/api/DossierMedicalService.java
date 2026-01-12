package ma.emsi.service.api;

import ma.emsi.entities.DossierMedical;
import java.util.List;

public interface DossierMedicalService {
    DossierMedical getDossierMedical(Long id);
    List<DossierMedical> getAllDossierMedicals();
    void createOrUpdateDossierMedical(DossierMedical dossierMedical);
    void deleteDossierMedical(Long id);
}
