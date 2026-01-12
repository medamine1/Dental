package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class Medecin extends BaseEntity {
    private String nom;
    private String specialite;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
