package com.gpaddy.hungdh.camscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gpaddyv2.queenscanner.R;

import java.io.File;

public class Guides extends AppCompatActivity {
    
    final String folderName="DocLocker";
    
    private Button aadharScan,aadharApply,panScan,panApply;

    private File folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guides);
        getSupportActionBar().hide();

        aadharScan=findViewById(R.id.aadharscan);
        aadharApply=findViewById(R.id.aadharapply);
        panScan=findViewById(R.id.panscan);
        panApply=findViewById(R.id.panapply);




        aadharScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkDoc("aadhar.pdf")){
                    Toast.makeText(getApplicationContext(), "Aadhar card exists", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Aadhar card does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
        panScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pass
            }
        });

        aadharApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aadhar=new Intent(Guides.this,aadharGuide.class);
                startActivity(aadhar);
           }
        });
        panApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pan=new Intent(Guides.this,panGuide.class);
                startActivity(pan);
            }
        });


    }

    private Boolean checkDoc(String fileName){

        folder = new File(Environment.getExternalStorageDirectory() + "/" + folderName + "/"+ fileName);
        if(folder.exists()){ return true;}
        else{ return false; }
    }
}