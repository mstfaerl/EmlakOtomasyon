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
public class Emlak {
    
    private Long emlak_id;
    private String emlak_m2;
    private String emlak_odasayisi;
    private String emlak_adres;
    private String emlak_isitma;
    private Double emlak_aidat;
    private String emlak_esyali;
    private Double emlak_fiyat;

    public Emlak() {
    }

    public Emlak(Long emlak_id, String emlak_m2, String emlak_odasayisi, String emlak_adres, String emlak_isitma, Double emlak_aidat, String emlak_esyali, Double emlak_fiyat) {
        this.emlak_id = emlak_id;
        this.emlak_m2 = emlak_m2;
        this.emlak_odasayisi = emlak_odasayisi;
        this.emlak_adres = emlak_adres;
        this.emlak_isitma = emlak_isitma;
        this.emlak_aidat = emlak_aidat;
        this.emlak_esyali = emlak_esyali;
        this.emlak_fiyat = emlak_fiyat;
    }
    
    public Long getEmlak_id() {
        return emlak_id;
    }

    public void setEmlak_id(Long emlak_id) {
        this.emlak_id = emlak_id;
    }

    public String getEmlak_m2() {
        return emlak_m2;
    }

    public void setEmlak_m2(String emlak_m2) {
        this.emlak_m2 = emlak_m2;
    }

    public String getEmlak_odasayisi() {
        return emlak_odasayisi;
    }

    public void setEmlak_odasayisi(String emlak_odasayisi) {
        this.emlak_odasayisi = emlak_odasayisi;
    }

    public String getEmlak_adres() {
        return emlak_adres;
    }

    public void setEmlak_adres(String emlak_adres) {
        this.emlak_adres = emlak_adres;
    }

    public String getEmlak_isitma() {
        return emlak_isitma;
    }

    public void setEmlak_isitma(String emlak_isitma) {
        this.emlak_isitma = emlak_isitma;
    }

    public Double getEmlak_aidat() {
        return emlak_aidat;
    }

    public void setEmlak_aidat(Double emlak_aidat) {
        this.emlak_aidat = emlak_aidat;
    }

    public String getEmlak_esyali() {
        return emlak_esyali;
    }

    public void setEmlak_esyali(String emlak_esyali) {
        this.emlak_esyali = emlak_esyali;
    }

    public Double getEmlak_fiyat() {
        return emlak_fiyat;
    }

    public void setEmlak_fiyat(Double emlak_fiyat) {
        this.emlak_fiyat = emlak_fiyat;
    }

    @Override
    public String toString() {
        return "Emlak{" + "emlak_id=" + emlak_id + ", emlak_m2=" + emlak_m2 + ", emlak_odasayisi=" + emlak_odasayisi + ", emlak_adres=" + emlak_adres + ", emlak_isitma=" + emlak_isitma + ", emlak_aidat=" + emlak_aidat + ", emlak_esyali=" + emlak_esyali + ", emlak_fiyat=" + emlak_fiyat + '}';
    }
    
}
