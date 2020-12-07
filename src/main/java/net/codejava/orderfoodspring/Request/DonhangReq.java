package net.codejava.orderfoodspring.Request;

import java.util.List;

public class DonhangReq {
    private String diachinhan;
    private String ghichu;
    private String sdt;
    private String tenkhach;
    private List<DatdoanReq> doan;

    public String getDiachinhan() {
        return diachinhan;
    }

    public void setDiachinhan(String diachinhan) {
        this.diachinhan = diachinhan;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenkhach() {
        return tenkhach;
    }

    public void setTenkhach(String tenkhach) {
        this.tenkhach = tenkhach;
    }

    public List<DatdoanReq> getDoan() {
        return doan;
    }

    public void setDoan(List<DatdoanReq> doan) {
        this.doan = doan;
    }

    public DonhangReq(String diachinhan, String ghichu, String sdt, String tenkhach, List<DatdoanReq> doan) {
        this.diachinhan = diachinhan;
        this.ghichu = ghichu;
        this.sdt = sdt;
        this.tenkhach = tenkhach;
        this.doan = doan;
    }

    public DonhangReq(){}
}