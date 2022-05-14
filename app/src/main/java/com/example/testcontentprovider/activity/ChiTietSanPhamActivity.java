package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.SanPham;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    ImageView imgChiTiet;
    TextView TenSP,Gia,MoTa;
    Button btnMua, btnAddCart;
    MainActivity mainActivity;
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
        sp = sp = (SanPham) intent.getSerializableExtra("SPItem");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        TenSP.setText(sp.getTensp());
        Gia.setText(decimalFormat.format(sp.getGiaban())+" VNĐ");
        String[] imgSplit= sp.getHinhsp().split("\\.");
        String imgName = imgSplit[0];
        String PACKAGE_NAME = getPackageName();
        int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/"+imgName , null, null);
        imgChiTiet.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));
        MoTa.setText(sp.getMota());
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyMuaHang();
                badge.setText(String.valueOf(mainActivity.manggiohang.size()));
            }
        });
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyMuaHang();
                badge.setText(String.valueOf(mainActivity.manggiohang.size()));
                chuyenGioHang();
            }
        });
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenGioHang();
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
        if(mainActivity.manggiohang.size() > 0){
            boolean flag = false;
            int sl = 1;
            for(int i = 0; i < mainActivity.manggiohang.size(); i++)
            {
                if(mainActivity.manggiohang.get(i).getMasp().equals(sp.getMasp())){
                    mainActivity.manggiohang.get(i).setSoluong(mainActivity.manggiohang.get(i).getSoluong()+sl);
                    double thanhtien = sp.getGiaban() * mainActivity.manggiohang.get(i).getSoluong();
                    mainActivity.manggiohang.get(i).setThanhtien(thanhtien);
                    flag = true;
                }

            }
            if(flag == false)
            {
                double thanhtien = sp.getGiaban() * sl;
                GioHang gh = new GioHang();
                gh.setMasp(sp.getMasp());
                gh.setTensp(sp.getTensp());
                gh.setHinhsp(sp.getHinhsp());
                gh.setGia(sp.getGiaban());
                gh.setSoluong(sl);
                gh.setThanhtien(thanhtien);
                mainActivity.manggiohang.add(gh);
            }
        }
        else {
            int sl = 1;
            double thanhtien = sp.getGiaban() * sl;
            GioHang gh = new GioHang();
            gh.setMasp(sp.getMasp());
            gh.setTensp(sp.getTensp());
            gh.setHinhsp(sp.getHinhsp());
            gh.setGia(sp.getGiaban());
            gh.setSoluong(sl);
            gh.setThanhtien(thanhtien);
            mainActivity.manggiohang.add(gh);
        }
    }

    public void chuyenGioHang()
    {
        Intent giohang = new Intent(ChiTietSanPhamActivity.this, CartActivity.class);
        startActivity(giohang);
    }
    private void AnhXa() {
        imgChiTiet = findViewById(R.id.imgct);
        TenSP = findViewById(R.id.tv_tensp);
        Gia = findViewById(R.id.tv_giasp);
        MoTa = findViewById(R.id.txtmota);
        btnMua = findViewById(R.id.btnBuy);
        btnAddCart = findViewById(R.id.btnAddcart);
        toolbar = findViewById(R.id.toolbarct);
        badge = findViewById(R.id.cart_sl);


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