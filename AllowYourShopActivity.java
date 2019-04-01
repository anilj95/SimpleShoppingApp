package com.example.anil.shoppingApplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class AllowYourShopActivity extends AppCompatActivity {
    private String url = "http://10.132.xxx.122:9x11/core-web/app/mobileConnect/tenant/MUAPT1103/mobileConnect/getProfile";
    private String urlMCStatus = "http://10.132.xxx.122:9x11/core-web/app/mobileConnect/tenant/MUAPT1103/mobileConnectStatus";

    private String transaction_id = "YOURSHOP" + Calendar.getInstance().getTimeInMillis();

    ProgressDialog progressDialog;
    String contact_number;

    Button cancel, ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_your_shop);

        Bundle bundle = getIntent().getExtras();
        contact_number = bundle.getString("MobileNumber", "");


        cancel = (Button) findViewById(R.id.btn_cancel);
        ok = (Button) findViewById(R.id.btn_oks);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(AllowYourShopActivity.this,LoginActivity.class));
                finish();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SendHttpRequestTask t = new SendHttpRequestTask();
                BeginGetProfileRequest test = new BeginGetProfileRequest();
                test.setServiceProviderKey("YOUR_SHOP");
                test.setSpCustomerId("9X53876XXX0");
                test.setRequestTransactionId(transaction_id);
                test.setTransactionType("PROFILE RETREIVE");
                test.setAppIdentity("OOB");
                test.setConsent(true);
                Gson gson = new Gson();
                String jsonData = gson.toJson(test);
                String[] params = new String[]{url, jsonData};
                t.execute(params);
                progressDialog.show();
            }
        });
    }

    private class SendHttpRequestTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String jsonData = params[1];
            String data = sendHttpRequest(url, jsonData);
            System.out.println("Data [" + data + "]");
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            StatusRequest statusRequest = new StatusRequest();
            statusRequest.setAppIdentity("OOB");
            statusRequest.setDeviceFingerPrint("");
            statusRequest.setMobilenumber(contact_number);
            statusRequest.setRefRequestTransactionId(transaction_id);
            statusRequest.setServiceProviderKey("YOUR_SHOP");
            SendHttpStatusRequestTask sendHttpStatusRequestTask = new SendHttpStatusRequestTask();
            Gson gson = new Gson();
            String jsonData = gson.toJson(statusRequest);
            sendHttpStatusRequestTask.execute(urlMCStatus, jsonData);
        }
    }

    private class SendHttpStatusRequestTask extends AsyncTask<String, Void, String> {
        String jsonData;

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            jsonData = params[1];
            String data = null;
            while (!data.contains("SUCCESS") || !data.contains("FAILURE")) {
                data = sendHttpRequest(url, jsonData);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Data [" + data + "]");
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.contains("SUCCESS")) {
                progressDialog.hide();
                startActivity(new Intent(AllowYourShopActivity.this, UserDetailsActivity.class));
            } else {

                progressDialog.hide();
            }
        }


    }

    private String sendHttpRequest(String url, String request) {
        StringBuffer buffer = new StringBuffer();
        try {
            System.out.println("URL [" + url + "] - Request [" + request + "]");

            HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            con.getOutputStream().write((request).getBytes());

            InputStream is = con.getInputStream();
            byte[] b = new byte[1024];

            while (is.read(b) != -1)
                buffer.append(new String(b));

            con.disconnect();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return buffer.toString();
    }
}
