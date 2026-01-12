package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class Acte extends BaseEntity {
    private String libelle;
    private double prix;

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
}
