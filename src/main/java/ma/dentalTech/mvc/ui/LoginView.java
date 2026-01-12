package ma.dentalTech.mvc.ui;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    public LoginView(Runnable onLoginSuccess) {
        setTitle("Cabinet Dentaire - Connexion");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.add(new JLabel("Nom d'utilisateur:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Mot de passe:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        loginButton = new JButton("Se connecter");
        formPanel.add(loginButton);
        statusLabel = new JLabel("");
        formPanel.add(statusLabel);

        add(formPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            // TODO: Replace with real authentication
            if ("admin".equals(user) && "admin".equals(pass)) {
                statusLabel.setText("");
                setVisible(false);
                onLoginSuccess.run();
            } else {
                statusLabel.setText("Identifiants invalides");
            }
        });
    }

    public static void showLogin(Runnable onLoginSuccess) {
        SwingUtilities.invokeLater(() -> new LoginView(onLoginSuccess).setVisible(true));
    }
}
