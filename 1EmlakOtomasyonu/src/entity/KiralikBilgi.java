/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.swing.JOptionPane;
import views.BilgiPanel;

/**
 *
 * @author m07er
 */
public class KiralikBilgi extends BilgiDekorasyon {


    public KiralikBilgi() {
        this.bilgiOlustur();
    }

    @Override
    public void bilgiOlustur() {
        this.setBilgi_tur("Kiralık");
    }

}
