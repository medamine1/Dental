package ma.emsi.mvc.controllers;

import ma.emsi.service.api.DossierMedicalService;
import ma.emsi.entities.DossierMedical;
import java.util.List;

public class DossierMedicalController {
    private final DossierMedicalService dossierMedicalService;
    public DossierMedicalController(DossierMedicalService dossierMedicalService) {
        this.dossierMedicalService = dossierMedicalService;
    }
    public DossierMedical getDossierMedical(Long id) { return dossierMedicalService.getDossierMedical(id); }
    public List<DossierMedical> getAllDossierMedicals() { return dossierMedicalService.getAllDossierMedicals(); }
    public void createOrUpdateDossierMedical(DossierMedical dossierMedical) { dossierMedicalService.createOrUpdateDossierMedical(dossierMedical); }
    public void deleteDossierMedical(Long id) { dossierMedicalService.deleteDossierMedical(id); }
}
