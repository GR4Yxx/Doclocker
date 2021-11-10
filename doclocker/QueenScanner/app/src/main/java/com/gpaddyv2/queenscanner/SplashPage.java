package com.gpaddyv2.queenscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SplashPage extends AppCompatActivity {
    ImageButton androidImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        this.getSupportActionBar().hide();

        androidImageButton = (ImageButton) findViewById(R.id.imageButton3);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();

            }
        });
    }
    public void openActivity2() {
        Intent intent= new Intent (this, Signup.class);
        startActivity(intent);
    }
}