package ma.dentalTech.mvc.ui.modules.patient;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import ma.dentalTech.mvc.dto.PatientDTO;
import ma.dentalTech.entities.enums.Sexe;
import ma.dentalTech.entities.enums.Assurance;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class PatientView extends JFrame {

    // Couleurs professionnelles
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SUCCESS_COLOR = new Color(39, 174, 96);
    private static final Color WARNING_COLOR = new Color(243, 156, 18);
    private static final Color DANGER_COLOR = new Color(231, 76, 60);
    private static final Color LIGHT_GRAY = new Color(245, 245, 245);
    private static final Color DARK_GRAY = new Color(52, 73, 94);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color WHITE = Color.WHITE;
    
    // Composants principaux
    private JPanel headerPanel;
    private JPanel contentPanel;
    private JPanel searchPanel;
    private JPanel statsPanel;
    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JTable patientTable;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private JButton searchBtn;
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton refreshBtn;
    private JButton backBtn;
    
    // Labels de statistiques
    private JLabel totalPatientsLabel;
    private JLabel malePatientsLabel;
    private JLabel femalePatientsLabel;
    private JLabel cnssPatientsLabel;

    public PatientView(List<PatientDTO> patients) {
        initializeComponents(patients);
        setupLayout();
        setupEventHandlers();
        loadStatistics(patients);
        
        setTitle("DentalTech - Gestion des Patients");
        setSize(1400, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 700));
        
        // Charger toutes les données de la base au démarrage
        SwingUtilities.invokeLater(this::refreshData);
    }

    private void initializeComponents(List<PatientDTO> patients) {
        // Header
        titleLabel = new JLabel("Gestion des Patients");
        subtitleLabel = new JLabel("Liste complète des patients du cabinet");
        
        // Table des patients
        createPatientTable(patients);
        
        // Panneau de recherche
        searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        searchBtn = createModernButton("🔍 Rechercher", PRIMARY_COLOR);
        addBtn = createModernButton("➕ Ajouter", SUCCESS_COLOR);
        editBtn = createModernButton("✏️ Modifier", WARNING_COLOR);
        deleteBtn = createModernButton("🗑️ Supprimer", DANGER_COLOR);
        refreshBtn = createModernButton("🔄 Actualiser", PRIMARY_COLOR);
        backBtn = createModernButton("⬅ Retour", DANGER_COLOR);
        
        // Statistiques
        totalPatientsLabel = createStatsLabel("0", "Total Patients", PRIMARY_COLOR);
        malePatientsLabel = createStatsLabel("0", "Hommes", SUCCESS_COLOR);
        femalePatientsLabel = createStatsLabel("0", "Femmes", WARNING_COLOR);
        cnssPatientsLabel = createStatsLabel("0", "CNSS", DANGER_COLOR);
    }
    
    private void createPatientTable(List<PatientDTO> patients) {
        String[] columns = {
            "ID", "Nom", "Prénom", "Âge", "Sexe", "Téléphone", 
            "Email", "Adresse", "Assurance", "Date de création"
        };
        
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };

        // Ajouter les données des patients
        for (PatientDTO patient : patients) {
            model.addRow(new Object[]{
                patient.getId(),
                patient.getNomComplet().split(" ")[0],
                patient.getNomComplet().split(" ").length > 1 ? patient.getNomComplet().split(" ")[1] : "",
                patient.getAge(),
                patient.getSexe(),
                patient.getTelephone(),
                patient.getEmail(),
                "N/A", // Adresse - serait ajoutée dans une version complète
                patient.getAssurance(),
                patient.getDateCreationFormatee()
            });
        }

        patientTable = new JTable(model);
        patientTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        patientTable.setRowHeight(35);
        patientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientTable.getTableHeader().setReorderingAllowed(false);
        
        // Personnaliser le rendu des cellules
        customizeTableRendering();
        
        scrollPane = new JScrollPane(patientTable);
        scrollPane.setBorder(new CompoundBorder(
            new LineBorder(LIGHT_GRAY),
            BorderFactory.createEmptyBorder(1, 1, 1, 1)
        ));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    private void customizeTableRendering() {
        // Header personnalisé
        JTableHeader header = patientTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(0, 40));
        header.setBackground(PRIMARY_COLOR);
        header.setForeground(WHITE);
        
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Rendu des cellules
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (isSelected) {
                    component.setBackground(new Color(41, 128, 185, 100));
                    component.setForeground(DARK_GRAY);
                } else {
                    component.setBackground(row % 2 == 0 ? WHITE : new Color(245, 245, 245));
                    component.setForeground(DARK_GRAY);
                }
                
                setHorizontalAlignment(SwingConstants.CENTER);
                setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                
                return component;
            }
        };
        
        for (int i = 0; i < patientTable.getColumnCount(); i++) {
            patientTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Coloration spécifique pour certaines colonnes
        patientTable.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if ("Homme".equals(value)) {
                    setForeground(SUCCESS_COLOR);
                } else if ("Femme".equals(value)) {
                    setForeground(new Color(231, 76, 60));
                }
                
                return component;
            }
        });
    }
    
    private JButton createModernButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 35));
        
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
    
    private JLabel createStatsLabel(String value, String description, Color color) {
        JLabel label = new JLabel();
        label.setText("<html><center><font size='5' color='" + 
                     Integer.toHexString(color.getRGB()).substring(2) + 
                     "'><b>" + value + "</b></font><br><font size='2' color='gray'>" + 
                     description + "</font></center></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setBorder(new CompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY),
            BorderFactory.createEmptyBorder(15, 10, 15, 10)
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
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        
        // Panel pour le titre et le bouton retour
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(backBtn);
        
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(WHITE);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));
        
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(rightPanel, BorderLayout.EAST);
        
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);
        
        // Search Panel
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        searchPanel.setBackground(WHITE);
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        searchPanel.add(new JLabel("Rechercher:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        searchPanel.add(Box.createHorizontalStrut(20));
        searchPanel.add(addBtn);
        searchPanel.add(editBtn);
        searchPanel.add(deleteBtn);
        searchPanel.add(Box.createHorizontalStrut(20));
        searchPanel.add(refreshBtn);
        
        // Stats Panel
        statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.setBackground(LIGHT_GRAY);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        statsPanel.add(totalPatientsLabel);
        statsPanel.add(malePatientsLabel);
        statsPanel.add(femalePatientsLabel);
        statsPanel.add(cnssPatientsLabel);
        
        // Content Panel
        contentPanel = new JPanel(new BorderLayout(0, 15));
        contentPanel.setBackground(LIGHT_GRAY);
        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Assemblage principal
        add(headerPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.SOUTH);
    }
    
    private void setupEventHandlers() {
        backBtn.addActionListener(e -> goBack());
        
        searchBtn.addActionListener(e -> performSearch());
        searchField.addActionListener(e -> performSearch());
        
        addBtn.addActionListener(e -> showAddPatientDialog());
        editBtn.addActionListener(e -> showEditPatientDialog());
        deleteBtn.addActionListener(e -> deleteSelectedPatient());
        refreshBtn.addActionListener(e -> refreshData());
        
        // Double-click sur une ligne pour éditer
        patientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    showEditPatientDialog();
                }
            }
        });
    }
    
    private void goBack() {
        this.dispose();
        // Ouvrir le dashboard principal
        SwingUtilities.invokeLater(() -> {
            try {
                ma.dentalTech.mvc.ui.DashboardView.showDashboard();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Erreur lors du retour au tableau de bord: " + e.getMessage(), 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private void loadStatistics(List<PatientDTO> patients) {
        int total = patients.size();
        int maleCount = 0;
        int femaleCount = 0;
        int cnssCount = 0;
        
        for (PatientDTO patient : patients) {
            if ("Homme".equals(patient.getSexe())) {
                maleCount++;
            } else if ("Femme".equals(patient.getSexe())) {
                femaleCount++;
            }
            
            if ("CNSS".equals(patient.getAssurance())) {
                cnssCount++;
            }
        }
        
        totalPatientsLabel.setText("<html><center><font size='5' color='" + 
                                  Integer.toHexString(PRIMARY_COLOR.getRGB()).substring(2) + 
                                  "'><b>" + total + "</b></font><br><font size='2' color='gray'>Total Patients</font></center></html>");
        malePatientsLabel.setText("<html><center><font size='5' color='" + 
                                  Integer.toHexString(SUCCESS_COLOR.getRGB()).substring(2) + 
                                  "'><b>" + maleCount + "</b></font><br><font size='2' color='gray'>Hommes</font></center></html>");
        femalePatientsLabel.setText("<html><center><font size='5' color='" + 
                                    Integer.toHexString(WARNING_COLOR.getRGB()).substring(2) + 
                                    "'><b>" + femaleCount + "</b></font><br><font size='2' color='gray'>Femmes</font></center></html>");
        cnssPatientsLabel.setText("<html><center><font size='5' color='" + 
                                  Integer.toHexString(DANGER_COLOR.getRGB()).substring(2) + 
                                  "'><b>" + cnssCount + "</b></font><br><font size='2' color='gray'>CNSS</font></center></html>");
    }
    
    private void performSearch() {
        String searchText = searchField.getText().trim().toLowerCase();
        
        // Obtenir toutes les données originales depuis le service
        try {
            var patientService = (ma.dentalTech.service.modules.patient.api.PatientService) 
                ma.dentalTech.conf.ApplicationContext.getBean("patientService");
            var allPatients = patientService.getAllPatientsAsDTO();
            
            DefaultTableModel model = (DefaultTableModel) patientTable.getModel();
            model.setRowCount(0); // Vider la table
            
            // Filtrer et ajouter les résultats
            for (PatientDTO patient : allPatients) {
                boolean match = searchText.isEmpty() || 
                    patient.getNomComplet().toLowerCase().contains(searchText) ||
                    patient.getTelephone().toLowerCase().contains(searchText) ||
                    patient.getEmail().toLowerCase().contains(searchText) ||
                    patient.getAssurance().toLowerCase().contains(searchText) ||
                    patient.getSexe().toLowerCase().contains(searchText);
                
                if (match) {
                    String[] nameParts = patient.getNomComplet().split(" ", 2);
                    String nom = nameParts[0];
                    String prenom = nameParts.length > 1 ? nameParts[1] : "";
                    
                    model.addRow(new Object[]{
                        patient.getId(),
                        nom,
                        prenom,
                        patient.getAge(),
                        patient.getSexe(),
                        patient.getTelephone(),
                        patient.getEmail(),
                        "N/A", // Adresse - serait ajoutée dans une version complète
                        patient.getAssurance(),
                        patient.getDateCreationFormatee()
                    });
                }
            }
            
            // Mettre à jour les statistiques
            loadStatistics(allPatients);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de la recherche: " + e.getMessage(), 
                "Erreur de recherche", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showAddPatientDialog() {
        JDialog dialog = new JDialog(this, "Ajouter un patient", true);
        dialog.setSize(500, 600);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Champs du formulaire
        JTextField nomField = new JTextField(20);
        JTextField prenomField = new JTextField(20);
        JTextField telephoneField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JComboBox<String> sexeCombo = new JComboBox<>(new String[]{"Homme", "Femme"});
        JComboBox<String> assuranceCombo = new JComboBox<>(new String[]{"CNSS", "CNOPS", "Autre", "Aucune"});
        JTextField adresseField = new JTextField(20);
        
        // Ajout des champs au panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1;
        panel.add(nomField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Prénom:"), gbc);
        gbc.gridx = 1;
        panel.add(prenomField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Téléphone:"), gbc);
        gbc.gridx = 1;
        panel.add(telephoneField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Sexe:"), gbc);
        gbc.gridx = 1;
        panel.add(sexeCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Assurance:"), gbc);
        gbc.gridx = 1;
        panel.add(assuranceCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Adresse:"), gbc);
        gbc.gridx = 1;
        panel.add(adresseField, gbc);
        
        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveBtn = new JButton("Enregistrer");
        JButton cancelBtn = new JButton("Annuler");
        
        saveBtn.setBackground(SUCCESS_COLOR);
        saveBtn.setForeground(WHITE);
        cancelBtn.setBackground(DANGER_COLOR);
        cancelBtn.setForeground(WHITE);
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        dialog.add(panel);
        
        // Actions
        saveBtn.addActionListener(e -> {
            try {
                String nom = nomField.getText().trim();
                String prenom = prenomField.getText().trim();
                String telephone = telephoneField.getText().trim();
                String email = emailField.getText().trim();
                String sexe = (String) sexeCombo.getSelectedItem();
                String assurance = (String) assuranceCombo.getSelectedItem();
                String adresse = adresseField.getText().trim();
                
                // Validation simple
                if (nom.isEmpty() || prenom.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Le nom et le prénom sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Créer le PatientDTO
                PatientDTO patientDTO = PatientDTO.builder()
                    .nomComplet(nom + " " + prenom)
                    .telephone(telephone.isEmpty() ? null : telephone)
                    .email(email.isEmpty() ? null : email)
                    .sexe(sexe)
                    .assurance(assurance)
                    .age(25) // Valeur par défaut, serait calculée ou demandée
                    .dateCreationFormatee(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                    .build();
                
                // Sauvegarder dans la base de données via le service
                var patientService = (ma.dentalTech.service.modules.patient.api.PatientService) 
                    ma.dentalTech.conf.ApplicationContext.getBean("patientService");
                
                boolean success = patientService.savePatient(patientDTO);
                
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Patient ajouté avec succès dans la base de données!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                    
                    // Rafraîchir la table
                    refreshData();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Erreur lors de la sauvegarde du patient", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelBtn.addActionListener(e -> dialog.dispose());
        
        dialog.setVisible(true);
    }
    
    private void showEditPatientDialog() {
        int selectedRow = patientTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez sélectionner un patient à modifier", 
                "Modification", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Récupérer les données actuelles
        String nom = (String) patientTable.getValueAt(selectedRow, 1);
        String prenom = (String) patientTable.getValueAt(selectedRow, 2);
        String telephone = (String) patientTable.getValueAt(selectedRow, 5);
        String email = (String) patientTable.getValueAt(selectedRow, 6);
        String sexe = (String) patientTable.getValueAt(selectedRow, 4);
        String assurance = (String) patientTable.getValueAt(selectedRow, 8);
        String adresse = (String) patientTable.getValueAt(selectedRow, 7);
        
        JDialog dialog = new JDialog(this, "Modifier un patient", true);
        dialog.setSize(500, 600);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Champs du formulaire pré-remplis
        JTextField nomField = new JTextField(nom, 20);
        JTextField prenomField = new JTextField(prenom, 20);
        JTextField telephoneField = new JTextField(telephone, 20);
        JTextField emailField = new JTextField(email, 20);
        JComboBox<String> sexeCombo = new JComboBox<>(new String[]{"Homme", "Femme"});
        sexeCombo.setSelectedItem(sexe);
        JComboBox<String> assuranceCombo = new JComboBox<>(new String[]{"CNSS", "CNOPS", "Autre", "Aucune"});
        assuranceCombo.setSelectedItem(assurance);
        JTextField adresseField = new JTextField(adresse, 20);
        
        // Ajout des champs au panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1;
        panel.add(nomField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Prénom:"), gbc);
        gbc.gridx = 1;
        panel.add(prenomField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Téléphone:"), gbc);
        gbc.gridx = 1;
        panel.add(telephoneField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Sexe:"), gbc);
        gbc.gridx = 1;
        panel.add(sexeCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Assurance:"), gbc);
        gbc.gridx = 1;
        panel.add(assuranceCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Adresse:"), gbc);
        gbc.gridx = 1;
        panel.add(adresseField, gbc);
        
        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveBtn = new JButton("Enregistrer");
        JButton cancelBtn = new JButton("Annuler");
        
        saveBtn.setBackground(SUCCESS_COLOR);
        saveBtn.setForeground(WHITE);
        cancelBtn.setBackground(DANGER_COLOR);
        cancelBtn.setForeground(WHITE);
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        dialog.add(panel);
        
        // Actions
        saveBtn.addActionListener(e -> {
            try {
                String newNom = nomField.getText().trim();
                String newPrenom = prenomField.getText().trim();
                String newTelephone = telephoneField.getText().trim();
                String newEmail = emailField.getText().trim();
                String newSexe = (String) sexeCombo.getSelectedItem();
                String newAssurance = (String) assuranceCombo.getSelectedItem();
                String newAdresse = adresseField.getText().trim();
                
                // Validation simple
                if (newNom.isEmpty() || newPrenom.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Le nom et le prénom sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Récupérer l'ID du patient depuis la table
                Long patientId = (Long) patientTable.getValueAt(selectedRow, 0);
                if (patientId == null) {
                    // Si l'ID n'est pas disponible, utiliser une approche alternative
                    JOptionPane.showMessageDialog(dialog, "Impossible de mettre à jour: ID du patient non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Créer le PatientDTO mis à jour
                PatientDTO patientDTO = PatientDTO.builder()
                    .id(patientId)
                    .nomComplet(newNom + " " + newPrenom)
                    .telephone(newTelephone.isEmpty() ? null : newTelephone)
                    .email(newEmail.isEmpty() ? null : newEmail)
                    .sexe(newSexe)
                    .assurance(newAssurance)
                    .age(25) // Valeur par défaut
                    .dateCreationFormatee(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                    .build();
                
                // Mettre à jour dans la base de données via le service
                var patientService = (ma.dentalTech.service.modules.patient.api.PatientService) 
                    ma.dentalTech.conf.ApplicationContext.getBean("patientService");
                
                boolean success = patientService.updatePatient(patientDTO);
                
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Patient modifié avec succès dans la base de données!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                    
                    // Rafraîchir la table
                    refreshData();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Erreur lors de la mise à jour du patient", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelBtn.addActionListener(e -> dialog.dispose());
        
        dialog.setVisible(true);
    }
    
    private void deleteSelectedPatient() {
        int selectedRow = patientTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez sélectionner un patient à supprimer", 
                "Suppression", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String patientInfo = (String) patientTable.getValueAt(selectedRow, 1) + " " + 
                           (String) patientTable.getValueAt(selectedRow, 2);
        
        int result = JOptionPane.showConfirmDialog(
            this,
            "Êtes-vous sûr de vouloir supprimer le patient: " + patientInfo + " ?",
            "Confirmation de suppression",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (result == JOptionPane.YES_OPTION) {
            try {
                // Récupérer l'ID du patient depuis la table
                Long patientId = (Long) patientTable.getValueAt(selectedRow, 0);
                if (patientId == null) {
                    JOptionPane.showMessageDialog(this, 
                        "Impossible de supprimer: ID du patient non trouvé", 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Supprimer de la base de données via le service
                var patientService = (ma.dentalTech.service.modules.patient.api.PatientService) 
                    ma.dentalTech.conf.ApplicationContext.getBean("patientService");
                
                boolean success = patientService.deletePatient(patientId);
                
                if (success) {
                    JOptionPane.showMessageDialog(this, 
                        "Patient supprimé avec succès de la base de données!", 
                        "Suppression", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    // Rafraîchir la table
                    refreshData();
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Erreur lors de la suppression du patient", 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Erreur: " + e.getMessage(), 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void refreshData() {
        try {
            // Obtenir les données fraîches depuis le service
            var patientService = (ma.dentalTech.service.modules.patient.api.PatientService) 
                ma.dentalTech.conf.ApplicationContext.getBean("patientService");
            var allPatients = patientService.getAllPatientsAsDTO();
            
            // Vider et recharger la table
            DefaultTableModel model = (DefaultTableModel) patientTable.getModel();
            model.setRowCount(0);
            
            for (PatientDTO patient : allPatients) {
                String[] nameParts = patient.getNomComplet().split(" ", 2);
                String nom = nameParts[0];
                String prenom = nameParts.length > 1 ? nameParts[1] : "";
                
                model.addRow(new Object[]{
                    patient.getId(),
                    nom,
                    prenom,
                    patient.getAge(),
                    patient.getSexe(),
                    patient.getTelephone(),
                    patient.getEmail(),
                    "N/A", // Adresse - serait ajoutée dans une version complète
                    patient.getAssurance(),
                    patient.getDateCreationFormatee()
                });
            }
            
            // Mettre à jour les statistiques
            loadStatistics(allPatients);
            
            // Vider le champ de recherche
            searchField.setText("");
            
            JOptionPane.showMessageDialog(this, 
                "Données actualisées avec succès", 
                "Actualisation", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de l'actualisation: " + e.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void showAsync(List<PatientDTO> patients) {
        SwingUtilities.invokeLater(() -> {
            try {
                PatientView view = new PatientView(patients);
                view.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Erreur lors de l'affichage des patients: " + e.getMessage(), 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

