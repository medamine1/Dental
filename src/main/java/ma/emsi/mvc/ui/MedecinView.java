package ma.emsi.mvc.ui;

import ma.emsi.entities.Medecin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MedecinView extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton addBtn, editBtn, deleteBtn, refreshBtn;

    public MedecinView(List<Medecin> medecins) {
        setTitle("Gestion des Médecins");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        String[] columns = {"ID", "Nom", "Prénom", "Spécialité", "Téléphone"};
        model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        refreshMedecins(medecins);
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

    public void refreshMedecins(List<Medecin> medecins) {
        model.setRowCount(0);
        for (Medecin m : medecins) {
            model.addRow(new Object[]{
                m.getId(), m.getNom(), m.getPrenom(), m.getSpecialite(), m.getTel()
            });
        }
    }

    public static void showUI(List<Medecin> medecins) {
        SwingUtilities.invokeLater(() -> new MedecinView(medecins).setVisible(true));
    }
}
