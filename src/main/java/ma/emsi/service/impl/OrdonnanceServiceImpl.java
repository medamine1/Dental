package ma.emsi.service.impl;

import ma.emsi.service.api.OrdonnanceService;
import ma.emsi.repository.api.OrdonnanceRepository;
import ma.emsi.entities.Ordonnance;
import java.util.List;

public class OrdonnanceServiceImpl implements OrdonnanceService {
    private final OrdonnanceRepository ordonnanceRepository;
    public OrdonnanceServiceImpl(OrdonnanceRepository ordonnanceRepository) {
        this.ordonnanceRepository = ordonnanceRepository;
    }
    @Override
    public Ordonnance getOrdonnance(Long id) { return ordonnanceRepository.findById(id); }
    @Override
    public List<Ordonnance> getAllOrdonnances() { return ordonnanceRepository.findAll(); }
    @Override
    public void createOrUpdateOrdonnance(Ordonnance ordonnance) { ordonnanceRepository.save(ordonnance); }
    @Override
    public void deleteOrdonnance(Long id) { ordonnanceRepository.delete(id); }
}
