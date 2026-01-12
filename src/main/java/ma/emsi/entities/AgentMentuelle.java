package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class AgentMentuelle extends BaseEntity {
    private String nom;
    private String prenom;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
}
