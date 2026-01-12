package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class Certificat extends BaseEntity {
    private String type;
    private String details;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
