package com.example.testcontentprovider.adapter;

import android.content.Context;
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

import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.MyViewHolder_HoaDon> {
    Context context;
    List<HoaDon> listHD;

    public HoaDonAdapter(Context context, List<HoaDon> listHD) {
        this.context = context;
        this.listHD = listHD;
    }

    @NonNull
    @Override
    public HoaDonAdapter.MyViewHolder_HoaDon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadon, parent, false);

        return new HoaDonAdapter.MyViewHolder_HoaDon(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_HoaDon holder, int position) {
        HoaDon hd = listHD.get(position);
        holder.txtMaHD.setText("Mã HD: "+hd.getMaHD());
        holder.txtNgayLap.setText("Ngày lập: "+hd.getNgayLap());
        holder.txtTongSL.setText("Tổng Sl: "+hd.getTongSoLuong());
        holder.txtTongTien.setText("Thành tiền: "+hd.getTongTien());
        /*holder.txtTenSP.setText(hd.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGia.setText("Giá: "+decimalFormat.format(gh.getGia()) + " VNĐ");
        String[] imgSplit = hd.getHinhsp().split("\\.");
        String imgName = imgSplit[0];
        String PACKAGE_NAME = context.getPackageName();
        int imgId = context.getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + imgName, null, null);
        holder.item_hinhSP.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imgId));

        holder.txtSL.setText("Số lượng: "+gh.getSoluong()+"");
        double thanhtien = gh.getSoluong() * gh.getGia();
        holder.txtThanhtien.setText("Thành tiền: "+decimalFormat.format(thanhtien) + " VNĐ");*/
    }

    @Override
    public int getItemCount() {
        return listHD.size();
    }

    public class MyViewHolder_HoaDon extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_hinh;
        TextView txtNgayLap, txtTongSL, txtTongTien, txtMaHD;
        IImageClickListener listener;

        public void setListener(IImageClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder_HoaDon(@NonNull View itemView) {
            super(itemView);
            item_hinh = itemView.findViewById(R.id.itemHoaDon_Hinh);
            txtTongSL = itemView.findViewById(R.id.itemHoaDon_TongSoLuong);
            txtTongTien = itemView.findViewById(R.id.itemHoaDon_TongTien);
            txtMaHD = itemView.findViewById(R.id.itemHoaDon_MaHD);
            txtNgayLap = itemView.findViewById(R.id.itemHoaDon_NgayLap);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
