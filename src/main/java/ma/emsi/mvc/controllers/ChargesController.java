package ma.emsi.mvc.controllers;

import ma.emsi.service.api.ChargesService;
import ma.emsi.entities.Charges;
import java.util.List;

public class ChargesController {
    private final ChargesService chargesService;
    public ChargesController(ChargesService chargesService) {
        this.chargesService = chargesService;
    }
    public Charges getCharges(Long id) { return chargesService.getCharges(id); }
    public List<Charges> getAllCharges() { return chargesService.getAllCharges(); }
    public void createOrUpdateCharges(Charges charges) { chargesService.createOrUpdateCharges(charges); }
    public void deleteCharges(Long id) { chargesService.deleteCharges(id); }
}
