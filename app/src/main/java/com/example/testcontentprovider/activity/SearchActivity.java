package com.example.testcontentprovider.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testcontentprovider.R;
import com.example.testcontentprovider.adapter.SanPhamAdapter;
import com.example.testcontentprovider.api.ApiService;
import com.example.testcontentprovider.model.SanPham;
import java.util.ArrayList;
import java.util.List;
import me.gujun.android.taggroup.TagGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    TextView txtNoContent;
    SearchView txtSearchBox;
    TagGroup tagGroup;
    List<SanPham> listSeach;
    ArrayList<String> tagList;
    SanPhamAdapter adapterSP;
    RecyclerView rv_searchSP;
    private LinearLayoutManager mlinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        AnhXa();

        //Gán list SP vào recycle view
        adapterSP = new SanPhamAdapter(SearchActivity.this,MainActivity.arraySP);
        rv_searchSP.setLayoutManager(mlinearLayoutManager);
        rv_searchSP.setAdapter(adapterSP);
        rv_searchSP.setVisibility(View.GONE);

        tagList = new ArrayList<String>();
        tagGroup.setTags(tagList);


        //Sự kiện cho trang Tìm kiếm sản phẩm
        txtSearchBox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                SearchSanPham(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
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
        //lv_SearchResult = findViewById(R.id.lv_SearchResult);
        txtSearchBox = findViewById(R.id.txtSearchBox);
        txtNoContent = findViewById(R.id.txtNoContent);
        tagGroup = findViewById(R.id.tag_group);
        rv_searchSP = findViewById(R.id.rv_SearchSP);
        mlinearLayoutManager = new LinearLayoutManager(this.getBaseContext());
    }
    private void SearchSanPham(String s) {
        if (s.trim().length() > 0) {
            listSeach = new ArrayList<>();
            listSeach.clear();
            for (int i = 0; i < MainActivity.arraySP.size(); i++) {
                if (MainActivity.arraySP.get(i).getTenSp().trim().toLowerCase().contains(s.trim().toLowerCase())) {
                    listSeach.add(MainActivity.arraySP.get(i));
                }
            }
            if(listSeach.size()!=0) {
                adapterSP = new SanPhamAdapter(getBaseContext(), (ArrayList<SanPham>) listSeach);
                adapterSP.notifyDataSetChanged();
                rv_searchSP.setAdapter(adapterSP);
                rv_searchSP.setVisibility(View.VISIBLE);
                txtNoContent.setVisibility(View.GONE);
            }else {
                rv_searchSP.setVisibility(View.GONE);
                txtNoContent.setVisibility(View.VISIBLE);
            }
            if(!IsExistOnSearchList(s)){
                tagList.add(s);
                tagGroup.setTags(tagList);
            }
        }
    }
    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public boolean IsExistOnSearchList(String search){
        for(int i=0; i<tagList.size();i++)
            if(tagList.get(i).trim().equals(search.trim()))
                return true;
        return false;
    }

}