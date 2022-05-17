package com.example.testcontentprovider.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SanPham implements Serializable {

    public static final int TYPE_LIST = 1;
    public static final int TYPE_GRID = 2;

    String masp, tensp, mota, hinhsp, madm;
    int soluong;
    double giaban;
    int typeDisplay;

    public int getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(int typeDisplay) {
        this.typeDisplay = typeDisplay;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public String getMadm() {
        return madm;
    }

    public void setMadm(String madm) {
        this.madm = madm;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

    public ArrayList<SanPham> getSanPham() {
        ArrayList<SanPham> dssp = new ArrayList<SanPham>();
        SanPham sp1 = new SanPham();
        sp1.setMasp("SP001");
        sp1.setTensp("Nơi tình yêu bắt đầu");
        sp1.setGiaban(1300000);
        sp1.setHinhsp("noitinhyeubatdau.jpg");
        sp1.setMota("abcd");
        SanPham sp2 = new SanPham();
        sp2.setMasp("SP002");
        sp2.setTensp("Giấc mơ tuyết trắng");
        sp2.setGiaban(500000);
        sp2.setHinhsp("giacmotuyettrang.jpg");
        sp2.setMota("abcd");
        SanPham sp3 = new SanPham();
        sp3.setMasp("SP003");
        sp3.setTensp("Ánh chiều vàng");
        sp3.setGiaban(1300000);
        sp3.setHinhsp("anhchieuvang.jpg");
        sp3.setMota("abcd");
        SanPham sp4 = new SanPham();
        sp4.setMasp("SP004");
        sp4.setTensp("Bình yên");
        sp4.setGiaban(1500000);
        sp4.setHinhsp("binhyen.jpg");
        sp4.setMota("abcd");
        SanPham sp5 = new SanPham();
        sp5.setMasp("SP005");
        sp5.setTensp("Mơ mộng");
        sp5.setGiaban(400000);
        sp5.setHinhsp("momong.jpg");
        sp5.setMota("abcd");
        dssp.add(sp1);
        dssp.add(sp2);
        dssp.add(sp3);
        dssp.add(sp4);
        dssp.add(sp5);
        return  dssp;
    }
}
