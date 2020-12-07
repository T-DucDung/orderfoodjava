package net.codejava.orderfoodspring.Request;

public class DatdoanReq {
    private Integer mamon;
    private Integer soluong;    

    public Integer getMamon() {
        return mamon;
    }

    public void setMamon(Integer mamon) {
        this.mamon = mamon;
    }

    public Integer getsoluong() {
        return soluong;
    }

    public void setsoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public DatdoanReq(Integer mamon, Integer soluong) {
        this.mamon = mamon;
        this.soluong = soluong;
    }

    public DatdoanReq(){}
}
