package ma.dentalTech.mvc.controllers.modules.consultation.swing_implementation;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.dentalTech.mvc.controllers.modules.consultation.api.ConsultationController;
import ma.dentalTech.mvc.dto.ConsultationDTO;
import ma.dentalTech.mvc.ui.modules.consultation.ConsultationView;
import ma.dentalTech.service.modules.consultation.api.ConsultationService;

@Data @AllArgsConstructor @NoArgsConstructor
public class ConsultationControllerImpl implements ConsultationController {

    private ConsultationService service;

    @Override
    public void showTodayConsultations() {
        List<ConsultationDTO> dtos = service.getTodayConsultationsAsDTO();
        ConsultationView.showAsync(dtos);
    }
}
