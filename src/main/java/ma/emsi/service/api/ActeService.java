package ma.emsi.service.api;

import ma.emsi.entities.Acte;
import java.util.List;

public interface ActeService {
    Acte getActe(Long id);
    List<Acte> getAllActes();
    void createOrUpdateActe(Acte acte);
    void deleteActe(Long id);
}
