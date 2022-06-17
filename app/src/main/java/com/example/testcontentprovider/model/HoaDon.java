package com.example.testcontentprovider.model;

import com.google.gson.annotations.SerializedName;

public class HoaDon {
    @SerializedName("maHd")
    String maHD;

    @SerializedName("maKh")
    int maKH;

    @SerializedName("ngayLap")
    String ngayLap;

    @SerializedName("ngayGiao")
    String ngayGiao;

    @SerializedName("diaChiGiao")
    String diaChiGiao;

    @SerializedName("trangThai")
    boolean trangThai;

    @SerializedName("tongTien")
    int tongTien;

    @SerializedName("tongSoLuong")
    int tongSoLuong;

    @SerializedName("maVc")
    String maVC;


    public HoaDon() {
    }

    public HoaDon(String ngayLap, String ngayGiao, int maKh, String maVc, String diaChigiao, Boolean trangThai, int tongTien, int tongSoluong) {
        this.ngayLap = ngayLap;
        this.ngayGiao = ngayGiao;
        this.maKH = maKh;
        this.maVC = maVc;
        this.diaChiGiao = diaChigiao;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.tongSoLuong = tongSoluong;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
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

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
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
}
