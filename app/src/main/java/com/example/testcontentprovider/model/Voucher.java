package com.example.testcontentprovider.model;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;

public class Voucher implements Serializable {
    @SerializedName("maVc")
    int maVC;

    @SerializedName("moTa")
    String moTa;

    @SerializedName("khuyenMai")
    int khuyenMai;

    @SerializedName("soLuong")
    int soLuong;

    @SerializedName("tieuDe")
    String tieuDe;

    @SerializedName("ngayBatDau")
    String ngayBatDau;

    @SerializedName("ngayKetThuc")
    String ngayKetThuc;

    @SerializedName("hinhBanner")
    String hinhBanner;

    public int getMaVC() {
        return maVC;
    }

    public void setMaVC(int maVC) {
        this.maVC = maVC;
    }
    public int getMaVc() {
        return maVC;
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

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getHinhBanner() {
        return hinhBanner;
    }

    public void setHinhBanner(String hinhBanner) {
        this.hinhBanner = hinhBanner;
    }


    public Voucher(int maVC, String moTa, int khuyenMai, int soLuong, String tieuDe, String ngayBatDau, String ngayKetThuc, String hinhBanner) {
        this.maVC = maVC;
        this.moTa = moTa;
        this.khuyenMai = khuyenMai;
        this.soLuong = soLuong;
        this.tieuDe = tieuDe;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hinhBanner = hinhBanner;
    }
    public Voucher(){}
}
