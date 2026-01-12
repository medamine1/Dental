package ma.emsi.service.impl;

import ma.emsi.service.api.InterventionMedecinService;
import ma.emsi.repository.api.InterventionMedecinRepository;
import ma.emsi.entities.InterventionMedecin;
import java.util.List;

public class InterventionMedecinServiceImpl implements InterventionMedecinService {
    private final InterventionMedecinRepository interventionMedecinRepository;
    public InterventionMedecinServiceImpl(InterventionMedecinRepository interventionMedecinRepository) {
        this.interventionMedecinRepository = interventionMedecinRepository;
    }
    @Override
    public InterventionMedecin getInterventionMedecin(Long id) { return interventionMedecinRepository.findById(id); }
    @Override
    public List<InterventionMedecin> getAllInterventionMedecins() { return interventionMedecinRepository.findAll(); }
    @Override
    public void createOrUpdateInterventionMedecin(InterventionMedecin interventionMedecin) { interventionMedecinRepository.save(interventionMedecin); }
    @Override
    public void deleteInterventionMedecin(Long id) { interventionMedecinRepository.delete(id); }
}
