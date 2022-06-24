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
import com.example.testcontentprovider.model.ChiTietGioHang;
import com.example.testcontentprovider.model.ChiTietHoaDon;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.HoaDon;
import com.example.testcontentprovider.model.SanPham;
import com.example.testcontentprovider.model.Voucher;

import java.text.DecimalFormat;
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
    static Voucher voucher;
    static int giamgia;
    static String  mavc;
    static HoaDon invoice;
    int tongtien;
    static String ma;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

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
        int tongsl = Integer.parseInt(getIntent().getStringExtra("TongSL"));
        txtTongSL.setText(tongsl+"");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tongtien = tt - giamgia;
        txtTongTien.setText(decimalFormat.format(tongtien) + " VNĐ");
        btnApDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = edmavc.getText().toString();
                ApiService.apiService.getVoucher(ma).enqueue(new Callback<Voucher>() {
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
                            else if(checkNgayBd(vc.getNgayBatDau()) == false)
                            {
                                txtResult_VC.setText("Mã voucher chưa hoạt động");
                                txtGiamGia.setText("0 VNĐ");
                                txtResult_VC.setVisibility(View.VISIBLE);
                                mavc = null;
                                giamgia = 0;
                            }
                            else if(checkNgayKt(vc.getNgayKetThuc()) == false)
                            {
                                txtResult_VC.setText("Mã voucher đã hết hạn");
                                txtGiamGia.setText("0 VNĐ");
                                txtResult_VC.setVisibility(View.VISIBLE);
                                mavc = null;
                                giamgia = 0;
                            }
                            else {
                                AlertDialog.Builder b = new AlertDialog.Builder(ThanhToanActivity.this);
                                b.setMessage("Áp dụng thành công");
                                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                        edmavc.setEnabled(false);
                                        btnApDung.setEnabled(false);
                                        eddiachi.requestFocus();
                                    }
                                });
                                AlertDialog al = b.create();
                                al.show();

                                txtGiamGia.setText(decimalFormat.format(vc.getKhuyenMai()) + " VNĐ");
                                tongtien -= vc.getKhuyenMai();
                                txtTongTien.setText(decimalFormat.format(tongtien) + " VNĐ");
                                mavc = String.valueOf(vc.getMaVc());
                                giamgia = vc.getKhuyenMai();
                                vc.setSoLuong(vc.getSoLuong() - 1);
                                voucher = vc;
                            }

                        }
                        else {
                            txtResult_VC.setText("Mã voucher không tồn tại");
                            txtGiamGia.setText("0 VNĐ");
                            txtResult_VC.setVisibility(View.VISIBLE);
                            mavc = null;
                            giamgia = 0;
                        }
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

                    int t = tongtien;
                    HoaDon hd = new HoaDon(ngaylap,
                            ng,
                            Integer.parseInt(MainActivity.makh),
                            mavc,
                            eddiachi.getText().toString(),
                            false,
                            Integer.parseInt(String.valueOf(t)),
                            tongsl);
                    ApiService.apiService.setHD(hd).enqueue(new Callback<HoaDon>() {
                        @Override
                        public void onResponse(Call<HoaDon> call, Response<HoaDon> response) {
                            if(response.isSuccessful())
                            {
                                Log.e("Message: ",response.message());
                                invoice = response.body();
                                for(int i = 0; i < MainActivity.manggiohang.size(); i++)
                                {
                                    ChiTietHoaDon cthd = new ChiTietHoaDon();
                                    cthd.setMaHd(Integer.parseInt(invoice.getMaHd()));
                                    cthd.setMaSp(MainActivity.manggiohang.get(i).getMaSp());
                                    cthd.setSoLuong(MainActivity.manggiohang.get(i).getSoLuong());
                                    cthd.setDonGia(MainActivity.manggiohang.get(i).getDonGia());
                                    addInvoiceDetail(cthd);
                                    ChiTietGioHang ctgh = MainActivity.manggiohang.get(i);
                                    deleteCartDetail(ctgh);
                                }
                            }
                            else {
                                Log.e("Error", response.message());
                            }
                            for(int i = 0; i < MainActivity.manggiohang.size(); i++)
                            {
                                SanPham sp = new SanPham();
                                sp = sp.getSPByMaSP(MainActivity.manggiohang.get(i).getMaSp());
                                int newsl = sp.getSoLuong() - MainActivity.manggiohang.get(i).getSoLuong();
                                sp.setSoLuong(newsl);
                                updateSP(sp.getMaSp(), sp);
                            }

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
                    updateVoucher(mavc, voucher);

                }
            }
        });
    }

    private void updateSP(int maSp, SanPham sp) {
        ApiService.apiService.updateSanPham(maSp, sp).enqueue(new Callback<SanPham>() {
            @Override
            public void onResponse(Call<SanPham> call, Response<SanPham> response) {
                if(response.isSuccessful())
                {
                    Log.e("SP","Update successfully");
                }
                else {
                    Log.e("SP", "Update failed");
                }
            }

            @Override
            public void onFailure(Call<SanPham> call, Throwable t) {
                Log.e("SP","Call Api Failed");
            }
        });
    }

    private void updateVoucher(String mavc, Voucher vc) {
        ApiService.apiService.updateVoucher(mavc, vc).enqueue(new Callback<Voucher>() {
            @Override
            public void onResponse(Call<Voucher> call, Response<Voucher> response) {
                if(response.isSuccessful())
                {
                    Log.e("Voucher","Update successfully");
                }
                else {
                    Log.e("Voucher", "Update failed");
                }
            }

            @Override
            public void onFailure(Call<Voucher> call, Throwable t) {
                Log.e("Voucher","Call Api Failed");
            }
        });
    }

    private void addInvoiceDetail(ChiTietHoaDon ct){
        ApiService.apiService.setCTHD(ct).enqueue(new Callback<ChiTietHoaDon>() {
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
        ApiService.apiService.deleteCartDetail(ct.getMaGh(), ct.getMaSp())
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
        ApiService.apiService.deleteCart(ma).enqueue(new Callback<GioHang>() {
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