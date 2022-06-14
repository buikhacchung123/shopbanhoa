package com.example.testcontentprovider.model;

import java.io.Serializable;
import java.util.Date;

public class HoaDon implements Serializable {
    int maHd;
    Date ngayLap;
    Date ngayGiao;
    int maKh;
    int maVc;
    String diaChigiao;
    Boolean trangThai;
    int tongTien;
    int tongSoluong;

    public HoaDon() {
    }

    public int getMaHd() {
        return maHd;
    }

    public void setMaHd(int maHd) {
        this.maHd = maHd;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public Date getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(Date ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public int getMaKh() {
        return maKh;
    }

    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }

    public int getMaVc() {
        return maVc;
    }

    public void setMaVc(int maVc) {
        this.maVc = maVc;
    }

    public String getDiaChigiao() {
        return diaChigiao;
    }

    public void setDiaChigiao(String diaChigiao) {
        this.diaChigiao = diaChigiao;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getTongSoluong() {
        return tongSoluong;
    }

    public void setTongSoluong(int tongSoluong) {
        this.tongSoluong = tongSoluong;
    }
}
