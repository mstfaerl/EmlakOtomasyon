/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author m07er
 */
public class Grup {
    
    private Long grup_id;
    private String grup_adi;

    public Grup() {
    }

    public Grup(Long grup_id, String grup_adi) {
        this.grup_id = grup_id;
        this.grup_adi = grup_adi;
    }

    public Long getGrup_id() {
        return grup_id;
    }

    public void setGrup_id(Long grup_id) {
        this.grup_id = grup_id;
    }

    public String getGrup_adi() {
        return grup_adi;
    }

    public void setGrup_adi(String grup_adi) {
        this.grup_adi = grup_adi;
    }

    @Override
    public String toString() {
        return "Grup{" + "grup_id=" + grup_id + ", grup_adi=" + grup_adi + '}';
    }

}
