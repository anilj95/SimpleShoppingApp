package com.example.anil.shoppingApplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Speakers> speakerList;
    private Button btnNext;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        speakerList = new ArrayList<Speakers>();
        for (int i = 0; i < 5; i++) {
            Speakers speakers = new Speakers();
            speakerList.add(speakers);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_speakers);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create an Object for Adapter
        mAdapter = new SpeakerDataAdapter(MainActivity.this,speakerList);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);
        btnNext = (Button)findViewById(R.id.btn_nxt);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PaymentConfirmationActivity.class));
            }
        });


    }
}
