package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testcontentprovider.R;

public class UpdateUserInforActivity extends AppCompatActivity {
    Button btnLuu, btnXemSP;
    EditText txtTen, txtSDT, txtDiaChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_infor);

        AnhXa();

        btnXemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateUserInforActivity.this,MainActivity.class));
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void AnhXa(){
        btnLuu = findViewById(R.id.btnLuu);
        btnXemSP = findViewById(R.id.btnXemSP);
        txtTen = findViewById(R.id.txtTen_Update);
        txtDiaChi = findViewById(R.id.txtDiaChi_Update);
        txtSDT = findViewById(R.id.txtSDT_Update);
    }
}