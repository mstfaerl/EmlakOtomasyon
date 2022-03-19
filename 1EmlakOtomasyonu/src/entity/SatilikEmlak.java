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
public class SatilikEmlak implements IEmlak{

    @Override
    public double hesapla(double a) {
        a = (a + (a*0.1));
        return a;
    }
    
}
