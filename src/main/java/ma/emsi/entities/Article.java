package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class Article extends BaseEntity {
    private String nom;
    private double prix;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
}
