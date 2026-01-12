package ma.emsi.mvc.ui;

import ma.emsi.entities.Consultation;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ConsultationView extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton addBtn, editBtn, deleteBtn, refreshBtn;

    public ConsultationView(List<Consultation> consultations) {
        setTitle("Gestion des Consultations");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        String[] columns = {"ID", "Patient", "Médecin", "Date", "Motif", "Notes"};
        model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        refreshConsultations(consultations);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        addBtn = new JButton("Ajouter");
        editBtn = new JButton("Modifier");
        deleteBtn = new JButton("Supprimer");
        refreshBtn = new JButton("Rafraîchir");
        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(refreshBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public void refreshConsultations(List<Consultation> consultations) {
        model.setRowCount(0);
        for (Consultation c : consultations) {
            model.addRow(new Object[]{
                c.getId(), c.getPatientName(), c.getMedecinName(), c.getDateConsultation(), c.getMotif(), c.getNotes()
            });
        }
    }

    public static void showUI(List<Consultation> consultations) {
        SwingUtilities.invokeLater(() -> new ConsultationView(consultations).setVisible(true));
    }
}
