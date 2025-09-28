package com.system.controller;

import com.system.dao.ProjectDAO;
import com.system.model.Project;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ProjectController {

    private ProjectDAO projectDAO;

    public ProjectController() {
        this.projectDAO = new ProjectDAO();
    }

    public void createProject(String name, String description, LocalDate startDate, LocalDate endDate, long managerId) {
        try {
            Project project = new Project();
            project.setName(name);
            project.setDescription(description);
            project.setStartDate(startDate);
            project.setEndDate(endDate);
            project.setManagerId(managerId);

            projectDAO.addProject(project);
            System.out.println("Project created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating project: " + e.getMessage());
        }
    }

    public void listProjects() {
        try {
            List<Project> projects = projectDAO.getAllProjects();
            if (projects.isEmpty()) {
                System.out.println("No projects found.");
                return;
            }

            System.out.println("\n--- Projects ---");
            for (Project p : projects) {
                System.out.printf("[%d] %s | %s - %s%n", 
                                  p.getId(), 
                                  p.getName(), 
                                  p.getStartDate(), 
                                  p.getEndDate());
            }
        } catch (SQLException e) {
            System.err.println("Error fetching projects: " + e.getMessage());
        }
    }

    public void updateProject(long id, String name, String description, LocalDate startDate, LocalDate endDate, long managerId) {
        try {
            Project project = new Project();
            project.setId(id);
            project.setName(name);
            project.setDescription(description);
            project.setStartDate(startDate);
            project.setEndDate(endDate);
            project.setManagerId(managerId);

            projectDAO.updateProject(project);
            System.out.println("Project updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error updating project: " + e.getMessage());
        }
    }

    public void deleteProject(long id) {
        try {
            projectDAO.deleteProject(id);
            System.out.println("Project deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting project: " + e.getMessage());
        }
    }
}
