package ma.emsi.entities;

import ma.emsi.common.BaseEntity;
import java.time.LocalDateTime;

public class Consultation extends BaseEntity {
    private LocalDateTime date;
    private String motif;

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }
}
