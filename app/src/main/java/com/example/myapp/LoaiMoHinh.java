package com.example.myapp;

public class LoaiMoHinh {
    private ing imgUri; // đường dẫn của ảnh
c boolean getIsXemTatCa() {
        return isXemTatCa;
    }
aiMoHinh(String tenLoai, String imgUri) {
        this.tenLoai = tenLoai;
        this.imgUri = imgUri;
    }

    public int getMaLmh() {
        return maLmh;
    }

    public void setMaLmh(int maLmh) {
        this.maLmh = maLmh;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }
}
