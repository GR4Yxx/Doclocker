package com.gpaddy.hungdh.camscanner;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.gpaddyv2.queenscanner.Login;
import com.gpaddyv2.queenscanner.R;

import org.jetbrains.annotations.NotNull;


public class Signup extends AppCompatActivity {
    ImageButton androidImageButton;
    EditText mFullName, mEmail, mPassword, mPhone;
    ImageButton mRegisterBtn;
    ImageButton mLoginBtn;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.getSupportActionBar().hide();
        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.imageButton4);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();


        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");
                    return;

                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }
                if (password.length() < 6) {
                    mPassword.setError("Password must be 6 Characters Long");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(com.gpaddy.hungdh.camscanner.Signup.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), com.gpaddy.hungdh.camscanner.Login.class));
                        } else {
                            Toast.makeText(com.gpaddy.hungdh.camscanner.Signup.this, "Error", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

        androidImageButton = (ImageButton) findViewById(R.id.imageButton4);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();

            }
        });
    }

    public void openActivity3() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}