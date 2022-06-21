package com.example.testcontentprovider.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.testcontentprovider.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShopInforActivity extends AppCompatActivity {
    Button btnHome;
    Toolbar toolbar;
    //String Description1, Description2, Description3;
    TextView txtPara1, txtPara2, txtPara3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_infor);

        AnhXa();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Gán dữ liệu từ Firebase
        txtPara1.setText(LoadingActivity.Description1);
        txtPara2.setText(LoadingActivity.Description2);
        txtPara3.setText(LoadingActivity.Description3);




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShopInforActivity.this,MainActivity.class));
            }
        });
    }



    public void AnhXa(){
        btnHome = findViewById(R.id.btnHome);
        toolbar = findViewById(R.id.toolbar_shopinfor);
        txtPara1 = findViewById(R.id.txtPara1);
        txtPara2 = findViewById(R.id.txtPara2);
        txtPara3 = findViewById(R.id.txtPara3);
    }
}