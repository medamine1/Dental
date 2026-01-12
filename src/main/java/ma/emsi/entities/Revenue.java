package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class Revenue extends BaseEntity {
    private double montant;
    private String source;

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
}
