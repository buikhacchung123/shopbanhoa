package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.data.ApiService;
import com.example.testcontentprovider.data.Constance;
import com.example.testcontentprovider.data.RetrofitClient;
import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.KhachHang;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText username, password;
    CheckBox checkBox;
    TextView linkDangKyNgay, linkQuenMatKhau;
    List<KhachHang> arrayKH;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AnhXa();
        loadData();
        apiService = RetrofitClient.getClient(Constance.API_URL).create(ApiService.class);
        LoadingAllKhachHang();

        if(getIntent().getSerializableExtra("intentUser")!=null && getIntent().getSerializableExtra("intentPass")!=null) {
            String user = getIntent().getSerializableExtra("intentUser").toString();
            String pass = getIntent().getSerializableExtra("intentPass").toString();
            if(user != null && pass != null){
                username.setText("user");
                password.setText("pass");
            }
        }

        //Sự kiện trang đăng nhập
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
                        if (un.equals("admin") && pw.equals("123")) {
                            LuuTT(un,pw,check);
                            Toast.makeText(LoginActivity.this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            //Test trang cap nhat khach hang
                            KhachHang test = GetKhachHangByUsername("thanh123");
                            intent.putExtra("CurrentUser", test.getUsername());
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
    private void LoadingAllKhachHang() {
        Call<List<KhachHang>> call = apiService.getAllKhachHangs();
        call.enqueue(new Callback<List<KhachHang>>() {
            @Override
            public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {
                arrayKH = response.body();
            }

            @Override
            public void onFailure(Call<List<KhachHang>> call, Throwable t) {

            }
        });
    }
    public KhachHang GetKhachHangByUsername(String userKH){
        for (KhachHang k : arrayKH){
            if(userKH.toLowerCase().trim().equals(k.getUsername().toLowerCase()))
                return k;
        }
        return new KhachHang();
    }
}