package ma.emsi.mvc.controllers;

import ma.emsi.service.api.MedicamentsService;
import ma.emsi.entities.Medicaments;
import java.util.List;

public class MedicamentsController {
    private final MedicamentsService medicamentsService;
    public MedicamentsController(MedicamentsService medicamentsService) {
        this.medicamentsService = medicamentsService;
    }
    public Medicaments getMedicaments(Long id) { return medicamentsService.getMedicaments(id); }
    public List<Medicaments> getAllMedicaments() { return medicamentsService.getAllMedicaments(); }
    public void createOrUpdateMedicaments(Medicaments medicaments) { medicamentsService.createOrUpdateMedicaments(medicaments); }
    public void deleteMedicaments(Long id) { medicamentsService.deleteMedicaments(id); }
}
