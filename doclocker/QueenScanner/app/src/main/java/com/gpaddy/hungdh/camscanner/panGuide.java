package com.gpaddy.hungdh.camscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gpaddyv2.queenscanner.R;

public class panGuide extends AppCompatActivity {
    private TextView panNAV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pan_guide);
        getSupportActionBar().hide();

        panNAV=findViewById(R.id.panNAV);
        panNAV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=NSDL Office"));
                startActivity(intent);
            }
        });
    }
}