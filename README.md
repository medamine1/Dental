# DentalTech - Cabinet Dentaire Management System

## Description
Application de gestion de cabinet dentaire développée en Java avec Swing, utilisant une architecture MVC et Spring pour l'injection de dépendances.

## Fonctionnalités
- **Gestion des patients**: Ajout, consultation et affichage des patients
- **Gestion des médecins**: Liste des médecins avec leurs spécialités
- **Gestion des consultations**: Planning et suivi des consultations quotidiennes
- **Interface graphique intuitive**: Interface Swing moderne et responsive

## Architecture
- **MVC Pattern**: Séparation claire entre Vue, Contrôleur et Modèle
- **Dependency Injection**: Utilisation de ApplicationContext pour la gestion des beans
- **File-based persistence**: Stockage des données dans des fichiers PSV
- **Modular design**: Architecture modulaire pour une maintenance facile

## Prérequis
- Java 23 ou supérieur
- Maven 3.6 ou supérieur

## Installation et Lancement

### Option 1: Lancement direct avec le script
```bash
# Double-cliquer sur le fichier lancer_app.bat
# Ou exécuter dans le terminal:
./lancer_app.bat
```

### Option 2: Lancement avec Maven
```bash
# Compiler le projet
mvn compile

# Lancer l'application
mvn exec:java -Dexec.mainClass="ma.dentalTech.MainApp"
```

### Option 3: Lancement avec le JAR
```bash
# Créer le JAR exécutable
mvn package

# Lancer l'application
java -jar target/DentalTech-1.0-SNAPSHOT-shaded.jar
```

## Configuration

### Base de données
Le projet est configuré pour MySQL:
- URL: `jdbc:mysql://localhost:3306/cabinet_dentaire`
- Utilisateur: `root`
- Mot de passe: (vide)

Pour modifier la configuration, éditez le fichier `src/main/resources/config/db.properties`.

### Identifiants de connexion
- Nom d'utilisateur: `admin`
- Mot de passe: `admin`

## Structure du projet
```
src/main/java/ma/dentalTech/
├── entities/           # Entités métier
├── mvc/
│   ├── controllers/    # Contrôleurs MVC
│   ├── dto/           # Data Transfer Objects
│   └── ui/            # Interfaces graphiques
├── repository/        # Accès aux données
├── service/           # Logique métier
└── conf/              # Configuration

src/main/resources/
├── config/            # Fichiers de configuration
├── fileBase/          # Données de démonstration
└── dataBase/          # Scripts SQL
```

## Modules

### Module Patient
- Entité: `Patient`
- Repository: `PatientRepositoryImpl` (file-based)
- Service: `PatientServiceImpl`
- Controller: `PatientControllerImpl` (Swing)
- Vue: `PatientView`

### Module Médecin
- Entité: `Medecin`
- Service: `MedecinServiceImpl` (données de démonstration)
- Controller: `MedecinControllerImpl` (Swing)
- Vue: `MedecinView`

### Module Consultation
- Entité: `Consultation`
- Service: `ConsultationServiceImpl` (données de démonstration)
- Controller: `ConsultationControllerImpl` (Swing)
- Vue: `ConsultationView`

## Tests
```bash
# Lancer les tests unitaires
mvn test

# Compiler et tester
mvn clean compile test
```

## Dépannage

### Problèmes courants
1. **Erreur de compilation**: Vérifiez que Java 23 est bien installé
2. **Erreur de connexion**: Vérifiez que MySQL est démarré et la base de données créée
3. **Interface ne s'affiche pas**: Vérifiez que les beans sont correctement configurés dans `beans.properties`

### Logs
Les erreurs sont affichées dans la console lors du démarrage de l'application.

## Développement

### Ajouter un nouveau module
1. Créer l'entité dans `entities/`
2. Créer le DTO dans `mvc/dto/`
3. Créer l'interface et l'implémentation du service dans `service/`
4. Créer l'interface et l'implémentation du contrôleur dans `mvc/controllers/`
5. Créer la vue dans `mvc/ui/`
6. Mettre à jour `beans.properties` et `ApplicationContext`

### Personnalisation
- Modifier les couleurs et polices dans les classes `*View`
- Ajouter de nouvelles fonctionnalités dans les services
- Étendre les entités pour ajouter des champs supplémentaires

## Auteur
Projet développé et complété pour être entièrement fonctionnel.
