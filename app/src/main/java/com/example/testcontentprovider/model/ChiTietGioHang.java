package com.example.testcontentprovider.model;

import com.google.gson.annotations.SerializedName;

public class ChiTietGioHang {
    @SerializedName("maGh")
    String maGH;

    @SerializedName("maSp")
    String maSP;

    @SerializedName("thanhTien")
    int thanhTien;

    @SerializedName("soLuong")
    int soLuong;

    @SerializedName("donGia")
    int donGia;

    public String getMaGH() {
        return maGH;
    }

    public void setMaGH(String maGH) {
        this.maGH = maGH;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public ChiTietGioHang(){}

    public ChiTietGioHang(String maGH, String maSP, int thanhTien, int soLuong, int donGia) {
        this.maGH = maGH;
        this.maSP = maSP;
        this.thanhTien = thanhTien;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
}
