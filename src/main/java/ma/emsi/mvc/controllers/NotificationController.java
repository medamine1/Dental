package ma.emsi.mvc.controllers;

import ma.emsi.service.api.NotificationService;
import ma.emsi.entities.Notification;
import java.util.List;

public class NotificationController {
    private final NotificationService notificationService;
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    public Notification getNotification(Long id) { return notificationService.getNotification(id); }
    public List<Notification> getAllNotifications() { return notificationService.getAllNotifications(); }
    public void createOrUpdateNotification(Notification notification) { notificationService.createOrUpdateNotification(notification); }
    public void deleteNotification(Long id) { notificationService.deleteNotification(id); }
}
