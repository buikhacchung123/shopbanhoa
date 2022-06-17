package com.example.testcontentprovider.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.data.Constance;
import com.example.testcontentprovider.data.RetrofitClient;
import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.HoaDon;
import com.example.testcontentprovider.model.SanPham;
import com.example.testcontentprovider.model.Voucher;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity {
    private ApiService apiService;
    public static List<DanhMuc> arrayDM;
    public static List<SanPham> arraySP;
    public static List<HoaDon> arrayHD;
    public static List<Voucher> arrayVC;
    public static String Description1;
    public static String Description2;
    public static String Description3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        apiService = RetrofitClient.getClient(Constance.API_URL).create(ApiService.class);
        LoadingAllDanhMucs();
        LoadingAllHoadons();
        //LoadingAllKhachHangs();
        LoadingAllVouchers();
        LoadingAllSanPhams();
        LoadDuLieuFirebase();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 10000);
    }
    private void LoadingAllDanhMucs() {
        Call<List<DanhMuc>> call = apiService.getAllDanhMucs();
        call.enqueue(new Callback<List<DanhMuc>>() {
            @Override
            public void onResponse(Call<List<DanhMuc>> call, Response<List<DanhMuc>> response) {
                arrayDM = response.body();
            }

            @Override
            public void onFailure(Call<List<DanhMuc>> call, Throwable t) {

            }
        });

    }
    private void LoadingAllSanPhams(){
        Call<List<SanPham>> call = apiService.getAllSanPhams();
        call.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                arraySP = response.body();
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }
    private void LoadingAllHoadons(){
        Call<List<HoaDon>> call = apiService.getAllHoadons(LoginActivity.CURRENT_USER.getMaND()+"");
        call.enqueue(new Callback<List<HoaDon>>() {
            @Override
            public void onResponse(Call<List<HoaDon>> call, Response<List<HoaDon>> response) {
                arrayHD = response.body();
            }

            @Override
            public void onFailure(Call<List<HoaDon>> call, Throwable t) {

            }
        });
    }
    private void LoadingAllVouchers() {
        Call<List<Voucher>> call = apiService.getAllVouchers();
        call.enqueue(new Callback<List<Voucher>>() {
            @Override
            public void onResponse(Call<List<Voucher>> call, Response<List<Voucher>> response) {
                arrayVC = response.body();
            }

            @Override
            public void onFailure(Call<List<Voucher>> call, Throwable t) {

            }
        });
    }
    public void LoadDuLieuFirebase(){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("Description1");
        DatabaseReference myRef2 = database.getReference("Description2");
        DatabaseReference myRef3 = database.getReference("Description3");

        //myRef1.setValue("Sứ mệnh của chúng tôi là giúp bạn trao đi tâm tư và biến mọi dịp trọng đại của bạn trở nên đặc biệt hơn.");
        //myRef2.setValue("Đội ngũ giàu kinh nghiệm của chúng tôi luôn đảm bảo đem đến cho bạn những bó hoa tươi nhất được gói cùng các loại nguyên vật liệu chất lượng cao. Chúng tôi cung cấp nhiều chủng loại hoa, kích thước và thiết kế khác nhau phù hợp cho bất kỳ dịp nào bạn cần.");
        //myRef3.setValue("Thông qua nền tảng trực tuyến, chúng tôi mong muốn đem đến cho bạn trải nghiệm thật tiện lợi, dễ dàng và tràn đầy niềm vui.");



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