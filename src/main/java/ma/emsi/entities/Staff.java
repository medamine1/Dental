package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class Staff extends BaseEntity {
    private String nom;
    private String poste;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }
}
