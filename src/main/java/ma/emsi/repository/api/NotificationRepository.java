package ma.emsi.repository.api;

import ma.emsi.entities.Notification;
import java.util.List;

public interface NotificationRepository {
    Notification findById(Long id);
    List<Notification> findAll();
    void save(Notification notification);
    void delete(Long id);
}
