package ma.dentalTech.mvc.ui.modules.consultation;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import ma.dentalTech.mvc.dto.ConsultationDTO;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class ConsultationView extends JFrame {

    JLabel lblTitre;
    JPanel panelTitre;
    JScrollPane panelTable;

    private void initLabel(){
        lblTitre = new JLabel("Consultations d'aujourd'hui", SwingConstants.CENTER);
        lblTitre.setFont(new Font("Optima", Font.BOLD, 28));
        lblTitre.setForeground(new Color(0, 102, 153));
        lblTitre.setBorder(new EmptyBorder(15, 0, 15, 0));
    }

    private void initPanels(){
        initLabel();
        panelTitre = new JPanel(new BorderLayout());
        panelTitre.setBackground(new Color(245, 245, 245));
        panelTitre.add(lblTitre, BorderLayout.CENTER);
    }
    
    private void initTable(List<ConsultationDTO> consultations){
        initPanels();
        String[] columns = {"Patient", "Médecin", "Heure", "Motif", "Montant"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        for (ConsultationDTO dto : consultations) {
            model.addRow(new Object[]{
                    dto.getNomPatient(),
                    dto.getNomMedecin(),
                    dto.getDateFormatee(),
                    dto.getMotif(),
                    dto.getMontant() + " DH"
            });
        }

        JTable table = new JTable(model);
        table.setFont(new Font("Optima", Font.PLAIN, 18));
        table.setRowHeight(40);
        table.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Centrer le contenu des cellules
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Personnaliser le header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Optima", Font.BOLD, 22));
        header.setPreferredSize(new Dimension(100, 45));
        ((DefaultTableCellRenderer) header.getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);

        panelTable = new JScrollPane(table);
        panelTable.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
    }

    private void initContainer(List<ConsultationDTO> consultations){
        initTable(consultations);
        var mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout(10, 10));

        mainContainer.add(panelTitre, BorderLayout.NORTH);
        mainContainer.add(panelTable, BorderLayout.CENTER);
    }

    public ConsultationView(List<ConsultationDTO> consultations) {
        super("DentalTech - Consultations");

        initContainer(consultations);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 400);
        setLocationRelativeTo(null);
    }

    public static void showAsync(List<ConsultationDTO> consultations) {
        SwingUtilities.invokeLater(() -> new ConsultationView(consultations).setVisible(true));
    }
}
