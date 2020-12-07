package net.codejava.orderfoodspring.Datdoan;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Datdoan {
    @EmbeddedId
    private Key primaryKey;
    
    private Integer soluong;
    private Long thanhtien;

    public Key getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Key primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Long getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(Long thanhtien) {
        this.thanhtien = thanhtien;
    }

    public Datdoan(Key primaryKey, Integer soluong, Long thanhtien) {
        this.primaryKey = primaryKey;
        this.soluong = soluong;
        this.thanhtien = thanhtien;
    }

    public Datdoan(){}
}
