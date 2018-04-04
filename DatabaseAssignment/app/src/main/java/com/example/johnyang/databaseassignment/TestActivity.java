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
import com.example.johnyang.databaseassignment.config.DatabaseHelper;
import com.example.johnyang.databaseassignment.model.Test;


public class TestActivity extends Activity {

    EditText testidEditText;
    EditText patienidEditText;
    EditText bplEditText;
    EditText bphEditText;
    EditText temperatureEditText;
    Button homeButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //instantiating the database
        db = new DatabaseHelper(this.getApplicationContext());

        //binding the varibales to the widgets
        testidEditText = (EditText) findViewById(R.id.editText);
        patienidEditText = (EditText) findViewById(R.id.editText2);
        bplEditText = (EditText) findViewById(R.id.editText5);
        bphEditText= (EditText) findViewById(R.id.editText7);
        temperatureEditText = (EditText) findViewById(R.id.editText8);
        homeButton = (Button) findViewById(R.id.button);

        //when home button is click it will bring you to the home activity
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                //taking user input and storing it to the data base, table test
                Test test = new Test(testidEditText.getText().toString(), patienidEditText.getText().toString(), bplEditText.getText().toString(), bphEditText.getText().toString(), temperatureEditText.getText().toString());
                db.insertTest(test);
            }
        });





    }
}
