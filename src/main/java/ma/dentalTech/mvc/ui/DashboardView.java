package ma.dentalTech.mvc.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ma.dentalTech.mvc.controllers.modules.patient.api.PatientController;
import ma.dentalTech.mvc.controllers.modules.medecin.api.MedecinController;
import ma.dentalTech.mvc.controllers.modules.consultation.api.ConsultationController;
import ma.dentalTech.conf.ApplicationContext;

public class DashboardView extends JFrame {
    
    // Couleurs professionnelles
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color SUCCESS_COLOR = new Color(39, 174, 96);
    private static final Color WARNING_COLOR = new Color(243, 156, 18);
    private static final Color DANGER_COLOR = new Color(231, 76, 60);
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);
    private static final Color DARK_GRAY = new Color(52, 73, 94);
    private static final Color WHITE = Color.WHITE;
    
    // Composants principaux
    private JPanel headerPanel;
    private JPanel contentPanel;
    private JPanel sidebarPanel;
    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JButton patientsBtn;
    private JButton medecinsBtn;
    private JButton consultationsBtn;
    private JButton statsBtn;
    private JButton quitBtn;
    
    // Panneau de statistiques
    private JPanel statsPanel;
    private JLabel totalPatientsLabel;
    private JLabel totalMedecinsLabel;
    private JLabel todayConsultationsLabel;
    
    public DashboardView() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        loadStatistics();
        
        setTitle("DentalTech - Cabinet Dentaire Professionnel");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 600));
    }
    
    private void initializeComponents() {
        // Header
        titleLabel = new JLabel("DentalTech");
        subtitleLabel = new JLabel("Système de Gestion de Cabinet Dentaire");
        
        // Boutons de navigation
        patientsBtn = createModernButton("📋 Patients", PRIMARY_COLOR);
        medecinsBtn = createModernButton("👨‍⚕️ Médecins", SUCCESS_COLOR);
        consultationsBtn = createModernButton("📅 Consultations", WARNING_COLOR);
        statsBtn = createModernButton("📊 Statistiques", SECONDARY_COLOR);
        quitBtn = createModernButton("🚪 Quitter", DANGER_COLOR);
        
        // Panneaux de statistiques
        totalPatientsLabel = createStatsLabel("0", "Patients");
        totalMedecinsLabel = createStatsLabel("0", "Médecins");
        todayConsultationsLabel = createStatsLabel("0", "Consultations aujourd'hui");
    }
    
    private JButton createModernButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 50));
        
        // Effet hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    private JLabel createStatsLabel(String value, String description) {
        JLabel label = new JLabel();
        label.setText("<html><center><font size='6' color='" + 
                     Integer.toHexString(PRIMARY_COLOR.getRGB()).substring(2) + 
                     "'><b>" + value + "</b></font><br><font size='3' color='gray'>" + 
                     description + "</font></center></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        label.setOpaque(true);
        label.setBackground(WHITE);
        return label;
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout(0, 0));
        
        // Header Panel
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(0, 100));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(WHITE);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));
        
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);
        
        // Sidebar Panel
        sidebarPanel = new JPanel(new GridBagLayout());
        sidebarPanel.setBackground(DARK_GRAY);
        sidebarPanel.setPreferredSize(new Dimension(250, 0));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        
        sidebarPanel.add(patientsBtn, gbc);
        sidebarPanel.add(medecinsBtn, gbc);
        sidebarPanel.add(consultationsBtn, gbc);
        sidebarPanel.add(statsBtn, gbc);
        
        // Espace flexible
        gbc.weighty = 1.0;
        sidebarPanel.add(Box.createVerticalGlue(), gbc);
        gbc.weighty = 0.0;
        
        sidebarPanel.add(quitBtn, gbc);
        
        // Content Panel
        contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setBackground(LIGHT_GRAY);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Stats Panel
        statsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        statsPanel.setBackground(LIGHT_GRAY);
        statsPanel.add(totalPatientsLabel);
        statsPanel.add(totalMedecinsLabel);
        statsPanel.add(todayConsultationsLabel);
        
        contentPanel.add(statsPanel, BorderLayout.NORTH);
        
        // Welcome Panel
        JPanel welcomePanel = createWelcomePanel();
        contentPanel.add(welcomePanel, BorderLayout.CENTER);
        
        // Assemblage principal
        add(headerPanel, BorderLayout.NORTH);
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }
    
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY),
            BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        
        JLabel welcomeLabel = new JLabel("<html><center>" +
            "<font size='5' color='" + Integer.toHexString(DARK_GRAY.getRGB()).substring(2) + "'>" +
            "<b>Bienvenue dans DentalTech</b></font><br><br>" +
            "<font size='3' color='gray'>" +
            "Votre système de gestion de cabinet dentaire professionnel<br>" +
            "Utilisez le menu de gauche pour accéder aux différentes fonctionnalités" +
            "</font></center></html>");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel.add(welcomeLabel, BorderLayout.CENTER);
        return panel;
    }
    
    private void setupEventHandlers() {
        patientsBtn.addActionListener(e -> {
            try {
                PatientController pc = ApplicationContext.getBean(PatientController.class);
                if (pc != null) {
                    pc.showRecentPatients();
                } else {
                    showError("Erreur", "Le service patients n'est pas disponible");
                }
            } catch (Exception ex) {
                showError("Erreur", "Impossible d'accéder au module patients: " + ex.getMessage());
            }
        });
        
        medecinsBtn.addActionListener(e -> {
            try {
                MedecinController mc = ApplicationContext.getBean(MedecinController.class);
                if (mc != null) {
                    mc.showAllMedecins();
                } else {
                    showError("Erreur", "Le service médecins n'est pas disponible");
                }
            } catch (Exception ex) {
                showError("Erreur", "Impossible d'accéder au module médecins: " + ex.getMessage());
            }
        });
        
        consultationsBtn.addActionListener(e -> {
            try {
                ConsultationController cc = ApplicationContext.getBean(ConsultationController.class);
                if (cc != null) {
                    cc.showTodayConsultations();
                } else {
                    showError("Erreur", "Le service consultations n'est pas disponible");
                }
            } catch (Exception ex) {
                showError("Erreur", "Impossible d'accéder au module consultations: " + ex.getMessage());
            }
        });
        
        statsBtn.addActionListener(e -> showStatistics());
        quitBtn.addActionListener(e -> confirmExit());
    }
    
    private void loadStatistics() {
        try {
            // Charger les statistiques depuis les services
            PatientController pc = ApplicationContext.getBean(PatientController.class);
            MedecinController mc = ApplicationContext.getBean(MedecinController.class);
            ConsultationController cc = ApplicationContext.getBean(ConsultationController.class);
            
            // Pour l'instant, utiliser des valeurs de démonstration
            // Dans une version réelle, ces valeurs viendraient des services
            totalPatientsLabel.setText("<html><center><font size='6' color='" + 
                                      Integer.toHexString(PRIMARY_COLOR.getRGB()).substring(2) + 
                                      "'><b>4</b></font><br><font size='3' color='gray'>Patients</font></center></html>");
            totalMedecinsLabel.setText("<html><center><font size='6' color='" + 
                                      Integer.toHexString(SUCCESS_COLOR.getRGB()).substring(2) + 
                                      "'><b>3</b></font><br><font size='3' color='gray'>Médecins</font></center></html>");
            todayConsultationsLabel.setText("<html><center><font size='6' color='" + 
                                            Integer.toHexString(WARNING_COLOR.getRGB()).substring(2) + 
                                            "'><b>3</b></font><br><font size='3' color='gray'>Consultations aujourd'hui</font></center></html>");
        } catch (Exception ex) {
            System.err.println("Erreur lors du chargement des statistiques: " + ex.getMessage());
        }
    }
    
    private void showStatistics() {
        JPanel statsPanel = new JPanel(new GridLayout(4, 2, 20, 20));
        statsPanel.setBackground(WHITE);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        statsPanel.add(createDetailLabel("Total Patients", "4", PRIMARY_COLOR));
        statsPanel.add(createDetailLabel("Total Médecins", "3", SUCCESS_COLOR));
        statsPanel.add(createDetailLabel("Consultations aujourd'hui", "3", WARNING_COLOR));
        statsPanel.add(createDetailLabel("Consultations cette semaine", "15", SECONDARY_COLOR));
        statsPanel.add(createDetailLabel("Patients avec assurance CNSS", "2", PRIMARY_COLOR));
        statsPanel.add(createDetailLabel("Patients avec assurance CNOPS", "1", SUCCESS_COLOR));
        statsPanel.add(createDetailLabel("Revenus aujourd'hui", "800 DH", SUCCESS_COLOR));
        statsPanel.add(createDetailLabel("Revenus cette semaine", "3500 DH", SUCCESS_COLOR));
        
        JScrollPane scrollPane = new JScrollPane(statsPanel);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        
        JDialog statsDialog = new JDialog(this, "Statistiques du Cabinet", true);
        statsDialog.setContentPane(scrollPane);
        statsDialog.setSize(700, 500);
        statsDialog.setLocationRelativeTo(this);
        statsDialog.setVisible(true);
    }
    
    private JLabel createDetailLabel(String title, String value, Color color) {
        JLabel label = new JLabel("<html><center><font size='4' color='" + 
                                  Integer.toHexString(color.getRGB()).substring(2) + 
                                  "'><b>" + value + "</b></font><br><font size='2' color='gray'>" + 
                                  title + "</font></center></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY),
            BorderFactory.createEmptyBorder(15, 10, 15, 10)
        ));
        label.setOpaque(true);
        label.setBackground(WHITE);
        return label;
    }
    
    private void showError(String title, String message) {
        JOptionPane.showMessageDialog(this, 
            message, 
            title, 
            JOptionPane.ERROR_MESSAGE);
    }
    
    private void confirmExit() {
        int result = JOptionPane.showConfirmDialog(
            this,
            "Êtes-vous sûr de vouloir quitter l'application ?",
            "Confirmation de sortie",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void showDashboard() {
        SwingUtilities.invokeLater(() -> {
            try {
                DashboardView dashboard = new DashboardView();
                dashboard.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Erreur lors du démarrage de l'application: " + e.getMessage(), 
                    "Erreur critique", 
                    JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
}
