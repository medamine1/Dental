package ma.emsi.service.impl;

import ma.emsi.service.api.NotificationService;
import ma.emsi.repository.api.NotificationRepository;
import ma.emsi.entities.Notification;
import java.util.List;

public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    @Override
    public Notification getNotification(Long id) { return notificationRepository.findById(id); }
    @Override
    public List<Notification> getAllNotifications() { return notificationRepository.findAll(); }
    @Override
    public void createOrUpdateNotification(Notification notification) { notificationRepository.save(notification); }
    @Override
    public void deleteNotification(Long id) { notificationRepository.delete(id); }
}
