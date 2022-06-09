package com.example.testcontentprovider.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.DMAdapter;
import com.example.testcontentprovider.adapter.DMSPAdapter;
import com.example.testcontentprovider.adapter.SanPhamAdapter;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.fragment.HomeFragment;
import com.example.testcontentprovider.fragment.NotificationFragment;
import com.example.testcontentprovider.fragment.ProfileFragment;
import com.example.testcontentprovider.model.ChiTietGioHang;
import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.KhachHang;
import com.example.testcontentprovider.model.SanPham;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    Fragment currentfragment;
    static GioHang gioHang;
    public static List<ChiTietGioHang> manggiohang;
    private DMSPAdapter dmspAdapter;
    private List<DanhMuc> mangdanhmuc;
    public static List<SanPham> dssp;
    DanhMuc dm = new DanhMuc();
    ListView listView;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    KhachHang kh;
    public static String magh;
    public static String makh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        LoadDSSP();
        LoadFrame(new HomeFragment());

        LoadDMMenu();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        // lay du lieu user tu login
        kh = (KhachHang) getIntent().getSerializableExtra("object_user");
        makh = String.valueOf(kh.getMaND());
        KtraGioHang();
        if(gioHang == null)
        {
            GioHang gh = new GioHang(kh.getMaND(), 0,0);
            addCart(gh);
            KtraGioHang();
        }

        LayDSChiTietGioHang();
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

                    case R.id.bottom_notification:
                        toolbar.setTitle("Danh sách yêu thích");
                        currentfragment = new NotificationFragment();
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
                intentCategory.putExtra("Category",mangdanhmuc.get(position));
                startActivity(intentCategory);
            }
        });
    }

    public static void LayDSChiTietGioHang() {
        ApiService.apiService.getAllCartDetail().enqueue(new Callback<List<ChiTietGioHang>>() {
            @Override
            public void onResponse(Call<List<ChiTietGioHang>> call, Response<List<ChiTietGioHang>> response) {
                List<ChiTietGioHang> list1 = response.body();
                if(list1 != null)
                {
                    for (int i = 0; i < list1.size(); i++)
                    {
                        if(list1.get(i).getMaGh().equals(gioHang.getMaGh()))
                        {
                            manggiohang.add(list1.get(i));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ChiTietGioHang>> call, Throwable t) {

            }
        });
    }

    private void KtraGioHang() {
        ApiService.apiService.getAllCart().enqueue(new Callback<List<GioHang>>() {
            @Override
            public void onResponse(Call<List<GioHang>> call, Response<List<GioHang>> response) {
                List<GioHang> list = response.body();
                if(list == null || list.isEmpty())
                {
                    return;
                }

                for(GioHang gh : list)
                {
                    if(String.valueOf(gh.getMaKh()).equals(kh.getMaND()))
                    {
                        gioHang = gh;
                        magh = gioHang.getMaGh();
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GioHang>> call, Throwable t) {

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
        dssp = new ArrayList<>();
    }
    public void LoadFrame(Fragment a) {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framlayout,a);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void LoadDMMenu() {
        ApiService.apiService.getAllDanhMucs().enqueue(new Callback<List<DanhMuc>>() {
            @Override
            public void onResponse(Call<List<DanhMuc>> call, Response<List<DanhMuc>> response) {
                mangdanhmuc = response.body();
                dmspAdapter = new DMSPAdapter(mangdanhmuc, getApplicationContext());
                listView.setAdapter(dmspAdapter);
            }

            @Override
            public void onFailure(Call<List<DanhMuc>> call, Throwable t) {

            }
        });
    }

    private void LoadDSSP() {
        ApiService.apiService.getSanPham().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                dssp = response.body();

            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Call api failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addCart(GioHang gh)
    {
        ApiService.apiService.setCart(gioHang).enqueue(new Callback<GioHang>() {
            @Override
            public void onResponse(Call<GioHang> call, Response<GioHang> response) {
                if(response.isSuccessful())
                {
                    gioHang = response.body();
                    magh = gioHang.getMaGh();
                }
                Toast.makeText(getBaseContext(),"Add New Cart Failed, Code: " + response.code(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<GioHang> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Call Api Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}