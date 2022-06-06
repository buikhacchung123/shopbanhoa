package com.example.testcontentprovider.api;

import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.KhachHang;
import com.example.testcontentprovider.model.SanPham;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://chhoa.somee.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("Nguoidungs/GetUserByUsername/{tenNd}")
    Call<List<KhachHang>> getUser(@Path("tenNd") String username);

    @GET("Sanphams")
    Call<List<SanPham>> getSanPham();

    @GET("Danhmucs")
    Call<List<DanhMuc>> getAllDanhMucs();

    @GET("Sanphams/GetSpByDm/{MaDM}")
    Call<List<SanPham>> getSanPhamTheoDM(@Path("MaDM") String id);

}
