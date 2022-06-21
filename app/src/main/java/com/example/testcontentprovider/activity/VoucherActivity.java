package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.SanPhamAdapter;
import com.example.testcontentprovider.adapter.VoucherAdapter;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.model.SanPham;
import com.example.testcontentprovider.model.Voucher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoucherActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtNoVoucher;
    RecyclerView rv_ListVoucher;
    VoucherAdapter adapter;
    List<Voucher> arrayVC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        AnhXa();
        LoadingAllVouchers();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void AnhXa(){
        toolbar = findViewById(R.id.toolbar_voucher);
        txtNoVoucher = findViewById(R.id.txtNoVoucher);
        rv_ListVoucher = findViewById(R.id.rv_listVoucher);
    }
    private void LoadingAllVouchers() {
        ApiService.apiService.getAllVouchers().enqueue(new Callback<List<Voucher>>() {
            @Override
            public void onResponse(Call<List<Voucher>> call, Response<List<Voucher>> response) {
                arrayVC = response.body();
                adapter = new VoucherAdapter(getBaseContext(),arrayVC);
                rv_ListVoucher.setAdapter(adapter);
                rv_ListVoucher.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                if(arrayVC == null) {
                    txtNoVoucher.setVisibility(View.VISIBLE);
                    rv_ListVoucher.setVisibility(View.GONE);
                } else if( arrayVC.size() == 0) {
                    txtNoVoucher.setVisibility(View.VISIBLE);
                    rv_ListVoucher.setVisibility(View.GONE);
                }else {
                    txtNoVoucher.setVisibility(View.GONE);
                    rv_ListVoucher.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Voucher>> call, Throwable t) {

            }
        });
    }
}