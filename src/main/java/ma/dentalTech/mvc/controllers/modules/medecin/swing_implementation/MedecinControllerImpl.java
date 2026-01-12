package ma.dentalTech.mvc.controllers.modules.medecin.swing_implementation;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.dentalTech.mvc.controllers.modules.medecin.api.MedecinController;
import ma.dentalTech.mvc.dto.MedecinDTO;
import ma.dentalTech.mvc.ui.modules.medecin.MedecinView;
import ma.dentalTech.service.modules.medecin.api.MedecinService;

@Data @AllArgsConstructor @NoArgsConstructor
public class MedecinControllerImpl implements MedecinController {

    private MedecinService service;

    @Override
    public void showAllMedecins() {
        List<MedecinDTO> dtos = service.getAllMedecinsAsDTO();
        MedecinView.showAsync(dtos);
    }
}
