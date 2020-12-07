package net.codejava.orderfoodspring.Loaidoan;

import javax.persistence.*;

@Entity
public class Loaidoan {
    private Integer maloai;
    private String tenloai;

    public Loaidoan(Integer maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public Loaidoan(){
        
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getmaloai() {
        return maloai;
    }

    public void setmaloai(Integer maloai) {
        this.maloai = maloai;
    }

    public String gettenloai() {
        return tenloai;
    }

    public void settenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
