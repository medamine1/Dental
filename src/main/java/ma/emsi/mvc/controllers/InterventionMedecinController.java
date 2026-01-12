package ma.emsi.mvc.controllers;

import ma.emsi.service.api.InterventionMedecinService;
import ma.emsi.entities.InterventionMedecin;
import java.util.List;

public class InterventionMedecinController {
    private final InterventionMedecinService interventionMedecinService;
    public InterventionMedecinController(InterventionMedecinService interventionMedecinService) {
        this.interventionMedecinService = interventionMedecinService;
    }
    public InterventionMedecin getInterventionMedecin(Long id) { return interventionMedecinService.getInterventionMedecin(id); }
    public List<InterventionMedecin> getAllInterventionMedecins() { return interventionMedecinService.getAllInterventionMedecins(); }
    public void createOrUpdateInterventionMedecin(InterventionMedecin interventionMedecin) { interventionMedecinService.createOrUpdateInterventionMedecin(interventionMedecin); }
    public void deleteInterventionMedecin(Long id) { interventionMedecinService.deleteInterventionMedecin(id); }
}
