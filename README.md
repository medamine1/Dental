# Cabinet Dentaire Management System

## Overview
A Java MVC application for managing a dental clinic, following best practices and a layered architecture.

## Structure
- `common/`, `entities/`, `enums/`, `repository/`, `service/`, `mvc/`, `security/`, `config/`, `resources/`, `test/`
- DTOs for all main entities in `mvc/dto/`
- UI/view placeholders in `mvc/ui/`
- Basic security in `security/`

## Build & Run
- Requires Java 17+ and Maven.
- To build: `mvn clean install`
- To run: `mvn exec:java -Dexec.mainClass="ma.emsi.MainApp"`

## Security
- Simple username/password authentication and role-based access in `security/`
- Example users: `admin/admin123` (ADMIN), `user/user123` (USER)

## Testing
- Sample tests in `test/java/ma/emsi/` (see `PatientServiceTest.java`, `MedecinServiceTest.java`)
- Run tests: `mvn test`

## Improvements
- Improved package naming and separation.
- Used DTOs for controller/view communication.
- Added UI/view and security scaffolding.
- Documented all changes in this README.

## Author
- Adapted and improved from professor's structure.
