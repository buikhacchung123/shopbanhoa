package com.example.testcontentprovider.api;

import com.example.testcontentprovider.model.ChiTietGioHang;
import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.KhachHang;
import com.example.testcontentprovider.model.SanPham;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("Giohangs")
    Call<List<GioHang>> getAllCart();

    //loi 400
    @POST("Giohangs")
    Call<GioHang> setCart(@Body GioHang gh);
    //loi 415
    @FormUrlEncoded
    @POST("Giohangs")
    Call<GioHang> setCart(@Field("maKh") int ma,
                          @Field("tongSp") int sl,
                          @Field("tongTien") double tong);

    @PUT("Giohangs/{maGh}")
    Call<GioHang> updateCart(@Path("maGh") int ma,
                             @Body GioHang cart);

    @GET("ChitietGiohangs")
    Call<List<ChiTietGioHang>> getAllCartDetail();

    @POST("ChitietGiohangs")
    Call<ChiTietGioHang> setCartDetail(@Body ChiTietGioHang ctgh);

    @PUT("ChitietGiohangs/{maGh}&{maSp}")
    Call<ChiTietGioHang> updateCartDetail(@Path("maGh") String magh,
                                          @Path("maSp") int masp,
                                          @Body ChiTietGioHang detail);

    @DELETE("ChitietGiohangs/{maGh}&{maSp}")
    Call<ChiTietGioHang> deleteCartDetail(@Path("maGh") String magh,
                                          @Path("maSp") int masp);

}
