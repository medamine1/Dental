package ma.emsi;

import ma.emsi.entities.Medecin;
import ma.emsi.repository.impl.MedecinRepositoryImpl;
import ma.emsi.service.impl.MedecinServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MedecinServiceTest {
    @Test
    public void testCreateAndGetMedecin() {
        var repo = new MedecinRepositoryImpl();
        var service = new MedecinServiceImpl(repo);
        Medecin m = new Medecin();
        m.setNom("Dr. House");
        m.setSpecialite("Diagnostic");
        service.createOrUpdateMedecin(m);
        assertNotNull(service.getAllMedecins());
        assertEquals("Dr. House", service.getAllMedecins().get(0).getNom());
    }
}
