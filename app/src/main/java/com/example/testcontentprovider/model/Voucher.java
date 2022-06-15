package com.example.testcontentprovider.model;

import java.io.Serializable;
import java.util.Date;

public class Voucher implements Serializable {
    int maVc;
    String tieuDe;
    String moTa;
    int khuyenMai;
    int soLuong;
    String ngayBatDau;
    String ngayKetThuc;
    String hinhBanner;

    public Voucher() {
    }

    public int getMaVc() {
        return maVc;
    }

    public void setMaVc(int maVc) {
        this.maVc = maVc;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNgayBatdau() {
        return ngayBatDau;
    }

    public void setNgayBatdau(String ngayBatdau) {
        this.ngayBatDau = ngayBatdau;
    }

    public String getNgayKetthuc() {
        return ngayKetThuc;
    }

    public void setNgayKetthuc(String ngayKetthuc) {
        this.ngayKetThuc = ngayKetthuc;
    }

    public String getHinhBanner() {
        return hinhBanner;
    }

    public void setHinhBanner(String hinhBanner) {
        this.hinhBanner = hinhBanner;
    }
}
