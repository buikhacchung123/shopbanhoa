package com.example.testcontentprovider.model;

import com.google.gson.annotations.SerializedName;

public class KhachHang {
    @SerializedName("maNd")
    String maNd;

    @SerializedName("tenNd")
    String hoten;

    @SerializedName("sdt")
    String sdt;

    @SerializedName("username")
    String username;

    @SerializedName("passWord")
    String password;

    @SerializedName("diaChi")
    String diachi;

    @SerializedName("trangThai")
    Boolean trangThai;

    public KhachHang(){
    }

    public KhachHang( String hoten, String sdt, String username, String password, String diachi, Boolean trangThai) {
        this.hoten = hoten;
        this.sdt = sdt;
        this.username = username;
        this.password = password;
        this.diachi = diachi;
        this.trangThai = trangThai;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getMaNd() {
        return maNd;
    }

    public void setMaNd(String maNd) {
        this.maNd = maNd;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }
}
