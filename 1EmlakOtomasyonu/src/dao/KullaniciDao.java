/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Grup;
import entity.Kullanici;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author m07er
 */
public class KullaniciDao extends Connect implements Dao<Kullanici> {
    
    GrupDao grupDao = new GrupDao();
    
    @Override
    public void create(Kullanici o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("insert into kullanici(kullanici_adi,kullanici_email,kullanici_sifre,kullanici_telno,grup_id) values(?,?,?,?,?)");
            pst.setString(1, o.getKullanici_adi());
            pst.setString(2, o.getKullanici_email());
            pst.setString(3, o.getKullanici_sifre());
            pst.setString(4, o.getKullanici_telno());
            pst.setLong(5, o.getGrup().getGrup_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void createKullanici(Kullanici o){
        Long id = null;
        id = Long.parseLong("2");
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("insert into kullanici(kullanici_adi,kullanici_email,kullanici_sifre,kullanici_telno,grup_id) values(?,?,?,?,?)");
            pst.setString(1, o.getKullanici_adi());
            pst.setString(2, o.getKullanici_email());
            pst.setString(3, o.getKullanici_sifre());
            pst.setString(4, o.getKullanici_telno());
            pst.setLong(5, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Kullanici o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("update kullanici set kullanici_adi=?,kullanici_email=?,kullanici_sifre=?,kullanici_telno=?,grup_id=? where kullanici_id=?");
            pst.setString(1, o.getKullanici_adi());
            pst.setString(2, o.getKullanici_email());
            pst.setString(3, o.getKullanici_sifre());
            pst.setString(4, o.getKullanici_telno());
            pst.setLong(5, o.getGrup().getGrup_id());
            pst.setLong(6, o.getKullanici_id());
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Kullanici> list() {
        ArrayList<Kullanici> kList = new ArrayList();
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select * from kullanici");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Kullanici tmp = new Kullanici();
                tmp.setKullanici_id(rs.getLong("kullanici_id"));
                tmp.setKullanici_adi(rs.getString("kullanici_adi"));
                tmp.setKullanici_email(rs.getString("kullanici_email"));
                tmp.setKullanici_sifre(rs.getString("kullanici_sifre"));
                tmp.setKullanici_telno(rs.getString("kullanici_telno"));
                kList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kList;
    }

    @Override
    public void delete(Kullanici o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("delete from kullanici where kullanici_id=?");
            pst.setLong(1, o.getKullanici_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ResultSet rsList() {
        ResultSet rs = null;
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select ku.kullanici_id,ku.kullanici_adi,ku.kullanici_email,ku.kullanici_sifre,ku.kullanici_telno,gr.grup_adi from kullanici ku,grup gr where ku.grup_id = gr.grup_id");
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
    
    /*public ResultSet idList(){
        ResultSet rs = null;
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select kullanici_id from kullanici");
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
*/

    @Override
    public Kullanici detail(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
