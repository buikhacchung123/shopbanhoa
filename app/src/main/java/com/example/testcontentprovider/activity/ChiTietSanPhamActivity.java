package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
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
import com.example.testcontentprovider.model.FavoriteHistory;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.SanPham;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

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
    int tongtien, tongsl;

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
        FavoriteHistory utils = new FavoriteHistory(getBaseContext());
        if(!utils.checkExist(sp))
            btnLove.setImageResource(R.drawable.ic_unfavorite);
        else
            btnLove.setImageResource(R.drawable.ic_favorite);


        //Sự kiện trang Chi tiết sản phẩm
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
                if(utils.checkExist(sp)) {
                    btnLove.setImageResource(R.drawable.ic_unfavorite);
                    utils.removeFavoriteHistory(sp);
                }
                else {
                    btnLove.setImageResource(R.drawable.ic_favorite);
                    utils.addFavoriteHistorry(sp);
                }
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
                    int thanhtien = sp.getGiaBan() * MainActivity.manggiohang.get(i).getSoLuong();
                    MainActivity.manggiohang.get(i).setThanhTien(thanhtien);
                    ChiTietGioHang ct = MainActivity.manggiohang.get(i);
                    updateCartDetail(ct.getMaGh(), ct);
                    tinhtt();
                    Map<String, String> map = new HashMap<>();
                    map.put("maGh", "" + ct.getMaGh() + "");
                    map.put("maKh", MainActivity.makh);
                    map.put("tongSp", "" + tongsl + "");
                    map.put("tongTien", "" + tongtien + "");
                    updateCart(ct.getMaGh(), map);
                    flag = true;
                }

            }
            if(flag == false)
            {
                int thanhtien = sp.getGiaBan() * sl;
                ChiTietGioHang ctgh = new ChiTietGioHang(MainActivity.magh,
                                                            sp.getMaSp(),
                                                            sl,
                                                            sp.getGiaBan(),
                                                            thanhtien);
                MainActivity.manggiohang.add(ctgh);
                addCartDetail(ctgh);
                tinhtt();
                Map<String, String> map = new HashMap<>();
                map.put("maGh", "" + ctgh.getMaGh() + "");
                map.put("maKh", MainActivity.makh);
                map.put("tongSp", "" + tongsl + "");
                map.put("tongTien", "" + tongtien + "");
                updateCart(ctgh.getMaGh(), map);
            }
        }
        else {
            int sl = 1;
            int thanhtien = sp.getGiaBan() * sl;
            ChiTietGioHang gh = new ChiTietGioHang(MainActivity.magh,
                    sp.getMaSp(),
                    sl,
                    sp.getGiaBan(),
                    thanhtien);
            gh.setMaGh(MainActivity.magh);
            MainActivity.manggiohang.add(gh);
            addCartDetail(gh);
            tinhtt();
            Map<String, String> map = new HashMap<>();
            map.put("maGh", "" + gh.getMaGh() + "");
            map.put("maKh", MainActivity.makh);
            map.put("tongSp", "" + tongsl + "");
            map.put("tongTien", "" + tongtien + "");
            updateCart(gh.getMaGh(), map);
        }
    }

    private void updateCartDetail(int maGh, ChiTietGioHang ct) {
        ApiService.apiService.updateCartDetail(maGh, ct).enqueue(new Callback<ChiTietGioHang>() {
            @Override
            public void onResponse(Call<ChiTietGioHang> call, Response<ChiTietGioHang> response) {
                if(response.code() != 204)
                {
                    Log.e("Error: ", response.raw() + "");
                    return;
                }
            }

            @Override
            public void onFailure(Call<ChiTietGioHang> call, Throwable t) {

            }
        });
    }

    private void addCartDetail(ChiTietGioHang map) {
        ApiService.apiService.setCartDetail(map).enqueue(new Callback<ChiTietGioHang>() {
            @Override
            public void onResponse(Call<ChiTietGioHang> call, Response<ChiTietGioHang> response) {
                if(!response.message().equals("Created")){
                    Log.e("Error: ", response.raw() + "");
                    Toast.makeText(getBaseContext(),"Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<ChiTietGioHang> call, Throwable t) {

            }
        });
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
            String sl = String.valueOf(MainActivity.manggiohang.size());
            badge.setText(sl+"");
        }
        else {
            badge.setText(String.valueOf(0));
        }
    }

    private void updateCart(int maGh, Map<String, String> map) {
        ApiService.apiService.updateCart(maGh, map).enqueue(new Callback<GioHang>() {
            @Override
            public void onResponse(Call<GioHang> call, Response<GioHang> response) {
                if(response.isSuccessful())
                {
                    Log.e("Message: ", response.message());
                }
            }

            @Override
            public void onFailure(Call<GioHang> call, Throwable t) {

            }
        });
    }


    private void tinhtt() {
        tongtien = 0;
        tongsl = 0;
        for(int i = 0; i < MainActivity.manggiohang.size(); i++)
        {
            tongtien += (MainActivity.manggiohang.get(i).getDonGia() * MainActivity.manggiohang.get(i).getSoLuong());
            tongsl += MainActivity.manggiohang.get(i).getSoLuong();
        }
    }


}