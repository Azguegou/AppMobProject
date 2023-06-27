package com.example.tp1a;

public class User {
    private String id;
    private String username;
    private String password;
    private int score;

    public User() {
        // Constructeur par défaut requis pour la désérialisation
    }

    public User(String id, String username, String password, int score) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}