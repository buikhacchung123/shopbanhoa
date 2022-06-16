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
import com.example.testcontentprovider.model.SanPham;
import com.example.testcontentprovider.model.Voucher;

import java.util.ArrayList;

public class VoucherActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtNoVoucher;
    RecyclerView rv_ListVoucher;
    VoucherAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        AnhXa();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new VoucherAdapter(getBaseContext(), LoadingActivity.arrayVC);
        rv_ListVoucher.setAdapter(adapter);
        rv_ListVoucher.setLayoutManager(new LinearLayoutManager(this));
        if(LoadingActivity.arrayVC.size()  == 0 || LoadingActivity.arrayVC == null)
        {
            txtNoVoucher.setVisibility(View.VISIBLE);
            rv_ListVoucher.setVisibility(View.GONE);
        }else{
            txtNoVoucher.setVisibility(View.GONE);
            rv_ListVoucher.setVisibility(View.VISIBLE);
        }
    }
    public void AnhXa(){
        toolbar = findViewById(R.id.toolbar_voucher);
        txtNoVoucher = findViewById(R.id.txtNoVoucher);
        rv_ListVoucher = findViewById(R.id.rv_listVoucher);
    }
}