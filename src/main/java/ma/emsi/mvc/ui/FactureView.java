package ma.emsi.mvc.ui;

import ma.emsi.entities.Facture;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FactureView extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton addBtn, editBtn, deleteBtn, refreshBtn;

    public FactureView(List<Facture> factures) {
        setTitle("Gestion des Factures");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        String[] columns = {"ID", "Patient", "Date", "Montant", "Statut"};
        model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        refreshFactures(factures);
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

    public void refreshFactures(List<Facture> factures) {
        model.setRowCount(0);
        for (Facture f : factures) {
            model.addRow(new Object[]{
                f.getId(), f.getPatientName(), f.getDateFacture(), f.getMontant(), f.getStatut()
            });
        }
    }

    public static void showUI(List<Facture> factures) {
        SwingUtilities.invokeLater(() -> new FactureView(factures).setVisible(true));
    }
}
