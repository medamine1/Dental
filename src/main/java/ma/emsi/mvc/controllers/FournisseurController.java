package ma.emsi.mvc.controllers;

import ma.emsi.service.api.FournisseurService;
import ma.emsi.entities.Fournisseur;
import java.util.List;

public class FournisseurController {
    private final FournisseurService fournisseurService;
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }
    public Fournisseur getFournisseur(Long id) { return fournisseurService.getFournisseur(id); }
    public List<Fournisseur> getAllFournisseurs() { return fournisseurService.getAllFournisseurs(); }
    public void createOrUpdateFournisseur(Fournisseur fournisseur) { fournisseurService.createOrUpdateFournisseur(fournisseur); }
    public void deleteFournisseur(Long id) { fournisseurService.deleteFournisseur(id); }
}
