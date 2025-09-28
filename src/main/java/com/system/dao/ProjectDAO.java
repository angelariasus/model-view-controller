package com.system.dao;
import com.system.util.DBConnection;

import com.system.model.Project;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    // Create
    public void addProject(Project project) throws SQLException {
        String sql = "INSERT INTO PROJECT (ID, NAME, DESCRIPTION, START_DATE, END_DATE, MANAGER_ID) " +
                     "VALUES (SEQ_PROJECT.NEXTVAL, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());
            stmt.setDate(3, Date.valueOf(project.getStartDate()));
            stmt.setDate(4, Date.valueOf(project.getEndDate()));
            stmt.setLong(5, project.getManagerId());
            stmt.executeUpdate();
        }
    }

    // Read
    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT ID, NAME, DESCRIPTION, START_DATE, END_DATE, MANAGER_ID FROM PROJECT";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getLong("ID"));
                project.setName(rs.getString("NAME"));
                project.setDescription(rs.getString("DESCRIPTION"));
                project.setStartDate(rs.getDate("START_DATE").toLocalDate());
                project.setEndDate(rs.getDate("END_DATE").toLocalDate());
                project.setManagerId(rs.getLong("MANAGER_ID"));
                projects.add(project);
            }
        }
        return projects;
    }

    // Update
    public void updateProject(Project project) throws SQLException {
        String sql = "UPDATE PROJECT SET NAME = ?, DESCRIPTION = ?, START_DATE = ?, END_DATE = ?, MANAGER_ID = ? WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());
            stmt.setDate(3, Date.valueOf(project.getStartDate()));
            stmt.setDate(4, Date.valueOf(project.getEndDate()));
            stmt.setLong(5, project.getManagerId());
            stmt.setLong(6, project.getId());
            stmt.executeUpdate();
        }
    }

    // Delete
    public void deleteProject(long id) throws SQLException {
        String sql = "DELETE FROM PROJECT WHERE ID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
