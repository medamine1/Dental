-- FULL SCHEMA (users/staff/medecin/secretaire + roles ...
-- ENUMS
DO $$ BEGIN
    CREATE TYPE sexe_enum AS ENUM ('HOMME', 'FEMME', 'AUTRE');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

CREATE TABLE IF NOT EXISTS utilisateur (
  id BIGSERIAL PRIMARY KEY,
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  last_modification_date TIMESTAMP NULL,
  created_by VARCHAR(64),
  updated_by VARCHAR(64),
  nom VARCHAR(120) NOT NULL,
  prenom VARCHAR(120) NOT NULL,
  email VARCHAR(160) NOT NULL UNIQUE,
  tel VARCHAR(40),
  adresse VARCHAR(255),
  cin VARCHAR(32) UNIQUE,
  sexe sexe_enum DEFAULT 'AUTRE',
  login VARCHAR(64) NOT NULL UNIQUE,
  password_hash VARCHAR(120) NOT NULL,
  last_login_date TIMESTAMP NULL,
  date_naissance DATE NULL,
  actif BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS staff (
  id BIGINT PRIMARY KEY,
  salaire DECIMAL(12,2) DEFAULT 0,
  prime DECIMAL(12,2) DEFAULT 0,
  date_recrutement DATE,
  solde_conge INT DEFAULT 0,
  CONSTRAINT fk_staff_user FOREIGN KEY (id) REFERENCES utilisateur(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS medecin (
  id BIGINT PRIMARY KEY,
  specialite VARCHAR(120),
  agenda_medecin TEXT,
  CONSTRAINT fk_med_staff FOREIGN KEY (id) REFERENCES staff(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS secretaire (
  id BIGINT PRIMARY KEY,
  num_cnss VARCHAR(64),
  commission DECIMAL(12,2) DEFAULT 0,
  CONSTRAINT fk_sec_staff FOREIGN KEY (id) REFERENCES staff(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS role (
  id BIGSERIAL PRIMARY KEY,
  libelle VARCHAR(80) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS utilisateur_role (
  utilisateur_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (utilisateur_id, role_id),
  CONSTRAINT fk_ur_user FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id) ON DELETE CASCADE,
  CONSTRAINT fk_ur_role FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS assurance (
  id BIGSERIAL PRIMARY KEY,
  nom VARCHAR(120) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS patient (
  id BIGSERIAL PRIMARY KEY,
  nom VARCHAR(120) NOT NULL,
  prenom VARCHAR(120) NOT NULL,
  date_naissance DATE,
  sexe sexe_enum DEFAULT 'AUTRE',
  adresse VARCHAR(255),
  tel VARCHAR(40),
  email VARCHAR(160),
  num_assurance VARCHAR(64),
  assurance_id BIGINT,
  actif BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT fk_patient_assurance FOREIGN KEY (assurance_id) REFERENCES assurance(id)
);

CREATE TABLE IF NOT EXISTS dossier_medical (
  id BIGSERIAL PRIMARY KEY,
  patient_id BIGINT NOT NULL,
  notes TEXT,
  date_ouverture DATE,
  CONSTRAINT fk_dm_patient FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS consultation (
  id BIGSERIAL PRIMARY KEY,
  patient_id BIGINT NOT NULL,
  medecin_id BIGINT NOT NULL,
  date_consultation TIMESTAMP NOT NULL,
  motif VARCHAR(255),
  notes TEXT,
  CONSTRAINT fk_consult_patient FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
  CONSTRAINT fk_consult_medecin FOREIGN KEY (medecin_id) REFERENCES medecin(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS acte (
  id BIGSERIAL PRIMARY KEY,
  libelle VARCHAR(120) NOT NULL,
  prix DECIMAL(12,2) NOT NULL,
  categorie VARCHAR(64),
  en_promo BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS consultation_acte (
  consultation_id BIGINT NOT NULL,
  acte_id BIGINT NOT NULL,
  PRIMARY KEY (consultation_id, acte_id),
  CONSTRAINT fk_ca_consult FOREIGN KEY (consultation_id) REFERENCES consultation(id) ON DELETE CASCADE,
  CONSTRAINT fk_ca_acte FOREIGN KEY (acte_id) REFERENCES acte(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ordonnance (
  id BIGSERIAL PRIMARY KEY,
  patient_id BIGINT NOT NULL,
  medecin_id BIGINT NOT NULL,
  date_ordonnance TIMESTAMP NOT NULL,
  details TEXT,
  CONSTRAINT fk_ord_patient FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
  CONSTRAINT fk_ord_medecin FOREIGN KEY (medecin_id) REFERENCES medecin(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS medicament (
  id BIGSERIAL PRIMARY KEY,
  nom VARCHAR(120) NOT NULL,
  description TEXT,
  forme VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS prescription (
  id BIGSERIAL PRIMARY KEY,
  ordonnance_id BIGINT NOT NULL,
  medicament_id BIGINT NOT NULL,
  posologie VARCHAR(255),
  CONSTRAINT fk_presc_ord FOREIGN KEY (ordonnance_id) REFERENCES ordonnance(id) ON DELETE CASCADE,
  CONSTRAINT fk_presc_med FOREIGN KEY (medicament_id) REFERENCES medicament(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS facture (
  id BIGSERIAL PRIMARY KEY,
  patient_id BIGINT NOT NULL,
  date_facture TIMESTAMP NOT NULL,
  montant DECIMAL(12,2) NOT NULL,
  statut VARCHAR(32),
  CONSTRAINT fk_fact_patient FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS article (
  id BIGSERIAL PRIMARY KEY,
  nom VARCHAR(120) NOT NULL,
  prix DECIMAL(12,2) NOT NULL,
  categorie VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS charges (
  id BIGSERIAL PRIMARY KEY,
  description VARCHAR(255),
  montant DECIMAL(12,2) NOT NULL,
  date_charge DATE
);

CREATE TABLE IF NOT EXISTS revenue (
  id BIGSERIAL PRIMARY KEY,
  source VARCHAR(120),
  montant DECIMAL(12,2) NOT NULL,
  date_revenue DATE
);

CREATE TABLE IF NOT EXISTS situation_financiere (
  id BIGSERIAL PRIMARY KEY,
  solde DECIMAL(12,2) NOT NULL,
  details TEXT,
  date_situation DATE
);

CREATE TABLE IF NOT EXISTS notification (
  id BIGSERIAL PRIMARY KEY,
  utilisateur_id BIGINT,
  message TEXT,
  date_notification TIMESTAMP,
  lu BOOLEAN DEFAULT FALSE,
  CONSTRAINT fk_notif_user FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS statistique (
  id BIGSERIAL PRIMARY KEY,
  type VARCHAR(64),
  valeur DECIMAL(12,2),
  date_stat DATE
);

CREATE TABLE IF NOT EXISTS agenda (
  id BIGSERIAL PRIMARY KEY,
  medecin_id BIGINT NOT NULL,
  date_agenda DATE NOT NULL,
  details TEXT,
  CONSTRAINT fk_agenda_med FOREIGN KEY (medecin_id) REFERENCES medecin(id) ON DELETE CASCADE
);
