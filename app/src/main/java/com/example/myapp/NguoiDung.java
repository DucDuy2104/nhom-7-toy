package com.example.myapp;

public class NguoiDung {
    p dùng, giới tính, ngày sinh, số điện thoại, email, dịa chỉ, tên đăng nhập, mật khẩu, đường dẫn ảnh
    private int role;

    publ emailNd;
        this.diaChiNd = diaChiNd;
        this.tenDn = tenDn;
        this.matKhau = matKhau;
        this.imageUri = imageUri;
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public NguoiDung() {
    }

    public int getIdNd() {
        return idNd;
    }

    public void setIdNd(int idNd) {
        this.idNd = idNd;
    }

    public String getTenNd() {
        return tenNd;
    }

    public void setTenNd(String tenNd) {
        this.tenNd = tenNd;
    }

    public String getGioiTinhNd() {
        return gioiTinhNd;
    }

    public void setGioiTinhNd(String gioiTinhNd) {
        this.gioiTinhNd = gioiTinhNd;
    }

    public String getNgaySinhNd() {
        return ngaySinhNd;
    }

    public void setNgaySinhNd(String ngaySinhNd) {
        this.ngaySinhNd = ngaySinhNd;
    }

    public String getSdtNd() {
        return sdtNd;
    }

    public void setSdtNd(String sdtNd) {
        this.sdtNd = sdtNd;
    }

    public String getEmailNd() {
        return emailNd;
    }

    public void setEmailNd(String emailNd) {
        this.emailNd = emailNd;
    }

    public String getDiaChiNd() {
        return diaChiNd;
    }

    public void setDiaChiNd(String diaChiNd) {
        this.diaChiNd = diaChiNd;
    }

    public String getTenDn() {
        return tenDn;
    }

    public void setTenDn(String tenDn) {
        this.tenDn = tenDn;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
