package com.example.android3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    EditText email, password;
    CheckBox cbRem;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(this);

        if (sessionManager.getLogin()){
            Intent intent = new Intent(MainActivity.this , ContactActivity.class);
            startActivity(intent);
        }
        email = findViewById(R.id.etEMail);
        password = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        cbRem = findViewById(R.id.cbRemeberMe);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo();

                Intent intent = new Intent(MainActivity.this , ContactActivity.class);
                startActivity(intent);
            }
        });


    }


    public void saveInfo(){
        if (cbRem.isChecked()){
            sessionManager.setlogin(true);
            sessionManager.setEmail(email.getText().toString());
        }
    }
}