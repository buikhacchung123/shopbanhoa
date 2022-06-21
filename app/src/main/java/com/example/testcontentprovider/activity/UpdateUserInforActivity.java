package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.model.KhachHang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserInforActivity extends AppCompatActivity {
    Button btnLuu, btnXemSP;
    EditText txtTen, txtSDT, txtDiaChi;
    KhachHang currentKH;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_infor);

        AnhXa();
        currentKH = MainActivity.CURRENT_USER;
        if(MainActivity.CURRENT_USER != null || MainActivity.CURRENT_USER.getMaND()+""!=null) {
            txtTen.setText(MainActivity.CURRENT_USER.getTenNd());
            txtSDT.setText(MainActivity.CURRENT_USER.getSdt());
            txtDiaChi.setText(MainActivity.CURRENT_USER.getDiaChi());
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
                    currentKH.setTenNd(tenDangNhap);
                    currentKH.setDiaChi(diaChi);
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
        ApiService.apiService.updateKhachHang(kh.getMaND(),kh).enqueue(new Callback<KhachHang>() {
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