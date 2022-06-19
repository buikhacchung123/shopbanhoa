package com.example.testcontentprovider.api;

import com.example.testcontentprovider.model.ChiTietGioHang;
import com.example.testcontentprovider.model.ChiTietHoaDon;
import com.example.testcontentprovider.model.DanhMuc;
import com.example.testcontentprovider.model.GioHang;
import com.example.testcontentprovider.model.HoaDon;
import com.example.testcontentprovider.model.KhachHang;
import com.example.testcontentprovider.model.SanPham;
import com.example.testcontentprovider.model.Voucher;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
    // xu ly dang nhap
    @GET("Nguoidungs/GetUserByUsername/{tenNd}")
    Call<List<KhachHang>> getUser(@Path("tenNd") String username);
    //
    @GET("Danhmucs")
    Call<List<DanhMuc>> getAllDanhMucs();
    // xu ly san pham
    @GET("Sanphams")
    Call<List<SanPham>> getSanPham();
    @GET("Sanphams/GetSpByDm/{MaDM}")
    Call<List<SanPham>> getSanPhamTheoDM(@Path("MaDM") String id);
    // xu ly gio hang
    @GET("Giohangs")
    Call<List<GioHang>> getCart();
    @Headers({"Accept: application/json"})
    @POST("Giohangs")
    Call<GioHang> setCart(@Body Map<String, String> body);
    @Headers({"Accept: application/json"})
    @PUT("Giohangs/{maGh}")
    Call<GioHang> updateCart(@Path("maGh") int ma,
                             @Body Map<String, String> body);
    @Headers({"Accept: application/json"})
    @PUT("Giohangs/{maGh}")
    Call<GioHang> updateCart(@Path("maGh") int ma,
                             @Body GioHang body);
    @DELETE("Giohangs/{maGh}")
    Call<GioHang> deleteCart(@Path("maGh") int ma);
    // xu ly chi tiet gio hang
    @GET("ChitietGiohangs")
    Call<List<ChiTietGioHang>> getAllCartDetail();
    @Headers({"Accept: application/json"})
    @POST("ChitietGiohangs")
    Call<ChiTietGioHang> setCartDetail(@Body ChiTietGioHang body);
    @PUT("ChitietGiohangs/{maGh}")
    Call<ChiTietGioHang> updateCartDetail(@Path("maGh") int magh,
                                          @Body ChiTietGioHang detail);
    @DELETE("ChitietGiohangs/{maGh}/{maSp}")
    Call<ChiTietGioHang> deleteCartDetail(@Path("maGh") int magh,
                                          @Path("maSp") int masp);
    // xu ly voucher
    @GET("Vouchers/{maVc}")
    Call<Voucher> getVoucher(@Path("maVc") String ma);
    @PUT("Vouchers/{maVc}")
    Call<Voucher> updateVoucher(@Path("maVc") String ma, @Body Voucher gh);
    // hoa don
    @GET("Hoadons/GetHdByMaNd/{maNd}")
    Call<List<HoaDon>> getHoaDon(@Path("maNd") int ma);
    @Headers({"Accept: application/json"})
    @POST("Hoadons")
    Call<HoaDon> setHD(@Body HoaDon hd);

    @GET("ChitietHoadons")
    Call<List<ChiTietHoaDon>> getHoaDon();


    @Headers({"Accept: application/json"})
    @POST("ChitietHoadons")
    Call<ChiTietHoaDon> setCTHD(@Body ChiTietHoaDon cthd);
}
