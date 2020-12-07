package net.codejava.orderfoodspring.Donhang;

import javax.persistence.*;

@Entity
public class Donhang {
    private Integer madon;
    private Integer makhach;
    private String tenkhach;
    private String sdt;
    private Long tongtien;
    private String ghichu;
    private String diachinhan;
    private Long thoigiannhan;
    private Long thoigiandat;

    public Donhang(){}

    public Donhang(Integer madon, Integer makhach, String tenkhach, String sdt, Long tongtien,
            String ghichu, String diachinhan, Long thoigiannhan, Long thoigiandat) {
        this.madon = madon;
        this.makhach = makhach;
        this.tenkhach = tenkhach;
        this.sdt = sdt;
        this.tongtien = tongtien;
        this.ghichu = ghichu;
        this.diachinhan = diachinhan;
        this.thoigiannhan = thoigiannhan;
        this.thoigiandat = thoigiandat;
    }

    public Donhang(Integer makhach, String tenkhach, String sdt, Long tongtien,
            String ghichu, String diachinhan, Long thoigiannhan, Long thoigiandat) {
        this.makhach = makhach;
        this.tenkhach = tenkhach;
        this.sdt = sdt;
        this.tongtien = tongtien;
        this.ghichu = ghichu;
        this.diachinhan = diachinhan;
        this.thoigiannhan = thoigiannhan;
        this.thoigiandat = thoigiandat;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getMadon() {
        return madon;
    }

    public void setMadon(Integer madon) {
        this.madon = madon;
    }

    public Integer getMakhach() {
        return makhach;
    }

    public void setMakhach(Integer makhach) {
        this.makhach = makhach;
    }

    public String getTenkhach() {
        return tenkhach;
    }

    public void setTenkhach(String tenkhach) {
        this.tenkhach = tenkhach;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Long getTongtien() {
        return tongtien;
    }

    public void setTongtien(Long tongtien) {
        this.tongtien = tongtien;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getDiachinhan() {
        return diachinhan;
    }

    public void setDiachinhan(String diachinhan) {
        this.diachinhan = diachinhan;
    }

    public Long getThoigiannhan() {
        return thoigiannhan;
    }

    public void setThoigiannhan(Long thoigiannhan) {
        this.thoigiannhan = thoigiannhan;
    }

    public Long getThoigiandat() {
        return thoigiandat;
    }

    public void setThoigiandat(Long thoigiandat) {
        this.thoigiandat = thoigiandat;
    }
}
