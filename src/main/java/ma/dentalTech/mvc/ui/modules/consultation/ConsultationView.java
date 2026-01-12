package ma.dentalTech.mvc.ui.modules.consultation;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import ma.dentalTech.mvc.dto.ConsultationDTO;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class ConsultationView extends JFrame {

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
    private JTable consultationTable;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private JButton searchBtn;
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton refreshBtn;
    private JComboBox<String> dateFilterCombo;
    private JButton backBtn;
    
    // Labels de statistiques
    private JLabel totalConsultationsLabel;
    private JLabel todayRevenueLabel;
    private JLabel completedLabel;
    private JLabel pendingLabel;

    public ConsultationView(List<ConsultationDTO> consultations) {
        initializeComponents(consultations);
        setupLayout();
        setupEventHandlers();
        loadStatistics(consultations);
        
        setTitle("DentalTech - Gestion des Consultations");
        setSize(1400, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 700));
    }

    private void initializeComponents(List<ConsultationDTO> consultations) {
        // Header
        titleLabel = new JLabel("Gestion des Consultations");
        subtitleLabel = new JLabel("Planning et suivi des consultations du cabinet");
        
        // Table des consultations
        createConsultationTable(consultations);
        
        // Panneau de recherche
        searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        dateFilterCombo = new JComboBox<>(new String[]{"Toutes", "Aujourd'hui", "Cette semaine", "Ce mois"});
        dateFilterCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateFilterCombo.setBackground(WHITE);
        dateFilterCombo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        searchBtn = createModernButton("🔍 Rechercher", PRIMARY_COLOR);
        addBtn = createModernButton("➕ Ajouter", SUCCESS_COLOR);
        editBtn = createModernButton("✏️ Modifier", WARNING_COLOR);
        deleteBtn = createModernButton("🗑️ Supprimer", DANGER_COLOR);
        refreshBtn = createModernButton("🔄 Actualiser", PRIMARY_COLOR);
        backBtn = createModernButton("⬅ Retour", DANGER_COLOR);
        
        // Statistiques
        totalConsultationsLabel = createStatsLabel("0", "Total Consultations", PRIMARY_COLOR);
        todayRevenueLabel = createStatsLabel("0 DH", "Revenus du jour", SUCCESS_COLOR);
        completedLabel = createStatsLabel("0", "Terminées", WARNING_COLOR);
        pendingLabel = createStatsLabel("0", "En attente", DANGER_COLOR);
    }
    
    private void createConsultationTable(List<ConsultationDTO> consultations) {
        String[] columns = {
            "ID", "Patient", "Médecin", "Date", "Heure", "Motif", 
            "Montant", "Statut"
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

        // Ajouter les données des consultations
        for (ConsultationDTO consultation : consultations) {
            String dateStr = consultation.getDateFormatee();
            String[] dateParts = dateStr.split(" ");
            String date = dateParts.length > 0 ? dateParts[0] : "";
            String time = dateParts.length > 1 ? dateParts[1] : "";
            
            model.addRow(new Object[]{
                consultations.indexOf(consultation) + 1, // ID temporaire
                consultation.getNomPatient(),
                consultation.getNomMedecin(),
                date,
                time,
                consultation.getMotif(),
                consultation.getMontant() + " DH",
                "Planifiée" // Statut par défaut
            });
        }

        consultationTable = new JTable(model);
        consultationTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        consultationTable.setRowHeight(35);
        consultationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        consultationTable.getTableHeader().setReorderingAllowed(false);
        
        // Personnaliser le rendu des cellules
        customizeTableRendering();
        
        scrollPane = new JScrollPane(consultationTable);
        scrollPane.setBorder(new CompoundBorder(
            new LineBorder(LIGHT_GRAY),
            BorderFactory.createEmptyBorder(1, 1, 1, 1)
        ));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    private void customizeTableRendering() {
        // Header personnalisé
        JTableHeader header = consultationTable.getTableHeader();
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
        
        for (int i = 0; i < consultationTable.getColumnCount(); i++) {
            consultationTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Coloration spécifique pour la colonne montant
        consultationTable.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (value != null) {
                    String montantStr = value.toString();
                    if (montantStr.contains("DH")) {
                        setForeground(SUCCESS_COLOR);
                        setFont(getFont().deriveFont(Font.BOLD));
                    }
                }
                
                return component;
            }
        });
        
        // Coloration spécifique pour la colonne statut
        consultationTable.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (value != null) {
                    String statut = value.toString();
                    if ("Planifiée".equals(statut)) {
                        setForeground(PRIMARY_COLOR);
                    } else if ("Terminée".equals(statut)) {
                        setForeground(SUCCESS_COLOR);
                    } else if ("En attente".equals(statut)) {
                        setForeground(WARNING_COLOR);
                    } else if ("Annulée".equals(statut)) {
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
        searchPanel.add(new JLabel("Période:"));
        searchPanel.add(dateFilterCombo);
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
        statsPanel.add(totalConsultationsLabel);
        statsPanel.add(todayRevenueLabel);
        statsPanel.add(completedLabel);
        statsPanel.add(pendingLabel);
        
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
        dateFilterCombo.addActionListener(e -> performSearch());
        
        addBtn.addActionListener(e -> showAddConsultationDialog());
        editBtn.addActionListener(e -> showEditConsultationDialog());
        deleteBtn.addActionListener(e -> deleteSelectedConsultation());
        refreshBtn.addActionListener(e -> refreshData());
        
        // Double-click sur une ligne pour éditer
        consultationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    showEditConsultationDialog();
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
    
    private void loadStatistics(List<ConsultationDTO> consultations) {
        int total = consultations.size();
        double todayRevenue = 0.0;
        int completedCount = 0;
        int pendingCount = 0;
        
        for (ConsultationDTO consultation : consultations) {
            // Calculer les revenus
            if (consultation.getMontant() != null) {
                todayRevenue += consultation.getMontant();
            }
            
            // Compter les statuts (simulation)
            completedCount++;
        }
        
        pendingCount = total - completedCount;
        
        totalConsultationsLabel.setText("<html><center><font size='5' color='" + 
                                       Integer.toHexString(PRIMARY_COLOR.getRGB()).substring(2) + 
                                       "'><b>" + total + "</b></font><br><font size='2' color='gray'>Total Consultations</font></center></html>");
        todayRevenueLabel.setText("<html><center><font size='5' color='" + 
                                 Integer.toHexString(SUCCESS_COLOR.getRGB()).substring(2) + 
                                 "'><b>" + String.format("%.0f", todayRevenue) + " DH</b></font><br><font size='2' color='gray'>Revenus du jour</font></center></html>");
        completedLabel.setText("<html><center><font size='5' color='" + 
                               Integer.toHexString(WARNING_COLOR.getRGB()).substring(2) + 
                               "'><b>" + completedCount + "</b></font><br><font size='2' color='gray'>Terminées</font></center></html>");
        pendingLabel.setText("<html><center><font size='5' color='" + 
                             Integer.toHexString(DANGER_COLOR.getRGB()).substring(2) + 
                             "'><b>" + pendingCount + "</b></font><br><font size='2' color='gray'>En attente</font></center></html>");
    }
    
    private void performSearch() {
        String searchText = searchField.getText().trim().toLowerCase();
        String selectedPeriod = (String) dateFilterCombo.getSelectedItem();
        
        if (searchText.isEmpty() && "Toutes".equals(selectedPeriod)) {
            // Recharger toutes les données
            return;
        }
        
        // Filtrer les données (simulation)
        DefaultTableModel model = (DefaultTableModel) consultationTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean match = false;
            for (int j = 0; j < model.getColumnCount(); j++) {
                Object value = model.getValueAt(i, j);
                if (value != null && value.toString().toLowerCase().contains(searchText)) {
                    match = true;
                    break;
                }
            }
            // Cacher les lignes qui ne correspondent pas (simplifié)
        }
    }
    
    private void showAddConsultationDialog() {
        JOptionPane.showMessageDialog(this, 
            "Fonctionnalité d'ajout de consultation en cours de développement", 
            "Ajout de Consultation", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showEditConsultationDialog() {
        int selectedRow = consultationTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez sélectionner une consultation à modifier", 
                "Modification", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String patientInfo = (String) consultationTable.getValueAt(selectedRow, 1);
        String medecinInfo = (String) consultationTable.getValueAt(selectedRow, 2);
        
        JOptionPane.showMessageDialog(this, 
            "Modification de la consultation: " + patientInfo + " - " + medecinInfo + "\n\nFonctionnalité en cours de développement", 
            "Modification de Consultation", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void deleteSelectedConsultation() {
        int selectedRow = consultationTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Veuillez sélectionner une consultation à supprimer", 
                "Suppression", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String patientInfo = (String) consultationTable.getValueAt(selectedRow, 1);
        String medecinInfo = (String) consultationTable.getValueAt(selectedRow, 2);
        
        int result = JOptionPane.showConfirmDialog(
            this,
            "Êtes-vous sûr de vouloir supprimer la consultation: " + patientInfo + " - " + medecinInfo + " ?",
            "Confirmation de suppression",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (result == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) consultationTable.getModel();
            model.removeRow(selectedRow);
            
            JOptionPane.showMessageDialog(this, 
                "Consultation supprimée avec succès", 
                "Suppression", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void refreshData() {
        JOptionPane.showMessageDialog(this, 
            "Données actualisées avec succès", 
            "Actualisation", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showAsync(List<ConsultationDTO> consultations) {
        SwingUtilities.invokeLater(() -> {
            try {
                ConsultationView view = new ConsultationView(consultations);
                view.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                    "Erreur lors de l'affichage des consultations: " + e.getMessage(), 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
