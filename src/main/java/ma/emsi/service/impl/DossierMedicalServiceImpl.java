package ma.emsi.service.impl;

import ma.emsi.service.api.DossierMedicalService;
import ma.emsi.repository.api.DossierMedicalRepository;
import ma.emsi.entities.DossierMedical;
import java.util.List;

public class DossierMedicalServiceImpl implements DossierMedicalService {
    private final DossierMedicalRepository dossierMedicalRepository;
    public DossierMedicalServiceImpl(DossierMedicalRepository dossierMedicalRepository) {
        this.dossierMedicalRepository = dossierMedicalRepository;
    }
    @Override
    public DossierMedical getDossierMedical(Long id) { return dossierMedicalRepository.findById(id); }
    @Override
    public List<DossierMedical> getAllDossierMedicals() { return dossierMedicalRepository.findAll(); }
    @Override
    public void createOrUpdateDossierMedical(DossierMedical dossierMedical) { dossierMedicalRepository.save(dossierMedical); }
    @Override
    public void deleteDossierMedical(Long id) { dossierMedicalRepository.delete(id); }
}
