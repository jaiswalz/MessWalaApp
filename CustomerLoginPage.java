package com.messwalaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.messwalaapp.dao.DatabaseHelper;
import com.messwalaapp.services.Session;

import java.util.HashMap;


public class CustomerLoginPage extends AppCompatActivity implements TextWatcher,CompoundButton.OnCheckedChangeListener{
    //Assigning DatabaseHelper and Session objects
    DatabaseHelper helper = new DatabaseHelper(this);
    private Session session ;


    public EditText lemail, lpass;
    public CheckBox lcheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        //creating session object
        session= new Session(this);
        lemail = (EditText) findViewById(R.id.userName);
        lpass = (EditText) findViewById(R.id.Password);
        lcheck = (CheckBox) findViewById(R.id.checkBox);
        //checkong login status
        Toast.makeText(CustomerLoginPage.this, "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

    }
    //when customer clicks the login button
    public void onLoginClick(View v) {
        if (v.getId() == R.id.LogIn) {

            //converting inputs to string
            String lemailstr = lemail.getText().toString();
            String lpassstr = lpass.getText().toString();

            //passing email to helper class to search and return password
            String lpassstr1 = helper.searchpass(lemailstr);

            //matching password and its length
            if (lpassstr.equals(lpassstr1)) {

                //creating session

                session.createLoginSession(lpassstr,lemailstr,lcheck.isChecked());

                //if remember me is not checked then set fields empty else set fields with user data(currently not working)
                if(!lcheck.isChecked()){
                    lemail.setText("");
                    lpass.setText("");
                    lcheck.setChecked(false);
                }

               // using hashmap to bring data from shared preferences
                else {
                    HashMap<String, String> user = session.getUserDetails();

                    // name
                    String email = user.get(Session.KEY_EMAIL);

                    // email
                    String pass = user.get(Session.KEY_PASSWORD);

                    // setting user data
                    lpass.setText(pass);
                    lemail.setText(email);
                    lcheck.setChecked(true);
                //using text change listner if remember me is changed
                    lemail.addTextChangedListener(this);
                    lpass.addTextChangedListener(this);
                    lcheck.setOnCheckedChangeListener(this);

                }

                // Staring Home Activity if Login
                Intent i = new Intent(CustomerLoginPage.this, HomePage.class);
                startActivity(i);
                finish();
            }
            //if password is incorrect or short

                else
                {
                Toast msg = Toast.makeText(CustomerLoginPage.this, "Username and Password Not Matched", Toast.LENGTH_SHORT);
                msg.show();
            }
        }
    }

//for change in remember me value the fields change accordingly
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        session.managePrefs(lcheck.isChecked());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        session.managePrefs(lcheck.isChecked());
    }
}