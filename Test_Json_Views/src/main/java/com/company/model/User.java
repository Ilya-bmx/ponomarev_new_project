package com.company.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by ponomarev_ia on 29.08.2019.
 */

@Data
@Entity
@Table(name = "USER_TABLE", schema = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active;
    private String role;

    public User(String username, String password, boolean active, String role) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public User(Long id, String username, String password, boolean active, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
