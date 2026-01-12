package ma.emsi.mvc.controllers;

import ma.emsi.service.api.OrdonnanceService;
import ma.emsi.entities.Ordonnance;
import java.util.List;

public class OrdonnanceController {
    private final OrdonnanceService ordonnanceService;
    public OrdonnanceController(OrdonnanceService ordonnanceService) {
        this.ordonnanceService = ordonnanceService;
    }
    public Ordonnance getOrdonnance(Long id) { return ordonnanceService.getOrdonnance(id); }
    public List<Ordonnance> getAllOrdonnances() { return ordonnanceService.getAllOrdonnances(); }
    public void createOrUpdateOrdonnance(Ordonnance ordonnance) { ordonnanceService.createOrUpdateOrdonnance(ordonnance); }
    public void deleteOrdonnance(Long id) { ordonnanceService.deleteOrdonnance(id); }
}
