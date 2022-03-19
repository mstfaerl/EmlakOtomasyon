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
public abstract class BilgiDekorasyon implements IBilgi{
    
    public static BilgiDekorasyon bilgi; 
    private Long bilgi_id;
    private String bilgi_kategori;
    private String bilgi_tur;

    public BilgiDekorasyon() {
    }

    public Long getBilgi_id() {
        return bilgi_id;
    }

    public void setBilgi_id(Long bilgi_id) {
        this.bilgi_id = bilgi_id;
    }

    public String getBilgi_kategori() {
        return bilgi_kategori;
    }

    public void setBilgi_kategori(String bilgi_kategori) {
        this.bilgi_kategori = bilgi_kategori;
    }

    public String getBilgi_tur() {
        return bilgi_tur;
    }

    public void setBilgi_tur(String bilgi_tur) {
        this.bilgi_tur = bilgi_tur;
    }

    @Override
    public void bilgiOlustur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
