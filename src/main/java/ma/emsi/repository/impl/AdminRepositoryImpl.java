package ma.emsi.repository.impl;

import ma.emsi.repository.api.AdminRepository;
import ma.emsi.entities.Admin;
import java.util.*;

public class AdminRepositoryImpl implements AdminRepository {
    private final Map<Long, Admin> db = new HashMap<>();
    @Override
    public Admin findById(Long id) { return db.get(id); }
    @Override
    public List<Admin> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Admin admin) { db.put(admin.getId(), admin); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
