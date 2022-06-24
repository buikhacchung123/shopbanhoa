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
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.testcontentprovider.R;
import com.example.testcontentprovider.activity.MainActivity;
import com.example.testcontentprovider.adapter.DMAdapter;
import com.example.testcontentprovider.adapter.SanPhamAdapter;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.SanPham;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private View view;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerView, rvDM;
    private List<SanPham> dssp;
    private List<DanhMuc> dsdm;
    private DMAdapter adapter;
    SanPham sp = new SanPham();
    DanhMuc dm = new DanhMuc();
    MainActivity mMain = new MainActivity();
    private SanPhamAdapter sanPhamAdapter;
    private LinearLayoutManager mlinearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    ImageButton button;

    private  int mCurrentType = SanPham.TYPE_LIST;
    private  int mCurrentPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        AnhXa();
        ActionViewFlipper();

        LoadSPHomePage();
        LoadDMHomePage();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentPosition();
                onClickChangeTypeDisplay();
            }
        });
        return view;
    }

    private void LoadDMHomePage() {
        ApiService.apiService.getAllDanhMucs().enqueue(new Callback<List<DanhMuc>>() {
            @Override
            public void onResponse(Call<List<DanhMuc>> call, Response<List<DanhMuc>> response) {
                dsdm = response.body();
                adapter = new DMAdapter(getContext(),dsdm);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                rvDM.setLayoutManager(layoutManager);
                rvDM.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<DanhMuc>> call, Throwable t) {

            }
        });
    }

    private void LoadSPHomePage() {
        ApiService.apiService.getSanPham().enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                dssp = response.body();
                sanPhamAdapter = new SanPhamAdapter(getContext(),dssp);
                recyclerView.setLayoutManager(mlinearLayoutManager);
                recyclerView.setAdapter(sanPhamAdapter);
                setTypeDisplayRecyclerView(SanPham.TYPE_LIST);
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Toast.makeText(getContext(),"Call api failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setTypeDisplayRecyclerView(int typeDisplay) {
        if(dssp == null || dssp.isEmpty())
            return;
        mCurrentType = typeDisplay;
        for(SanPham sp : dssp)
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
        quangcao.add("https://dc.flowercorner.vn/uploads/P620dd604b502e5.09606159_dat-hoa-online-giam-den-25.jpg");
        quangcao.add("https://dc.flowercorner.vn/uploads/P620dd64fbbc6e3.58140733_dat-hoa-online-giao-mien-phi.jpg");
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