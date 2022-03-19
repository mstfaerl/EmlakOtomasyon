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
public class EmlakYontemHesapla {
    
    private IEmlak emlak ;

    public EmlakYontemHesapla(IEmlak emlak) {
        this.emlak = emlak;
    }
    
    public double hesapla(double a){
        return emlak.hesapla(a);
    }
    
}
