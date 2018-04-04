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
import android.widget.TextView;
import com.example.johnyang.databaseassignment.config.DatabaseHelper;
import com.example.johnyang.databaseassignment.config.Middleware;
import com.example.johnyang.databaseassignment.model.User;

public class MainActivity extends Activity {

    DatabaseHelper db;
    User user;
    Button loginButton;
    EditText emailTextView;
    EditText passwordEditText;
    TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //clears database
        //this.getApplicationContext().deleteDatabase("androidassignmentTwoDatabase");

        //saves the user to middleware
        user = Middleware.getCurrentUser(getApplicationContext());

        if(user != null) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        //instantiating the variable to the widget
        db = new DatabaseHelper(getApplicationContext());
        loginButton = findViewById(R.id.button_login);
        emailTextView = findViewById(R.id.edit_text_email);
        passwordEditText = findViewById(R.id.edit_text_password);
        titleTextView = findViewById(R.id.text_view_login_title);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getting the email and password from the database
                user = db.login(emailTextView.getText().toString(), passwordEditText.getText().toString());
                //if user matches the database, brings to home activity or else it will show error message
                if(user != null) {
                    Middleware.setUser(user, getApplicationContext());
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    titleTextView.setText("Invalid Email or Password");
                }

            }
        });
    }
}