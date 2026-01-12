package ma.emsi.service.api;

import ma.emsi.entities.Consultation;
import java.util.List;

public interface ConsultationService {
    Consultation getConsultation(Long id);
    List<Consultation> getAllConsultations();
    void createOrUpdateConsultation(Consultation consultation);
    void deleteConsultation(Long id);
}
