/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Emlak;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author m07er
 */
public class EmlakDao extends Connect implements Dao<Emlak> {

    @Override
    public void create(Emlak o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("insert into emlak(emlak_m2,emlak_odasayisi,emlak_adres,emlak_isitma,emlak_aidat,emlak_esyali,emlak_fiyat) values(?,?,?,?,?,?,?)");
            pst.setString(1, o.getEmlak_m2());
            pst.setString(2, o.getEmlak_odasayisi());
            pst.setString(3, o.getEmlak_adres());
            pst.setString(4, o.getEmlak_isitma());
            pst.setDouble(5, o.getEmlak_aidat());
            pst.setString(6, o.getEmlak_esyali());
            pst.setDouble(7, o.getEmlak_fiyat());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Emlak o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("update emlak set emlak_m2=?,emlak_odasayisi=?,emlak_adres=?,emlak_isitma=?,emlak_aidat=?,emlak_esyali=?,emlak_fiyat=? where emlak_id=?");
            pst.setString(1, o.getEmlak_m2());
            pst.setString(2, o.getEmlak_odasayisi());
            pst.setString(3, o.getEmlak_adres());
            pst.setString(4, o.getEmlak_isitma());
            pst.setDouble(5, o.getEmlak_aidat());
            pst.setString(6, o.getEmlak_esyali());
            pst.setDouble(7, o.getEmlak_fiyat());
            pst.setLong(8, o.getEmlak_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Emlak> list() {
        ArrayList<Emlak> eList = new ArrayList();
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select * from emlak");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Emlak tmp = new Emlak();
                tmp.setEmlak_id(rs.getLong("emlak_id"));
                tmp.setEmlak_m2(rs.getString("emlak_m2"));
                tmp.setEmlak_odasayisi(rs.getString("emlak_odasayisi"));
                tmp.setEmlak_adres(rs.getString("emlak_adres"));
                tmp.setEmlak_isitma(rs.getString("emlak_isitma"));
                tmp.setEmlak_aidat(rs.getDouble("emlak_aidat"));
                tmp.setEmlak_esyali(rs.getString("emlak_esyali"));
                tmp.setEmlak_fiyat(rs.getDouble("emlak_fiyat"));
                eList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return eList;
    }
    
    public ArrayList<Emlak> list1(String s) {
        ArrayList<Emlak> eList = new ArrayList();
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select * from emlak where emlak_odasayisi = ?");
            pst.setString(1, s);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Emlak tmp = new Emlak();
                tmp.setEmlak_id(rs.getLong("emlak_id"));
                tmp.setEmlak_m2(rs.getString("emlak_m2"));
                tmp.setEmlak_odasayisi(rs.getString("emlak_odasayisi"));
                tmp.setEmlak_adres(rs.getString("emlak_adres"));
                tmp.setEmlak_isitma(rs.getString("emlak_isitma"));
                tmp.setEmlak_aidat(rs.getDouble("emlak_aidat"));
                tmp.setEmlak_esyali(rs.getString("emlak_esyali"));
                tmp.setEmlak_fiyat(rs.getDouble("emlak_fiyat"));
                eList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return eList;
    }

    @Override
    public void delete(Emlak o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("delete from emlak where emlak_id=?");
            pst.setLong(1, o.getEmlak_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ResultSet rsList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Emlak detail(Long id) {
        Emlak emlak = null;
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select * from emlak where emlak_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                emlak = new Emlak(rs.getLong("emlak_id"), rs.getString("emlak_m2"), rs.getString("emlak_odasayisi"), rs.getString("emlak_adres"), rs.getString("emlak_isitma"), rs.getDouble("emlak_aidat"), rs.getString("emlak_esyali"), rs.getDouble("emlak_fiyat"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return emlak;
    }
    
    public int EmlakOdaSayisi(String s){
        int count = 0;
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select count(emlak_odasayisi) as oda_count from emlak where emlak_odasayisi=?");
            pst.setString(1, s);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                count = rs.getInt("oda_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return count;
    }

}
