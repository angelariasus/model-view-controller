package com.system.view;

import com.system.controller.UserController;
import com.system.controller.ProjectController;
import com.system.controller.TaskController;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuView {

    private final Scanner scanner;
    private final UserController userController;
    private final ProjectController projectController;
    private final TaskController taskController;

    public ConsoleMenu() {
        this.scanner = new Scanner(System.in);
        this.userController = new UserController();
        this.projectController = new ProjectController();
        this.taskController = new TaskController();
    }

    public void start() {
        int option;
        do {
            System.out.println("\n==== PROJECT MANAGEMENT SYSTEM ====");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Projects");
            System.out.println("3. Manage Tasks");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (option) {
                case 1 -> userMenu();
                case 2 -> projectMenu();
                case 3 -> taskMenu();
                case 0 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid option, try again.");
            }
        } while (option != 0);
    }

    // -------------------- USERS --------------------
    private void userMenu() {
        int option;
        do {
            System.out.println("\n--- USER MANAGEMENT ---");
            System.out.println("1. Add User");
            System.out.println("2. List Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> addUser();
                case 2 -> userController.listUsers();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 0 -> System.out.println("Returning...");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 0);
    }

    private void addUser() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Role (Admin/Manager/Member): ");
        String role = scanner.nextLine();

        userController.createUser(name, email, role);
    }

    private void updateUser() {
        System.out.print("User ID to update: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("New Name: ");
        String name = scanner.nextLine();
        System.out.print("New Email: ");
        String email = scanner.nextLine();
        System.out.print("New Role: ");
        String role = scanner.nextLine();

        userController.updateUser(id, name, email, role);
    }

    private void deleteUser() {
        System.out.print("User ID to delete: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        userController.deleteUser(id);
    }

    // -------------------- PROJECTS --------------------
    private void projectMenu() {
        int option;
        do {
            System.out.println("\n--- PROJECT MANAGEMENT ---");
            System.out.println("1. Add Project");
            System.out.println("2. List Projects");
            System.out.println("3. Update Project");
            System.out.println("4. Delete Project");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> addProject();
                case 2 -> projectController.listProjects();
                case 3 -> updateProject();
                case 4 -> deleteProject();
                case 0 -> System.out.println("Returning...");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 0);
    }

    private void addProject() {
        System.out.print("Project Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Start Date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("End Date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Manager ID: ");
        long managerId = scanner.nextLong();
        scanner.nextLine();

        projectController.createProject(name, description, startDate, endDate, managerId);
    }

    private void updateProject() {
        System.out.print("Project ID to update: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("New Name: ");
        String name = scanner.nextLine();
        System.out.print("New Description: ");
        String description = scanner.nextLine();
        System.out.print("New Start Date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("New End Date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        System.out.print("New Manager ID: ");
        long managerId = scanner.nextLong();
        scanner.nextLine();

        projectController.updateProject(id, name, description, startDate, endDate, managerId);
    }

    private void deleteProject() {
        System.out.print("Project ID to delete: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        projectController.deleteProject(id);
    }

    // -------------------- TASKS --------------------
    private void taskMenu() {
        int option;
        do {
            System.out.println("\n--- TASK MANAGEMENT ---");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks by Project");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> addTask();
                case 2 -> listTasksByProject();
                case 3 -> updateTask();
                case 4 -> deleteTask();
                case 0 -> System.out.println("Returning...");
                default -> System.out.println("Invalid option.");
            }
        } while (option != 0);
    }

    private void addTask() {
        System.out.print("Task Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Status (Pending/In Progress/Completed): ");
        String status = scanner.nextLine();
        System.out.print("Project ID: ");
        long projectId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Assigned To (User ID): ");
        long assignedTo = scanner.nextLong();
        scanner.nextLine();

        taskController.createTask(name, description, status, projectId, assignedTo);
    }

    private void listTasksByProject() {
        System.out.print("Project ID: ");
        long projectId = scanner.nextLong();
        scanner.nextLine();

        taskController.listTasksByProject(projectId);
    }

    private void updateTask() {
        System.out.print("Task ID to update: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("New Name: ");
        String name = scanner.nextLine();
        System.out.print("New Description: ");
        String description = scanner.nextLine();
        System.out.print("New Status: ");
        String status = scanner.nextLine();
        System.out.print("New Project ID: ");
        long projectId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("New Assigned To (User ID): ");
        long assignedTo = scanner.nextLong();
        scanner.nextLine();

        taskController.updateTask(id, name, description, status, projectId, assignedTo);
    }

    private void deleteTask() {
        System.out.print("Task ID to delete: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        taskController.deleteTask(id);
    }
}
