package ma.emsi.service.api;

import ma.emsi.entities.Notification;
import java.util.List;

public interface NotificationService {
    Notification getNotification(Long id);
    List<Notification> getAllNotifications();
    void createOrUpdateNotification(Notification notification);
    void deleteNotification(Long id);
}
