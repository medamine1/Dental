package ma.emsi.service.impl;

import ma.emsi.service.api.CabinetMedicaleService;
import ma.emsi.repository.api.CabinetMedicaleRepository;
import ma.emsi.entities.CabinetMedicale;
import java.util.List;

public class CabinetMedicaleServiceImpl implements CabinetMedicaleService {
    private final CabinetMedicaleRepository cabinetMedicaleRepository;
    public CabinetMedicaleServiceImpl(CabinetMedicaleRepository cabinetMedicaleRepository) {
        this.cabinetMedicaleRepository = cabinetMedicaleRepository;
    }
    @Override
    public CabinetMedicale getCabinetMedicale(Long id) { return cabinetMedicaleRepository.findById(id); }
    @Override
    public List<CabinetMedicale> getAllCabinetMedicales() { return cabinetMedicaleRepository.findAll(); }
    @Override
    public void createOrUpdateCabinetMedicale(CabinetMedicale cabinetMedicale) { cabinetMedicaleRepository.save(cabinetMedicale); }
    @Override
    public void deleteCabinetMedicale(Long id) { cabinetMedicaleRepository.delete(id); }
}
