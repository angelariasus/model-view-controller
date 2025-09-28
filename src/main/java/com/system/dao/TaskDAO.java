package com.system.dao;
import com.system.util.DBConnection;

import com.system.model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    // Create
    public void addTask(Task task) throws SQLException {
        String sql = "INSERT INTO TASK (ID, NAME, DESCRIPTION, STATUS, PROJECT_ID, ASSIGNED_TO) " +
                     "VALUES (SEQ_TASK.NEXTVAL, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getStatus());
            stmt.setLong(4, task.getProjectId());
            stmt.setLong(5, task.getAssignedTo());
            stmt.executeUpdate();
        }
    }

    // Read
    public List<Task> getTasksByProject(long projectId) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT ID, NAME, DESCRIPTION, STATUS, PROJECT_ID, ASSIGNED_TO FROM TASK WHERE PROJECT_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, projectId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getLong("ID"));
                task.setName(rs.getString("NAME"));
                task.setDescription(rs.getString("DESCRIPTION"));
                task.setStatus(rs.getString("STATUS"));
                task.setProjectId(rs.getLong("PROJECT_ID"));
                task.setAssignedTo(rs.getLong("ASSIGNED_TO"));
                tasks.add(task);
            }
        }
        return tasks;
    }

    // Update
    public void updateTask(Task task) throws SQLException {
        String sql = "UPDATE TASK SET NAME = ?, DESCRIPTION = ?, STATUS = ?, PROJECT_ID = ?, ASSIGNED_TO = ? WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getStatus());
            stmt.setLong(4, task.getProjectId());
            stmt.setLong(5, task.getAssignedTo());
            stmt.setLong(6, task.getId());
            stmt.executeUpdate();
        }
    }

    // Delete
    public void deleteTask(long id) throws SQLException {
        String sql = "DELETE FROM TASK WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
