/*
 * Assignment #2
 * Name: John Yang
 * Student ID:100941170
 * Professor: Ilir Dema
 */

package com.example.johnyang.databaseassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.johnyang.databaseassignment.config.DatabaseHelper;
import com.example.johnyang.databaseassignment.model.Patient;

import static android.widget.Toast.makeText;

public class PatientActivity extends Activity {

    EditText patientidEditText;
    EditText firstnameEditText;
    EditText lastnameEditText;
    EditText departmentEditText;
    EditText doctoridEditText;
    EditText roomEditText;

    Button nextButton;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        //binding the varibales to the widgets
        patientidEditText = (EditText) findViewById(R.id.editText6);
        doctoridEditText = (EditText) findViewById(R.id.editText10);
        firstnameEditText = (EditText) findViewById(R.id.editText11);
        lastnameEditText = (EditText) findViewById(R.id.editText12);
        departmentEditText = (EditText) findViewById(R.id.editText13);
        roomEditText = (EditText) findViewById(R.id.editText14);

        nextButton = (Button) findViewById(R.id.button2);
        //instantiating database
        db = new DatabaseHelper(this.getApplicationContext());

        //when next button is clicked it will bring you to the test activity
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity.this, TestActivity.class);
                startActivity(intent);
                finish();

                //taking user input and storing it to the data base, table patient
                Patient patient = new Patient(patientidEditText.getText().toString(), firstnameEditText.getText().toString(), lastnameEditText.getText().toString(), departmentEditText.getText().toString(), doctoridEditText.getText().toString(), roomEditText.getText().toString());
                db.insertPatient(patient);
            }
        });


    }
}
