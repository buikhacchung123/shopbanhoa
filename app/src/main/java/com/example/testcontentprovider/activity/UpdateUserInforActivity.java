package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.data.ApiService;
import com.example.testcontentprovider.data.Constance;
import com.example.testcontentprovider.data.RetrofitClient;
import com.example.testcontentprovider.model.KhachHang;
import com.google.android.gms.common.api.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserInforActivity extends AppCompatActivity {
    Button btnLuu, btnXemSP;
    EditText txtTen, txtSDT, txtDiaChi;
    private ApiService apiService;
    KhachHang currentKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_infor);


        apiService = RetrofitClient.getClient(Constance.API_URL).create(ApiService.class);
        AnhXa();
        currentKH = LoginActivity.CURRENT_USER;


        /*if(getIntent().getSerializableExtra("CurrentUser1") !=null)
        {
            String currentUser = getIntent().getSerializableExtra("CurrentUser1").toString();
            currentKH = GetKhachHangByUsername(currentUser);
            if(currentKH!=null) {
                txtTen.setText(currentKH.getHoten());
                txtSDT.setText(currentKH.getSdt());
                txtDiaChi.setText(currentKH.getDiachi());
            }
        }*/
        if(LoginActivity.CURRENT_USER != null || LoginActivity.CURRENT_USER.getMaNd().trim()!="") {
            txtTen.setText(LoginActivity.CURRENT_USER.getHoten());
            txtSDT.setText(LoginActivity.CURRENT_USER.getSdt());
            txtDiaChi.setText(LoginActivity.CURRENT_USER.getDiachi());
        }


        //Sự kiện trang Cập nhật thông tin người dùng
        btnXemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateUserInforActivity.this,MainActivity.class));
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String tenDangNhap = txtTen.getText().toString().trim();
                    String diaChi = txtDiaChi.getText().toString().trim();
                    String sdt = txtSDT.getText().toString().trim();
                    if(tenDangNhap.isEmpty() || diaChi.isEmpty() || sdt.isEmpty()){
                        Toast.makeText(getBaseContext(), "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(IsPhoneExist(sdt) && !sdt.trim().equals(currentKH.getSdt().trim())){
                        txtSDT.setError("Số điện thoại đã tồn tại.");
                        txtSDT.requestFocus();
                        return;
                    }
                    currentKH.setHoten(tenDangNhap);
                    currentKH.setDiachi(diaChi);
                    currentKH.setSdt(sdt);
                    UpdateUser(currentKH);
                    startActivity(new Intent(getBaseContext(),MainActivity.class));

                }catch(Exception ex){
                    startActivity(new Intent(UpdateUserInforActivity.this,ErrorActivity.class));
                }
            }
        });
    }

    public void AnhXa(){
        btnLuu = findViewById(R.id.btnChange);
        btnXemSP = findViewById(R.id.btnXemSP);
        txtTen = findViewById(R.id.txtTen_Update);
        txtDiaChi = findViewById(R.id.txtDiaChi_Update);
        txtSDT = findViewById(R.id.txtSDT_Update);
    }
    public boolean IsPhoneExist(String phone){
        for(KhachHang k : LoginActivity.arrayKH){
            if(k.getSdt() != null && !k.getSdt().isEmpty())
                if(k.getSdt().trim().equals(phone.trim()))
                    return true;
        }
        return false;
    }
    public void UpdateUser(KhachHang kh){
        Call<KhachHang> call = apiService.updateKhachHang(kh.getMaNd().toString(),kh);
        call.enqueue(new Callback<KhachHang>() {
            @Override
            public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                String s = response.message();
                if(response.isSuccessful())
                    Toast.makeText(getBaseContext(),"Cập nhật thông tin thành công.",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getBaseContext(),"Cập nhật thông tin không thành công, vui lòng thử lại sau.",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<KhachHang> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Cập nhật thông tin không thành công, vui lòng thử lại sau.",Toast.LENGTH_LONG).show();
            }
        });
    }
    public KhachHang GetKhachHangByUsername(String userKH){
        if(LoginActivity.arrayKH !=null)
            for (KhachHang k : LoginActivity.arrayKH){
                if(k.getUsername()!= null && userKH.toLowerCase().trim().equals(k.getUsername().toLowerCase().trim()))
                    return k;
            }
        return new KhachHang();
    }
}