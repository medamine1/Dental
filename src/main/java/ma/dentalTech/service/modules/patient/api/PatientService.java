package ma.dentalTech.service.modules.patient.api;


import java.util.List;
import ma.dentalTech.mvc.dto.PatientDTO;

public interface PatientService {

    /**
     * Récupère les patients ajoutés aujourd'hui,
     * triés par ordre de création (plus récent -> plus ancien),
     * et les expose sous forme de PatientDTO (nom complet, âge, date formatée).
     */
    List<PatientDTO> getTodayPatientsAsDTO();
    
    /**
     * Récupère tous les patients de la base de données
     * @return liste de tous les patients sous forme de DTO
     */
    List<PatientDTO> getAllPatientsAsDTO();
    
    /**
     * Sauvegarde un nouveau patient dans la base de données
     * @param patientDTO les informations du patient à sauvegarder
     * @return true si la sauvegarde a réussi, false sinon
     */
    boolean savePatient(PatientDTO patientDTO);
    
    /**
     * Met à jour les informations d'un patient existant
     * @param patientDTO les informations mises à jour du patient
     * @return true si la mise à jour a réussi, false sinon
     */
    boolean updatePatient(PatientDTO patientDTO);
    
    /**
     * Supprime un patient de la base de données
     * @param patientId l'identifiant du patient à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    boolean deletePatient(Long patientId);
}
