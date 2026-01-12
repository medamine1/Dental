package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class Prescription extends BaseEntity {
    private Long ordonnanceId;
    private String medicament;
    private String posologie;

    public Long getOrdonnanceId() { return ordonnanceId; }
    public void setOrdonnanceId(Long ordonnanceId) { this.ordonnanceId = ordonnanceId; }
    public String getMedicament() { return medicament; }
    public void setMedicament(String medicament) { this.medicament = medicament; }
    public String getPosologie() { return posologie; }
    public void setPosologie(String posologie) { this.posologie = posologie; }
}
