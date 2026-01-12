package ma.emsi;

import ma.emsi.entities.Patient;
import ma.emsi.repository.impl.PatientRepositoryImpl;
import ma.emsi.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientServiceTest {
    @Test
    public void testCreateAndGetPatient() {
        var repo = new PatientRepositoryImpl();
        var service = new PatientServiceImpl(repo);
        Patient p = new Patient();
        p.setNom("Test");
        p.setPrenom("User");
        service.createOrUpdatePatient(p);
        assertNotNull(service.getAllPatients());
    }
}
