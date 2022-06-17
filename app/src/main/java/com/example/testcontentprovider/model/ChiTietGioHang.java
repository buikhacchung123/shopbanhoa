package com.example.testcontentprovider.model;

import com.google.gson.annotations.SerializedName;

public class ChiTietGioHang {
    @SerializedName("maGh")
    int maGH;

    @SerializedName("maSp")
    int maSP;

    @SerializedName("thanhTien")
    int thanhTien;

    @SerializedName("soLuong")
    int soLuong;

    @SerializedName("donGia")
    int donGia;

    public ChiTietGioHang() {
    }

    public ChiTietGioHang(int maGh, int maSp, int soLuong, int donGia, int thanhTien) {
        this.maGH = maGh;
        this.maSP = maSp;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaGh() {
        return maGH;
    }

    public void setMaGh(int maGh) {
        this.maGH = maGh;
    }

    public int getMaSp() {
        return maSP;
    }

    public void setMaSp(int maSp) { this.maSP = maSp; }

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

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
}