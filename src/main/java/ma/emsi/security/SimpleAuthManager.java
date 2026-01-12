package ma.emsi.security;

import java.util.HashMap;
import java.util.Map;

public class SimpleAuthManager {
    private final Map<String, String> users = new HashMap<>(); // username -> password
    private final Map<String, String> roles = new HashMap<>(); // username -> role

    public SimpleAuthManager() {
        users.put("admin", "admin123");
        roles.put("admin", "ADMIN");
        users.put("user", "user123");
        roles.put("user", "USER");
    }

    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public String getRole(String username) {
        return roles.get(username);
    }
}
