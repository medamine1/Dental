package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class Roles extends BaseEntity {
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
