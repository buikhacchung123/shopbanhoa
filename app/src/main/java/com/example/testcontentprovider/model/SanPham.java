package com.example.testcontentprovider.model;

import android.widget.Toast;

import com.example.testcontentprovider.activity.LoginActivity;
import com.example.testcontentprovider.activity.MainActivity;
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

    String  tenSp, moTa, hinhSp;
    int maSp, soLuong, maDm;
    int giaBan;
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

    public SanPham(int maSp, String tenSp, String moTa, String hinhSp, int maDm, int soLuong, int giaBan) {
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

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
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

    public int getMaDm() {
        return maDm;
    }

    public void setMaDm(int maDm) {
        this.maDm = maDm;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public SanPham getSPByMaSP(int ma)
    {
        SanPham kq = new SanPham();
        for (int i = 0; i < MainActivity.arraySP.size(); i++)
        {
            if(MainActivity.arraySP.get(i).getMaSp() == ma)
            {
                kq = MainActivity.arraySP.get(i);
                break;
            }
        }
        return kq;
    }

}
