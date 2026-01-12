package ma.dentalTech.service.modules.consultation.api;

import java.util.List;
import ma.dentalTech.mvc.dto.ConsultationDTO;

public interface ConsultationService {
    List<ConsultationDTO> getTodayConsultationsAsDTO();
}
