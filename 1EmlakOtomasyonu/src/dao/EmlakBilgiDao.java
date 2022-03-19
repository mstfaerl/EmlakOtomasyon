/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entity.EmlakBilgi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author m07er
 */
public class EmlakBilgiDao extends Connect implements Dao<EmlakBilgi> {

    EmlakDao emlakDao;
    BilgiDao bilgiDao;

    @Override
    public void create(EmlakBilgi o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("insert into emlakbilgi(emlak_id,bilgi_id,yeniFiyat) values(?,?,?)");
            pst.setLong(1, o.getEmlak().getEmlak_id());
            pst.setLong(2, o.getBilgi().getBilgi_id());
            pst.setDouble(3, o.getYeniFiyat());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(EmlakBilgi o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("update emlakbilgi set emlak_id=?,bilgi_id=?,yenifiyat=? where emlakbilgi_id=?");
            pst.setLong(1, o.getEmlak().getEmlak_id());
            pst.setLong(2, o.getBilgi().getBilgi_id());
            pst.setDouble(3, o.getYeniFiyat());
            pst.setLong(4, o.getEmlakBilgi_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<EmlakBilgi> list() {
        ArrayList<EmlakBilgi> ebList = new ArrayList(); //sadece ıd erişimi için arraylist
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select * from emlakbilgi");
            ResultSet rs = pst.executeQuery();   
            while (rs.next()) {
                EmlakBilgi tmp = new EmlakBilgi();
                tmp.setEmlakBilgi_id(rs.getLong("emlakbilgi_id"));
                tmp.setEmlak(this.emlakDao.detail(rs.getLong("emlak_id")));
                tmp.setBilgi(this.bilgiDao.detail(rs.getLong("bilgi_id")));
                tmp.setEmlakBilgi_ot(rs.getDate("emlakbilgi_ot"));
                tmp.setYeniFiyat(rs.getDouble("yeniFiyat"));
                ebList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ebList;
    }

    @Override
    public void delete(EmlakBilgi o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("delete from emlakbilgi where emlakbilgi_id=?");
            pst.setLong(1, o.getEmlakBilgi_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ResultSet rsList() {
        ResultSet rs = null;
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select eb.emlakbilgi_id,e.emlak_id,e.emlak_m2,e.emlak_odasayisi,e.emlak_adres,e.emlak_isitma,e.emlak_aidat,e.emlak_esyali,e.emlak_fiyat,\n" +
"b.bilgi_id,b.bilgi_kategori,b.bilgi_tur,emlakbilgi_ot,yenifiyat from emlak e,bilgi b,emlakbilgi eb where e.emlak_id=eb.emlak_id and b.bilgi_id=eb.bilgi_id");
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
    
    public ResultSet rsKullaniciList() {
        ResultSet rs = null;
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select b.bilgi_kategori,b.bilgi_tur,e.emlak_m2,e.emlak_odasayisi,e.emlak_adres,e.emlak_isitma,e.emlak_aidat,e.emlak_esyali,e.emlak_fiyat,eb.yenifiyat from emlak e,bilgi b,emlakbilgi eb where e.emlak_id=eb.emlak_id and b.bilgi_id=eb.bilgi_id");
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    @Override
    public EmlakBilgi detail(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
