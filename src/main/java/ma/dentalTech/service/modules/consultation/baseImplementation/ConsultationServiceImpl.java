package ma.dentalTech.service.modules.consultation.baseImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import ma.dentalTech.entities.consultation.Consultation;
import ma.dentalTech.mvc.dto.ConsultationDTO;
import ma.dentalTech.service.modules.consultation.api.ConsultationService;

public class ConsultationServiceImpl implements ConsultationService {

    public ConsultationServiceImpl() {
        // Constructeur par défaut
    }
    
    public ConsultationServiceImpl(Object dummy) {
        // Constructeur avec paramètre pour éviter les conflits
    }

    private static String formatDate(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public List<ConsultationDTO> getTodayConsultationsAsDTO() {
        LocalDate today = LocalDate.now();
        
        // Données de démonstration pour aujourd'hui
        List<Consultation> consultations = List.of(
            Consultation.builder()
                .id(1L)
                .patientId(1L)
                .medecinId(1L)
                .dateConsultation(LocalDateTime.of(today, java.time.LocalTime.of(10, 30)))
                .motif("Contrôle routine")
                .notes("Patient satisfait, aucune anomalie détectée")
                .montant(250.0)
                .build(),
            Consultation.builder()
                .id(2L)
                .patientId(2L)
                .medecinId(2L)
                .dateConsultation(LocalDateTime.of(today, java.time.LocalTime.of(14, 0)))
                .motif("Dévissage")
                .notes("Dévissage des bagues, patient coopératif")
                .montant(150.0)
                .build(),
            Consultation.builder()
                .id(3L)
                .patientId(3L)
                .medecinId(3L)
                .dateConsultation(LocalDateTime.of(today, java.time.LocalTime.of(16, 30)))
                .motif("Extraction dentaire")
                .notes("Extraction dent 48, prescription antibiotique")
                .montant(400.0)
                .build()
        );

        return consultations.stream()
                .map(c -> ConsultationDTO.builder()
                        .nomPatient(getPatientName(c.getPatientId()))
                        .nomMedecin(getMedecinName(c.getMedecinId()))
                        .dateConsultation(c.getDateConsultation())
                        .motif(c.getMotif())
                        .montant(c.getMontant())
                        .dateFormatee(formatDate(c.getDateConsultation()))
                        .build())
                .collect(Collectors.toList());
    }
    
    private String getPatientName(Long patientId) {
        switch (patientId.intValue()) {
            case 1: return "Amal Z.";
            case 2: return "Omar B.";
            case 3: return "Nour C.";
            default: return "Patient " + patientId;
        }
    }
    
    private String getMedecinName(Long medecinId) {
        switch (medecinId.intValue()) {
            case 1: return "Dr. Alami";
            case 2: return "Dr. Rachidi";
            case 3: return "Dr. Bennani";
            default: return "Dr. " + medecinId;
        }
    }
}
