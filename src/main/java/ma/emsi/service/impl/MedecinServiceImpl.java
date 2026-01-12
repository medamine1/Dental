package ma.emsi.service.impl;

import ma.emsi.service.api.MedecinService;
import ma.emsi.repository.api.MedecinRepository;
import ma.emsi.entities.Medecin;
import java.util.List;

public class MedecinServiceImpl implements MedecinService {
    private final MedecinRepository medecinRepository;
    public MedecinServiceImpl(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }
    @Override
    public Medecin getMedecin(Long id) { return medecinRepository.findById(id); }
    @Override
    public List<Medecin> getAllMedecins() { return medecinRepository.findAll(); }
    @Override
    public void createOrUpdateMedecin(Medecin medecin) { medecinRepository.save(medecin); }
    @Override
    public void deleteMedecin(Long id) { medecinRepository.delete(id); }
}
