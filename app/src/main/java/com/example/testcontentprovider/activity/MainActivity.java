package com.example.testcontentprovider.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.DMSPAdapter;
import com.example.testcontentprovider.data.ApiService;
import com.example.testcontentprovider.fragment.HomeFragment;
import com.example.testcontentprovider.fragment.FavoriteFragment;
import com.example.testcontentprovider.fragment.ProfileFragment;
import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.GioHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    Fragment currentfragment;
    public static List<GioHang> manggiohang;
    private DMSPAdapter dmspAdapter;
    //private List<DanhMuc> mangdanhmuc;
    DanhMuc dm = new DanhMuc();
    private ApiService apiService;
    ListView listView;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    public static String CurrentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        LoadFrame(new HomeFragment());
        if(getIntent().getSerializableExtra("CurrentUser") != null)
            CurrentUser = getIntent().getSerializableExtra("CurrentUser").toString().toLowerCase();
        dmspAdapter = new DMSPAdapter(LoadingActivity.arrayDM, getBaseContext());
        listView.setAdapter(dmspAdapter);


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
                intentCategory.putExtra("Category",LoadingActivity.arrayDM.get(position));
                startActivity(intentCategory);
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
    }
    public void LoadFrame(Fragment a) {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framlayout,a);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}