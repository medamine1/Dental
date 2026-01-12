package ma.emsi.service.impl;

import ma.emsi.service.api.MedicamentsService;
import ma.emsi.repository.api.MedicamentsRepository;
import ma.emsi.entities.Medicaments;
import java.util.List;

public class MedicamentsServiceImpl implements MedicamentsService {
    private final MedicamentsRepository medicamentsRepository;
    public MedicamentsServiceImpl(MedicamentsRepository medicamentsRepository) {
        this.medicamentsRepository = medicamentsRepository;
    }
    @Override
    public Medicaments getMedicaments(Long id) { return medicamentsRepository.findById(id); }
    @Override
    public List<Medicaments> getAllMedicaments() { return medicamentsRepository.findAll(); }
    @Override
    public void createOrUpdateMedicaments(Medicaments medicaments) { medicamentsRepository.save(medicaments); }
    @Override
    public void deleteMedicaments(Long id) { medicamentsRepository.delete(id); }
}
