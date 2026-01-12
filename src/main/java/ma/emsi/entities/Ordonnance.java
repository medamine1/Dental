package ma.emsi.entities;

import ma.emsi.common.BaseEntity;
import java.time.LocalDateTime;

public class Ordonnance extends BaseEntity {
    private Long patientId;
    private LocalDateTime date;
    private String details;
    private String patientName;
    private String medecinName;
    private java.time.LocalDateTime dateOrdonnance;

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getMedecinName() { return medecinName; }
    public void setMedecinName(String medecinName) { this.medecinName = medecinName; }
    public java.time.LocalDateTime getDateOrdonnance() { return dateOrdonnance; }
    public void setDateOrdonnance(java.time.LocalDateTime dateOrdonnance) { this.dateOrdonnance = dateOrdonnance; }
}
