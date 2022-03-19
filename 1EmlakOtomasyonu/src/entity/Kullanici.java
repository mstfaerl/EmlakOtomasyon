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
public class Kullanici {

    private Long kullanici_id;
    private String kullanici_adi;
    private String kullanici_email;
    private String kullanici_sifre;
    private String kullanici_telno;
    private Grup grup;

    public Kullanici() {
    }

    public Kullanici(Long kullanici_id, String kullanici_adi, String kullanici_email, String kullanici_sifre, String kullanici_telno, Grup grup) {
        this.kullanici_id = kullanici_id;
        this.kullanici_adi = kullanici_adi;
        this.kullanici_email = kullanici_email;
        this.kullanici_sifre = kullanici_sifre;
        this.kullanici_telno = kullanici_telno;
        this.grup = grup;
    }

    public Long getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(Long kullanici_id) {
        this.kullanici_id = kullanici_id;
    }
    
    public void setKullanici_sifre(String kullanici_sifre) {
        this.kullanici_sifre = kullanici_sifre;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public String getKullanici_email() {
        return kullanici_email;
    }

    public void setKullanici_email(String kullanici_email) {
        this.kullanici_email = kullanici_email;
    }

    public String getKullanici_sifre() {
        return kullanici_sifre;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getKullanici_telno() {
        return kullanici_telno;
    }

    public void setKullanici_telno(String kullanici_telno) {
        this.kullanici_telno = kullanici_telno;
    }

    public Grup getGrup() {
        return grup;
    }

    public void setGrup(Grup grup) {
        this.grup = grup;
    }

    @Override
    public String toString() {
        return "Kullanici{" + "kullanici_id=" + kullanici_id + ", kullanici_adi=" + kullanici_adi + ", kullanici_email=" + kullanici_email + ", kullanici_sifre=" + kullanici_sifre + ", kullanici_telno=" + kullanici_telno + ", grup=" + grup + '}';
    }

}
