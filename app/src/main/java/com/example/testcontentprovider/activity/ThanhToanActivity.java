package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.CartAdapter;
import com.example.testcontentprovider.adapter.PaymentAdapter;

public class ThanhToanActivity extends AppCompatActivity {
    RecyclerView rvThanhToan;
    static PaymentAdapter adapter;
    TextView txtTongTien, txtTongSL, txtResult_VC, txtGiamGia, txtTamTinh;
    Button btnApDung;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        AnhXa();

        adapter = new PaymentAdapter(getApplicationContext(), MainActivity.manggiohang);
        rvThanhToan.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvThanhToan.setLayoutManager(layoutManager);
        rvThanhToan.setAdapter(adapter);
        txtTamTinh.setText(getIntent().getStringExtra("TongTien"));
        txtTongSL.setText(getIntent().getStringExtra("TongSL"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnApDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResult_VC.setText("Mã voucher ko tồn tại");
                txtGiamGia.setText("0 VNĐ");
                txtResult_VC.setVisibility(View.VISIBLE);
            }
        });
    }
    public void AnhXa(){
        rvThanhToan = findViewById(R.id.rcv_thanhToan);
        txtTongTien = findViewById(R.id.txtTongTien);
        txtTongSL = findViewById(R.id.txtTongSL);
        txtResult_VC = findViewById(R.id.tv_ResultVoucher);
        btnApDung = findViewById(R.id.btnApDung);
        toolbar = findViewById(R.id.payment_toolbar);
        txtGiamGia = findViewById(R.id.txtGiamGia);
        txtTamTinh = findViewById(R.id.txtTamTinh);
    }
}