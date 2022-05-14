package com.example.testcontentprovider.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.testcontentprovider.activity.ChiTietSanPhamActivity;
import com.example.testcontentprovider.model.SanPham;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.MyViewHolder> {
    Context context;
    ArrayList<SanPham> array;

    public SanPhamAdapter(Context context, ArrayList<SanPham> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.MyViewHolder holder, int position) {
        SanPham sanPham = array.get(position);
        if (sanPham == null) {
            return;
        }
        String[] imgSplit = sanPham.getHinhsp().split("\\.");
        String imgName = imgSplit[0];
        String PACKAGE_NAME = context.getPackageName();
        int imgId = context.getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + imgName, null, null);


        holder.hinhAnh.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imgId));
        holder.txtTen.setText(sanPham.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGia.setText("Giá: " + decimalFormat.format(sanPham.getGiaban()) + " VNĐ");


    }

    @Override
    public int getItemCount() {
        if (array != null) {
            return array.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTen, txtGia;
        ImageView hinhAnh;
        Button btnMua;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            txtTen = itemView.findViewById(R.id.item_tensp);
            txtGia = itemView.findViewById(R.id.item_giasp);
            hinhAnh = itemView.findViewById(R.id.item_hinhsp);

            btnMua = itemView.findViewById(R.id.btnMua);
            btnMua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    SanPham sp = array.get(position);
                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                    intent.putExtra("SPItem", sp);
                    context.startActivity(intent);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    SanPham sp = array.get(position);
                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                    intent.putExtra("SPItem", sp);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            SanPham sp = array.get(position);
            Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
            intent.putExtra("SPItem", (Serializable) sp);
            context.startActivity(intent);
        }
    }
}
