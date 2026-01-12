package ma.dentalTech.service.modules.medecin.baseImplementation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import ma.dentalTech.entities.medecin.Medecin;
import ma.dentalTech.mvc.dto.MedecinDTO;
import ma.dentalTech.service.modules.medecin.api.MedecinService;

public class MedecinServiceImpl implements MedecinService {

    public MedecinServiceImpl() {
        // Constructeur par défaut
    }
    
    public MedecinServiceImpl(Object dummy) {
        // Constructeur avec paramètre pour éviter les conflits
    }

    private static String formatDate(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public List<MedecinDTO> getAllMedecinsAsDTO() {
        // Données de démonstration
        List<Medecin> medecins = List.of(
            Medecin.builder()
                .id(1L)
                .nom("Dr. Alami")
                .prenom("Fatima")
                .specialite("Dentiste généraliste")
                .telephone("0611-111111")
                .email("alami@cabinet.ma")
                .dateCreation(LocalDateTime.now().minusDays(30))
                .build(),
            Medecin.builder()
                .id(2L)
                .nom("Dr. Rachidi")
                .prenom("Mohamed")
                .specialite("Orthodontiste")
                .telephone("0622-222222")
                .email("rachidi@cabinet.ma")
                .dateCreation(LocalDateTime.now().minusDays(60))
                .build(),
            Medecin.builder()
                .id(3L)
                .nom("Dr. Bennani")
                .prenom("Samira")
                .specialite("Chirurgien-dentiste")
                .telephone("0633-333333")
                .email("bennani@cabinet.ma")
                .dateCreation(LocalDateTime.now().minusDays(90))
                .build()
        );

        return medecins.stream()
                .map(m -> MedecinDTO.builder()
                        .nomComplet(m.getNom() + " " + m.getPrenom())
                        .specialite(m.getSpecialite())
                        .telephone(m.getTelephone())
                        .dateCreationFormatee(formatDate(m.getDateCreation()))
                        .build())
                .collect(Collectors.toList());
    }
}
