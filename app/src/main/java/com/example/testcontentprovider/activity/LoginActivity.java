package com.example.testcontentprovider.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText username, password;
    CheckBox checkBox;
    TextView linkDangKyNgay, linkQuenMatKhau;
    public static List<KhachHang> arrayKH;
    List<KhachHang> arrayKH_Search;
    private KhachHang mkh;
    static GioHang cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        loadData();
        LoadListKhachHangs();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String un = username.getText().toString().trim();
                    String pw = password.getText().toString().trim();
                    boolean check = checkBox.isChecked();
                    if(un.length() == 0 || pw.length() == 0)
                        Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                    else
                    {
                        ApiService.apiService.getUser(un).enqueue(new Callback<List<KhachHang>>() {
                            @Override
                            public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {
                                arrayKH_Search = response.body();
                                if(arrayKH_Search == null || arrayKH_Search.isEmpty()){
                                    Toast.makeText(LoginActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                boolean isHasUser = false;
                                for (KhachHang kh : arrayKH_Search)
                                {
                                    if(un.equals(kh.getUsername()) && pw.equals(kh.getPassWord())){
                                        isHasUser = true;
                                        mkh = kh;
                                        break;
                                    }
                                }

                                if(isHasUser){
                                    LuuTT(un,pw,check);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("object_user", mkh);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu sai!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<KhachHang>> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, "Call api failed", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }catch (Exception ex) {
                    startActivity(new Intent(LoginActivity.this,ErrorActivity.class));
                }

            }
        });
        linkDangKyNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),RegisterActivity.class));
            }
        });
        linkQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),ChangePasswordActivity.class));
            }
        });
    }

    private void AnhXa() {
        btnLogin = findViewById(R.id.btnDangNhap);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        checkBox  = findViewById(R.id.rememberme);
       arrayKH_Search = new ArrayList<>();
       arrayKH = new ArrayList<>();
        linkQuenMatKhau = findViewById(R.id.linkQuenMatKhau);
        linkDangKyNgay = findViewById(R.id.linkDangKyNgay);
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
    public void LoadListKhachHangs(){
        ApiService.apiService.getAllKhachHangs().enqueue(new Callback<List<KhachHang>>() {
            @Override
            public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {
                arrayKH = response.body();
            }

            @Override
            public void onFailure(Call<List<KhachHang>> call, Throwable t) {

            }
        });

    }
}