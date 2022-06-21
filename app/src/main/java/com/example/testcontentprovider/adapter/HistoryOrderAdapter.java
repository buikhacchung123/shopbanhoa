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
import com.example.testcontentprovider.model.HoaDon;
import com.example.testcontentprovider.model.IImageClickListener;

import java.text.DecimalFormat;
import java.util.List;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.MyViewHolder_HistoryOrder> {
    Context context;
    List<HoaDon> listHD;

    public HistoryOrderAdapter(Context context, List<HoaDon> listHD) {
        this.context = context;
        this.listHD = listHD;
    }

    @NonNull
    @Override
    public MyViewHolder_HistoryOrder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_order, parent, false);
        return new HistoryOrderAdapter.MyViewHolder_HistoryOrder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_HistoryOrder holder, int position) {
        HoaDon hd = listHD.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtMa.setText("Mã hóa đơn: "+hd.getMaHd());
        holder.txtTongTien.setText("Thành tiền: "+decimalFormat.format(hd.getTongTien())+" VND");
        holder.txtTongSL.setText("Số lượng: "+hd.getTongSoluong());
        String PACKAGE_NAME = context.getPackageName();
        int imgId = context.getResources().getIdentifier(PACKAGE_NAME + ":drawable/bill_icon", null, null);
        holder.item_hinh.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imgId));
    }

    @Override
    public int getItemCount() {
        return listHD.size();
    }

    public class MyViewHolder_HistoryOrder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_hinh;
        TextView txtMa, txtTongSL, txtTongTien;
        IImageClickListener listener;

        public void setListener(IImageClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder_HistoryOrder(@NonNull View itemView) {
            super(itemView);
            item_hinh = itemView.findViewById(R.id.itemHD_Hinh);
            txtMa = itemView.findViewById(R.id.itemHD_Ma);
            txtTongSL = itemView.findViewById(R.id.itemHD_TongSL);
            txtTongTien = itemView.findViewById(R.id.itemHD_TongTien);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
