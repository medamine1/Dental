package ma.emsi.service.impl;

import ma.emsi.service.api.FactureService;
import ma.emsi.repository.api.FactureRepository;
import ma.emsi.entities.Facture;
import java.util.List;

public class FactureServiceImpl implements FactureService {
    private final FactureRepository factureRepository;
    public FactureServiceImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }
    @Override
    public Facture getFacture(Long id) { return factureRepository.findById(id); }
    @Override
    public List<Facture> getAllFactures() { return factureRepository.findAll(); }
    @Override
    public void createOrUpdateFacture(Facture facture) { factureRepository.save(facture); }
    @Override
    public void deleteFacture(Long id) { factureRepository.delete(id); }
}
