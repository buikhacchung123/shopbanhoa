package com.example.testcontentprovider.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Rating implements Serializable {
    int StartRating;
    String NoiDung, MaKhachHang, MaSanPham, Hinh, MaHoaDon;

    public Rating(){
    }

    public Rating(int startRating, String noiDung, String maKhachHang, String maSanPham, String hinh, String maHoaDon) {
        StartRating = startRating;
        NoiDung = noiDung;
        MaKhachHang = maKhachHang;
        MaSanPham = maSanPham;
        Hinh = hinh;
        MaHoaDon = maHoaDon;
    }

    public int getStartRating() {
        return StartRating;
    }

    public void setStartRating(int startRating) {
        StartRating = startRating;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        MaKhachHang = maKhachHang;
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        MaSanPham = maSanPham;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        MaHoaDon = maHoaDon;
    }

    public ArrayList<Rating> getRatingByMaSP() {
        ArrayList<Rating> listRating = new ArrayList<Rating>();
        return listRating;
    }
}
