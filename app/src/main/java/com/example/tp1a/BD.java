package com.example.tp1a;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BD {
    private static final String URL = "jdbc:mysql://localhost:3306/appmob";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
                Log.d("BD","Connexion à la base de données fermée !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertUser(String username, String password) {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                String query = "INSERT INTO user (id, username, password, score) VALUES (null, ?, ?, 0)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.executeUpdate();
                Log.d("BD","Utilisateur inséré avec succès dans la base de données !");
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection(connection);
            }
        }
    }
}