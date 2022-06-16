package com.example.testcontentprovider.adapter;

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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.activity.CartActivity;
import com.example.testcontentprovider.activity.ChiTietSanPhamActivity;
import com.example.testcontentprovider.model.IImageClickListener;
import com.example.testcontentprovider.activity.MainActivity;
import com.example.testcontentprovider.model.GioHang;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    List<GioHang> gioHangList;

    public CartAdapter(Context context, List<GioHang> gioHangList) {
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
        GioHang gh = gioHangList.get(position);
        holder.txtTensp.setText(gh.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGia.setText(decimalFormat.format(gh.getGia()) + " VNĐ");
        String[] imgSplit = gh.getHinhsp().split("\\.");
        String imgName = imgSplit[0];
        String PACKAGE_NAME = context.getPackageName();
        int imgId = context.getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + imgName, null, null);
        holder.item_hinhsp.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imgId));

        holder.txtSL.setText(gh.getSoluong()+"");
        double thanhtien = gh.getSoluong() * gh.getGia();
        holder.txtThanhtien.setText(decimalFormat.format(thanhtien) + " VNĐ");
        holder.btnCongSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gioHangList.get(position).getSoluong() < 100){
                        int soluongmoi = gioHangList.get(position).getSoluong() + 1;
                        gioHangList.get(position).setSoluong(soluongmoi);
                    }
                    holder.txtSL.setText(gioHangList.get(position).getSoluong()+"");
                    double thanhtien = gioHangList.get(position).getSoluong() * gioHangList.get(position).getGia();
                    holder.txtThanhtien.setText(decimalFormat.format(thanhtien) + " VNĐ");

                    com.example.testcontentprovider.activity.CartActivity.tinhTongTien();
            }
        });
        holder.btnTruSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gioHangList.get(position).getSoluong() > 1){
                        int soluongmoi = gioHangList.get(position).getSoluong() - 1;
                        gioHangList.get(position).setSoluong(soluongmoi);

                        holder.txtSL.setText(gioHangList.get(position).getSoluong()+"");
                        double thanhtien = gioHangList.get(position).getSoluong() * gioHangList.get(position).getGia();
                        holder.txtThanhtien.setText(decimalFormat.format(thanhtien) + " VNĐ");
                        com.example.testcontentprovider.activity.CartActivity.tinhTongTien();
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn có muốn xóa sản phẩm khỏi giỏ hàng");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.manggiohang.remove(position);
                                notifyDataSetChanged();
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
                if(giatri == 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn xóa sản phẩm khỏi giỏ hàng");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.manggiohang.remove(position);
                            notifyDataSetChanged();
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
            txtThanhtien = itemView.findViewById(R.id.itemVC_NgayKetThuc);
            btnCongSL = itemView.findViewById(R.id.btnThemSL);
            btnTruSL = itemView.findViewById(R.id.btnGiamSL);
            btnXoaGH = itemView.findViewById(R.id.btnXoaSP);
            btnXoaGH.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v == btnXoaGH){
                listener.onImageClick(v, getAdapterPosition(),1);
            }
        }
    }
}
