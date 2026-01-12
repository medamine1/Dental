package ma.emsi.service.api;

import ma.emsi.entities.Charges;
import java.util.List;

public interface ChargesService {
    Charges getCharges(Long id);
    List<Charges> getAllCharges();
    void createOrUpdateCharges(Charges charges);
    void deleteCharges(Long id);
}
