package net.codejava.orderfoodspring.Request;

public class DanhgiaReq {
    private int id;
    private int mamon;
    private String noidung;

    public DanhgiaReq(){}

    public DanhgiaReq(int id, int mamon, String noidung) {
        this.id = id;
        this.mamon = mamon;
        this.noidung = noidung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMamon() {
        return mamon;
    }

    public void setMamon(int mamon) {
        this.mamon = mamon;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
