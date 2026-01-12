package ma.emsi.mvc.controllers;

import ma.emsi.service.api.CabinetMedicaleService;
import ma.emsi.entities.CabinetMedicale;
import java.util.List;

public class CabinetMedicaleController {
    private final CabinetMedicaleService cabinetMedicaleService;
    public CabinetMedicaleController(CabinetMedicaleService cabinetMedicaleService) {
        this.cabinetMedicaleService = cabinetMedicaleService;
    }
    public CabinetMedicale getCabinetMedicale(Long id) { return cabinetMedicaleService.getCabinetMedicale(id); }
    public List<CabinetMedicale> getAllCabinetMedicales() { return cabinetMedicaleService.getAllCabinetMedicales(); }
    public void createOrUpdateCabinetMedicale(CabinetMedicale cabinetMedicale) { cabinetMedicaleService.createOrUpdateCabinetMedicale(cabinetMedicale); }
    public void deleteCabinetMedicale(Long id) { cabinetMedicaleService.deleteCabinetMedicale(id); }
}
