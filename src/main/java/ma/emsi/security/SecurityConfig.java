package ma.emsi.security;

public class SecurityConfig {
    // Placeholder for security configuration (e.g., role-based access)
    public static boolean hasAccess(String username, String requiredRole, SimpleAuthManager authManager) {
        String userRole = authManager.getRole(username);
        return userRole != null && userRole.equals(requiredRole);
    }
}
