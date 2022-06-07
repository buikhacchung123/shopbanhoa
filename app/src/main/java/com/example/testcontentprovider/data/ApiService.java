package com.example.testcontentprovider.data;

import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.KhachHang;
import com.example.testcontentprovider.model.SanPham;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/api/Danhmucs")
    Call<List<DanhMuc>> getAllDanhMucs();

    @GET("/api/Sanphams")
    Call<List<SanPham>> getAllPSanPhams();

    @GET("/api/Sanphams/GetSpByDm/{MaDM}")
    Call<List<SanPham>> getSanPhamTheoDM(@Path("MaDM") String id);

    @POST("/api/Nguoidungs")
    Call<KhachHang> insertKhachHang(@Body KhachHang khachHang);

    @GET("/api/Nguoidungs")
    Call<List<KhachHang>> getAllKhachHangs();

    @PUT("/api/Nguoidungs/{MaND}")
    Call<KhachHang> updateKhachHang(@Path("MaND") String id,@Body KhachHang khachHang);

}