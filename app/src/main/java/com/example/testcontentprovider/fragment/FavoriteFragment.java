package com.example.testcontentprovider.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.activity.LoadingActivity;
import com.example.testcontentprovider.adapter.SanPhamAdapter;
import com.example.testcontentprovider.model.FavoriteHistory;
import com.example.testcontentprovider.model.SanPham;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    RecyclerView rv;
    TextView txtNoFavoriteList;
    private View view;
    ArrayList<SanPham> favorList;
    private int mCurrentType = SanPham.TYPE_LIST;
    private SanPhamAdapter adapterSp;


    public FavoriteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        AnhXa();


        favorList = new FavoriteHistory(getContext()).getFavorHistory();
        adapterSp = new SanPhamAdapter(getContext(),favorList);

        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setAdapter(adapterSp);
        setTypeDisplayRecyclerView(SanPham.TYPE_LIST);
        if(favorList.size()==0){
            txtNoFavoriteList.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        }else{
            txtNoFavoriteList.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
        return view;
    }
    public void AnhXa(){
        rv = view.findViewById(R.id.rv_FavoriteHistory);
        txtNoFavoriteList = view.findViewById(R.id.txtNoFavorite);
    }
    private void setTypeDisplayRecyclerView(int typeDisplay) {
        if(favorList == null || favorList.isEmpty())
            return;
        mCurrentType = typeDisplay;
        for(SanPham sp : favorList)
            sp.setTypeDisplay(typeDisplay);
    }

    @Override
    public void onResume(){
        super.onResume();
        adapterSp.notifyDataSetChanged();
        rv.setAdapter(adapterSp);
        setTypeDisplayRecyclerView(SanPham.TYPE_LIST);
        if(favorList.size()==0 || favorList == null){
            txtNoFavoriteList.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        }else{
            txtNoFavoriteList.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
    }
}