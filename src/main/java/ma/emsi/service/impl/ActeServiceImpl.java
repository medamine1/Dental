package ma.emsi.service.impl;

import ma.emsi.service.api.ActeService;
import ma.emsi.repository.api.ActeRepository;
import ma.emsi.entities.Acte;
import java.util.List;

public class ActeServiceImpl implements ActeService {
    private final ActeRepository acteRepository;
    public ActeServiceImpl(ActeRepository acteRepository) {
        this.acteRepository = acteRepository;
    }
    @Override
    public Acte getActe(Long id) { return acteRepository.findById(id); }
    @Override
    public List<Acte> getAllActes() { return acteRepository.findAll(); }
    @Override
    public void createOrUpdateActe(Acte acte) { acteRepository.save(acte); }
    @Override
    public void deleteActe(Long id) { acteRepository.delete(id); }
}
