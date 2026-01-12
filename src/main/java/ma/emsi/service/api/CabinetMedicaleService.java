package ma.emsi.service.api;

import ma.emsi.entities.CabinetMedicale;
import java.util.List;

public interface CabinetMedicaleService {
    CabinetMedicale getCabinetMedicale(Long id);
    List<CabinetMedicale> getAllCabinetMedicales();
    void createOrUpdateCabinetMedicale(CabinetMedicale cabinetMedicale);
    void deleteCabinetMedicale(Long id);
}
