package com.bloodcare.bloodcare;

public class User {
    private int id;
    private String username;
    private String passwordHash;
    private String fullName;
    private String role; 

    public User() {}
    public User(int id, String username, String fullName, String role) {
        this.id = id; this.username = username; this.fullName = fullName; this.role = role;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; } // Huruf 'U' besar
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return passwordHash; }
    public void setPassword(String password) { this.passwordHash = password; }

    public String getFullName() { return fullName; } // Huruf 'F' dan 'N' besar
    public void setFullName(String fullname) { this.fullName = fullname; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
