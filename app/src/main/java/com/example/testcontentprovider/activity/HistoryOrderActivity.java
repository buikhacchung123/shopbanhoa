package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.HistoryOrderAdapter;
import com.example.testcontentprovider.adapter.VoucherAdapter;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.model.HoaDon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryOrderActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtNoHistory;
    RecyclerView rv_ListOrders;
    List<HoaDon> arrayHD;
    HistoryOrderAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        AnhXa();
        LoadingAllHoadons();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void AnhXa(){
        toolbar = findViewById(R.id.toolbar_voucher);
        txtNoHistory = findViewById(R.id.txtNoHistoryOrder);
        rv_ListOrders = findViewById(R.id.rv_listOrder);
    }
    private void LoadingAllHoadons(){
        Call<List<HoaDon>> call = ApiService.apiService.getAllHoaDons(MainActivity.CURRENT_USER.getMaND());
        call.enqueue(new Callback<List<HoaDon>>() {
            @Override
            public void onResponse(Call<List<HoaDon>> call, Response<List<HoaDon>> response) {
                arrayHD = response.body();
                adapter = new HistoryOrderAdapter(getBaseContext(),arrayHD);
                rv_ListOrders.setAdapter(adapter);
                rv_ListOrders.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                if(arrayHD == null ){
                    txtNoHistory.setVisibility(View.VISIBLE);
                    rv_ListOrders.setVisibility(View.GONE);
                    return;
                }
                else if(arrayHD.size() == 0 )
                {
                    txtNoHistory.setVisibility(View.VISIBLE);
                    rv_ListOrders.setVisibility(View.GONE);
                    return;
                }else{
                    txtNoHistory.setVisibility(View.GONE);
                    rv_ListOrders.setVisibility(View.VISIBLE);
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<HoaDon>> call, Throwable t) {

            }
        });
    }
}