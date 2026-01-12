package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class SituationFinanciere extends BaseEntity {
    private double solde;
    private String details;

    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
