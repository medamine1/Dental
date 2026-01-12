package ma.emsi.service.impl;

import ma.emsi.service.api.ConsultationService;
import ma.emsi.repository.api.ConsultationRepository;
import ma.emsi.entities.Consultation;
import java.util.List;

public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository consultationRepository;
    public ConsultationServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }
    @Override
    public Consultation getConsultation(Long id) { return consultationRepository.findById(id); }
    @Override
    public List<Consultation> getAllConsultations() { return consultationRepository.findAll(); }
    @Override
    public void createOrUpdateConsultation(Consultation consultation) { consultationRepository.save(consultation); }
    @Override
    public void deleteConsultation(Long id) { consultationRepository.delete(id); }
}
