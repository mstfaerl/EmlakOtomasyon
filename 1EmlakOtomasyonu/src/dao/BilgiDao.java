/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.BilgiDekorasyon;
import entity.KiralikBilgi;
import entity.SatilikBilgi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author m07er
 */
public class BilgiDao extends Connect implements Dao<BilgiDekorasyon> {

    @Override
    public void create(BilgiDekorasyon o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("insert into bilgi(bilgi_kategori,bilgi_tur) values(?,?)");
            pst.setString(1, o.getBilgi_kategori());
            pst.setString(2, o.getBilgi_tur());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void update(BilgiDekorasyon o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("update bilgi set bilgi_kategori=?,bilgi_tur=? where bilgi_id=?");
            pst.setString(1, o.getBilgi_kategori());
            pst.setString(2, o.getBilgi_tur());
            pst.setLong(3, o.getBilgi_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<BilgiDekorasyon> list() {
        ArrayList<BilgiDekorasyon> bList = new ArrayList();
        BilgiDekorasyon bilgi;
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select * from bilgi");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("bilgi_tur").equalsIgnoreCase("Satılık")) {
                    bilgi = new SatilikBilgi();
                    bilgi.setBilgi_id(rs.getLong("bilgi_id"));
                    bilgi.setBilgi_kategori(rs.getString("bilgi_kategori"));
                    bList.add(bilgi);
                } else if (rs.getString("bilgi_tur").equalsIgnoreCase("Kiralık")) {
                    bilgi = new KiralikBilgi();
                    bilgi.setBilgi_id(rs.getLong("bilgi_id"));
                    bilgi.setBilgi_kategori(rs.getString("bilgi_kategori"));
                    bList.add(bilgi);
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return bList;
    }

    @Override
    public void delete(BilgiDekorasyon o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("delete from bilgi where bilgi_id=?");
            pst.setLong(1, o.getBilgi_id());
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
    public BilgiDekorasyon detail(Long id) {
        BilgiDekorasyon bilgi = null;
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select * from bilgi where bilgi_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                if (rs.getString("bilgi_tur").equalsIgnoreCase("Satılık")) {
                    bilgi = new SatilikBilgi();
                    bilgi.setBilgi_id(rs.getLong("bilgi_id"));
                    bilgi.setBilgi_kategori(rs.getString("bilgi_kategori"));
                } else if (rs.getString("bilgi_tur").equalsIgnoreCase("Kiralık")) {
                    bilgi = new KiralikBilgi();
                    bilgi.setBilgi_id(rs.getLong("bilgi_id"));
                    bilgi.setBilgi_kategori(rs.getString("bilgi_kategori"));
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bilgi;
    }

    /* public Bilgi bul(String kategori,String tur){
        Bilgi bilgi = null;
        try{
            PreparedStatement pst = this.getConnect().prepareStatement("select bilgi_id from bilgi where bilgi_kategori=? and bilgi_tur=?");
            pst.setString(1,kategori);
            pst.setString(2, tur);
            ResultSet rs = pst.executeQuery();
            rs.next();
            bilgi = new Bilgi(rs.getLong("bilgi_id"));
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bilgi; 
    }*/
}
