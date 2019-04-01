package com.example.anil.shoppingApplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    EditText userName, password, contact;
    Button ok, login, signup, mregister;
    ProgressDialog progressDialog;
    String contact_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.field_email);
        password = (EditText) findViewById(R.id.field_password);
        contact = (EditText) findViewById(R.id.field_contact_number);
        ok = (Button) findViewById(R.id.button_ok);
        signup = (Button) findViewById(R.id.yourshop_reg);
        login = (Button) findViewById(R.id.m_login);
        mregister = (Button) findViewById(R.id.m_register);

        userName.setEnabled(false);
        password.setEnabled(false);
        ok.setEnabled(false);
        signup.setEnabled(false);

        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, AllowYourShopActivity.class);
                intent.putExtra("MobileNumber",contact.getText().toString() );
                startActivity(intent);
            }
        });


    }
}
