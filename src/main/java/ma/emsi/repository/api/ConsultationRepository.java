package ma.emsi.repository.api;

import ma.emsi.entities.Consultation;
import java.util.List;

public interface ConsultationRepository {
    Consultation findById(Long id);
    List<Consultation> findAll();
    void save(Consultation consultation);
    void delete(Long id);
}
