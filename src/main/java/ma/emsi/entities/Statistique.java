package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class Statistique extends BaseEntity {
    private String type;
    private double valeur;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getValeur() { return valeur; }
    public void setValeur(double valeur) { this.valeur = valeur; }
}
