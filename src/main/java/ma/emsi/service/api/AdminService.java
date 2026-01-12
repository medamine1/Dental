package ma.emsi.service.api;

import ma.emsi.entities.Admin;
import java.util.List;

public interface AdminService {
    Admin getAdmin(Long id);
    List<Admin> getAllAdmins();
    void createOrUpdateAdmin(Admin admin);
    void deleteAdmin(Long id);
}
