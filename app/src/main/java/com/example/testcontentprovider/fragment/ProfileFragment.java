package com.example.testcontentprovider.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.testcontentprovider.R;
import com.example.testcontentprovider.activity.ChangePasswordActivity;
import com.example.testcontentprovider.activity.ErrorActivity;
import com.example.testcontentprovider.activity.HistoryOrderActivity;
import com.example.testcontentprovider.activity.LoginActivity;
import com.example.testcontentprovider.activity.MainActivity;
import com.example.testcontentprovider.activity.LoginActivity;
import com.example.testcontentprovider.activity.ShopInforActivity;
import com.example.testcontentprovider.activity.UpdateUserInforActivity;
import com.example.testcontentprovider.activity.VoucherActivity;
import com.example.testcontentprovider.model.KhachHang;
import java.io.Serializable;

public class ProfileFragment extends Fragment {
    ConstraintLayout btnThayDoiThongTin, btnDangXuat, btnDoiThuong, btnLichSuMuaHang, btnThongTinCH, btnDoiMK, btnVoucher;
    private View view;


    public ProfileFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        AnhXa();

        //Sự kiện trang Fragment Profile
        btnThayDoiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), UpdateUserInforActivity.class));
            }
        });
        btnThongTinCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ShopInforActivity.class));
            }
        });
        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = getContext().getSharedPreferences("thongtin.dat", getContext().MODE_PRIVATE);
                settings.edit().remove("username").commit();
                settings.edit().remove("password").commit();
                LoginActivity.CURRENT_USER=new KhachHang();
                startActivity(new Intent(getContext(), LoginActivity.class));

            }
        });
        btnLichSuMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), HistoryOrderActivity.class));
            }
        });
        btnDoiThuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ErrorActivity.class));
            }
        });
        btnVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), VoucherActivity.class));
            }
        });

        return view;
    }
    public void AnhXa(){
        btnDangXuat = view.findViewById(R.id.btnDangXuat);
        btnDoiThuong = view.findViewById(R.id.btnDoiThuong);
        btnLichSuMuaHang = view.findViewById(R.id.btnLichSuMuaHang);
        btnThayDoiThongTin = view.findViewById(R.id.btnThayDoiThongTin);
        btnThongTinCH = view.findViewById(R.id.btnThongTinCuaHang);
        btnDoiMK = view.findViewById(R.id.btnDoiMatKhau);
        btnVoucher = view.findViewById(R.id.btnVoucher);
    }

    void showCustomDialog(){
        final Dialog dialog = new Dialog(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(true);
        dialog.setContentView(R.layout.layout_changepass);

        EditText oldpass = dialog.findViewById(R.id.ed_oldpass);
        EditText newpass = dialog.findViewById(R.id.ed_newpass);
        EditText renewpass = dialog.findViewById(R.id.ed_confirmpass);
        Button btnDoimk = dialog.findViewById(R.id.btn_updatepass);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        btnDoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Xác nhận đổi mật khẩu ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            String opass = oldpass.getText().toString();
                            String pass=newpass.getText().toString();
                            String cpass=renewpass.getText().toString();

                            if(opass.equals("")||pass.equals("")||cpass.equals(""))
                            {
                                Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();
                            }
                            else {
                                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                                if (pass.matches(cpass)) {
                                    b.setMessage("Update thành công");
                                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            Intent intent = new Intent(getContext(), LoginActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                    AlertDialog al = b.create();
                                    al.show();
                                }
                                else {
                                    newpass.setError("Xác nhận mật khẩu sai");
                                    renewpass.requestFocus();
                                    return;
                                }
                            }
                            return;
                        }catch (Exception ex){
                            startActivity(new Intent(getContext(), ErrorActivity.class));
                        }

                    }
                });
                builder.show();
            }
        });
        dialog.show();
    }
}