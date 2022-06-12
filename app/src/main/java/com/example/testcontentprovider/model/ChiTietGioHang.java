package com.example.testcontentprovider.model;

import java.io.Serializable;

public class ChiTietGioHang implements Serializable {
    String maGh;
    int maSp;
    int soLuong;
    double donGia;
    double thanhTien;

    public ChiTietGioHang() {
    }

    public ChiTietGioHang(String maGh, int maSp, int soLuong, double donGia, double thanhTien) {
        this.maGh = maGh;
        this.maSp = maSp;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public String getMaGh() {
        return maGh;
    }

    public void setMaGh(String maGh) {
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

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
}
