package net.codejava.orderfoodspring.Trangthaidonhang;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class KeyT implements Serializable {
    private Integer matrangthai;
    private Integer madon;

    public Integer getMatrangthai() {
        return matrangthai;
    }

    public void setMatrangthai(Integer matrangthai) {
        this.matrangthai = matrangthai;
    }

    public Integer getMadon() {
        return madon;
    }

    public void setMadon(Integer madon) {
        this.madon = madon;
    }

    public KeyT(Integer matrangthai, Integer madon) {
        this.matrangthai = matrangthai;
        this.madon = madon;
    }

    public KeyT(){}
    
}
