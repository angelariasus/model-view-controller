# Model-View-Controller

This project implements a **Project Management System** following the **Model-View-Controller (MVC)** architectural pattern. It was developed in Java using Maven for dependency management and Oracle Database as the persistence engine.

The system was designed as part of the **Information Systems Design** course, with the goal of applying the principles of modularity, separation of concerns, and object-oriented programming, resulting in a clean, scalable, and maintainable solution.

---

## Objectives
- Apply the MVC architecture in a real-world case study.
- Separate data persistence, business logic, and user interface into independent layers.
- Implement a complete flow for managing users, projects, and tasks.
- Strengthen knowledge of designing and implementing systems with conceptual, logical, and physical database models.

## System Architecture
The system follows the **MVC design pattern**, with each layer having a clearly defined responsibility:
- View → Console-based interface (`ConsoleMenu.java`) for user interaction.
- Controller → Orchestrates actions between View and DAO.
- DAO (Data Access Object) → Manages data access using JDBC and Oracle.
- Model → Represents entities such as `User`, `Project`, and `Task`.
- Util → Shared utilities like database connection handling (`DBConnection.java`).

## Database Setup 
Run the following script in Oracle SQL Developer or SQL*Plus to create the database schema:
```sql
CREATE TABLE users (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    role VARCHAR2(50) NOT NULL
);

CREATE TABLE projects (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    description VARCHAR2(255),
    start_date DATE,
    end_date DATE,
    manager_id NUMBER,
    CONSTRAINT fk_manager FOREIGN KEY (manager_id) REFERENCES users(id)
);

CREATE TABLE tasks (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    description VARCHAR2(255),
    status VARCHAR2(50),
    project_id NUMBER,
    assigned_to NUMBER,
    CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES projects(id),
    CONSTRAINT fk_assigned_user FOREIGN KEY (assigned_to) REFERENCES users(id)
);

CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE project_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE task_seq START WITH 1 INCREMENT BY 1;
```

## Setup
1. Clone the repository
```bash
git clone https://github.com/angelariasus/project-management-system
cd project-management-system
```
2. Configure Oracle connection
Edit the file `src/main/java/com/system/util/DBConnection.java`:
```java
private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
private static final String USER = "user";
private static final String PASSWORD = "java";
```

Make sure you have the Oracle JDBC driver (`ojdbc11.jar`) installed and configured in Maven or in your local Maven repository.

## Build and Run
To build and run the system in console mode:
```bash
mvn clean compile exec:java -Dexec.mainClass="com.system.App"
```

## Key Features
- User Management: Create, list, update, and delete users.
- Project Management: Manage projects and assign managers.
- Task Management: Assign tasks to users, track status, and link to projects.
- Console-based interface for direct interaction without a GUI.

## Learning Outcomes
- Practical implementation of the MVC architecture in Java.
- Integration of Oracle Database with Java using JDBC.
- Experience managing a system with multiple entities and relationships.
- Development of a clean, modular, and maintainable project structure.

Project developed by **angelariasus**