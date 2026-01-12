package ma.emsi.repository.api;

import ma.emsi.entities.Admin;
import java.util.List;

public interface AdminRepository {
    Admin findById(Long id);
    List<Admin> findAll();
    void save(Admin admin);
    void delete(Long id);
}
