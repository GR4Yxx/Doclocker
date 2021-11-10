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
import com.gpaddy.hungdh.camscanner.MainActivity;
import com.gpaddyv2.queenscanner.R;
import com.gpaddyv2.queenscanner.Signup;

import org.jetbrains.annotations.NotNull;

import java.io.File;


public class Login extends AppCompatActivity {
    ImageButton androidImageButton;
    EditText mFullName, mEmail, mPassword, mPhone;
    ImageButton mRegisterBtn;
    ImageButton mLoginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getSupportActionBar().hide();

        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mLoginBtn = findViewById(R.id.loginbtn);
        mRegisterBtn= findViewById(R.id.Signupbtn);
        fAuth = FirebaseAuth.getInstance();
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), Signup.class));

            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
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
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(com.gpaddy.hungdh.camscanner.Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(com.gpaddy.hungdh.camscanner.Login.this, "Login error", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


            }
        });
    }
}