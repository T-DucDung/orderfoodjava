package net.codejava.orderfoodspring.Datdoan;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Key implements Serializable{
    private Integer madon;
    private Integer mamon;

    public Key(Integer madon, Integer mamon) {
        this.madon = madon;
        this.mamon = mamon;
    }

    public Key(){}

    public Integer getMadon() {
        return madon;
    }

    public void setMadon(Integer madon) {
        this.madon = madon;
    }

    public Integer getMamon() {
        return mamon;
    }

    public void setMamon(Integer mamon) {
        this.mamon = mamon;
    }
}
