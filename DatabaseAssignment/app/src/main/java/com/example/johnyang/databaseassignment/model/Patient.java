/*
 * Assignment #2
 * Name: John Yang
 * Student ID:100941170
 * Professor: Ilir Dema
 */

package com.example.johnyang.databaseassignment.model;

import java.io.Serializable;

/**
 * Created by johnyang on 2017-11-22.
 */

public class Patient implements Serializable {
    String id;
    String firstname;
    String lastname;
    String department;
    String doctorid;
    String room;

    //constructors
    public Patient () {

    }

    public Patient (String id, String firstname, String lastname, String department, String doctorid, String room){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.doctorid = doctorid;
        this.room = room;
    }

    //getters
    public String getId () {
        return id;
    }

    public String getFirstname () {
        return firstname;
    }

    public String getLastname () {
        return lastname;
    }

    public String getDepartment () {
        return department;
    }

    public String getDoctorid () {
        return doctorid;
    }

    public String getRoom () {
        return room;
    }

    //setters
    public void setId(String id){
        this.id = id;
    }

    public void setFirstname (String firstname){
        this.firstname = firstname;
    }

    public void setLastname (String lastname){
        this.lastname = lastname;
    }

    public void setDepartment (String department){
        this.department = department;
    }

    public void setDoctorid (String doctorid){this.doctorid = doctorid;}

    public void setRoom (String room){
        this.room = room;
    }

    @Override
    public String toString() {
        return  firstname + '\'' + lastname + '\'';
    }
}
