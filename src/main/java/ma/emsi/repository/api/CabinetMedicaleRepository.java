package ma.emsi.repository.api;

import ma.emsi.entities.CabinetMedicale;
import java.util.List;

public interface CabinetMedicaleRepository {
    CabinetMedicale findById(Long id);
    List<CabinetMedicale> findAll();
    void save(CabinetMedicale cabinetMedicale);
    void delete(Long id);
}
