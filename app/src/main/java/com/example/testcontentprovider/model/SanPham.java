package com.example.testcontentprovider.model;


import com.google.gson.annotations.SerializedName;
import android.widget.Toast;
import com.example.testcontentprovider.activity.LoginActivity;
import com.example.testcontentprovider.activity.MainActivity;
import com.example.testcontentprovider.api.ApiService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanPham implements Serializable {

    public static final int TYPE_LIST = 1;
    public static final int TYPE_GRID = 2;


    @SerializedName("maSp")
    int masp;

    @SerializedName("tenSp")
    String tensp;

    @SerializedName("moTa")
    String mota;

    @SerializedName("hinhSp")
    String hinhsp;

    @SerializedName("maDm")
    int madm;

    @SerializedName("soLuong")
    int soluong;

    @SerializedName("giaBan")
    int giaban;


    int typeDisplay;

    public SanPham(){

    }
    public SanPham(SanPham sp)
    {
        this.masp = sp.getMasp();
        this.tensp = sp.getTensp();
        this.mota = sp.getMota();
        this.hinhsp = sp.getHinhsp();
        this.madm = sp.getMadm();
        this.soluong = sp.getSoluong();
        this.giaban = sp.getGiaban();
    }

    public SanPham(int maSp, String tenSp, String moTa, String hinhSp, int maDm, int soLuong, int giaBan) {
        this.masp = maSp;
        this.tensp = tenSp;
        this.mota = moTa;
        this.hinhsp = hinhSp;
        this.madm = maDm;
        this.soluong = soLuong;
        this.giaban = giaBan;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public int getMadm() {
        return madm;
    }

    public void setMadm(int madm) {
        this.madm = madm;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }
    public int getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(int typeDisplay) {
        this.typeDisplay = typeDisplay;
    }
    public SanPham getSPByMaSP(int ma)
    {
        SanPham kq = new SanPham();
        for (int i = 0; i < MainActivity.dssp.size(); i++)
        {
            if(MainActivity.dssp.get(i).getMasp() == ma)
            {
                kq = MainActivity.dssp.get(i);
                break;
            }
        }
        return kq;
    }
}
