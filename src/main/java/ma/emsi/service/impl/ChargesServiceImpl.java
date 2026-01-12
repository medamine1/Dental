package ma.emsi.service.impl;

import ma.emsi.service.api.ChargesService;
import ma.emsi.repository.api.ChargesRepository;
import ma.emsi.entities.Charges;
import java.util.List;

public class ChargesServiceImpl implements ChargesService {
    private final ChargesRepository chargesRepository;
    public ChargesServiceImpl(ChargesRepository chargesRepository) {
        this.chargesRepository = chargesRepository;
    }
    @Override
    public Charges getCharges(Long id) { return chargesRepository.findById(id); }
    @Override
    public List<Charges> getAllCharges() { return chargesRepository.findAll(); }
    @Override
    public void createOrUpdateCharges(Charges charges) { chargesRepository.save(charges); }
    @Override
    public void deleteCharges(Long id) { chargesRepository.delete(id); }
}
