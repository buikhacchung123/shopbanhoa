package com.example.testcontentprovider.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhMuc implements Serializable {
    @SerializedName("maDm")
    String MaDM;

    @SerializedName("hinhDM")
    String hinhDM;

    @SerializedName("tenDm")
    String tenDM;

    public DanhMuc(String maDM, String tenDM, String hinhDM) {
        this.hinhDM = hinhDM;
        this.tenDM = tenDM;
        this.MaDM = maDM;
    }

    public DanhMuc() { }
    public String getMaDM() {
        return MaDM;
    }

    public void setMaDM(String maDM) {
        MaDM = maDM;
    }

    public String getHinhDM() {
        return hinhDM;
    }

    public void setHinhDM(String hinhDM) {
        this.hinhDM = hinhDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

}
