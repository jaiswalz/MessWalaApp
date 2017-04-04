package com.messwalaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.messwalaapp.dao.DatabaseHelper;
import com.messwalaapp.services.Session;

public class MainPage extends AppCompatActivity {
    DatabaseHelper signindb;
    Intent i;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        signindb = new DatabaseHelper(this);
        session=new Session(this);
        // if user is currently logged in.It takes them to home page.
        if (session.isLoggedIn())
        {
            Intent i = new Intent(MainPage.this, HomePage.class);
            startActivity(i);
            finish();
        }
    }

    // Main page with 3 different buttons Admin Login , Customer Login and Sign Up
        public void onButtonClick(View v)
    {
        if (v.getId()==R.id.AdminLogin) {
            i = new Intent(MainPage.this, AdminLoginpage.class);
            startActivity(i);
        }
        if (v.getId()==R.id.CustomerLogin) {
            i = new Intent(MainPage.this, CustomerLoginPage.class);
            startActivity(i);
        }
        if (v.getId()==R.id.SignUp) {
            i = new Intent(MainPage.this, CustomerSignInPage.class);
            startActivity(i);
        }
            }
    }

