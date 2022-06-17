package com.example.testcontentprovider.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.PaymentAdapter;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.data.Constance;
import com.example.testcontentprovider.data.RetrofitClient;
import com.example.testcontentprovider.model.ChiTietGioHang;
import com.example.testcontentprovider.model.ChiTietHoaDon;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.HoaDon;
import com.example.testcontentprovider.model.Voucher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThanhToanActivity extends AppCompatActivity {
    RecyclerView rvThanhToan;
    static PaymentAdapter adapter;
    TextView txtTongTien, txtTongSL, txtResult_VC, txtGiamGia, txtTamTinh;
    EditText edmavc, edsdt, eddiachi, edngaygiao;
    Button btnApDung, btnDatHang;
    Toolbar toolbar;
    List<Voucher> vouchers;
    static int giamgia;
    static String  mavc;
    static HoaDon invoice;
    int tongtien;
    static String ma;
    private ApiService apiService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        AnhXa();
        apiService = RetrofitClient.getClient(Constance.API_URL).create(ApiService.class);
        adapter = new PaymentAdapter(getApplicationContext(), MainActivity.manggiohang);
        rvThanhToan.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvThanhToan.setLayoutManager(layoutManager);
        rvThanhToan.setAdapter(adapter);
        int tt = getIntent().getIntExtra("tt", 0);
        txtTamTinh.setText(getIntent().getStringExtra("TongTien"));
        int tongsl = Integer.parseInt(getIntent().getStringExtra("TongSL"));
        txtTongSL.setText(tongsl+"");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tongtien = tt - giamgia;
        txtTongTien.setText(tongtien + " VNĐ");
        btnApDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = edmavc.getText().toString();
                apiService.getVoucher(ma).enqueue(new Callback<Voucher>() {
                    @Override
                    public void onResponse(Call<Voucher> call, Response<Voucher> response) {
                        Voucher vc = response.body();
                        if(vc != null)
                        {
                            if(vc.getSoLuong() <= 0)
                            {
                                txtResult_VC.setText("Mã voucher đã hết");
                                txtGiamGia.setText("0 VNĐ");
                                txtResult_VC.setVisibility(View.VISIBLE);
                                mavc = null;
                                giamgia = 0;
                            }
                            if(checkNgayBd(vc.getNgayBatDau()) == false)
                            {
                                txtResult_VC.setText("Mã voucher chưa hoạt động");
                                txtGiamGia.setText("0 VNĐ");
                                txtResult_VC.setVisibility(View.VISIBLE);
                                mavc = null;
                                giamgia = 0;
                            }
                            if(checkNgayKt(vc.getNgayKetThuc()) == false)
                            {
                                txtResult_VC.setText("Mã voucher đã hết hạn");
                                txtGiamGia.setText("0 VNĐ");
                                txtResult_VC.setVisibility(View.VISIBLE);
                                mavc = null;
                                giamgia = 0;
                            }
                            else {
                                txtGiamGia.setText(vc.getKhuyenMai() + " VNĐ");
                                txtTongTien.setText(tongtien - vc.getKhuyenMai() + " VNĐ");
                                mavc = String.valueOf(vc.getMaVc());
                                giamgia = vc.getKhuyenMai();
                                vc.setSoLuong(vc.getSoLuong() - 1);
                                updateVoucher(mavc, vc);
                            }

                        }
                        else {
                            txtResult_VC.setText("Mã voucher không tồn tại");
                            txtGiamGia.setText("0 VNĐ");
                            txtResult_VC.setVisibility(View.VISIBLE);
                            mavc = null;
                            giamgia = 0;
                        }
                        Log.e("Message",giamgia+"");
                    }

                    @Override
                    public void onFailure(Call<Voucher> call, Throwable t) {
                        Log.e("Error",t.getMessage());
                    }
                });
                Log.e("Hey","");
            }
        });
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eddiachi.getText().toString().equals("") || edsdt.getText().toString().equals("") || edngaygiao.getText().toString().equals(""))
                {
                    AlertDialog.Builder b = new AlertDialog.Builder(ThanhToanActivity.this);
                    b.setMessage("Bạn phải điền đầy đủ thông tin!");
                    b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                }
                else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    String ngaylap = sdf.format(new Date());
                    String ngaygiao = edngaygiao.getText().toString();
                    String ng = ngaygiao + "T23:59:59";
                    String strtong = txtTongTien.getText().toString();
                    String tong = strtong.substring(0, strtong.length() - 4);
                    HoaDon hd = new HoaDon(ngaylap,
                            ng,
                            Integer.parseInt(MainActivity.makh),
                            mavc,
                            eddiachi.getText().toString(),
                            false,
                            Integer.parseInt(tong),
                            tongsl);
                    apiService.setHD(hd).enqueue(new Callback<HoaDon>() {
                        @Override
                        public void onResponse(Call<HoaDon> call, Response<HoaDon> response) {
                            if(response.isSuccessful())
                            {
                                Log.e("Message: ",response.message());
                                invoice = response.body();
                                for(int i = 0; i < MainActivity.manggiohang.size(); i++)
                                {
                                    ChiTietHoaDon cthd = new ChiTietHoaDon();
                                    cthd.setMaHd(Integer.parseInt(invoice.getMaHD()));
                                    cthd.setMaSp(MainActivity.manggiohang.get(i).getMaSp());
                                    cthd.setSoLuong(MainActivity.manggiohang.get(i).getSoLuong());
                                    cthd.setDonGia(MainActivity.manggiohang.get(i).getDonGia());
                                    addInvoiceDetail(cthd);
                                    ChiTietGioHang ctgh = MainActivity.manggiohang.get(i);
                                    deleteCartDetail(ctgh);
                                }
                            }
                            else {

                            }
                            Log.e("Error", response.message());
                            MainActivity.manggiohang.removeAll(MainActivity.manggiohang);
                            deleteCart(MainActivity.magh);
                            MainActivity.gioHang = null;
                            CartActivity.checkGH();
                            Intent intent = new Intent(ThanhToanActivity.this, HoanThanhThanhToanActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<HoaDon> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    private void updateVoucher(String mavc, Voucher vc) {
        apiService.updateVoucher(mavc, vc).enqueue(new Callback<Voucher>() {
            @Override
            public void onResponse(Call<Voucher> call, Response<Voucher> response) {
                if(response.isSuccessful())
                {
                    Log.e("Voucher","Update successfully");
                }
                Log.e("Voucher","Update failed");
            }

            @Override
            public void onFailure(Call<Voucher> call, Throwable t) {
                Log.e("Voucher","Call Api Failed");
            }
        });
    }

    private void addInvoiceDetail(ChiTietHoaDon ct){
        apiService.setCTHD(ct).enqueue(new Callback<ChiTietHoaDon>() {
            @Override
            public void onResponse(Call<ChiTietHoaDon> call, Response<ChiTietHoaDon> response) {
                if(response.isSuccessful())
                {
                    Log.e("Message", response.message());
                }
                Log.e("Error", response.message());
            }

            @Override
            public void onFailure(Call<ChiTietHoaDon> call, Throwable t) {

            }
        });
    }
    public void deleteCartDetail(ChiTietGioHang ct) {
        apiService.deleteCartDetail(ct.getMaGh(), ct.getMaSp())
                .enqueue(new Callback<ChiTietGioHang>() {
                    @Override
                    public void onResponse(Call<ChiTietGioHang> call, Response<ChiTietGioHang> response) {
                        if (response.isSuccessful()) {
                            Log.e("Message", "Delete successfully");
                        }
                    }

                    @Override
                    public void onFailure(Call<ChiTietGioHang> call, Throwable t) {

                    }
                });
    }
    private void deleteCart(int ma) {
        apiService.deleteCart(ma).enqueue(new Callback<GioHang>() {
            @Override
            public void onResponse(Call<GioHang> call, Response<GioHang> response) {
                if (response.isSuccessful()) {
                    Log.e("Message", "Delete successfully");

                }
            }

            @Override
            public void onFailure(Call<GioHang> call, Throwable t) {

            }
        });
    }

    public void AnhXa(){
        rvThanhToan = findViewById(R.id.rcv_thanhToan);
        txtTongTien = findViewById(R.id.txtTongTien);
        txtTongSL = findViewById(R.id.txtTongSL);
        txtResult_VC = findViewById(R.id.tv_ResultVoucher);
        btnApDung = findViewById(R.id.btnApDung);
        btnDatHang = findViewById(R.id.btnDatHang);
        toolbar = findViewById(R.id.payment_toolbar);
        txtGiamGia = findViewById(R.id.txtGiamGia);
        txtTamTinh = findViewById(R.id.txtTamTinh);
        edmavc = findViewById(R.id.ed_mavc);
        eddiachi = findViewById(R.id.txtDiaChiGiao);
        edngaygiao = findViewById(R.id.txtNgayGiao);
        edsdt = findViewById(R.id.txtSDT);
    }
    private Boolean checkNgayBd(String ngayBd)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date strday = new Date();
        try {
            strday = simpleDateFormat.parse(ngayBd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (new Date().after(strday)) {
            return true;
        }
        return false;
    }
    private Boolean checkNgayKt(String ngayKt)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date strday = new Date();
        try {
            strday = simpleDateFormat.parse(ngayKt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (new Date().before(strday)) {
            return true;
        }
        return false;
    }
}