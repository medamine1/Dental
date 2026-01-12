package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class Charges extends BaseEntity {
    private String description;
    private double montant;

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
}
