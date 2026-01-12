package ma.emsi.service.impl;

import ma.emsi.service.api.SecretaireService;
import ma.emsi.repository.api.SecretaireRepository;
import ma.emsi.entities.Secretaire;
import java.util.List;

public class SecretaireServiceImpl implements SecretaireService {
    private final SecretaireRepository secretaireRepository;
    public SecretaireServiceImpl(SecretaireRepository secretaireRepository) {
        this.secretaireRepository = secretaireRepository;
    }
    @Override
    public Secretaire getSecretaire(Long id) { return secretaireRepository.findById(id); }
    @Override
    public List<Secretaire> getAllSecretaires() { return secretaireRepository.findAll(); }
    @Override
    public void createOrUpdateSecretaire(Secretaire secretaire) { secretaireRepository.save(secretaire); }
    @Override
    public void deleteSecretaire(Long id) { secretaireRepository.delete(id); }
}
