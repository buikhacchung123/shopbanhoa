package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.testcontentprovider.R;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.model.KhachHang;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText txtUsername, txtPass, txtConfirmPass;
    Button btnChange;
    TextView linkBackToHome;
    private ApiService apiService;
    AlertDialog.Builder b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        AnhXa();
        b = new AlertDialog.Builder(this);

        if(MainActivity.CURRENT_USER!=null){
            txtUsername.setText(MainActivity.CURRENT_USER.getUsername());
        }

        //Sự kiện trang Đổi mật khẩu
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String username = txtUsername.getText().toString().trim();
                    String password = txtPass.getText().toString().trim();
                    String confirmPass = txtConfirmPass.getText().toString().trim();
                    if(username.isEmpty() || password.isEmpty() || confirmPass.isEmpty()){
                        Toast.makeText(getBaseContext(), "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!password.equals(confirmPass)) {
                        txtConfirmPass.setError("Xác nhận mật khẩu và mật khẩu không trùng khớp.");
                        txtConfirmPass.requestFocus();
                        return;
                    }
                    if(!IsUsernameExist(username)){
                        txtUsername.setError("Username không tồn tại.");
                        txtUsername.requestFocus();
                        return;
                    }

                    KhachHang k = GetKhachHangByUsername(username);
                    if(k != null) {
                        k.setPassWord(password);
                        ChangePassword(k);
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
    public boolean IsUsernameExist(String mail){
        for (KhachHang k : LoginActivity.arrayKH){
            if(k.getUsername() != null && !k.getUsername().isEmpty())
                if(k.getUsername().toLowerCase().trim().equals(mail.toLowerCase().trim()))
                    return true;
        }
        return false;
    }
    public KhachHang GetKhachHangByUsername(String userKH){
        for (KhachHang k : LoginActivity.arrayKH){
            if(k.getUsername()!= null && userKH.toLowerCase().trim().equals(k.getUsername().toLowerCase().trim()))
                return k;
        }
        return new KhachHang();
    }
    public void ChangePassword(KhachHang kh){
        ApiService.apiService.updateKhachHang(kh.getMaND(),kh).enqueue(new Callback<KhachHang>() {
            @Override
            public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                String s = response.message();
                if(response.isSuccessful()){
                    b.setMessage("Đổi mật khẩu thành công, trở về trang đăng nhập");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                            intent.putExtra("intentUser",txtUsername.getText());
                            intent.putExtra("intentPass",txtPass.getText());
                            startActivity(intent);
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                } else if(s.equals("Internal Server Error"))
                {
                    Toast.makeText(getBaseContext(),"Lỗi mạng.",Toast.LENGTH_LONG).show();
                    return;
                }
                else
                    Toast.makeText(getBaseContext(),"Đổi mật khẩu không thành công, vui lòng thử lại sau.",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<KhachHang> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Đổi mật khẩu không thành công, vui lòng thử lại sau.",Toast.LENGTH_LONG).show();
            }
        });
    }
}