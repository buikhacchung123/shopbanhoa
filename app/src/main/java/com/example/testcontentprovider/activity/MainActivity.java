package com.example.testcontentprovider.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.testcontentprovider.R;
import com.example.testcontentprovider.fragment.HomeFragment;
import com.example.testcontentprovider.fragment.NotificationFragment;
import com.example.testcontentprovider.fragment.ProfileFragment;
import com.example.testcontentprovider.fragment.VoucherFragment;
import com.example.testcontentprovider.model.GioHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    Fragment currentfragment;
    public static List<GioHang> manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AhXa();
        LoadFrame(new HomeFragment());

        setSupportActionBar(toolbar);

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
                        toolbar.setTitle("Thông báo");
                        currentfragment = new NotificationFragment();
                        LoadFrame(currentfragment);
                        break;
                    case R.id.bottom_reward:
                        toolbar.setTitle("Voucher");
                        currentfragment = new VoucherFragment();
                        LoadFrame(currentfragment);
                        break;
                }
                return true;

            }

        });


    }



    private void AhXa() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(manggiohang == null)
        {
            manggiohang = new ArrayList<>();
        }
    }

    public void LoadFrame(Fragment a)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framlayout,a);
        transaction.addToBackStack(null);
        transaction.commit();


    }
}