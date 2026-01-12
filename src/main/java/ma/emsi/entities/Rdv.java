package ma.emsi.entities;

import ma.emsi.common.BaseEntity;
import java.time.LocalDateTime;

public class Rdv extends BaseEntity {
    private Long patientId;
    private LocalDateTime date;
    private String motif;

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }
}
