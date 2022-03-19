/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author m07er
 */
public class EmlakBilgi {
    
    private Long emlakBilgi_id;
    private Emlak emlak;
    private BilgiDekorasyon bilgi;
    private Date emlakBilgi_ot;
    private double yeniFiyat;

    public EmlakBilgi() {
        
    }

    public EmlakBilgi(Long emlakBilgi_id, Emlak emlak, BilgiDekorasyon bilgi, Date emlakBilgi_ot,double yeniFiyat) {
        this.emlakBilgi_id = emlakBilgi_id;
        this.emlak = emlak;
        this.bilgi = bilgi;
        this.emlakBilgi_ot = emlakBilgi_ot;
        this.yeniFiyat = yeniFiyat;
    }

    public Long getEmlakBilgi_id() {
        return emlakBilgi_id;
    }

    public void setEmlakBilgi_id(Long emlakBilgi_id) {
        this.emlakBilgi_id = emlakBilgi_id;
    }

    public Emlak getEmlak() {
        return emlak;
    }

    public void setEmlak(Emlak emlak) {
        this.emlak = emlak;
    }

    public BilgiDekorasyon getBilgi() {
        return bilgi;
    }

    public void setBilgi(BilgiDekorasyon bilgi) {
        this.bilgi = bilgi;
    }

    public Date getEmlakBilgi_ot() {
        return emlakBilgi_ot;
    }

    public void setEmlakBilgi_ot(Date emlakBilgi_ot) {
        this.emlakBilgi_ot = emlakBilgi_ot;
    }

    public double getYeniFiyat() {
        return yeniFiyat;
    }

    public void setYeniFiyat(double yeniFiyat) {
        this.yeniFiyat = yeniFiyat;
    }
    
    @Override
    public String toString() {
        return "EmlakBilgi{" + "emlakBilgi_id=" + emlakBilgi_id + ", emlak=" + emlak + ", bilgi=" + bilgi + ", emlakBilgi_ot=" + emlakBilgi_ot + '}';
    }

}
