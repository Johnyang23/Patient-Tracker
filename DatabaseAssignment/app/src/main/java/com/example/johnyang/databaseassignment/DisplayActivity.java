/*
 * Assignment #2
 * Name: John Yang
 * Student ID:100941170
 * Professor: Ilir Dema
 */

package com.example.johnyang.databaseassignment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.johnyang.databaseassignment.model.Patient;


public class DisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        //displaying patient info
        Patient patient = (Patient)getIntent().getExtras().get("selectedPatient");

        TextView patientid = (TextView) findViewById(R.id.textView2);
        patientid.setText("Patient ID: " + patient.getId());

        TextView doctorid = (TextView) findViewById(R.id.textView8);
        doctorid.setText("Doctor ID: " + patient.getDoctorid());

        TextView firstname = (TextView) findViewById(R.id.textView);
        firstname.setText("First Name: " + patient.getFirstname());

        TextView lastname = (TextView) findViewById(R.id.textView15);
        lastname.setText("Last Name: " + patient.getLastname());

        TextView department = (TextView) findViewById(R.id.textView16);
        department.setText("Department: " + patient.getDepartment());

        TextView room = (TextView) findViewById(R.id.textView17);
        room.setText("Room: " + patient.getRoom());



    }
}
