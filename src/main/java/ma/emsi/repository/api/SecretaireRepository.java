package ma.emsi.repository.api;

import ma.emsi.entities.Secretaire;
import java.util.List;

public interface SecretaireRepository {
    Secretaire findById(Long id);
    List<Secretaire> findAll();
    void save(Secretaire secretaire);
    void delete(Long id);
}
