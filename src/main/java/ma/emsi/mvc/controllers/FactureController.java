package ma.emsi.mvc.controllers;

import ma.emsi.service.api.FactureService;
import ma.emsi.entities.Facture;
import java.util.List;

public class FactureController {
    private final FactureService factureService;
    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }
    public Facture getFacture(Long id) { return factureService.getFacture(id); }
    public List<Facture> getAllFactures() { return factureService.getAllFactures(); }
    public void createOrUpdateFacture(Facture facture) { factureService.createOrUpdateFacture(facture); }
    public void deleteFacture(Long id) { factureService.deleteFacture(id); }
}
