package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.CartAdapter;
import com.example.testcontentprovider.adapter.PaymentAdapter;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.model.Voucher;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThanhToanActivity extends AppCompatActivity {
    RecyclerView rvThanhToan;
    static PaymentAdapter adapter;
    TextView txtTongTien, txtTongSL, txtResult_VC, txtGiamGia, txtTamTinh;
    EditText edmavc;
    Button btnApDung;
    Toolbar toolbar;
    List<Voucher> vouchers;
    static int giamgia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        AnhXa();

        adapter = new PaymentAdapter(getApplicationContext(), MainActivity.manggiohang);
        rvThanhToan.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvThanhToan.setLayoutManager(layoutManager);
        rvThanhToan.setAdapter(adapter);
        int tt = getIntent().getIntExtra("tt", 0);
        txtTamTinh.setText(getIntent().getStringExtra("TongTien"));
        txtTongSL.setText(getIntent().getStringExtra("TongSL"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnApDung.setOnClickListener(new View.OnClickListener() {
            String ma = edmavc.getText().toString();
            @Override
            public void onClick(View v) {
                ApiService.apiService.getAllVoucher().enqueue(new Callback<List<Voucher>>() {
                    @Override
                    public void onResponse(Call<List<Voucher>> call, Response<List<Voucher>> response) {
                        vouchers = response.body();
                        if(vouchers != null || !vouchers.isEmpty())
                        {
                            for(Voucher vc: vouchers)
                            {
                                if(String.valueOf(vc.getMaVc()).equals(ma))
                                {
                                    txtGiamGia.setText(vc.getKhuyenMai() + " VNĐ");
                                    giamgia = vc.getKhuyenMai();
                                    break;
                                }
                                else {
                                    txtResult_VC.setText("Mã voucher ko tồn tại");
                                    txtGiamGia.setText("0 VNĐ");
                                    txtResult_VC.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Voucher>> call, Throwable t) {

                    }
                });
            }
        });
    }
    public void AnhXa(){
        rvThanhToan = findViewById(R.id.rcv_thanhToan);
        txtTongTien = findViewById(R.id.txtTongTien);
        txtTongSL = findViewById(R.id.txtTongSL);
        txtResult_VC = findViewById(R.id.tv_ResultVoucher);
        btnApDung = findViewById(R.id.btnApDung);
        toolbar = findViewById(R.id.payment_toolbar);
        txtGiamGia = findViewById(R.id.txtGiamGia);
        txtTamTinh = findViewById(R.id.txtTamTinh);
        edmavc = findViewById(R.id.ed_mavc);
    }
}