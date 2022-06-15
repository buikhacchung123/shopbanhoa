package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testcontentprovider.R;

public class HoanThanhThanhToanActivity extends AppCompatActivity {

    Button btnBackToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoanthanhthanhtoan);
        btnBackToHome = findViewById(R.id.btnBackHome);
        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HoanThanhThanhToanActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}