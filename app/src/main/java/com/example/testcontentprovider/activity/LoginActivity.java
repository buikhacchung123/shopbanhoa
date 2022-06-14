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
    private ApiService apiService;
    public static KhachHang CURRENT_USER;
    public static List<KhachHang> arrayKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiService = RetrofitClient.getClient(Constance.API_URL).create(ApiService.class);
        LoadingAllKhachHangs();
        AnhXa();
        loadData();



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
                    if(un.length() == 0 || pw.length() == 0) {
                        Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    KhachHang userLogin =GetKhachHangByUsername(un);
                    if(!IsUsernameExist(un) || userLogin == null){
                        username.setError("Tài khoản không tồn tại.");
                        username.requestFocus();
                        return;
                    }

                    if(!userLogin.getPassword().trim().equals(pw))
                    {
                        password.setError("Mật khẩu không đúng.");
                        password.requestFocus();
                        return;
                    }
                    LuuTT(un,pw,check);
                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    CURRENT_USER = userLogin;
                    Intent intent = new Intent(LoginActivity.this, LoadingActivity.class);
                    intent.putExtra("CurrentUser", userLogin.getUsername());
                    startActivity(intent);
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
    public KhachHang GetKhachHangByUsername(String userKH){
        if(LoginActivity.arrayKH.size()!=0 ||LoginActivity.arrayKH!=null)
            for (KhachHang k : LoginActivity.arrayKH){
                if(k != null)
                    if(k.getUsername()!= null && userKH.toLowerCase().trim().equals(k.getUsername().toLowerCase()))
                        return k;
            }
        return new KhachHang();
    }
    public boolean IsUsernameExist(String mail){
        for (KhachHang k : LoginActivity.arrayKH){
            if(k.getUsername() != null && !k.getUsername().isEmpty())
                if(k.getUsername().toLowerCase().trim().equals(mail.toLowerCase().trim()))
                    return true;
        }
        return false;
    }
    private void LoadingAllKhachHangs() {
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
}