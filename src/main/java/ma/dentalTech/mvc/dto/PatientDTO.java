package ma.dentalTech.mvc.dto;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class PatientDTO {
    private Long id;
    private String nomComplet;
    private int age;
    private String sexe;
    private String telephone;
    private String email;
    private String dateCreationFormatee;
    private String assurance;
}
