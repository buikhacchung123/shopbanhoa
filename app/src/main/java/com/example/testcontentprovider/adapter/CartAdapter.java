package com.example.testcontentprovider.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.activity.CartActivity;
import com.example.testcontentprovider.activity.ChiTietSanPhamActivity;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.data.Constance;
import com.example.testcontentprovider.data.RetrofitClient;
import com.example.testcontentprovider.fragment.HomeFragment;
import com.example.testcontentprovider.model.ChiTietGioHang;
import com.example.testcontentprovider.model.IImageClickListener;
import com.example.testcontentprovider.activity.MainActivity;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.SanPham;
import com.google.android.gms.common.api.Api;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    List<ChiTietGioHang> gioHangList;
    SanPham sp = new SanPham();
    int tongtien;
    int tongsl;
    private ApiService apiService;
    public CartAdapter(Context context, List<ChiTietGioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        //apiService = RetrofitClient.getClient(Constance.API_URL).create(ApiService.class);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        apiService = RetrofitClient.getClient(Constance.API_URL).create(ApiService.class);
        ChiTietGioHang gh = gioHangList.get(position);
        holder.txtTensp.setText(sp.getSPByMaSP(gh.getMaSp()).getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGia.setText(decimalFormat.format(gh.getDonGia()) + " VNĐ");
        String[] imgSplit = sp.getSPByMaSP(gh.getMaSp()).getHinhsp().split("\\.");
        String imgName = imgSplit[0];
        String PACKAGE_NAME = context.getPackageName();
        int imgId = context.getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + imgName, null, null);
        holder.item_hinhsp.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imgId));

        holder.txtSL.setText(gh.getSoLuong() + "");
        double thanhtien = gh.getSoLuong() * gh.getDonGia();
        holder.txtThanhtien.setText(decimalFormat.format(thanhtien) + " VNĐ");
        holder.btnCongSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gioHangList.get(position).getSoLuong() < 100) {
                    int soluongmoi = gioHangList.get(position).getSoLuong() + 1;
                    gioHangList.get(position).setSoLuong(soluongmoi);
                    int thanhtien = (int) (soluongmoi * gioHangList.get(position).getDonGia());
                    gioHangList.get(position).setThanhTien(thanhtien);
                    ChiTietGioHang ct = gioHangList.get(position);
                    updateCartDetail(ct);
                    tinhtt();
                    Map<String, String> map = new HashMap<>();
                    map.put("maGh", "" + ct.getMaGh() + "");
                    map.put("maKh", MainActivity.makh);
                    map.put("tongSp", "" + tongsl + "");
                    map.put("tongTien", "" + tongtien + "");
                    updateCart(ct.getMaGh(), map);
                }
                holder.txtSL.setText(gioHangList.get(position).getSoLuong() + "");
                double thanhtien = gioHangList.get(position).getSoLuong() * gioHangList.get(position).getDonGia();
                holder.txtThanhtien.setText(decimalFormat.format(thanhtien) + " VNĐ");

                com.example.testcontentprovider.activity.CartActivity.tinhTongTien();
            }
        });
        holder.btnTruSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gioHangList.get(position).getSoLuong() > 1) {
                    int soluongmoi = gioHangList.get(position).getSoLuong() - 1;
                    gioHangList.get(position).setSoLuong(soluongmoi);
                    int thanhtien = (int) (soluongmoi * gioHangList.get(position).getDonGia());
                    gioHangList.get(position).setThanhTien(thanhtien);
                    ChiTietGioHang ct = gioHangList.get(position);
                    updateCartDetail(ct);
                    holder.txtSL.setText(gioHangList.get(position).getSoLuong() + "");
                    holder.txtThanhtien.setText(decimalFormat.format(thanhtien) + " VNĐ");
                    com.example.testcontentprovider.activity.CartActivity.tinhTongTien();
                    tinhtt();
                    Map<String, String> map = new HashMap<>();
                    map.put("maGh", "" + ct.getMaGh() + "");
                    map.put("maKh", MainActivity.makh);
                    map.put("tongSp", "" + tongsl + "");
                    map.put("tongTien", "" + tongtien + "");
                    updateCart(ct.getMaGh(), map);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn xóa sản phẩm khỏi giỏ hàng");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ChiTietGioHang ct = gioHangList.get(position);
                            deleteCartDetail(ct);
                            MainActivity.manggiohang.remove(position);
                            notifyDataSetChanged();
                            com.example.testcontentprovider.activity.CartActivity.tinhTongTien();
                            tinhtt();
                            Map<String, String> map = new HashMap<>();
                            map.put("maGh", "" + ct.getMaGh() + "");
                            map.put("maKh", MainActivity.makh);
                            map.put("tongSp", "" + tongsl + "");
                            map.put("tongTien", "" + tongtien + "");
                            updateCart(ct.getMaGh(), map);
                            CartActivity.checkGH();
                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });
        holder.setListener(new IImageClickListener() {
            @Override
            public void onImageClick(View view, int position, int giatri) {
                if (giatri == 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn xóa sản phẩm khỏi giỏ hàng");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ChiTietGioHang ct = gioHangList.get(position);
                            deleteCartDetail(ct);
                            MainActivity.manggiohang.remove(position);
                            notifyDataSetChanged();
                            tinhtt();
                            Map<String, String> map = new HashMap<>();
                            map.put("maGh", "" + ct.getMaGh() + "");
                            map.put("maKh", MainActivity.makh);
                            map.put("tongSp", "" + tongsl + "");
                            map.put("tongTien", "" + tongtien + "");
                            updateCart(ct.getMaGh(), map);
                            com.example.testcontentprovider.activity.CartActivity.tinhTongTien();
                            CartActivity.checkGH();
                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    private void updateCart(int maGh, Map<String, String> map) {
        apiService.updateCart(maGh, map).enqueue(new Callback<GioHang>() {
            @Override
            public void onResponse(Call<GioHang> call, Response<GioHang> response) {
                if(response.isSuccessful())
                {
                    Log.e("Message: ", response.message());
                }
            }

            @Override
            public void onFailure(Call<GioHang> call, Throwable t) {

            }
        });
    }

    private void tinhtt() {
        tongsl = 0;
        tongtien = 0;
        for(int i = 0; i < MainActivity.manggiohang.size(); i++)
        {
            tongtien += (MainActivity.manggiohang.get(i).getDonGia() * MainActivity.manggiohang.get(i).getSoLuong());
            tongsl += MainActivity.manggiohang.get(i).getSoLuong();
        }
    }

    @Override
    public int getItemCount() {
        if(gioHangList != null)
            return gioHangList.size();
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_hinhsp, btnXoaGH;
        Button btnTruSL, btnCongSL;
        TextView txtGia, txtTensp, txtSL, txtThanhtien;
        IImageClickListener listener;

        public void setListener(IImageClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_hinhsp = itemView.findViewById(R.id.itemcart_hinhsp);
            txtGia = itemView.findViewById(R.id.itemcart_gia);
            txtSL = itemView.findViewById(R.id.itemcart_sl);
            txtTensp = itemView.findViewById(R.id.itemcart_tensp);
            txtThanhtien = itemView.findViewById(R.id.itemVC_NgayKetThuc);
            btnCongSL = itemView.findViewById(R.id.btnThemSL);
            btnTruSL = itemView.findViewById(R.id.btnGiamSL);
            btnXoaGH = itemView.findViewById(R.id.btnXoaSP);
            btnXoaGH.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == btnXoaGH) {
                listener.onImageClick(v, getAdapterPosition(), 1);
            }
        }
    }

    public void updateCartDetail(ChiTietGioHang ct) {
        apiService.updateCartDetail(ct.getMaGh(), ct)
                .enqueue(new Callback<ChiTietGioHang>() {
                    @Override
                    public void onResponse(Call<ChiTietGioHang> call, Response<ChiTietGioHang> response) {
                        if (response.code() == 204) {
                            Log.e("Notification: ", "Update successfully");
                        }
                    }
                    @Override
                    public void onFailure(Call<ChiTietGioHang> call, Throwable t) {

                    }
                });
    }

    public void deleteCartDetail(ChiTietGioHang ct) {
        apiService.deleteCartDetail(ct.getMaGh(), ct.getMaSp())
                .enqueue(new Callback<ChiTietGioHang>() {
                    @Override
                    public void onResponse(Call<ChiTietGioHang> call, Response<ChiTietGioHang> response) {
                        if (response.isSuccessful()) {
                            Log.e("Notification: ", "Delete successfully");
                        }
                    }

                    @Override
                    public void onFailure(Call<ChiTietGioHang> call, Throwable t) {

                    }
                });
    }
}
