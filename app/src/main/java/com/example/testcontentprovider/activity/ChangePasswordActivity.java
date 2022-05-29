package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                try {
                    String username = txtUsername.getText().toString().trim();
                    String password = txtPass.getText().toString().trim();
                    String confirmPass = txtConfirmPass.getText().toString().trim();
                    if(username.isEmpty() || password.isEmpty() || confirmPass.isEmpty()){
                        Toast.makeText(getBaseContext(), "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                    }else{
                        if(password != confirmPass)
                            Toast.makeText(getBaseContext(), "Xác nhận mật khẩu và mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                        else{
                            //Kiểm tra username đã tồn tại chưa
                        }
                    }
                }catch (Exception ex){
                    startActivity(new Intent(getBaseContext(),ErrorActivity.class));
                }
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