package ma.emsi.mvc.controllers;

import ma.emsi.service.api.ConsultationService;
import ma.emsi.entities.Consultation;
import java.util.List;

public class ConsultationController {
    private final ConsultationService consultationService;
    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }
    public Consultation getConsultation(Long id) { return consultationService.getConsultation(id); }
    public List<Consultation> getAllConsultations() { return consultationService.getAllConsultations(); }
    public void createOrUpdateConsultation(Consultation consultation) { consultationService.createOrUpdateConsultation(consultation); }
    public void deleteConsultation(Long id) { consultationService.deleteConsultation(id); }
}
