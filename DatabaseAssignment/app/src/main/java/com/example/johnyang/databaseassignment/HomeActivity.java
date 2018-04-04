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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.johnyang.databaseassignment.config.DatabaseHelper;
import com.example.johnyang.databaseassignment.config.Middleware;
import com.example.johnyang.databaseassignment.model.Patient;
import com.example.johnyang.databaseassignment.model.User;
import java.util.List;

public class HomeActivity extends Activity {

    User user;
    TextView greetingTextView;
    Button addButton;
    ListView patientList;
    List<Patient> patientlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //To logout out user PS. this will crash the app
        //Middleware.removeUser(getApplicationContext());

        user = Middleware.getCurrentUser(getApplicationContext());

        //Output user information
        greetingTextView = (TextView) findViewById(R.id.text_view_greeting);
        addButton = (Button) findViewById(R.id.button3);
        greetingTextView.setText("Welcome " + user.getEmail() + ". Your role is " + user.getRole());

        //instantiating the database
        DatabaseHelper dbHelper =  new DatabaseHelper(this.getApplicationContext());
        patientlist = dbHelper.getAllPatient();
        //storing the patientlist in array and bind it to the adapter
        ArrayAdapter<Patient> adapter = new ArrayAdapter<Patient>(this, android.R.layout.simple_list_item_1, patientlist);
        patientList = (ListView) findViewById(R.id.list_view);
        patientList.setAdapter(adapter);

        //if user selects one of the list it will bring to the display activity with the patient info
        patientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeActivity.this, DisplayActivity.class);
                intent.putExtra("selectedPatient", patientlist.get(i));
                startActivity(intent);

            }
        });

        //brings you to the patient activity
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PatientActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }



}


