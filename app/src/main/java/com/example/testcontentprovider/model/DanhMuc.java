package com.example.testcontentprovider.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhMuc implements Serializable {
    String hinhDM, tenDM;

    public DanhMuc(String hinhDM, String tenDM) {
        this.hinhDM = hinhDM;
        this.tenDM = tenDM;
    }

    public DanhMuc() {

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
        sp1.setTenDM("Nơi tình yêu bắt đầu");
        sp1.setHinhDM("noitinhyeubatdau.jpg");
        DanhMuc sp2 = new DanhMuc();
        sp2.setTenDM("Giấc mơ tuyết trắng");
        sp2.setHinhDM("giacmotuyettrang.jpg");
        DanhMuc sp3 = new DanhMuc();
        sp3.setTenDM("Ánh chiều vàng");
        sp3.setHinhDM("anhchieuvang.jpg");
        DanhMuc sp4 = new DanhMuc();
        sp4.setTenDM("Bình yên");
        sp4.setHinhDM("binhyen.jpg");
        DanhMuc sp5 = new DanhMuc();
        sp5.setTenDM("Mơ mộng");
        sp5.setHinhDM("momong.jpg");
        dsdm.add(sp1);
        dsdm.add(sp2);
        dsdm.add(sp3);
        dsdm.add(sp4);
        dsdm.add(sp5);
        return  dsdm;
    }

}
