package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testcontentprovider.R;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText txtUsername, txtPass, txtConfirmPass;
    Button btnChange;
    TextView linkBackToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        AnhXa();

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        linkBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),LoginActivity.class));
            }
        });
    }
    public void AnhXa(){
        txtUsername = findViewById(R.id.username);
        txtPass = findViewById(R.id.pass);
        txtConfirmPass = findViewById(R.id.repass);
        btnChange = findViewById(R.id.btnChange);
        linkBackToHome = findViewById(R.id.linkBackToLogin);
    }
}