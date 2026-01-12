package ma.emsi.mvc.controllers;

import ma.emsi.service.api.SecretaireService;
import ma.emsi.entities.Secretaire;
import java.util.List;

public class SecretaireController {
    private final SecretaireService secretaireService;
    public SecretaireController(SecretaireService secretaireService) {
        this.secretaireService = secretaireService;
    }
    public Secretaire getSecretaire(Long id) { return secretaireService.getSecretaire(id); }
    public List<Secretaire> getAllSecretaires() { return secretaireService.getAllSecretaires(); }
    public void createOrUpdateSecretaire(Secretaire secretaire) { secretaireService.createOrUpdateSecretaire(secretaire); }
    public void deleteSecretaire(Long id) { secretaireService.deleteSecretaire(id); }
}
