package com.example.anysensormonitoring;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText email,pass;
    Button login;
    TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        btn = findViewById(R.id.btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail,pas;

                mail = email.getText().toString();
                pas = pass.getText().toString();

                if(mail.equals("")||pas.equals("")){
                    Toast.makeText(LoginActivity.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                if(mail.equals("")){
                    Toast.makeText(LoginActivity.this,"Email is Blank", Toast.LENGTH_SHORT).show();
                }
                else if(pas.equals("")){
                    Toast.makeText(LoginActivity.this,"Password is Blank", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Authenction
                    Intent A = new Intent(LoginActivity.this, Menu.class);
                    startActivity(A);
                    finish();
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Register.class);
                startActivity(i);
                finish();
            }
        });

    }
}