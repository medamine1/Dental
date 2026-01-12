package ma.emsi.entities;

import ma.emsi.common.BaseEntity;
import java.time.LocalDateTime;

public class Notification extends BaseEntity {
    private String message;
    private LocalDateTime date;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
