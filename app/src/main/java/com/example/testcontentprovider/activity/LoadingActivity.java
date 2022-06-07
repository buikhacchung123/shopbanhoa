package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.DMSPAdapter;
import com.example.testcontentprovider.data.ApiService;
import com.example.testcontentprovider.data.Constance;
import com.example.testcontentprovider.data.RetrofitClient;
import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.KhachHang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity {
    private ApiService apiService;
    public static List<DanhMuc> arrayDM;
    public static List<KhachHang> arrayKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        apiService = RetrofitClient.getClient(Constance.API_URL).create(ApiService.class);
        LoadingDanhMucHomePage();
        LoadingAllKhachHang();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
    private void LoadingDanhMucHomePage() {
        Call<List<DanhMuc>> call = apiService.getAllDanhMucs();
        call.enqueue(new Callback<List<DanhMuc>>() {
            @Override
            public void onResponse(Call<List<DanhMuc>> call, Response<List<DanhMuc>> response) {
                arrayDM = response.body();
            }

            @Override
            public void onFailure(Call<List<DanhMuc>> call, Throwable t) {

            }
        });
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
}