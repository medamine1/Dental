package ma.dentalTech.mvc.ui;

import javax.swing.*;
import java.awt.*;
import ma.dentalTech.mvc.controllers.modules.patient.api.PatientController;
import ma.dentalTech.mvc.controllers.modules.medecin.api.MedecinController;
import ma.dentalTech.mvc.controllers.modules.consultation.api.ConsultationController;
import ma.dentalTech.conf.ApplicationContext;

public class DashboardView extends JFrame {
    public DashboardView() {
        setTitle("Cabinet Dentaire - Tableau de bord");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel welcomeLabel = new JLabel("Bienvenue dans le Cabinet Dentaire", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Optima", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel navPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        JButton patientsBtn = new JButton("Patients");
        JButton medecinsBtn = new JButton("Médecins");
        JButton consultationsBtn = new JButton("Consultations");
        JButton quitBtn = new JButton("Quitter");
        navPanel.add(patientsBtn);
        navPanel.add(medecinsBtn);
        navPanel.add(consultationsBtn);
        navPanel.add(quitBtn);
        add(navPanel, BorderLayout.CENTER);

        patientsBtn.addActionListener(e -> {
            PatientController pc = ApplicationContext.getBean(PatientController.class);
            pc.showRecentPatients();
        });
        
        medecinsBtn.addActionListener(e -> {
            MedecinController mc = ApplicationContext.getBean(MedecinController.class);
            mc.showAllMedecins();
        });
        
        consultationsBtn.addActionListener(e -> {
            ConsultationController cc = ApplicationContext.getBean(ConsultationController.class);
            cc.showTodayConsultations();
        });
        
        quitBtn.addActionListener(e -> System.exit(0));
    }

    public static void showDashboard() {
        SwingUtilities.invokeLater(() -> new DashboardView().setVisible(true));
    }
}
