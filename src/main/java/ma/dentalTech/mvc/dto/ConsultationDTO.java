package ma.dentalTech.mvc.dto;

import java.time.LocalDateTime;
import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ConsultationDTO {
    private String nomPatient;
    private String nomMedecin;
    private LocalDateTime dateConsultation;
    private String motif;
    private Double montant;
    private String dateFormatee;
}
