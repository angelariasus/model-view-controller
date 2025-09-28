package com.system.controller;

import com.system.dao.TaskDAO;
import com.system.model.Task;

import java.sql.SQLException;
import java.util.List;

public class TaskController {

    private TaskDAO taskDAO;

    public TaskController() {
        this.taskDAO = new TaskDAO();
    }

    public void createTask(String name, String description, String status, long projectId, long assignedTo) {
        try {
            Task task = new Task();
            task.setName(name);
            task.setDescription(description);
            task.setStatus(status);
            task.setProjectId(projectId);
            task.setAssignedTo(assignedTo);

            taskDAO.addTask(task);
            System.out.println("Task created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating task: " + e.getMessage());
        }
    }

    public void listTasksByProject(long projectId) {
        try {
            List<Task> tasks = taskDAO.getTasksByProject(projectId);
            if (tasks.isEmpty()) {
                System.out.println("No tasks found for this project.");
                return;
            }

            System.out.println("\n--- Tasks for Project ID " + projectId + " ---");
            for (Task t : tasks) {
                System.out.printf("[%d] %s - %s (%s)%n", 
                                  t.getId(), 
                                  t.getName(), 
                                  t.getDescription(), 
                                  t.getStatus());
            }
        } catch (SQLException e) {
            System.err.println("Error fetching tasks: " + e.getMessage());
        }
    }

    public void updateTask(long id, String name, String description, String status, long projectId, long assignedTo) {
        try {
            Task task = new Task();
            task.setId(id);
            task.setName(name);
            task.setDescription(description);
            task.setStatus(status);
            task.setProjectId(projectId);
            task.setAssignedTo(assignedTo);

            taskDAO.updateTask(task);
            System.out.println("Task updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error updating task: " + e.getMessage());
        }
    }

    public void deleteTask(long id) {
        try {
            taskDAO.deleteTask(id);
            System.out.println("Task deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting task: " + e.getMessage());
        }
    }
}
