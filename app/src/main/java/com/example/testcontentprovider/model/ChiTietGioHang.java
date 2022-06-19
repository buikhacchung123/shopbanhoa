package com.example.testcontentprovider.model;

import java.io.Serializable;

public class ChiTietGioHang implements Serializable{
    int maGh;
    int maSp;
    int soLuong;
    int donGia;
    int thanhTien;

    public ChiTietGioHang() {
    }

    public ChiTietGioHang(int maGh, int maSp, int soLuong, int donGia, int thanhTien) {
        this.maGh = maGh;
        this.maSp = maSp;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaGh() {
        return maGh;
    }

    public void setMaGh(int maGh) {
        this.maGh = maGh;
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
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

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
}
