package ma.dentalTech.entities.consultation;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Consultation {
    private Long id;
    private Long patientId;
    private Long medecinId;
    private LocalDateTime dateConsultation;
    private String motif;
    private String notes;
    private Double montant;
}
