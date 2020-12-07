package net.codejava.orderfoodspring.Trangthaidonhang;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Trangthaidonhang {
    @EmbeddedId
    private KeyT primaryKey;
    private String noidung;
    private Long ngaytao;

    public KeyT getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(KeyT primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Long getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Long ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Trangthaidonhang(KeyT primaryKey, String noidung, Long ngaytao) {
        this.primaryKey = primaryKey;
        this.noidung = noidung;
        this.ngaytao = ngaytao;
    }

    public Trangthaidonhang(){}
}
