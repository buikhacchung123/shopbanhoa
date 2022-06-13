package com.example.testcontentprovider.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.testcontentprovider.R;
import com.example.testcontentprovider.activity.LoadingActivity;
import com.example.testcontentprovider.adapter.DMAdapter;
import com.example.testcontentprovider.adapter.SanPhamAdapter;
import com.example.testcontentprovider.data.ApiService;
import com.example.testcontentprovider.data.Constance;
import com.example.testcontentprovider.data.RetrofitClient;
import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.SanPham;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private View view;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerView, rvDM;
    private DMAdapter adapter;
    private ApiService apiService;
    private SanPhamAdapter sanPhamAdapter;
    private LinearLayoutManager mlinearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    ImageButton button;

    private int mCurrentType = SanPham.TYPE_LIST;
    private int mCurrentPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        AnhXa();
        ActionViewFlipper();


        apiService = RetrofitClient.getClient(Constance.API_URL).create(ApiService.class);

        //Set giao diện list SP
        sanPhamAdapter = new SanPhamAdapter(getContext(),(ArrayList<SanPham>) LoadingActivity.arraySP);
        recyclerView.setLayoutManager(mlinearLayoutManager);
        recyclerView.setAdapter(sanPhamAdapter);
        setTypeDisplayRecyclerView(SanPham.TYPE_LIST);

        //Set giao diện list DM
        adapter = new DMAdapter(getContext(), (ArrayList<DanhMuc>) LoadingActivity.arrayDM);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rvDM.setLayoutManager(layoutManager);
        rvDM.setAdapter(adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentPosition();
                onClickChangeTypeDisplay();
            }
        });
        return view;
    }

    private void setTypeDisplayRecyclerView(int typeDisplay) {
        if(LoadingActivity.arraySP == null || LoadingActivity.arraySP.isEmpty())
            return;
        mCurrentType = typeDisplay;
        for(SanPham sp : LoadingActivity.arraySP)
            sp.setTypeDisplay(typeDisplay);
    }
    private void AnhXa() {
        viewFlipper = view.findViewById(R.id.viewflipperhome);
        recyclerView = view.findViewById(R.id.recyclerview);
        rvDM = view.findViewById(R.id.rvdanhmuc);
        mlinearLayoutManager = new LinearLayoutManager(this.getContext());
        gridLayoutManager = new GridLayoutManager(this.getContext(), 2);
        button = view.findViewById(R.id.btn_changeDisplay);
    }
    private void ActionViewFlipper() {
        List<String> quangcao = new ArrayList<>();
        quangcao.add("https://hyt.r.worldssl.net/cms-images/banner/434410_dong-hanh-cung-nong-dan-viet-nam.jpg");
        quangcao.add("https://hyt.r.worldssl.net/cms-images/banner/434456_ngay-cua-me.jpg");
        for(int i = 0; i < quangcao.size(); i++)
        {
            ImageView imageView = new ImageView(getContext());
            Glide.with(getContext()).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(10000);
        viewFlipper.setAutoStart(true);

        Animation slidein = AnimationUtils.loadAnimation(getContext(), R.anim.slidein);
        Animation slideout = AnimationUtils.loadAnimation(getContext(), R.anim.slideout);
        viewFlipper.setInAnimation(slidein);
        viewFlipper.setOutAnimation(slideout);
    }
    private void onClickChangeTypeDisplay() {
        if(mCurrentType == SanPham.TYPE_LIST){
            setTypeDisplayRecyclerView(SanPham.TYPE_GRID);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        else if(mCurrentType == SanPham.TYPE_GRID){
            setTypeDisplayRecyclerView(SanPham.TYPE_LIST);
            recyclerView.setLayoutManager(mlinearLayoutManager);
        }
        sanPhamAdapter.notifyDataSetChanged();
        setIconButton();
        recyclerView.scrollToPosition(mCurrentPosition);
    }
    private void setIconButton() {
        switch (mCurrentType){
            case SanPham.TYPE_LIST:
                button.setImageResource(R.drawable.ic_list);
                break;
            case SanPham.TYPE_GRID:
                button.setImageResource(R.drawable.ic_grid);
                break;
        }
    }
    private void setCurrentPosition(){
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        switch (mCurrentType){
            case SanPham.TYPE_LIST:
                mCurrentPosition = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
                break;
            case SanPham.TYPE_GRID:
                mCurrentPosition = ((GridLayoutManager)layoutManager).findFirstVisibleItemPosition();
                break;
        }
    }

}