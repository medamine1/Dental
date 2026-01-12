package ma.emsi.service.impl;

import ma.emsi.service.api.AdminService;
import ma.emsi.repository.api.AdminRepository;
import ma.emsi.entities.Admin;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    @Override
    public Admin getAdmin(Long id) { return adminRepository.findById(id); }
    @Override
    public List<Admin> getAllAdmins() { return adminRepository.findAll(); }
    @Override
    public void createOrUpdateAdmin(Admin admin) { adminRepository.save(admin); }
    @Override
    public void deleteAdmin(Long id) { adminRepository.delete(id); }
}
