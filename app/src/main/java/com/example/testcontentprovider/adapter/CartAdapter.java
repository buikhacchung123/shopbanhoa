package com.example.testcontentprovider.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
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
import com.example.testcontentprovider.fragment.HomeFragment;
import com.example.testcontentprovider.model.ChiTietGioHang;
import com.example.testcontentprovider.model.IImageClickListener;
import com.example.testcontentprovider.activity.MainActivity;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.SanPham;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    List<ChiTietGioHang> gioHangList;
    SanPham sp = new SanPham();

    public CartAdapter(Context context, List<ChiTietGioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChiTietGioHang gh = gioHangList.get(position);
        holder.txtTensp.setText(sp.getSPByMaSP(gh.getMaSp()).getTenSp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGia.setText(decimalFormat.format(gh.getDonGia()) + " VNĐ");
        String[] imgSplit = sp.getSPByMaSP(gh.getMaSp()).getHinhSp().split("\\.");
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

                    ChiTietGioHang ct = gioHangList.get(position);
                    updateCartDetail(ct);
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

                    ChiTietGioHang ct = gioHangList.get(position);
                    updateCartDetail(ct);

                    holder.txtSL.setText(gioHangList.get(position).getSoLuong() + "");
                    double thanhtien = gioHangList.get(position).getSoLuong() * gioHangList.get(position).getDonGia();
                    holder.txtThanhtien.setText(decimalFormat.format(thanhtien) + " VNĐ");
                    com.example.testcontentprovider.activity.CartActivity.tinhTongTien();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn xóa sản phẩm khỏi giỏ hàng");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.manggiohang.remove(position);
                            notifyDataSetChanged();

                            ChiTietGioHang ct = gioHangList.get(position);
                            deleteCartDetail(ct);

                            com.example.testcontentprovider.activity.CartActivity.tinhTongTien();
                            CartActivity.checkGH();
                            ChiTietSanPhamActivity.checkSLSP();
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
                            MainActivity.manggiohang.remove(position);
                            notifyDataSetChanged();

                            ChiTietGioHang ct = gioHangList.get(position);
                            deleteCartDetail(ct);

                            com.example.testcontentprovider.activity.CartActivity.tinhTongTien();
                            CartActivity.checkGH();
                            ChiTietSanPhamActivity.checkSLSP();
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

    @Override
    public int getItemCount() {
        return gioHangList.size();
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
            txtThanhtien = itemView.findViewById(R.id.itemPayment_ThanhTien);
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
        ApiService.apiService.updateCartDetail(ct.getMaGh(), ct)
                .enqueue(new Callback<ChiTietGioHang>() {
                    @Override
                    public void onResponse(Call<ChiTietGioHang> call, Response<ChiTietGioHang> response) {
                        if (response.isSuccessful()) {
                            MainActivity.LayDSChiTietGioHang(ct.getMaGh());
                        }
                    }

                    @Override
                    public void onFailure(Call<ChiTietGioHang> call, Throwable t) {

                    }
                });
    }

    public void deleteCartDetail(ChiTietGioHang ct) {
        ApiService.apiService.deleteCartDetail(ct.getMaGh(), ct.getMaSp())
                .enqueue(new Callback<ChiTietGioHang>() {
                    @Override
                    public void onResponse(Call<ChiTietGioHang> call, Response<ChiTietGioHang> response) {
                        if (response.isSuccessful()) {
                            MainActivity.LayDSChiTietGioHang(ct.getMaGh());
                        }
                    }

                    @Override
                    public void onFailure(Call<ChiTietGioHang> call, Throwable t) {

                    }
                });
    }
}
