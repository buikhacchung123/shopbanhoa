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
import com.example.testcontentprovider.model.IImageClickListener;
import com.example.testcontentprovider.model.Voucher;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.MyViewHolder_Voucher> {
    Context context;
    List<Voucher> listVC;

    public VoucherAdapter(Context context, List<Voucher> listVC) {
        this.context = context;
        this.listVC = listVC;
    }

    @NonNull
    @Override
    public VoucherAdapter.MyViewHolder_Voucher onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher, parent, false);
        return new VoucherAdapter.MyViewHolder_Voucher(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_Voucher holder, int position) {
        Voucher vc = listVC.get(position);
        holder.txtTieuDe.setText(vc.getTieuDe());
        holder.txtMaVC.setText("Mã code: "+vc.getMaVC());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtKhuyenMai.setText("Giảm "+decimalFormat.format(vc.getKhuyenMai()) + " VNĐ");
        String[] imgSplit = vc.getHinhBanner().split("\\.");
        String imgName = imgSplit[0];
        String PACKAGE_NAME = context.getPackageName();
        int imgId = context.getResources().getIdentifier(PACKAGE_NAME + ":drawable/"+imgName, null, null);
        holder.item_hinh.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imgId));

        //Format ngày
//Date startDate = SimpleDateFormat.parse(vc.getNgayBatDau());
        //Date date = SimpleDateFormat.parse();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.txtNgayBatDau.setText("Ngày bắt đầu: "+vc.getNgayBatDau());
        holder.txtNgayKetThuc.setText("Ngày kết thúc: "+vc.getNgayKetThuc());
    }

    @Override
    public int getItemCount() {
        return listVC.size();
    }

    public class MyViewHolder_Voucher extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_hinh;
        TextView txtTieuDe, txtNgayBatDau, txtNgayKetThuc, txtKhuyenMai, txtMaVC;
        IImageClickListener listener;

        public void setListener(IImageClickListener listener) {
            this.listener = listener;
        }

        public MyViewHolder_Voucher(@NonNull View itemView) {
            super(itemView);
            item_hinh = itemView.findViewById(R.id.itemVC_Hinh);
            txtKhuyenMai = itemView.findViewById(R.id.itemVC_KhuyenMai);
            txtNgayBatDau = itemView.findViewById(R.id.itemVC_NgayBatDau);
            txtTieuDe = itemView.findViewById(R.id.itemVC_TieuDe);
            txtNgayKetThuc = itemView.findViewById(R.id.itemVC_NgayKetThuc);
            txtMaVC = itemView.findViewById(R.id.itemVC_MaVC);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
