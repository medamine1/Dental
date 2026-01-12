package ma.emsi.mvc.controllers;

import ma.emsi.service.api.AdminService;
import ma.emsi.entities.Admin;
import java.util.List;

public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    public Admin getAdmin(Long id) { return adminService.getAdmin(id); }
    public List<Admin> getAllAdmins() { return adminService.getAllAdmins(); }
    public void createOrUpdateAdmin(Admin admin) { adminService.createOrUpdateAdmin(admin); }
    public void deleteAdmin(Long id) { adminService.deleteAdmin(id); }
}
