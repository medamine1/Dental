package ma.dentalTech.mvc.ui.modules.medecin;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import ma.dentalTech.mvc.dto.MedecinDTO;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class MedecinView extends JFrame {

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
    private JTable medecinTable;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private JButton searchBtn;
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton refreshBtn;
    private JButton backBtn;
    
    // Labels de statistiques
    private JLabel totalMedecinsLabel;
    private JLabel generalisteLabel;
    private JLabel orthodontisteLabel;
    private JLabel chirurgienLabel;

    public MedecinView(List<MedecinDTO> medecins) {
        initializeComponents(medecins);
        setupLayout();
        setupEventHandlers();
        loadStatistics(medecins);
        
        setTitle("DentalTech - Gestion des Médecins");
        setSize(1400, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 700));
    }

    private void initializeComponents(List<MedecinDTO> medecins) {
        // Header
        titleLabel = new JLabel("Gestion des Médecins");
        subtitleLabel = new JLabel("Liste complète des médecins du cabinet");
        
        // Table des médecins
        createMedecinTable(medecins);
        
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
        totalMedecinsLabel = createStatsLabel("0", "Total Médecins", PRIMARY_COLOR);
        generalisteLabel = createStatsLabel("0", "Généralistes", SUCCESS_COLOR);
        orthodontisteLabel = createStatsLabel("0", "Orthodontistes", WARNING_COLOR);
        chirurgienLabel = createStatsLabel("0", "Chirurgiens", DANGER_COLOR);
    }
    
    private void createMedecinTable(List<MedecinDTO> medecins) {
        String[] columns = {
            "ID", "Nom", "Prénom", "Spécialité", "Téléphone", 
            "Email", "Date d'ajout"
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

        // Ajouter les données des médecins
        for (MedecinDTO medecin : medecins) {
            String[] nameParts = medecin.getNomComplet().split(" ", 2);
            String nom = nameParts[0];
            String prenom = nameParts.length > 1 ? nameParts[1] : "";
            
            model.addRow(new Object[]{
                medecins.indexOf(medecin) + 1, // ID temporaire
                nom,
                prenom,
                medecin.getSpecialite(),
                medecin.getTelephone(),
                "N/A", // Email - serait ajouté dans une version complète
                medecin.getDateCreationFormatee()
            });
        }

        medecinTable = new JTable(model);
        medecinTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        medecinTable.setRowHeight(35);
        medecinTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        medecinTable.getTableHeader().setReorderingAllowed(false);
        
        // Personnaliser le rendu des cellules
        customizeTableRendering();
        
        scrollPane = new JScrollPane(medecinTable);
        scrollPane.setBorder(new CompoundBorder(
            new LineBorder(LIGHT_GRAY),
            BorderFactory.createEmptyBorder(1, 1, 1, 1)
        ));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    private void customizeTableRendering() {
        // Header personnalisé
        JTableHeader header = medecinTable.getTableHeader();
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
        
        for (int i = 0; i < medecinTable.getColumnCount(); i++) {
            medecinTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Coloration spécifique pour la colonne spécialité
        medecinTable.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (value != null) {
                    String specialite = value.toString();
                    if (specialite.contains("généraliste") || specialite.contains("Généraliste")) {
                        setForeground(SUCCESS_COLOR);
                    } else if (specialite.contains("orthodontiste") || specialite.contains("Orthodontiste")) {
                        setForeground(WARNING_COLOR);
                    } else if (specialite.contains("chirurgien") || specialite.contains("Chirurgien")) {
                        setForeground(DANGER_COLOR);
                    }
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
        statsPanel.add(totalMedecinsLabel);
        statsPanel.add(generalisteLabel);
        statsPanel.add(orthodontisteLabel);
        statsPanel.add(chirurgienLabel);
        
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
        
        addBtn.addActionListener(e -> showAddMedecinDialog());
        editBtn.addActionListener(e -> showEditMedecinDialog());
        deleteBtn.addActionListener(e -> deleteSelectedMedecin());
        refreshBtn.addActionListener(e -> refreshData());
        
        // Double-click sur une ligne pour éditer
        medecinTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    showEditMedecinDialog();
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
    
    private void loadStatistics(List<MedecinDTO> medecins) {
        int total = medecins.size();
        int generalisteCount = 0;
        int orthodontisteCount = 0;
        int chirurgienCount = 0;
        
        for (MedecinDTO medecin : medecins) {
            String specialite = medecin.getSpecialite().toLowerCase();
            if (specialite.contains("généraliste") || specialite.contains("generaliste")) {
                generalisteCount++;
            } else if (specialite.contains("orthodontiste")) {
                orthodontisteCount++;
            } else if (specialite.contains("chirurgien")) {
                chirurgienCount++;
            }
        }
        
        totalMedecinsLabel.setText("<html><center><font size='5' color='" + 
                                  Integer.toHexString(PRIMARY_COLOR.getRGB()).substring(2) + 
                                  "'><b>" + total + "</b></font><br><font size='2' color='gray'>Total Médecins</font></center></html>");
        generalisteLabel.setText("<html><center><font size='5' color='" + 
                                Integer.toHexString(SUCCESS_COLOR.getRGB()).substring(2) + 
                                "'><b>" + generalisteCount + "</b></font><br><font size='2' color='gray'>Généralistes</font></center></html>");
        orthodontisteLabel.setText("<html><center><font size='5' color='" + 
                                  Integer.toHexString(WARNING_COLOR.getRGB()).substring(2) + 
                                  "'><b>" + orthodontisteCount + "</b></font><br><font size='2' color='gray'>Orthodontistes</font></center></html>");
        chirurgienLabel.setText("<html><center><font size='5' color='" + 
                                Integer.toHexString(DANGER_COLOR.getRGB()).substring(2) + 
                                "'><b>" + chirurgienCount + "</b></font><br><font size='2' color='gray'>Chirurgiens</font></center></html>");
    }
    
    private void performSearch() {
        String searchText = searchField.getText().trim().toLowerCase();
        
        // Obtenir toutes les données originales depuis le service
        try {
            var medecinService = (ma.dentalTech.service.modules.medecin.api.MedecinService) 
                ma.dentalTech.conf.ApplicationContext.getBean("medecinService");
            var allMedecins = medecinService.getAllMedecinsAsDTO();
            
            DefaultTableModel model = (DefaultTableModel) medecinTable.getModel();
            model.setRowCount(0); // Vider la table
            
            // Filtrer et ajouter les résultats
            for (MedecinDTO medecin : allMedecins) {
                boolean match = searchText.isEmpty() || 
                    medecin.getNomComplet().toLowerCase().contains(searchText) ||
                    medecin.getSpecialite().toLowerCase().contains(searchText) ||
                    medecin.getTelephone().toLowerCase().contains(searchText);
                
                if (match) {
                    String[] nameParts = medecin.getNomComplet().split(" ", 2);
                    String nom = nameParts[0];
                    String prenom = nameParts.length > 1 ? nameParts[1] : "";
                    
                    model.addRow(new Object[]{
                        allMedecins.indexOf(medecin) + 1,
                        nom,
                        prenom,
                        medecin.getSpecialite(),
                        medecin.getTelephone(),
                        "N/A", // Email - serait ajouté dans une version complète
                        medecin.getDateCreationFormatee()
                    });
                }
            }
            
            // Mettre à jour les statistiques
            loadStatistics(allMedecins);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de la recherche: " + e.getMessage(), 
                "Erreur de recherche", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showAddMedecinDialog() {
        JDialog dialog = new JDialog(this, "Ajouter un médecin", true);
        dialog.setSize(500, 400);
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
        JComboBox<String> specialiteCombo = new JComboBox<>(new String[]{"Généraliste", "Orthodontiste", "Chirurgien", "Pédiatre"});
        JTextField emailField = new JTextField(20);
        
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
        panel.add(new JLabel("Spécialité:"), gbc);
        gbc.gridx = 1;
        panel.add(specialiteCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);
        
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
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        dialog.add(panel);
        
        // Actions
        saveBtn.addActionListener(e -> {
            try {
                String nom = nomField.getText().trim();
                String prenom = prenomField.getText().trim();
                String telephone = telephoneField.getText().trim();
                String specialite = (String) specialiteCombo.getSelectedItem();
                String email = emailField.getText().trim();
                
                // Validation simple
                if (nom.isEmpty() || prenom.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Le nom et le prénom sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Ajouter le médecin à la table (simulation)
                DefaultTableModel model = (DefaultTableModel) medecinTable.getModel();
                model.addRow(new Object[]{
                    model.getRowCount() + 1,
                    nom,
                    prenom,
                    specialite,
                    telephone,
                    email,
                    java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                });
                
                JOptionPane.showMessageDialog(dialog, "Médecin ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelBtn.addActionListener(e -> dialog.dispose());
        
        dialog.setVisible(true);
    }
    
    private void showEditMedecinDialog() {
        int selectedRow = medecinTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez sélectionner un médecin à modifier", 
                "Modification", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Récupérer les données actuelles
        String nom = (String) medecinTable.getValueAt(selectedRow, 1);
        String prenom = (String) medecinTable.getValueAt(selectedRow, 2);
        String telephone = (String) medecinTable.getValueAt(selectedRow, 4);
        String specialite = (String) medecinTable.getValueAt(selectedRow, 3);
        String email = (String) medecinTable.getValueAt(selectedRow, 5);
        
        JDialog dialog = new JDialog(this, "Modifier un médecin", true);
        dialog.setSize(500, 400);
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
        JComboBox<String> specialiteCombo = new JComboBox<>(new String[]{"Généraliste", "Orthodontiste", "Chirurgien", "Pédiatre"});
        specialiteCombo.setSelectedItem(specialite);
        JTextField emailField = new JTextField(email, 20);
        
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
        panel.add(new JLabel("Spécialité:"), gbc);
        gbc.gridx = 1;
        panel.add(specialiteCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);
        
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
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        dialog.add(panel);
        
        // Actions
        saveBtn.addActionListener(e -> {
            try {
                String newNom = nomField.getText().trim();
                String newPrenom = prenomField.getText().trim();
                String newTelephone = telephoneField.getText().trim();
                String newSpecialite = (String) specialiteCombo.getSelectedItem();
                String newEmail = emailField.getText().trim();
                
                // Validation simple
                if (newNom.isEmpty() || newPrenom.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Le nom et le prénom sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Mettre à jour la table
                DefaultTableModel model = (DefaultTableModel) medecinTable.getModel();
                model.setValueAt(newNom, selectedRow, 1);
                model.setValueAt(newPrenom, selectedRow, 2);
                model.setValueAt(newSpecialite, selectedRow, 3);
                model.setValueAt(newTelephone, selectedRow, 4);
                model.setValueAt(newEmail, selectedRow, 5);
                
                JOptionPane.showMessageDialog(dialog, "Médecin modifié avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelBtn.addActionListener(e -> dialog.dispose());
        
        dialog.setVisible(true);
    }
    
    private void deleteSelectedMedecin() {
        int selectedRow = medecinTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez sélectionner un médecin à supprimer", 
                "Suppression", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String medecinInfo = (String) medecinTable.getValueAt(selectedRow, 1) + " " + 
                           (String) medecinTable.getValueAt(selectedRow, 2);
        
        int result = JOptionPane.showConfirmDialog(
            this,
            "Êtes-vous sûr de vouloir supprimer le médecin: " + medecinInfo + " ?",
            "Confirmation de suppression",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (result == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) medecinTable.getModel();
            model.removeRow(selectedRow);
            
            JOptionPane.showMessageDialog(this, 
                "Médecin supprimé avec succès", 
                "Suppression", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void refreshData() {
        try {
            // Obtenir les données fraîches depuis le service
            var medecinService = (ma.dentalTech.service.modules.medecin.api.MedecinService) 
                ma.dentalTech.conf.ApplicationContext.getBean("medecinService");
            var allMedecins = medecinService.getAllMedecinsAsDTO();
            
            // Vider et recharger la table
            DefaultTableModel model = (DefaultTableModel) medecinTable.getModel();
            model.setRowCount(0);
            
            for (MedecinDTO medecin : allMedecins) {
                String[] nameParts = medecin.getNomComplet().split(" ", 2);
                String nom = nameParts[0];
                String prenom = nameParts.length > 1 ? nameParts[1] : "";
                
                model.addRow(new Object[]{
                    allMedecins.indexOf(medecin) + 1,
                    nom,
                    prenom,
                    medecin.getSpecialite(),
                    medecin.getTelephone(),
                    "N/A", // Email - serait ajouté dans une version complète
                    medecin.getDateCreationFormatee()
                });
            }
            
            // Mettre à jour les statistiques
            loadStatistics(allMedecins);
            
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

    public static void showAsync(List<MedecinDTO> medecins) {
        SwingUtilities.invokeLater(() -> {
            try {
                MedecinView view = new MedecinView(medecins);
                view.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Erreur lors de l'affichage des médecins: " + e.getMessage(), 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
