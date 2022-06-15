package com.example.testcontentprovider.model;

import com.google.gson.annotations.SerializedName;

public class HoaDon {
    @SerializedName("maHd")
    String maHD;

    @SerializedName("maKh")
    String maKH;

    @SerializedName("ngayLap")
    String ngayLap;

    @SerializedName("ngayGiao")
    String ngayGiao;

    @SerializedName("diaChiGiao")
    String diaChiGiao;

    @SerializedName("trangThai")
    int trangThai;

    @SerializedName("tongTien")
    int tongTien;

    @SerializedName("tongSoLuong")
    int tongSoLuong;

    @SerializedName("maVc")
    String maVC;

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getDiaChiGiao() {
        return diaChiGiao;
    }

    public void setDiaChiGiao(String diaChiGiao) {
        this.diaChiGiao = diaChiGiao;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTongSoLuong(int tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    public String getMaVC() {
        return maVC;
    }

    public void setMaVC(String maVC) {
        this.maVC = maVC;
    }

    public HoaDon(){}

    public HoaDon(String maHD, String maKH, String ngayLap, String ngayGiao, String diaChiGiao, int trangThai, int tongTien, int tongSoLuong, String maVC) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.ngayGiao = ngayGiao;
        this.diaChiGiao = diaChiGiao;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.tongSoLuong = tongSoLuong;
        this.maVC = maVC;
    }
}
