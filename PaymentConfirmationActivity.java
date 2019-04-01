package com.example.anil.shoppingApplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class PaymentConfirmationActivity extends AppCompatActivity {

    private String url = "http://10.132.208.122:9011/core-web/trustwall/app/mobileConnect/tenant/MUAPT1103/mobileConnect/auth";
    private String urlMCStatus = "http://10.132.208.122:9011/core-web/trustwall/app/mobileConnect/tenant/MUAPT1103/mobileConnectStatus";

    Button btn_confirm;
    ImageView speaker;
    TextView price;
    ProgressDialog progressDialog;
    String contact_number;
    Context mContext;
    private String transaction_id = "YOURSHOP" + Calendar.getInstance().getTimeInMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);

        speaker = (ImageView)findViewById(R.id.speaker_confirm);
        price = (TextView)findViewById(R.id.toatal_price);
        btn_confirm = (Button)findViewById(R.id.btn_confirm);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");


        /*SharedPreferences sharedPref = mContext.getSharedPreferences("SpeakerDetails",mContext.MODE_PRIVATE);
        String cost = sharedPref.getString("Price", null);
        String image = sharedPref.getString("Image", null);
        Bitmap bitmap = BitmapFactory.decodeFile(image);

        speaker.setImageBitmap(bitmap);
        price.setText(cost);*/




        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SendHttpRequestTask t = new SendHttpRequestTask();
                BeginGetProfileRequest test = new BeginGetProfileRequest();
                test.setServiceProviderKey("YOUR_SHOP");
                test.setSpCustomerId("9953876880");
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
            StatusRequest statusRequest =new StatusRequest();
            statusRequest.setAppIdentity("OOB");
            statusRequest.setDeviceFingerPrint("");
            statusRequest.setMobilenumber("");
            statusRequest.setRefRequestTransactionId(transaction_id);
            statusRequest.setServiceProviderKey("YOUR_SHOP");
            SendHttpStatusRequestTask sendHttpStatusRequestTask = new SendHttpStatusRequestTask();
            Gson gson = new Gson();
            String jsonData = gson.toJson(statusRequest);
            sendHttpStatusRequestTask.execute(urlMCStatus,jsonData);
        }


    }
    private class SendHttpStatusRequestTask extends AsyncTask<String, Void, String> {
        String jsonData;
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            jsonData = params[1];
            String data=null;
            while (!data.contains("SUCCESS")||!data.contains("FAILURE")){
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
            if(result.contains("SUCCESS")) {
                progressDialog.hide();
                startActivity(new Intent(PaymentConfirmationActivity.this, OrderConfirmationActivity.class));
            }else{

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
