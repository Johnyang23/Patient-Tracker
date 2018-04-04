package com.example.johnyang.databaseassignment.model;

import java.io.Serializable;

/**
 * Created by johnyang on 2017-11-22.
 */
/*
 * Assignment #2
 * Name: John Yang
 * Student ID:100941170
 * Professor: Ilir Dema
 */

public class Test implements Serializable {
    String id;
    String patientid;
    String bpl;
    String bph;
    String temperature;

    //constructors
    public Test () {

    }

    public Test (String id, String patientid, String bpl, String bph, String temperature) {
        this.id = id;
        this.patientid = patientid;
        this.bpl = bpl;
        this.bph = bph;
        this.temperature = temperature;
    }

    //getters
    public String getId () {
        return id;
    }

    public String getPatientid () {
        return patientid;
    }

    public String getBpl() {return bpl;}

    public String getBph() {
        return bph;
    }

    public String getTemperature() {
        return temperature;
    }

    //setters
    public void setId (String id) { this.id = id; }

    public void setPatientid (String patientid) { this.patientid = patientid; }

    public void setBpl (String bpl) { this.bpl = bpl; }

    public void setBph (String bph) { this.bph = bph; }

    public void setTemperature (String temperature){ this.temperature = temperature; }



}
