package com.example.tp1a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BD {
    private static final String URL = "jdbc:mysql://localhost/appmob";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie à la base de données !");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion à la base de données fermée !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertUser(String username, String password) {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                String query = "INSERT INTO user (username, password, score) VALUES (?, ?, 0)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.executeUpdate();
                System.out.println("Utilisateur inséré avec succès dans la base de données !");
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection(connection);
            }
        }
    }
}