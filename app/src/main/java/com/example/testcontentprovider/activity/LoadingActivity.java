package com.example.testcontentprovider.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.testcontentprovider.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoadingActivity extends AppCompatActivity {
    public static String Description1, Description2, Description3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadDuLieuFirebase();
                Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                startActivity(intent);
                //finish();
            }
        }, 1000);
    }
    public void LoadDuLieuFirebase() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("Description1");
        DatabaseReference myRef2 = database.getReference("Description2");
        DatabaseReference myRef3 = database.getReference("Description3");

        myRef1.setValue("Sứ mệnh của chúng tôi là giúp bạn trao đi tâm tư và biến mọi dịp trọng đại của bạn trở nên đặc biệt hơn.");
        myRef2.setValue("Đội ngũ giàu kinh nghiệm của chúng tôi luôn đảm bảo đem đến cho bạn những bó hoa tươi nhất được gói cùng các loại nguyên vật liệu chất lượng cao. Chúng tôi cung cấp nhiều chủng loại hoa, kích thước và thiết kế khác nhau phù hợp cho bất kỳ dịp nào bạn cần.");
        myRef3.setValue("Thông qua nền tảng trực tuyến, chúng tôi mong muốn đem đến cho bạn trải nghiệm thật tiện lợi, dễ dàng và tràn đầy niềm vui.");


        // Read from the database
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot snapshot) {
                Description1 = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot snapshot) {
                Description2 = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot snapshot) {
                Description3 = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}