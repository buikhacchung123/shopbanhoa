package com.example.testcontentprovider.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class SanPham implements Serializable {

    public static final int TYPE_LIST = 1;
    public static final int TYPE_GRID = 2;

    @SerializedName("maSp")
    String masp;

    @SerializedName("tenSp")
    String tensp;

    @SerializedName("moTa")
    String mota;

    @SerializedName("hinhSp")
    String hinhsp;

    @SerializedName("maDm")
    String madm;

    @SerializedName("soLuong")
    int soluong;

    @SerializedName("giaBan")
    double giaban;

    int typeDisplay;

    public int getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(int typeDisplay) {
        this.typeDisplay = typeDisplay;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
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

    public String getMadm() {
        return madm;
    }

    public void setMadm(String madm) {
        this.madm = madm;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

}
