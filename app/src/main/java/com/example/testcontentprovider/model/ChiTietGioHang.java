package com.example.testcontentprovider.model;

public class ChiTietGioHang {
    String maGh;
    int soLuong, maSp;
    double donGia, thanhTien;

    public ChiTietGioHang() {
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
