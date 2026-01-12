package ma.emsi.service.api;

import ma.emsi.entities.Antecedents;
import java.util.List;

public interface AntecedentsService {
    Antecedents getAntecedents(Long id);
    List<Antecedents> getAllAntecedents();
    void createOrUpdateAntecedents(Antecedents antecedents);
    void deleteAntecedents(Long id);
}
