package ma.emsi.mvc.ui;

import ma.emsi.entities.Ordonnance;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OrdonnanceView extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton addBtn, editBtn, deleteBtn, refreshBtn;

    public OrdonnanceView(List<Ordonnance> ordonnances) {
        setTitle("Gestion des Ordonnances");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        String[] columns = {"ID", "Patient", "Médecin", "Date", "Détails"};
        model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        refreshOrdonnances(ordonnances);
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

    public void refreshOrdonnances(List<Ordonnance> ordonnances) {
        model.setRowCount(0);
        for (Ordonnance o : ordonnances) {
            model.addRow(new Object[]{
                o.getId(), o.getPatientName(), o.getMedecinName(), o.getDateOrdonnance(), o.getDetails()
            });
        }
    }

    public static void showUI(List<Ordonnance> ordonnances) {
        SwingUtilities.invokeLater(() -> new OrdonnanceView(ordonnances).setVisible(true));
    }
}
