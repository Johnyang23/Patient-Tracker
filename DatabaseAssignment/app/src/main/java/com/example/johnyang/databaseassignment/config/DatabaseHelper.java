/*
 * Assignment #2
 * Name: John Yang
 * Student ID:100941170
 * Professor: Ilir Dema
 */


package com.example.johnyang.databaseassignment.config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.johnyang.databaseassignment.model.Patient;
import com.example.johnyang.databaseassignment.model.Test;
import com.example.johnyang.databaseassignment.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnyang on 2017-12-01.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat Tag
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "androidassignmentTwoDatabase";

    // Table Names
    // Authentication Tables
    private static final String TABLE_USER = "users";
    // Object Tables
    private static final String TABLE_PATIENT = "patients";
    private static final String TABLE_TEST = "tests";

    // Common Column Names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_DEPARTMENT = "department";

    // Authentication Column Names
    // User Table - Column Names
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ROLE = "role";

    // PATIENTS Table - Column Names
    private static final String COLUMN_DOCTOR_ID = "doctor_id";
    private static final String COLUMN_ROOM = "room";

    // TESTS Table - Column Names
    private static final String COLUMN_PATIENT_ID = "patient_id";
    private static final String COLUMN_BPL = "bpl";
    private static final String COLUMN_BPH = "bph";
    private static final String COLUMN_TEMPERATURE = "temperature";

    // Table Create Statements
    // User Table SQL Create Statement
    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USER + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_ROLE + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_PASSWORD + " TEXT,"
                    + COLUMN_FIRST_NAME + " TEXT,"
                    + COLUMN_LAST_NAME + " TEXT,"
                    + COLUMN_DEPARTMENT + " TEXT);";

    // Patient Table SQL Create Statement
    private static final String CREATE_TABLE_PATIENTS =
            "CREATE TABLE " + TABLE_PATIENT + "("
                    + COLUMN_ID + " TEXT PRIMARY KEY,"
                    + COLUMN_DOCTOR_ID + " TEXT,"
                    + COLUMN_FIRST_NAME + " TEXT,"
                    + COLUMN_LAST_NAME + " TEXT,"
                    + COLUMN_DEPARTMENT + " TEXT,"
                    + COLUMN_ROOM + " TEXT);";

    // Test Table SQL Create Statement
    private static final String CREATE_TABLE_TESTS =
            "CREATE TABLE " + TABLE_TEST + "("
                    + COLUMN_ID + " TEXT PRIMARY KEY,"
                    + COLUMN_PATIENT_ID + " TEXT,"
                    + COLUMN_BPL + " TEXT,"
                    + COLUMN_BPH + " TEXT,"
                    + COLUMN_TEMPERATURE + " TEXT);";


    private long insertUser(SQLiteDatabase db, String email, String role, String password, String firstName, String lastName, String department) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_ROLE, role);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_FIRST_NAME, firstName);
        values.put(COLUMN_LAST_NAME, lastName);
        values.put(COLUMN_DEPARTMENT, department);

        return db.insert(TABLE_USER, null, values);
    }

    public User login(String email, String password) {
        //Initialize userID
        Cursor c;
        User user = null;

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            //Store the sql query for selecting the User Record
            String selectQuery =
                    "SELECT * FROM " + TABLE_USER
                            + " WHERE " + COLUMN_EMAIL + " = \"" + email
                            + "\" AND " + COLUMN_PASSWORD + " = \"" + password + "\";";

            //Create a Cursor to store the Record and execute the Query stored in selectQuery
            c = db.rawQuery(selectQuery, null);


            if(c == null) {
                return null;
            }

            c.moveToFirst();

            //Set userID to the id of the User Record
            user = new User(
                    c.getString(c.getColumnIndex(COLUMN_ID)),
                    c.getString(c.getColumnIndex(COLUMN_EMAIL)),
                    c.getString(c.getColumnIndex(COLUMN_FIRST_NAME)),
                    c.getString(c.getColumnIndex(COLUMN_LAST_NAME)),
                    c.getString(c.getColumnIndex(COLUMN_DEPARTMENT)),
                    c.getString(c.getColumnIndex(COLUMN_ROLE))
            );

            return user;
        } catch(Exception e) {
            return null;
        }

    }

    public void dropTables() {

        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create Tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_PATIENTS);
        db.execSQL(CREATE_TABLE_TESTS);

        //Insert Users
        // Insert Doctor Demo User Record
        long doctorID = insertUser(db, "doctor@doctor.com", "doctor", "doctor", "Doc", "Doe", "Emergency");
        // Insert Nurse Demo User Record
        long nurseID = insertUser(db, "nurse@nurse.com", "nurse", "nurse", "Nurse", "Doe", "Emergency");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop Tables
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);
    }

    //insert patient record
    public void insertPatient(Patient patient){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, patient.getId());
        values.put(COLUMN_DOCTOR_ID, patient.getDoctorid());
        values.put(COLUMN_FIRST_NAME, patient.getFirstname());
        values.put(COLUMN_LAST_NAME, patient.getLastname());
        values.put(COLUMN_DEPARTMENT, patient.getDepartment());
        values.put(COLUMN_ROOM, patient.getRoom());

        db.insert(TABLE_PATIENT, null, values);
        db.close();

    }

    //insert test record
    public void insertTest (Test test){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, test.getId());
        values.put(COLUMN_PATIENT_ID, test.getPatientid());
        values.put(COLUMN_BPL, test.getBpl());
        values.put(COLUMN_BPH, test.getBph());
        values.put(COLUMN_TEMPERATURE, test.getTemperature());

        db.insert(TABLE_TEST, null, values);
        db.close();
    }

    //getting all patients
    public List<Patient> getAllPatient(){
        List<Patient> patientList = new ArrayList<Patient>();

        String selectQuery = "SELECT * FROM " + TABLE_PATIENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Patient patient = new Patient();
                patient.setId(cursor.getString(0));
                patient.setDoctorid(cursor.getString(1));
                patient.setFirstname(cursor.getString(2));
                patient.setLastname(cursor.getString(3));
                patient.setDepartment(cursor.getString(4));
                patient.setRoom(cursor.getString(5));

                patientList.add(patient);

            } while (cursor.moveToNext());
        }

        return patientList;
    }

    //getting all tests
    public List<Test> getAllTest(){
        List<Test> testList = new ArrayList<Test>();

        String selectQuery = "SELECT * FROM " + TABLE_TEST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            Test test = new Test();
            test.setId(cursor.getString(0));
            test.setPatientid(cursor.getString(1));
            test.setBpl(cursor.getString(2));
            test.setBph(cursor.getString(3));
            test.setTemperature(cursor.getString(4));

            testList.add(test);

        } while (cursor.moveToNext());

        return testList;
    }

}