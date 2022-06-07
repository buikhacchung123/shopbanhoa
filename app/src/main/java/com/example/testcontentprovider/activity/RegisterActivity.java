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
import com.example.testcontentprovider.adapter.SanPhamAdapter;
import com.example.testcontentprovider.data.ApiService;
import com.example.testcontentprovider.data.Constance;
import com.example.testcontentprovider.data.RetrofitClient;
import com.example.testcontentprovider.model.KhachHang;
import com.example.testcontentprovider.model.SanPham;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText edHoten, edSdt, edUsername, edPassword, edConfirmpassword, edDiachi;
    AppCompatButton btnRegister;
    private ApiService apiService;
    List<KhachHang> arrayKH;
    AlertDialog.Builder b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AnhXa();
        apiService = RetrofitClient.getClient(Constance.API_URL).create(ApiService.class);
        b = new AlertDialog.Builder(this);
        LoadingAllKhachHang();


        //Sự kiện trang Đăng ký
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String user = edUsername.getText().toString().trim();
                    String pass = edPassword.getText().toString().trim();
                    String repass = edConfirmpassword.getText().toString().trim();
                    String SDT = edSdt.getText().toString().trim();
                    String hoTen = edHoten.getText().toString().trim();
                    String diaChi = edDiachi.getText().toString().trim();

                    if(user.equals("")||pass.equals("")||repass.equals("")||SDT.equals("")||hoTen.equals("")||diaChi.equals(""))
                        Toast.makeText(RegisterActivity.this,"Vui lòng điền đầy đủ thông tin.",Toast.LENGTH_LONG).show();
                    else {
                        if (!pass.matches(repass)) {
                            edConfirmpassword.setError("Xác nhận mật khẩu sai");
                            edPassword.requestFocus();
                            return;
                        }
                        if(IsPhoneExist(SDT)){
                            edSdt.setError("Số điện thoại đã tồn tại");
                            edSdt.requestFocus();
                            return;
                        }
                        if(IsUsernameExist(user)) {
                            edUsername.setError("Username đã tồn tại");
                            edUsername.requestFocus();
                            return;
                        }
                        KhachHang k = new KhachHang(hoTen,SDT,user,pass,diaChi,true);
                        InsertKhachHang(k);

                    }
                }catch(Exception ex){
                    startActivity(new Intent(RegisterActivity.this,ErrorActivity.class));
                }
            }
        });

    }

    private void AnhXa() {
        edHoten = findViewById(R.id.txtTen_Update);
        edSdt = findViewById(R.id.txtSDT_Update);
        edDiachi = findViewById(R.id.diachi);
        edUsername = findViewById(R.id.username);
        edPassword = findViewById(R.id.pass);
        edConfirmpassword = findViewById(R.id.repass);
        btnRegister = findViewById(R.id.btnChange);
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
    public boolean IsUsernameExist(String mail){
        for (KhachHang k : arrayKH){
            if(k.getUsername() != null && !k.getUsername().isEmpty())
                if(k.getUsername().toLowerCase().trim().equals(mail.toLowerCase().trim()))
                    return true;
        }
        return false;
    }
    public boolean IsPhoneExist(String phone){
        for(KhachHang k :arrayKH){
            if(k.getSdt() != null && !k.getSdt().isEmpty())
                if(k.getSdt().trim().equals(phone.trim()))
                    return true;
        }
        return false;
    }
    private void InsertKhachHang(KhachHang kh) {
        Call<KhachHang> call = apiService.insertKhachHang(kh);
        call.enqueue(new Callback<KhachHang>() {
            @Override
            public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                String result = response.message();
                if(result.equals("Created")){
                    b.setMessage("Đăng kí thành công, trở về trang đăng nhập");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.putExtra("intentUser",edUsername.getText());
                            intent.putExtra("intentPass",edPassword.getText());
                            startActivity(intent);
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                }
                else
                    Toast.makeText(getBaseContext(),"Đăng kí không thành công, vui lòng thử lại sau.",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<KhachHang> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Đăng kí không thành công, vui lòng thử lại sau.",Toast.LENGTH_LONG).show();
            }
        });
    }
}

