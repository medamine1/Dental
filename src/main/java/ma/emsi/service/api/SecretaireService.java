package ma.emsi.service.api;

import ma.emsi.entities.Secretaire;
import java.util.List;

public interface SecretaireService {
    Secretaire getSecretaire(Long id);
    List<Secretaire> getAllSecretaires();
    void createOrUpdateSecretaire(Secretaire secretaire);
    void deleteSecretaire(Long id);
}
