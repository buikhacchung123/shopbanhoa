package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.CartAdapter;
import com.example.testcontentprovider.model.GioHang;

import java.text.DecimalFormat;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    static TextView giohangtrong;
    static TextView txtTongtien;
    Toolbar toolbar;
    RecyclerView recyclerView;
    Button btnTiepTucMuahang, btnThanhToan;
    static CartAdapter adapter;
    List<GioHang> gioHangList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        AnhXa();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(MainActivity.manggiohang.size() == 0)
        {
            giohangtrong.setVisibility(View.VISIBLE);
        }
        else {
            adapter = new CartAdapter(getApplicationContext(), MainActivity.manggiohang);
            recyclerView.setAdapter(adapter);
        }
        tinhTongTien();

        btnTiepTucMuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public static void tinhTongTien() {
        double tongtien = 0;
        for(int i = 0; i < MainActivity.manggiohang.size(); i++)
        {
            tongtien += (MainActivity.manggiohang.get(i).getGia() * MainActivity.manggiohang.get(i).getSoluong());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongtien.setText(decimalFormat.format(tongtien) + " VNĐ");
    }
    public static void checkGH(){
        if(MainActivity.manggiohang.size() <= 0){
            giohangtrong.setVisibility(View.VISIBLE);
        }else {
            giohangtrong.setVisibility(View.INVISIBLE);
            adapter.notifyDataSetChanged();
            tinhTongTien();
        }
    }
    private void AnhXa() {
        giohangtrong = findViewById(R.id.txtcartnull);
        txtTongtien = findViewById(R.id.txttongtien);
        toolbar = findViewById(R.id.cart_toolbar);
        recyclerView = findViewById(R.id.rcv_cart);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnTiepTucMuahang = findViewById(R.id.btntieptucmua);
    }
}