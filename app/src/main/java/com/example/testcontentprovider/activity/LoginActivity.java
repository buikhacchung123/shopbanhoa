package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testcontentprovider.R;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText username, password;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        loadData();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String un = username.getText().toString();
                    String pw = password.getText().toString();
                    boolean check = checkBox.isChecked();
                    if(un.length() == 0 || pw.length() == 0)
                        Toast.makeText(LoginActivity.this, "Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    else
                    {
                        if (un.equals("admin") && pw.equals("123")) {
                            LuuTT(un,pw,check);
                            Toast.makeText(LoginActivity.this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception ex) {
                    startActivity(new Intent(LoginActivity.this,ErrorActivity.class));
                }
            }
        });
    }

    private void AnhXa() {
        login = findViewById(R.id.btnDangNhap);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        checkBox  = findViewById(R.id.rememberme);
    }

    private void LuuTT(String un, String pw, boolean check) {
        SharedPreferences pref = getSharedPreferences("thongtin.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(check){
            editor.putString("username",un);
            editor.putString("password",pw);
            editor.putBoolean("check",check);
        }
        else{
            editor.clear();
        }
        editor.commit();
    }
    // đọc thông tin từ file thongtin.dat
    private void loadData(){
        SharedPreferences pref = getSharedPreferences("thongtin.dat",MODE_PRIVATE);
        boolean check = pref.getBoolean("check",false);
        if(check){
            username.setText(pref.getString("username",""));
            password.setText(pref.getString("password",""));
            checkBox.setChecked(check);
        }
    }

    public void register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}