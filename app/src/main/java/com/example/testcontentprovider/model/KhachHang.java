package com.example.testcontentprovider.model;

import android.widget.EditText;

import java.io.Serializable;

public class KhachHang implements Serializable {
    int maNd;
    String tenNd;
    String username;
    String passWord;
    String diaChi;
    String sdt;
    Boolean trangThai;
    int maNnd;

    public int getMaND() {
        return maNd;
    }

    public void setMaND(int maND) {
        this.maNd = maND;
    }

    public String getTenNd() {
        return tenNd;
    }

    public void setTenNd(String tenNd) {
        this.tenNd = tenNd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaNnd() {
        return maNnd;
    }

    public void setMaNnd(int maNnd) {
        this.maNnd = maNnd;
    }

    public KhachHang(){

    }

    public KhachHang(String tenNd, String username, String passWord, String diaChi, String sdt, Boolean trangThai) {
        this.tenNd = tenNd;
        this.username = username;
        this.passWord = passWord;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.trangThai = trangThai;
    }
}
