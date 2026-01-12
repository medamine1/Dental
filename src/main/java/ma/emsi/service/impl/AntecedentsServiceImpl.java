package ma.emsi.service.impl;

import ma.emsi.service.api.AntecedentsService;
import ma.emsi.repository.api.AntecedentsRepository;
import ma.emsi.entities.Antecedents;
import java.util.List;

public class AntecedentsServiceImpl implements AntecedentsService {
    private final AntecedentsRepository antecedentsRepository;
    public AntecedentsServiceImpl(AntecedentsRepository antecedentsRepository) {
        this.antecedentsRepository = antecedentsRepository;
    }
    @Override
    public Antecedents getAntecedents(Long id) { return antecedentsRepository.findById(id); }
    @Override
    public List<Antecedents> getAllAntecedents() { return antecedentsRepository.findAll(); }
    @Override
    public void createOrUpdateAntecedents(Antecedents antecedents) { antecedentsRepository.save(antecedents); }
    @Override
    public void deleteAntecedents(Long id) { antecedentsRepository.delete(id); }
}
