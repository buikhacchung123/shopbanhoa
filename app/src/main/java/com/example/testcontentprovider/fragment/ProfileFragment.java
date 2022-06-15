package com.example.testcontentprovider.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.activity.ChangePasswordActivity;
import com.example.testcontentprovider.activity.ErrorActivity;
import com.example.testcontentprovider.activity.HistoryOrderActivity;
import com.example.testcontentprovider.activity.LoginActivity;
import com.example.testcontentprovider.activity.MainActivity;
import com.example.testcontentprovider.activity.ShopInforActivity;
import com.example.testcontentprovider.activity.UpdateUserInforActivity;
import com.example.testcontentprovider.model.KhachHang;

import java.io.Serializable;

public class ProfileFragment extends Fragment {
    ConstraintLayout btnThayDoiThongTin, btnDangXuat, btnDoiThuong, btnLichSuMuaHang, btnThongTinCH, btnDoiMK;
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
                startActivity(new Intent(getContext(), ChangePasswordActivity.class));
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
        return view;
    }
    public void AnhXa(){
        btnDangXuat = view.findViewById(R.id.btnDangXuat);
        btnDoiThuong = view.findViewById(R.id.btnDoiThuong);
        btnLichSuMuaHang = view.findViewById(R.id.btnLichSuMuaHang);
        btnThayDoiThongTin = view.findViewById(R.id.btnThayDoiThongTin);
        btnThongTinCH = view.findViewById(R.id.btnThongTinCuaHang);
        btnDoiMK = view.findViewById(R.id.btnDoiMatKhau);
    }
}