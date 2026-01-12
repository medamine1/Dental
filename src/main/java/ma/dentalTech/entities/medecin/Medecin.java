package ma.dentalTech.entities.medecin;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Medecin {
    private Long id;
    private String nom;
    private String prenom;
    private String specialite;
    private String telephone;
    private String email;
    private LocalDateTime dateCreation;
}
