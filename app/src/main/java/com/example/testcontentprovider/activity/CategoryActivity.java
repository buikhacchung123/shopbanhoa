package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.testcontentprovider.R;
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

public class CategoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv_category;
    private SanPhamAdapter sanPhamAdapter;
    private LinearLayoutManager mlinearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    ImageButton btnChangeDisplay;
    private List<SanPham> dssp;
    private ApiService apiService;
    private  int mCurrentType = SanPham.TYPE_LIST;
    private  int mCurrentPosition;
    DanhMuc dm;
    TextView txtCategory_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        AnhXa();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Nhận dữ liệu từ intent
        dm = (DanhMuc) getIntent().getSerializableExtra("Category");
        txtCategory_name.setText(dm.getTenDM());

        //Lấy dữ liệu sản phẩm
        apiService = RetrofitClient.getClient(Constance.API_URL).create(ApiService.class);
        LoadingSanPhamTheoDM();


        //Sự kiện trang Sản phẩm theo danh mục
        btnChangeDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentPosition();
                onClickChangeTypeDisplay();
            }
        });
    }
    public void AnhXa(){
        toolbar = findViewById(R.id.toolbar_historyOrder);
        btnChangeDisplay = findViewById(R.id.btn_changeDisplay);
        mlinearLayoutManager = new LinearLayoutManager(this.getBaseContext());
        gridLayoutManager = new GridLayoutManager(this.getBaseContext(), 2);
        txtCategory_name = findViewById(R.id.txtHistoryOrder);
        rv_category = findViewById(R.id.rv_listOrder);
    }
    private void onClickChangeTypeDisplay() {
        if(mCurrentType == SanPham.TYPE_LIST){
            setTypeDisplayRecyclerView(SanPham.TYPE_GRID);
            rv_category.setLayoutManager(gridLayoutManager);
        }
        else if(mCurrentType == SanPham.TYPE_GRID){
            setTypeDisplayRecyclerView(SanPham.TYPE_LIST);
            rv_category.setLayoutManager(mlinearLayoutManager);
        }
        sanPhamAdapter.notifyDataSetChanged();
        setIconButton();
        rv_category.scrollToPosition(mCurrentPosition);
    }
    private void setIconButton() {
        switch (mCurrentType){
            case SanPham.TYPE_LIST:
                btnChangeDisplay.setImageResource(R.drawable.ic_list);
                break;
            case SanPham.TYPE_GRID:
                btnChangeDisplay.setImageResource(R.drawable.ic_grid);
                break;
        }
    }
    private void setTypeDisplayRecyclerView(int typeDisplay) {
        if(dssp == null || dssp.isEmpty())
            return;
        mCurrentType = typeDisplay;
        for(SanPham sp : dssp)
            sp.setTypeDisplay(typeDisplay);
    }
    private void setCurrentPosition(){
        RecyclerView.LayoutManager layoutManager = rv_category.getLayoutManager();
        switch (mCurrentType){
            case SanPham.TYPE_LIST:
                mCurrentPosition = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
                break;
            case SanPham.TYPE_GRID:
                mCurrentPosition = ((GridLayoutManager)layoutManager).findFirstVisibleItemPosition();
                break;
        }
    }
    private void LoadingSanPhamTheoDM() {
        Call<List<SanPham>> call = apiService.getSanPhamTheoDM(dm.getMaDM());
        call.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                dssp = response.body();
                setTypeDisplayRecyclerView(SanPham.TYPE_LIST);
                sanPhamAdapter = new SanPhamAdapter(getBaseContext(),(ArrayList<SanPham>) dssp);
                rv_category.setLayoutManager(mlinearLayoutManager);
                rv_category.setAdapter(sanPhamAdapter);
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {

            }
        });
    }
}