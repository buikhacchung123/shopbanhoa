package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.CartAdapter;

public class ThanhToanActivity extends AppCompatActivity {
    RecyclerView rvThanhToan;
    static CartAdapter adapter;
    TextView txtTongTien, txtTongSL, txtResult_VC;
    Button btnApDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        AnhXa();

        adapter = new CartAdapter(getApplicationContext(), MainActivity.manggiohang);
        rvThanhToan.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvThanhToan.setLayoutManager(layoutManager);
        rvThanhToan.setAdapter(adapter);
        txtTongTien.setText(getIntent().getStringExtra("TongTien"));
        txtTongSL.setText(getIntent().getStringExtra("TongSL"));

        btnApDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtResult_VC.setText("Mã voucher ko tồn tại");
            }
        });
    }
    public void AnhXa(){
        rvThanhToan = findViewById(R.id.rcv_thanhToan);
        txtTongTien = findViewById(R.id.txtTongTien);
        txtTongSL = findViewById(R.id.txtTongSL1);
        txtResult_VC = findViewById(R.id.tv_ResultVoucher);
        btnApDung = findViewById(R.id.btnApDung);
    }
}