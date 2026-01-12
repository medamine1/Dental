package ma.emsi.entities;

import ma.emsi.common.BaseEntity;
import java.time.LocalDateTime;

public class Ordonnance extends BaseEntity {
    private Long patientId;
    private LocalDateTime date;
    private String details;

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
