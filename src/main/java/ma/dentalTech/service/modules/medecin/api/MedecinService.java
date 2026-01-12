package ma.dentalTech.service.modules.medecin.api;

import java.util.List;
import ma.dentalTech.mvc.dto.MedecinDTO;

public interface MedecinService {
    List<MedecinDTO> getAllMedecinsAsDTO();
}
