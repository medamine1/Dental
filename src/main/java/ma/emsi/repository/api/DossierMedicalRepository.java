package ma.emsi.repository.api;

import ma.emsi.entities.DossierMedical;
import java.util.List;

public interface DossierMedicalRepository {
    DossierMedical findById(Long id);
    List<DossierMedical> findAll();
    void save(DossierMedical dossierMedical);
    void delete(Long id);
}
