package com.messwalaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AdminLoginpage extends AppCompatActivity {
    public EditText apass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_loginpage);
        apass = (EditText) findViewById(R.id.adminLogin);
    }

        public void onAloginbtnClick(View v){
            if(v.getId()==(R.id.adminLoginBtn)){
                String adpass= apass.getText().toString();
            }
        }


}
