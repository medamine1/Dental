package ma.emsi.mvc.controllers;

import ma.emsi.service.api.ActeService;
import ma.emsi.entities.Acte;
import java.util.List;

public class ActeController {
    private final ActeService acteService;
    public ActeController(ActeService acteService) {
        this.acteService = acteService;
    }
    public Acte getActe(Long id) { return acteService.getActe(id); }
    public List<Acte> getAllActes() { return acteService.getAllActes(); }
    public void createOrUpdateActe(Acte acte) { acteService.createOrUpdateActe(acte); }
    public void deleteActe(Long id) { acteService.deleteActe(id); }
}
