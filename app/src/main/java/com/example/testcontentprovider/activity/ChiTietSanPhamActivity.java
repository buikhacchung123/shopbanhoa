package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.model.ChiTietGioHang;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.SanPham;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    ImageView imgChiTiet;
    TextView TenSP,Gia,MoTa;
    Button btnMua, btnAddCart;
    ImageButton btnLove;
    SanPham sp;
    static NotificationBadge badge;
    FrameLayout frameLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        AnhXa();
        Intent intent = getIntent();
        sp = (SanPham) intent.getSerializableExtra("SPItem");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        TenSP.setText(sp.getTenSp());
        Gia.setText(decimalFormat.format(sp.getGiaBan())+" VNĐ");
        String[] imgSplit= sp.getHinhSp().split("\\.");
        String imgName = imgSplit[0];
        String PACKAGE_NAME = getPackageName();
        int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+imgName , null, null);
        imgChiTiet.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));
        MoTa.setText(sp.getMoTa());
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyMuaHang();
                badge.setText(String.valueOf(MainActivity.manggiohang.size()));
            }
        });
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyMuaHang();
                badge.setText(String.valueOf(MainActivity.manggiohang.size()));
                chuyenGioHang();
            }
        });
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenGioHang();
            }
        });
        btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLove.setImageResource(R.drawable.ic_favorite);
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void xuLyMuaHang() {
        if(MainActivity.manggiohang.size() > 0){
            boolean flag = false;
            int sl = 1;
            for(int i = 0; i < MainActivity.manggiohang.size(); i++)
            {
                if(MainActivity.manggiohang.get(i).getMaSp() == sp.getMaSp()){
                    MainActivity.manggiohang.get(i).setSoLuong(MainActivity.manggiohang.get(i).getSoLuong()+sl);
                    double thanhtien = sp.getGiaBan() * MainActivity.manggiohang.get(i).getSoLuong();
                    MainActivity.manggiohang.get(i).setThanhTien(thanhtien);
                    flag = true;
                }

            }
            if(flag == false)
            {
                double thanhtien = sp.getGiaBan() * sl;
                ChiTietGioHang ctgh = new ChiTietGioHang();
                ctgh.setMaSp(sp.getMaSp());
                ctgh.setDonGia(sp.getGiaBan());
                ctgh.setSoLuong(sl);
                ctgh.setThanhTien(thanhtien);
                ctgh.setMaGh(MainActivity.magh);
                MainActivity.manggiohang.add(ctgh);
                ApiService.apiService.setCartDetail(ctgh).enqueue(new Callback<ChiTietGioHang>() {
                    @Override
                    public void onResponse(Call<ChiTietGioHang> call, Response<ChiTietGioHang> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getBaseContext(),"Add Error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<ChiTietGioHang> call, Throwable t) {

                    }
                });
            }
        }
        else {
            int sl = 1;
            double thanhtien = sp.getGiaBan() * sl;
            ChiTietGioHang gh = new ChiTietGioHang();
            gh.setMaSp(sp.getMaSp());
            gh.setDonGia(sp.getGiaBan());
            gh.setSoLuong(sl);
            gh.setThanhTien(thanhtien);
            gh.setMaGh(MainActivity.magh);
            MainActivity.manggiohang.add(gh);
            ApiService.apiService.setCartDetail(gh).enqueue(new Callback<ChiTietGioHang>() {
                @Override
                public void onResponse(Call<ChiTietGioHang> call, Response<ChiTietGioHang> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getBaseContext(),"Code: " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                @Override
                public void onFailure(Call<ChiTietGioHang> call, Throwable t) {

                }
            });
        }
    }

    public void chuyenGioHang() {
        Intent giohang = new Intent(ChiTietSanPhamActivity.this, CartActivity.class);
        startActivity(giohang);
    }
    private void AnhXa() {
        imgChiTiet = findViewById(R.id.imgct);
        TenSP = findViewById(R.id.tv_tensp);
        Gia = findViewById(R.id.tv_giasp);
        MoTa = findViewById(R.id.txtMoTa);
        btnMua = findViewById(R.id.btnBuy);
        btnAddCart = findViewById(R.id.btnAddcart);
        toolbar = findViewById(R.id.toolbarct);
        badge = findViewById(R.id.cart_sl);
        btnLove = findViewById(R.id.btnLove);

        frameLayout = findViewById(R.id.shopingcart);
    }
    public static void checkSLSP(){
        if(MainActivity.manggiohang != null)
        {
            badge.setText(String.valueOf(MainActivity.manggiohang.size()));
        }
        else {
            badge.setText(String.valueOf(0));
        }
    }





}