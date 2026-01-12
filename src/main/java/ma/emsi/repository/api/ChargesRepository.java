package ma.emsi.repository.api;

import ma.emsi.entities.Charges;
import java.util.List;

public interface ChargesRepository {
    Charges findById(Long id);
    List<Charges> findAll();
    void save(Charges charges);
    void delete(Long id);
}
