package ma.emsi.repository.impl;

import ma.emsi.repository.api.CabinetMedicaleRepository;
import ma.emsi.entities.CabinetMedicale;
import java.util.*;

public class CabinetMedicaleRepositoryImpl implements CabinetMedicaleRepository {
    private final Map<Long, CabinetMedicale> db = new HashMap<>();
    @Override
    public CabinetMedicale findById(Long id) { return db.get(id); }
    @Override
    public List<CabinetMedicale> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(CabinetMedicale cabinetMedicale) { db.put(cabinetMedicale.getId(), cabinetMedicale); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
