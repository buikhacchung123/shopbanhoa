package com.example.testcontentprovider.model;

import java.io.Serializable;
import java.util.Date;

public class Voucher implements Serializable {
    int maVc;
    String tieuDe;
    String moTa;
    int khuyenMai;
    int soLuong;
    Date ngayBatdau;
    Date ngayKetthuc;
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

    public Date getNgayBatdau() {
        return ngayBatdau;
    }

    public void setNgayBatdau(Date ngayBatdau) {
        this.ngayBatdau = ngayBatdau;
    }

    public Date getNgayKetthuc() {
        return ngayKetthuc;
    }

    public void setNgayKetthuc(Date ngayKetthuc) {
        this.ngayKetthuc = ngayKetthuc;
    }

    public String getHinhBanner() {
        return hinhBanner;
    }

    public void setHinhBanner(String hinhBanner) {
        this.hinhBanner = hinhBanner;
    }
}
