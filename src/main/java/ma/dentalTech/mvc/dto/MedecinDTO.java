package ma.dentalTech.mvc.dto;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class MedecinDTO {
    private String nomComplet;
    private String specialite;
    private String telephone;
    private String dateCreationFormatee;
}
