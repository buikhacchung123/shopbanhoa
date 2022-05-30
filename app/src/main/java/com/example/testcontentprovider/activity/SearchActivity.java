package com.example.testcontentprovider.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.SanPhamAdapter;
import com.example.testcontentprovider.model.SanPham;
import java.util.ArrayList;
import me.gujun.android.taggroup.TagGroup;

public class SearchActivity extends AppCompatActivity {
    TextView txtNoContent;
    ListView lv_SearchResult;
    SearchView txtSearchBox;
    TagGroup tagGroup;
    ArrayList<SanPham> arraySP;
    SanPhamAdapter adapterSP;
    RecyclerView rv_searchSP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        AnhXa();
        arraySP = new ArrayList<>();
        adapterSP = new SanPhamAdapter(SearchActivity.this,arraySP);
        rv_searchSP.setAdapter(adapterSP);

        //Sự kiện cho trang Tìm kiếm sản phẩm
        txtSearchBox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                SearchSanPham(s);
                return false;
            }
        });
        tagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                txtSearchBox.setQuery(tag,false);
                hideSoftKeyboard(txtSearchBox);
            }
        });
    }
    private void AnhXa() {
        lv_SearchResult = findViewById(R.id.lv_SearchResult);
        txtSearchBox = findViewById(R.id.txtSearchBox);
        txtNoContent = findViewById(R.id.txtNoContent);
        tagGroup = findViewById(R.id.tag_group);
        rv_searchSP = findViewById(R.id.rv_SearchSP);
    }
    private void SearchSanPham(String s) {
        ArrayList<SanPham> tmp = new ArrayList<>();

        Toast.makeText(this, tmp.size()+"", Toast.LENGTH_SHORT).show();
        if(tmp.size() > 0){
            //adapterSP.clear();
            //adapterSP.addAll(tmp);
            adapterSP.notifyDataSetChanged();
            lv_SearchResult.setVisibility(View.VISIBLE);
            txtNoContent.setVisibility(View.GONE);
        }
        if(s.isEmpty()){
            lv_SearchResult.setVisibility(View.GONE);
            txtNoContent.setVisibility(View.VISIBLE);
        }
    }
    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}