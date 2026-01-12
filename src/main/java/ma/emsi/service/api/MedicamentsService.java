package ma.emsi.service.api;

import ma.emsi.entities.Medicaments;
import java.util.List;

public interface MedicamentsService {
    Medicaments getMedicaments(Long id);
    List<Medicaments> getAllMedicaments();
    void createOrUpdateMedicaments(Medicaments medicaments);
    void deleteMedicaments(Long id);
}
