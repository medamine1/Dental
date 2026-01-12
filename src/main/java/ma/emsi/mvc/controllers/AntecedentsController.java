package ma.emsi.mvc.controllers;

import ma.emsi.service.api.AntecedentsService;
import ma.emsi.entities.Antecedents;
import java.util.List;

public class AntecedentsController {
    private final AntecedentsService antecedentsService;
    public AntecedentsController(AntecedentsService antecedentsService) {
        this.antecedentsService = antecedentsService;
    }
    public Antecedents getAntecedents(Long id) { return antecedentsService.getAntecedents(id); }
    public List<Antecedents> getAllAntecedents() { return antecedentsService.getAllAntecedents(); }
    public void createOrUpdateAntecedents(Antecedents antecedents) { antecedentsService.createOrUpdateAntecedents(antecedents); }
    public void deleteAntecedents(Long id) { antecedentsService.deleteAntecedents(id); }
}
