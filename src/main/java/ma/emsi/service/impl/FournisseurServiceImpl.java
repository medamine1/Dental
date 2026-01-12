package ma.emsi.service.impl;

import ma.emsi.service.api.FournisseurService;
import ma.emsi.repository.api.FournisseurRepository;
import ma.emsi.entities.Fournisseur;
import java.util.List;

public class FournisseurServiceImpl implements FournisseurService {
    private final FournisseurRepository fournisseurRepository;
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }
    @Override
    public Fournisseur getFournisseur(Long id) { return fournisseurRepository.findById(id); }
    @Override
    public List<Fournisseur> getAllFournisseurs() { return fournisseurRepository.findAll(); }
    @Override
    public void createOrUpdateFournisseur(Fournisseur fournisseur) { fournisseurRepository.save(fournisseur); }
    @Override
    public void deleteFournisseur(Long id) { fournisseurRepository.delete(id); }
}
