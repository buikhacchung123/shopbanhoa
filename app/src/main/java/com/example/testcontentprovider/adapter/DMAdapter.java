package com.example.testcontentprovider.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.activity.ChiTietSanPhamActivity;
import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.SanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DMAdapter extends RecyclerView.Adapter<DMAdapter.MyViewHolder>{
    Context context;
    ArrayList<DanhMuc> array;

    public DMAdapter(Context context, ArrayList<DanhMuc> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhmuc, parent, false);
        item.setLayoutParams(new ViewGroup.LayoutParams((int) (parent.getWidth() * 0.6),ViewGroup.LayoutParams.MATCH_PARENT));

        return new DMAdapter.MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DanhMuc danhMuc = array.get(position);
        if (danhMuc == null) {
            return;
        }
        String[] imgSplit = danhMuc.getHinhDM().split("\\.");
        String imgName = imgSplit[0];
        String PACKAGE_NAME = context.getPackageName();
        int imgId = context.getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + imgName, null, null);


        holder.hinhAnh.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imgId));
        holder.txtTen.setText(danhMuc.getTenDM());
    }

    @Override
    public int getItemCount() {
        if (array != null) {
            return array.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen;
        ImageView hinhAnh;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTenDM);
            hinhAnh = itemView.findViewById(R.id.img_DM);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();

                }
            });
        }
    }
}
