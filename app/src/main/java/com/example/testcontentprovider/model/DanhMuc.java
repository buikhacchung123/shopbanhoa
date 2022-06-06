package com.example.testcontentprovider.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhMuc implements Serializable {
    @SerializedName("maDm")
    String MaDM;

    @SerializedName("hinhDm")
    String hinhDM;

    @SerializedName("tenDm")
    String tenDM;

    public DanhMuc(String maDM, String tenDM, String hinhDM) {
        this.hinhDM = hinhDM;
        this.tenDM = tenDM;
        this.MaDM = maDM;
    }

    public DanhMuc() {

    }
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
    public ArrayList<DanhMuc> getDM() {
        ArrayList<DanhMuc> dsdm = new ArrayList<DanhMuc>();
        DanhMuc sp1 = new DanhMuc();
        sp1.setMaDM("DM001");
        sp1.setTenDM("Hoa tình yêu");
        sp1.setHinhDM("hoatinhyeu.jpg");
        DanhMuc sp2 = new DanhMuc();
        sp2.setMaDM("DM002");
        sp2.setTenDM("Hoa tặng mẹ");
        sp2.setHinhDM("hoatangme.jpg");
        DanhMuc sp3 = new DanhMuc();
        sp3.setMaDM("DM003");
        sp3.setTenDM("Hoa sinh nhật");
        sp3.setHinhDM("hoasinhnhat.jpg");
        DanhMuc sp4 = new DanhMuc();
        sp4.setMaDM("DM004");
        sp4.setTenDM("Hoa chúc mừng");
        sp4.setHinhDM("hoachucmung.jpg");
        DanhMuc sp5 = new DanhMuc();
        sp5.setMaDM("DM005");
        sp5.setTenDM("Hoa chia buồn");
        sp5.setHinhDM("hoachiabuon.png");
        dsdm.add(sp1);
        dsdm.add(sp2);
        dsdm.add(sp3);
        dsdm.add(sp4);
        dsdm.add(sp5);
        return  dsdm;
    }

}
