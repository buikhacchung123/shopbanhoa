package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.testcontentprovider.R;

public class HistoryOrderActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtNoHistory;
    RecyclerView rv_ListOrders;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        AnhXa();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(LoadingActivity.arrayHD.size()  == 0)
        {
            txtNoHistory.setVisibility(View.VISIBLE);
            rv_ListOrders.setVisibility(View.GONE);
        }else{
            txtNoHistory.setVisibility(View.GONE);
            rv_ListOrders.setVisibility(View.VISIBLE);
        }
    }
    public void AnhXa(){
        toolbar = findViewById(R.id.toolbar_historyOrder);
        txtNoHistory = findViewById(R.id.txtNoHistoryOrder);
        rv_ListOrders = findViewById(R.id.rv_listOrder);
    }
}