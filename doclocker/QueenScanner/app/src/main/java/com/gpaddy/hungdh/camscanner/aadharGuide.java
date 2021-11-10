package com.gpaddy.hungdh.camscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gpaddyv2.queenscanner.R;

public class aadharGuide extends AppCompatActivity {
    private TextView aadharNAV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadhar_guide);
        getSupportActionBar().hide();

        aadharNAV=findViewById(R.id.aadharcardNAV);
        aadharNAV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=BMC"));
                startActivity(intent);
            }
        });
    }

}