package com.example.testcontentprovider.model;

import android.widget.Toast;

import com.example.testcontentprovider.activity.LoginActivity;
import com.example.testcontentprovider.api.ApiService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanPham implements Serializable {

    public static final int TYPE_LIST = 1;
    public static final int TYPE_GRID = 2;

    String maSp, tenSp, moTa, hinhSp, maDm;
    int soLuong;
    double giaBan;
    int typeDisplay;

    public SanPham(){

    }
    public SanPham(SanPham sp)
    {
        this.maSp = sp.getMaSp();
        this.tenSp = sp.getTenSp();
        this.moTa = sp.getMoTa();
        this.hinhSp = sp.getHinhSp();
        this.maDm = sp.getMaDm();
        this.soLuong = sp.getSoLuong();
        this.giaBan = sp.getGiaBan();
    }

    public SanPham(String maSp, String tenSp, String moTa, String hinhSp, String maDm, int soLuong, double giaBan) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.moTa = moTa;
        this.hinhSp = hinhSp;
        this.maDm = maDm;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public int getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(int typeDisplay) {
        this.typeDisplay = typeDisplay;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhSp() {
        return hinhSp;
    }

    public void setHinhSp(String hinhSp) {
        this.hinhSp = hinhSp;
    }

    public String getMaDm() {
        return maDm;
    }

    public void setMaDm(String maDm) {
        this.maDm = maDm;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }



}
