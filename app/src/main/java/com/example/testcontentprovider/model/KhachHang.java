package com.example.testcontentprovider.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KhachHang implements Serializable {
    @SerializedName("maNd")
    int maNd;

    @SerializedName("tenNd")
    String tenNd;

    @SerializedName("sdt")
    String sdt;

    @SerializedName("username")
    String username;

    @SerializedName("passWord")
    String password;

    @SerializedName("diaChi")
    String diachi;

    //@SerializedName("trangThai")
    Boolean trangThai;

    @SerializedName("maNnd")
    int maNnd;

    public KhachHang(){
    }

    public KhachHang( String tenNd, String sdt, String username, String password, String diachi, Boolean trangThai) {
        this.tenNd = tenNd;
        this.sdt = sdt;
        this.username = username;
        this.password = password;
        this.diachi = diachi;
        this.trangThai = trangThai;
    }
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
        return password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public String getDiaChi() {
        return diachi;
    }

    public void setDiaChi(String diaChi) {
        this.diachi = diaChi;
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
}