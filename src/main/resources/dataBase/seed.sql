-- Jeu de données de Test pour PostgreSQL

-- Roles (explicit IDs for FK references)
INSERT INTO role(id, libelle) VALUES
  (1, 'ADMIN'),
  (2, 'MEDECIN'),
  (3, 'SECRETAIRE');

-- Assurance (explicit IDs for FK references)
INSERT INTO assurance(id, nom) VALUES
  (1, 'CNSS'),
  (2, 'AXA'),
  (3, 'Sanad');

-- Utilisateurs (explicit IDs for FK references)
INSERT INTO utilisateur(id, nom, prenom, email, tel, adresse, cin, sexe, login, password_hash, actif)
VALUES
  (1, 'Dupont', 'Jean', 'jean.dupont@example.com', '0612345678', '1 rue de Paris', 'AA123456', 'HOMME', 'jdupont', 'hashedpass1', TRUE),
  (2, 'Martin', 'Sophie', 'sophie.martin@example.com', '0623456789', '2 avenue Lyon', 'BB234567', 'FEMME', 'smartin', 'hashedpass2', TRUE);

-- Staff
INSERT INTO staff(id, salaire, prime, date_recrutement, solde_conge)
VALUES
  (1, 12000, 500, '2022-01-10', 10),
  (2, 9000, 300, '2023-03-15', 15);

-- Medecin
INSERT INTO medecin(id, specialite, agenda_medecin)
VALUES
  (1, 'Dentiste', 'Agenda 1');

-- Secretaire
INSERT INTO secretaire(id, num_cnss, commission)
VALUES
  (2, 'CNSS123', 200);

-- Utilisateur_role (reference explicit role IDs)
INSERT INTO utilisateur_role(utilisateur_id, role_id) VALUES
  (1, 2), -- Jean Dupont is MEDECIN
  (2, 3); -- Sophie Martin is SECRETAIRE

-- Patients (explicit IDs for FK references)
INSERT INTO patient(id, nom, prenom, date_naissance, sexe, adresse, tel, email, num_assurance, assurance_id, actif)
VALUES
  (1, 'Benali', 'Karim', '1990-05-20', 'HOMME', '3 rue Rabat', '0634567890', 'karim.benali@example.com', 'AXA123', 2, TRUE),
  (2, 'El Amrani', 'Leila', '1985-11-02', 'FEMME', '4 avenue Casa', '0645678901', 'leila.elamrani@example.com', 'CNSS456', 1, TRUE);

-- Dossier Médical
INSERT INTO dossier_medical(patient_id, notes, date_ouverture)
VALUES
  (1, 'Patient suivi pour caries.', '2024-01-01'),
  (2, 'Patient suivi pour détartrage.', '2024-01-05');

-- Actes
INSERT INTO acte(libelle, prix, categorie, en_promo)
VALUES
  ('Consultation', 200, 'General', FALSE),
  ('Détartrage', 400, 'Soins', TRUE);

-- Médicaments
INSERT INTO medicament(nom, description, forme)
VALUES
  ('Paracetamol', 'Antalgique', 'Comprimé'),
  ('Amoxicilline', 'Antibiotique', 'Capsule');

-- Ordonnances
INSERT INTO ordonnance(patient_id, medecin_id, date_ordonnance, details)
VALUES
  (1, 1, '2024-01-10 10:00:00', 'Prendre Paracetamol 3x/j'),
  (2, 1, '2024-01-12 11:00:00', 'Prendre Amoxicilline 2x/j');

-- Prescriptions
INSERT INTO prescription(ordonnance_id, medicament_id, posologie)
VALUES
  (1, 1, '3 comprimés par jour'),
  (2, 2, '2 capsules par jour');

-- Factures
INSERT INTO facture(patient_id, date_facture, montant, statut)
VALUES
  (1, '2024-01-15 09:00:00', 600, 'PAYEE'),
  (2, '2024-01-16 10:00:00', 400, 'EN_ATTENTE');

-- Articles
INSERT INTO article(nom, prix, categorie)
VALUES
  ('Brosse à dents', 30, 'Hygiène'),
  ('Fil dentaire', 20, 'Hygiène');

-- Charges
INSERT INTO charges(description, montant, date_charge)
VALUES
  ('Loyer', 5000, '2024-01-01'),
  ('Electricité', 800, '2024-01-05');

-- Revenues
INSERT INTO revenue(source, montant, date_revenue)
VALUES
  ('Consultations', 2000, '2024-01-15'),
  ('Vente produits', 300, '2024-01-16');

-- Situation Financière
INSERT INTO situation_financiere(solde, details, date_situation)
VALUES
  (15000, 'Solde début janvier', '2024-01-01');

-- Notifications
INSERT INTO notification(utilisateur_id, message, date_notification, lu)
VALUES
  (1, 'Rendez-vous demain à 10h', '2024-01-14 08:00:00', FALSE),
  (2, 'Facture en attente', '2024-01-16 09:00:00', FALSE);

-- Statistiques
INSERT INTO statistique(type, valeur, date_stat)
VALUES
  ('Consultations', 10, '2024-01-15'),
  ('Revenus', 2300, '2024-01-16');

-- Agenda
INSERT INTO agenda(medecin_id, date_agenda, details)
VALUES
  (1, '2024-01-17', 'Consultation Benali'),
  (1, '2024-01-18', 'Consultation El Amrani');
