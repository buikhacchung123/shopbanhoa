package com.example.testcontentprovider.model;

import java.io.Serializable;
import java.util.Date;

public class HoaDon implements Serializable {
    String maHd;
    String ngayLap;
    String ngayGiao;
    int maKh;
    String maVc;
    String diaChiGiao;
    Boolean trangThai;
    int tongTien;
    int tongSoLuong;

    public HoaDon() {
    }

    public HoaDon(String ngayLap, String ngayGiao, int maKh, String maVc, String diaChigiao, Boolean trangThai, int tongTien, int tongSoluong) {
        this.ngayLap = ngayLap;
        this.ngayGiao = ngayGiao;
        this.maKh = maKh;
        this.maVc = maVc;
        this.diaChiGiao = diaChigiao;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.tongSoLuong = tongSoluong;
    }

    public String getMaHd() {
        return maHd;
    }

    public void setMaHd(String maHd) {
        this.maHd = maHd;
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

    public int getMaKh() {
        return maKh;
    }

    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }

    public String getMaVc() {
        return maVc;
    }

    public void setMaVc(String maVc) {
        this.maVc = maVc;
    }

    public String getDiaChigiao() {
        return diaChiGiao;
    }

    public void setDiaChigiao(String diaChigiao) {
        this.diaChiGiao = diaChigiao;
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
        return tongSoLuong;
    }

    public void setTongSoluong(int tongSoluong) {
        this.tongSoLuong = tongSoluong;
    }
}
