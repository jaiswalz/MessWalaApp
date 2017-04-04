package com.messwalaapp.services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.messwalaapp.CustomerLoginPage;

import java.util.HashMap;

/**
 * Created by Aakash Jaiswal on 3/25/2017.
 */

public class Session {

    CustomerLoginPage key = new CustomerLoginPage();
    // Shared Preferences
    public SharedPreferences pref;

    // Editor for Shared preferences
    public SharedPreferences.Editor editor;

    // Context
    public Context _context;

    // Shared pref mode
    private int PRIVATE_MODE = 0;

    // Sharedpref file name
    public static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    public static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_PASSWORD = "pass";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // Remember Me Check Box(Make Variable public to access from outside)
    public static final String KEY_CHECK = "check";

    // Constructor
    public Session(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String pass, String email, Boolean check) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // Storing email in pref
        editor.putString(KEY_PASSWORD, pass);

        // Storing Remember me Value
        editor.putBoolean(KEY_CHECK, check);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, CustomerLoginPage.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }


    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, CustomerLoginPage.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


    public void managePrefs(boolean rem_userpass) {
        if (rem_userpass) {
            editor.putString(KEY_EMAIL, key.lemail.getText().toString().trim());
            editor.putString(KEY_PASSWORD, key.lpass.getText().toString().trim());
            editor.putBoolean(KEY_CHECK, true);
            editor.apply();
        } else {
            editor.putBoolean(KEY_CHECK, false);
            editor.remove(KEY_PASSWORD);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_EMAIL);//editor.putString(KEY_USERNAME, "");
            editor.apply();
        }
    }
}