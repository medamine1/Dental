package ma.emsi.mvc.controllers;

import ma.emsi.service.api.MedecinService;
import ma.emsi.entities.Medecin;
import java.util.List;

public class MedecinController {
    private final MedecinService medecinService;
    public MedecinController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }
    public Medecin getMedecin(Long id) { return medecinService.getMedecin(id); }
    public List<Medecin> getAllMedecins() { return medecinService.getAllMedecins(); }
    public void createOrUpdateMedecin(Medecin medecin) { medecinService.createOrUpdateMedecin(medecin); }
    public void deleteMedecin(Long id) { medecinService.deleteMedecin(id); }
}
