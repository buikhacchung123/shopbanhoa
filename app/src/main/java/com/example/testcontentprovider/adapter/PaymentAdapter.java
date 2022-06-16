package com.example.testcontentprovider.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.IImageClickListener;

import java.text.DecimalFormat;
import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder_Payment> {
    Context context;
    List<GioHang> gioHangList;

    public PaymentAdapter(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public PaymentAdapter.MyViewHolder_Payment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment, parent, false);

        return new PaymentAdapter.MyViewHolder_Payment(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_Payment holder, int position) {
        GioHang gh = gioHangList.get(position);
        holder.txtTenSP.setText(gh.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGia.setText("Giá: "+decimalFormat.format(gh.getGia()) + " VNĐ");
        String[] imgSplit = gh.getHinhsp().split("\\.");
        String imgName = imgSplit[0];
        String PACKAGE_NAME = context.getPackageName();
        int imgId = context.getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + imgName, null, null);
        holder.item_hinhSP.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imgId));

        holder.txtSL.setText("Số lượng: "+gh.getSoluong()+"");
        double thanhtien = gh.getSoluong() * gh.getGia();
        holder.txtThanhtien.setText("Thành tiền: "+decimalFormat.format(thanhtien) + " VNĐ");
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class MyViewHolder_Payment extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_hinhSP;
        TextView txtGia, txtTenSP, txtSL, txtThanhtien;
        IImageClickListener listener;

        public void setListener(IImageClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder_Payment(@NonNull View itemView) {
            super(itemView);
            item_hinhSP = itemView.findViewById(R.id.itemVC_Hinh);
            txtGia = itemView.findViewById(R.id.itemVC_KhuyenMai);
            txtSL = itemView.findViewById(R.id.itemVC_NgayBatDau);
            txtTenSP = itemView.findViewById(R.id.itemVC_TieuDe);
            txtThanhtien = itemView.findViewById(R.id.itemVC_NgayKetThuc);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
