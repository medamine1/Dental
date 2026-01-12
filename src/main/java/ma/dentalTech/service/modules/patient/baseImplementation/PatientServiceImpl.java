package ma.dentalTech.service.modules.patient.baseImplementation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.dentalTech.entities.patient.Patient;
import ma.dentalTech.mvc.dto.PatientDTO;
import ma.dentalTech.repository.modules.patient.api.PatientRepository;
import ma.dentalTech.service.modules.patient.api.PatientService;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientServiceImpl implements PatientService {

    private PatientRepository repository;


    /**
     * Formattage de date
     * @param dt : date Non Formatée
     * @return  date formatée
     */
    private static String formatDate(java.time.LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Calculer l'âge du patient à partir de sa date de naissance
     * @param birthDate
     * @return age
     */
    private static int computeAge(java.time.LocalDate birthDate) {
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public List<PatientDTO> getTodayPatientsAsDTO() {
        LocalDate today = LocalDate.now();
        return repository.findAll().stream()

                .filter(p -> p.getDateCreation() != null && p.getDateCreation().toLocalDate().equals(today))
                .sorted(Comparator.comparing(Patient::getDateCreation).reversed())
                .map(p -> PatientDTO.builder()
                        .id(p.getId())
                        .nomComplet((p.getNom() == null ? "" : p.getNom().trim()) + " " + (p.getPrenom() == null ? "" : p.getPrenom().trim()))
                        .age(computeAge(p.getDateNaissance()))
                        .sexe(p.getSexe() != null ? p.getSexe().toString() : "N/A")
                        .telephone(p.getTelephone() != null ? p.getTelephone() : "N/A")
                        .email(p.getEmail() != null ? p.getEmail() : "N/A")
                        .dateCreationFormatee(formatDate(p.getDateCreation()))
                        .assurance(p.getAssurance() != null ? p.getAssurance().toString() : "Aucune")
                        .build())
                .collect(Collectors.toList());
    }
    
    @Override
    public List<PatientDTO> getAllPatientsAsDTO() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Patient::getDateCreation).reversed())
                .map(p -> PatientDTO.builder()
                        .id(p.getId())
                        .nomComplet((p.getNom() == null ? "" : p.getNom().trim()) + " " + (p.getPrenom() == null ? "" : p.getPrenom().trim()))
                        .age(computeAge(p.getDateNaissance()))
                        .sexe(p.getSexe() != null ? p.getSexe().toString() : "N/A")
                        .telephone(p.getTelephone() != null ? p.getTelephone() : "N/A")
                        .email(p.getEmail() != null ? p.getEmail() : "N/A")
                        .dateCreationFormatee(formatDate(p.getDateCreation()))
                        .assurance(p.getAssurance() != null ? p.getAssurance().toString() : "Aucune")
                        .build())
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean savePatient(PatientDTO patientDTO) {
        try {
            // Créer une entité Patient à partir du DTO
            Patient patient = new Patient();
            
            // Séparer nom et prénom
            String[] nameParts = patientDTO.getNomComplet().split(" ", 2);
            patient.setNom(nameParts[0]);
            patient.setPrenom(nameParts.length > 1 ? nameParts[1] : "");
            
            // Convertir le sexe
            if (patientDTO.getSexe() != null) {
                try {
                    patient.setSexe(ma.dentalTech.entities.enums.Sexe.valueOf(patientDTO.getSexe()));
                } catch (IllegalArgumentException e) {
                    patient.setSexe(ma.dentalTech.entities.enums.Sexe.Homme); // Valeur par défaut
                }
            }
            
            // Convertir l'assurance
            if (patientDTO.getAssurance() != null) {
                try {
                    patient.setAssurance(ma.dentalTech.entities.enums.Assurance.valueOf(patientDTO.getAssurance()));
                } catch (IllegalArgumentException e) {
                    patient.setAssurance(ma.dentalTech.entities.enums.Assurance.Aucune); // Valeur par défaut
                }
            }
            
            patient.setTelephone(patientDTO.getTelephone());
            patient.setEmail(patientDTO.getEmail());
            patient.setDateCreation(java.time.LocalDateTime.now());
            // Date de naissance serait calculée à partir de l'âge ou demandée séparément
            patient.setDateNaissance(java.time.LocalDate.now().minusYears(patientDTO.getAge()));
            
            // Sauvegarder dans le repository
            repository.create(patient);
            
            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de la sauvegarde du patient: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean updatePatient(PatientDTO patientDTO) {
        try {
            // Récupérer le patient existant
            Patient existingPatient = repository.findById(patientDTO.getId());
            if (existingPatient == null) {
                return false;
            }
            
            // Mettre à jour les champs
            String[] nameParts = patientDTO.getNomComplet().split(" ", 2);
            existingPatient.setNom(nameParts[0]);
            existingPatient.setPrenom(nameParts.length > 1 ? nameParts[1] : "");
            
            if (patientDTO.getSexe() != null) {
                try {
                    existingPatient.setSexe(ma.dentalTech.entities.enums.Sexe.valueOf(patientDTO.getSexe()));
                } catch (IllegalArgumentException e) {
                    // Garder l'ancienne valeur si invalide
                }
            }
            
            if (patientDTO.getAssurance() != null) {
                try {
                    existingPatient.setAssurance(ma.dentalTech.entities.enums.Assurance.valueOf(patientDTO.getAssurance()));
                } catch (IllegalArgumentException e) {
                    // Garder l'ancienne valeur si invalide
                }
            }
            
            existingPatient.setTelephone(patientDTO.getTelephone());
            existingPatient.setEmail(patientDTO.getEmail());
            
            // Mettre à jour dans le repository
            repository.update(existingPatient);
            
            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour du patient: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean deletePatient(Long patientId) {
        try {
            Patient patient = repository.findById(patientId);
            if (patient == null) {
                return false;
            }
            
            repository.deleteById(patientId);
            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression du patient: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
