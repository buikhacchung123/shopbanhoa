package com.example.testcontentprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.DMSPAdapter;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.fragment.FavoriteFragment;
import com.example.testcontentprovider.fragment.HomeFragment;
import com.example.testcontentprovider.fragment.ProfileFragment;
import com.example.testcontentprovider.model.ChiTietGioHang;
import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.KhachHang;
import com.example.testcontentprovider.model.SanPham;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    Fragment currentfragment;
    private DMSPAdapter dmspAdapter;
    public static List<DanhMuc> arrayDM;

    public static List<SanPham> arraySP;
    DanhMuc dm = new DanhMuc();
    ListView listView;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    KhachHang kh;
    public static String makh;
    public static GioHang gioHang;
    public static int magh;
    public static List<ChiTietGioHang> manggiohang;
    public static KhachHang CURRENT_USER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lấy dữ liệu từ login
        if(getIntent().getSerializableExtra("object_user")!=null) {
            kh = (KhachHang) getIntent().getSerializableExtra("object_user");
            CURRENT_USER = kh;
            makh = String.valueOf(kh.getMaND());
        }else {
            kh = CURRENT_USER;
        }

        //Lấy data sản phẩm, danh mục, giỏ hàng
        AnhXa();
        LoadListSanPham();
        LoadListCart();
        LoadFrame(new HomeFragment());
        LoadListDanhMuc();


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);


        //Sự kiện trang Home
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id){
                    case R.id.bottom_home:
                        toolbar.setTitle("Trang chủ");
                        currentfragment = new HomeFragment();
                        LoadFrame(currentfragment);
                        break;
                    case R.id.bottom_profile:
                        toolbar.setTitle("Thông tin tài khoản");
                        currentfragment = new ProfileFragment();
                        LoadFrame(currentfragment);
                        break;

                    case R.id.bottom_favorite:
                        toolbar.setTitle("Danh sách yêu thích");
                        currentfragment = new FavoriteFragment();
                        LoadFrame(currentfragment);
                        break;
                    case R.id.bottom_reward:
                        Intent cart = new Intent(MainActivity.this, CartActivity.class);
                        startActivity(cart);
                        break;
                }
                return true;
            }
        });
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(search);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int position, long l) {
                Intent intentCategory = new Intent(getBaseContext(), CategoryActivity.class);
                intentCategory.putExtra("Category",arrayDM.get(position));
                startActivity(intentCategory);
            }
        });
    }
    private void loadCart(int ma) {
        ApiService.apiService.getCart().enqueue(new Callback<List<GioHang>>() {
            @Override
            public void onResponse(Call<List<GioHang>> call, Response<List<GioHang>> response) {
                List<GioHang> list = response.body();
                if(list != null || list.size() > 0)
                {
                    for (GioHang gh : list)
                    {
                        if(gh.getMaKh() == ma)
                        {
                            gioHang = new GioHang();
                            gioHang.setMaGh(gh.getMaGh());
                            gioHang.setMaKh(gh.getMaKh());
                            gioHang.setTongSp(gh.getTongSp());
                            gioHang.setTongtien(gh.getTongtien());
                            magh = Integer.parseInt(gioHang.getMaGh());
                            LayDSChiTietGioHang(magh);
                            return;
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<GioHang>> call, Throwable t) {

            }
        });
    }
    public static void LayDSChiTietGioHang(int ma) {
        List<ChiTietGioHang> templist = new ArrayList<>();
        ApiService.apiService.getAllCartDetail().enqueue(new Callback<List<ChiTietGioHang>>() {
            @Override
            public void onResponse(Call<List<ChiTietGioHang>> call, Response<List<ChiTietGioHang>> response) {
                List<ChiTietGioHang> list1 = response.body();
                if(list1 != null)
                {
                    for (int i = 0; i < list1.size(); i++)
                    {
                        if(list1.get(i).getMaGh() == (ma))
                        {
                            templist.add(list1.get(i));
                        }
                    }
                    manggiohang = templist;
                }
                else {
                    manggiohang = null;
                }
            }

            @Override
            public void onFailure(Call<List<ChiTietGioHang>> call, Throwable t) {

            }
        });
    }
    private void AnhXa() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerlayout_main);
        if(manggiohang == null)
            manggiohang = new ArrayList<>();
        listView = findViewById(R.id.lv_main);
        frameLayout = findViewById(R.id.frame_search);
        arraySP = new ArrayList<>();
    }
    public void LoadFrame(Fragment a) {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framlayout,a);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void LoadListDanhMuc() {
        ApiService.apiService.getAllDanhMucs().enqueue(new Callback<List<DanhMuc>>() {
            @Override
            public void onResponse(Call<List<DanhMuc>> call, Response<List<DanhMuc>> response) {
                arrayDM = response.body();
                dmspAdapter = new DMSPAdapter(arrayDM, getApplicationContext());
                listView.setAdapter(dmspAdapter);
            }

            @Override
            public void onFailure(Call<List<DanhMuc>> call, Throwable t) {

            }
        });
    }
    private void LoadListSanPham() {
        ApiService.apiService.getSanPham().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                arraySP = response.body();
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Call api failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void LoadListCart(){
        ApiService.apiService.getCart().enqueue(new Callback<List<GioHang>>() {
            @Override
            public void onResponse(Call<List<GioHang>> call, Response<List<GioHang>> response) {
                List<GioHang> list = response.body();
                if(list != null || list.size() > 0)
                {
                    for (GioHang gh : list)
                    {
                        if(gh.getMaKh() == kh.getMaND())
                        {
                            gioHang = new GioHang();
                            gioHang.setMaGh(gh.getMaGh());
                            gioHang.setMaKh(gh.getMaKh());
                            gioHang.setTongSp(gh.getTongSp());
                            gioHang.setTongtien(gh.getTongtien());
                            magh = Integer.parseInt(gioHang.getMaGh());
                            LayDSChiTietGioHang(magh);
                            return;
                        }

                    }
                    Map<String, String> map = new HashMap<>();
                    map.put("maKh", makh);
                    map.put("tongSp", "0");
                    map.put("tongTien", "0");
                    addCart(map);
                    loadCart(kh.getMaND());
                }

            }
            @Override
            public void onFailure(Call<List<GioHang>> call, Throwable t) {

            }
        });
    }
    public void addCart(Map <String, String> map) {
        ApiService.apiService.setCart(map).enqueue(new Callback<GioHang>() {
            @Override
            public void onResponse(Call<GioHang> call, Response<GioHang> response) {
                String mess = response.message();
                if(!mess.equals("Created"))
                {
                    Log.e( "Message: ",response.message().toString());
                }
                gioHang = response.body();
                magh = Integer.parseInt(gioHang.getMaGh());
            }

            @Override
            public void onFailure(Call<GioHang> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Call Api Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}