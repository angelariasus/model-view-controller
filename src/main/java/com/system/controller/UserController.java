package com.system.controller;

import com.system.dao.UserDAO;
import com.system.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserController {

    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public void createUser(String name, String email, String role) {
        try {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setRole(role);
            userDAO.addUser(user);
            System.out.println("User created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
        }
    }

    public void listUsers() {
        try {
            List<User> users = userDAO.getAllUsers();
            if (users.isEmpty()) {
                System.out.println("No users found.");
                return;
            }

            System.out.println("\n--- Users ---");
            for (User u : users) {
                System.out.printf("[%d] %s - %s (%s)%n", u.getId(), u.getName(), u.getEmail(), u.getRole());
            }
        } catch (SQLException e) {
            System.err.println("Error fetching users: " + e.getMessage());
        }
    }

    public void updateUser(long id, String name, String email, String role) {
        try {
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setEmail(email);
            user.setRole(role);

            userDAO.updateUser(user);
            System.out.println("User updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
        }
    }

    public void deleteUser(long id) {
        try {
            userDAO.deleteUser(id);
            System.out.println("User deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }
}
