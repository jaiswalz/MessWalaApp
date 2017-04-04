package com.messwalaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.messwalaapp.bean.SignIn;
import com.messwalaapp.dao.DatabaseHelper;
import com.messwalaapp.services.Session;
import com.messwalaapp.services.validationfunction;

/**
 * Created by Aakash Jaiswal on 3/10/2017.
 */

public class CustomerSignInPage extends AppCompatActivity {
    //creating DatabaseHelper and Validation function Objects
    DatabaseHelper helper = new DatabaseHelper(this);
    validationfunction vhelper = new validationfunction();
    validationfunction vhelper1 = new validationfunction();
    private Session session ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signin_page);
        session=new Session(this);
    }

    public void onSignInClick(View v) {
        if (v.getId() == R.id.AdminSignIn) {

            //intializing Fileds
            EditText name = (EditText) findViewById(R.id.AdminName);
            EditText email = (EditText) findViewById(R.id.AdminEmail);
            EditText phone = (EditText) findViewById(R.id.AdminPhone);
            EditText pass = (EditText) findViewById(R.id.AdminPassword);
            EditText conpass = (EditText) findViewById(R.id.AdminConPassword);

            //Converting fields to string
            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String phonestr = phone.getText().toString();
            String passstr = pass.getText().toString();
            String conpassstr = conpass.getText().toString();

            //Passing email and password for Validation
            boolean Vemail = vhelper.emailValidator(emailstr);
            boolean Vpass = vhelper1.passwordValidator(passstr);
            //Matching Both Passwords
            if (!passstr.equals(conpassstr)) {

                Toast msg = Toast.makeText(CustomerSignInPage.this, "password don't match", Toast.LENGTH_SHORT);
                msg.show();
            }
            //email validation
            else if(!Vemail) {
                Toast msg = Toast.makeText(CustomerSignInPage.this, "Invalid Email", Toast.LENGTH_SHORT);
                msg.show();
            }
            //password validation
            else if(!Vpass){
                Toast msg = Toast.makeText(CustomerSignInPage.this, "Invalid Password", Toast.LENGTH_SHORT);
                msg.show();
            }
            //password length
            else if(passstr.length()<8) {
                Toast msg = Toast.makeText(CustomerSignInPage.this, "Password too short", Toast.LENGTH_SHORT);
                    msg.show();
            }

            else {
                //Inserting data in Table using Bean Class
                SignIn c = new SignIn();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setPassword(passstr);
                c.setPhone(phonestr);
                //Database helper Object is to insert in database
                helper.insertDetails(c);
                Toast msg = Toast.makeText(CustomerSignInPage.this, "Signed In Successfully ", Toast.LENGTH_SHORT);
                msg.show();
                session.createLoginSession(passstr,emailstr,false);
               Intent i = new Intent(CustomerSignInPage.this, HomePage.class);
                startActivity(i);
            }
        }
    }

}