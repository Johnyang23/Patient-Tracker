/*
 * Assignment #2
 * Name: John Yang
 * Student ID:100941170
 * Professor: Ilir Dema
 */

package com.example.johnyang.databaseassignment.config;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.johnyang.databaseassignment.model.User;

/**
 * Created by johnyang on 2017-12-01.
 */

public class Middleware {

    //The name for the shared preferences file that stores the user id
    private static final String PREFS_NAME = "ASSIGNMENT_TWO_USER_ID_PREF";

    //The name of the keys in the shared preference file that stores the user information
    private static final String KEY_USER_ID = "USER_ID";
    private static final String KEY_USER_EMAIL = "USER_EMAIL";
    private static final String KEY_USER_FIRST_NAME = "FIRST_NAME";
    private static final String KEY_USER_LAST_NAME = "LAST_NAME";
    private static final String KEY_USER_DEPARTMENT = "DEPARTMENT";
    private static final String KEY_USER_ROLE = "ROLE";

    //user that exsists
    public static User getCurrentUser(Context context) {
        User user;
        String userId;
        SharedPreferences sharedPrefs;

        sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        userId = sharedPrefs.getString(KEY_USER_ID, null);

        if(userId == null || userId.isEmpty() || userId.trim().toString() == "") {
            return null;
        }

        user = new User(
                userId,
                sharedPrefs.getString(KEY_USER_EMAIL, null),
                sharedPrefs.getString(KEY_USER_FIRST_NAME, null),
                sharedPrefs.getString(KEY_USER_LAST_NAME, null),
                sharedPrefs.getString(KEY_USER_DEPARTMENT, null),
                sharedPrefs.getString(KEY_USER_ROLE, null)
        );

        return user;
    }


    //new user
    public static void setUser(User user, Context context) {
        SharedPreferences sharedPref;
        SharedPreferences.Editor editor;

        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        editor.putString(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_EMAIL, user.getEmail());
        editor.putString(KEY_USER_FIRST_NAME, user.getFirstName());
        editor.putString(KEY_USER_LAST_NAME, user.getLastName());
        editor.putString(KEY_USER_DEPARTMENT, user.getDepartment());
        editor.putString(KEY_USER_ROLE, user.getRole());
        editor.commit();
    }

    //delete user
    public static void removeUser(Context context) {
        SharedPreferences sharedPref;
        SharedPreferences.Editor editor;

        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        editor.clear();
        editor.commit();
    }
}