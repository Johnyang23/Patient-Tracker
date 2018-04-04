/*
 * Assignment #2
 * Name: John Yang
 * Student ID:100941170
 * Professor: Ilir Dema
 */

package com.example.johnyang.databaseassignment.model;

/**
 * Created by johnyang on 2017-12-01.
 */

public class User {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String department;
    private String role;

    public User(String id, String email, String firstName, String lastName, String department, String role) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartment() {
        return department;
    }

    public String getRole() {
        return role;
    }
}