package ma.emsi.repository.api;

import ma.emsi.entities.Antecedents;
import java.util.List;

public interface AntecedentsRepository {
    Antecedents findById(Long id);
    List<Antecedents> findAll();
    void save(Antecedents antecedents);
    void delete(Long id);
}
