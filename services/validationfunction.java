package com.messwalaapp.services;

import android.support.v7.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aakash Jaiswal on 3/19/2017.
 */

public class validationfunction extends AppCompatActivity {
    //Email Validation Function
    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        //Email Regex
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //password Validation function
    public boolean passwordValidator(String password) {

        Pattern pattern;
        Matcher matcher;
        //Password Regex (Alphanumeric, 1 digit and 1 symbol)
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}
