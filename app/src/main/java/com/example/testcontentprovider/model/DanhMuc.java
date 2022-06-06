package com.example.testcontentprovider.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhMuc implements Serializable {
    String maDm,hinhDM, tenDm;
    public DanhMuc() {

    }

    public String getMaDm() {
        return maDm;
    }

    public void setMaDm(String maDm) {
        this.maDm = maDm;
    }

    public String getTenDm() {
        return tenDm;
    }

    public void setTenDm(String tenDm) {
        this.tenDm = tenDm;
    }

    public String getHinhDM() {
        return hinhDM;
    }

    public void setHinhDM(String hinhDM) {
        this.hinhDM = hinhDM;
    }


}
