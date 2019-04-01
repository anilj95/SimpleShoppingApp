package com.example.anil.shoppingApplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserDetailsActivity extends AppCompatActivity {

    Button btn_proceed;
    TextView firstName,lastName;
    EditText address1,address2,postCode,country,creditNo,expiryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        firstName = (TextView) findViewById(R.id.tv_firstname);
        lastName = (TextView) findViewById(R.id.tv_lasttname);
        address1 = (EditText) findViewById(R.id.et_address1);
        address2 = (EditText) findViewById(R.id.et_address2);
        postCode = (EditText) findViewById(R.id.et_post_code);
        country = (EditText) findViewById(R.id.et_country);
        creditNo = (EditText) findViewById(R.id.et_creditcard);
        expiryDate = (EditText) findViewById(R.id.et_expiry);

        btn_proceed = (Button)findViewById(R.id.btn_proceed);
        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDetailsActivity.this,PaymentConfirmationActivity.class));
            }
        });
    }
}
