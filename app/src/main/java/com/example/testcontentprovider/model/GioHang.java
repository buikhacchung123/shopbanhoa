package com.example.testcontentprovider.model;

import java.io.Serializable;

public class GioHang implements Serializable {
    String maGh;
    int maKh;
    int tongSp;
    double tongtien;

    public GioHang(){
    }

    public GioHang(int maKh, int tongSp, double tongtien) {
        this.maKh = maKh;
        this.tongSp = tongSp;
        this.tongtien = tongtien;
    }

    public String getMaGh() {
        return maGh;
    }

    public void setMaGh(String maGh) {
        this.maGh = maGh;
    }

    public int getMaKh() {
        return maKh;
    }

    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public int getTongSp() {
        return tongSp;
    }

    public void setTongSp(int tongSp) {
        this.tongSp = tongSp;
    }
}
