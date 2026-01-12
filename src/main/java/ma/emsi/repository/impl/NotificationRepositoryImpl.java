package ma.emsi.repository.impl;

import ma.emsi.repository.api.NotificationRepository;
import ma.emsi.entities.Notification;
import java.util.*;

public class NotificationRepositoryImpl implements NotificationRepository {
    private final Map<Long, Notification> db = new HashMap<>();
    @Override
    public Notification findById(Long id) { return db.get(id); }
    @Override
    public List<Notification> findAll() { return new ArrayList<>(db.values()); }
    @Override
    public void save(Notification notification) { db.put(notification.getId(), notification); }
    @Override
    public void delete(Long id) { db.remove(id); }
}
