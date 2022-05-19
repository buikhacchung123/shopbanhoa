package com.example.testcontentprovider.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.model.DanhMuc;

import java.util.List;

public class DMSPAdapter extends BaseAdapter {

    List<DanhMuc> array;
    Context context;
    DanhMuc danhMuc;

    public DMSPAdapter(List<DanhMuc> array, Context context) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView tendm;
        ImageView hinhdm;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_danhmuc_menu,null);
            viewHolder.tendm = convertView.findViewById(R.id.txtTenDM_menu);
            viewHolder.hinhdm = convertView.findViewById(R.id.img_DM_menu);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.tendm.setText(array.get(position).getTenDM());
        String[] imgSplit = array.get(position).getHinhDM().split("\\.");
        String imgName = imgSplit[0];
        String PACKAGE_NAME = context.getPackageName();
        int imgId = context.getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + imgName, null, null);


        viewHolder.hinhdm.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), imgId));

        return convertView;
    }
}
