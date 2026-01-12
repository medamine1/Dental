package ma.emsi.entities;

import ma.emsi.common.BaseEntity;

public class DossierMedical extends BaseEntity {
    private Long patientId;
    private String notes;

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
