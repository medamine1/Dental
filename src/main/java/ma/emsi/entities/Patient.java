package ma.emsi.entities;

import ma.emsi.common.BaseEntity;
import ma.emsi.enums.Sexe;

public class Patient extends BaseEntity {
    private String nom;
    private String prenom;
    private Sexe sexe;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }
    // TODO: Add more fields and relationships
}
