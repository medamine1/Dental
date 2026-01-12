package ma.emsi.mvc.ui;

import ma.emsi.entities.Patient;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PatientView extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton addBtn, editBtn, deleteBtn, refreshBtn;

    public PatientView(List<Patient> patients) {
        setTitle("Gestion des Patients");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Table
        String[] columns = {"ID", "Nom", "Prénom", "Date Naissance", "Sexe", "Téléphone"};
        model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        refreshPatients(patients);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
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

        // TODO: Add action listeners for buttons to call controller methods
    }

    public void refreshPatients(List<Patient> patients) {
        model.setRowCount(0);
        for (Patient p : patients) {
            model.addRow(new Object[]{
                p.getId(), p.getNom(), p.getPrenom(), "N/A", p.getSexe(), "N/A"
            });
        }
    }

    public static void showUI(List<Patient> patients) {
        SwingUtilities.invokeLater(() -> new PatientView(patients).setVisible(true));
    }
}
