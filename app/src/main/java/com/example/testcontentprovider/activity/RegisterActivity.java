package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testcontentprovider.R;

public class RegisterActivity extends AppCompatActivity {
    EditText hoten, sdt, username, password, confirmpassword, diachi;
    AppCompatButton register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AnhXa();
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String user = username.getText().toString().trim();
                    String pass = password.getText().toString().trim();
                    String repass = confirmpassword.getText().toString().trim();
                    String SDT = sdt.getText().toString().trim();
                    String hoTen = hoten.getText().toString().trim();
                    String diaChi = diachi.getText().toString().trim();

                    if(user.equals("")||pass.equals("")||repass.equals("")||SDT.equals("")||hoTen.equals("")||diaChi.equals(""))
                        Toast.makeText(RegisterActivity.this,"Vui lòng điền đầy đủ thông tin.",Toast.LENGTH_LONG).show();
                    else {
                        if (pass.matches(repass)) {
                            b.setMessage("Đăng kí thành công");
                            b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
                            AlertDialog al = b.create();
                            al.show();
                        }
                        else {
                            confirmpassword.setError("Xác nhận mật khẩu sai");
                            password.requestFocus();
                            return;
                        }
                    }
                }catch(Exception ex){
                    startActivity(new Intent(RegisterActivity.this,ErrorActivity.class));
                }
            }
        });

    }

    private void AnhXa() {
        hoten = findViewById(R.id.txtTen_Update);
        sdt = findViewById(R.id.txtSDT_Update);
        diachi = findViewById(R.id.diachi);
        username = findViewById(R.id.username);
        password = findViewById(R.id.pass);
        confirmpassword = findViewById(R.id.repass);
        register = findViewById(R.id.btnChange);
    }

    public void signin(View view) {
        Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(login);
    }

}

