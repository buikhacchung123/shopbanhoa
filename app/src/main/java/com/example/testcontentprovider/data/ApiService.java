package com.example.testcontentprovider.data;

import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.SanPham;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/api/Danhmucs")
    Call<List<DanhMuc>> getAllDanhMucs();

    @GET("/api/Sanphams")
    Call<List<SanPham>> getAllPSanPhams();
}
