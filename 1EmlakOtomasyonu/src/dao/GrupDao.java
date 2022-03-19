/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Grup;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author m07er
 */
public class GrupDao extends Connect implements Dao<Grup> {

    @Override
    public void create(Grup o) {
        try {
            PreparedStatement pst;
            pst = this.getConnect().prepareStatement("insert into grup(grup_adi) values(?)");
            pst.setString(1, o.getGrup_adi());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Grup o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("update grup set grup_adi=? where grup_id=?");
            pst.setString(1, o.getGrup_adi());
            pst.setLong(2, o.getGrup_id());
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Grup> list() {
        ArrayList<Grup> gList = new ArrayList();
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select * from grup");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Grup tmp = new Grup();
                tmp.setGrup_id(rs.getLong("grup_id"));
                tmp.setGrup_adi(rs.getString("grup_adi"));
                gList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return gList;
    }

    @Override
    public void delete(Grup o) {
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("delete from grup where grup_id=?");
            pst.setLong(1, o.getGrup_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ResultSet rsList() {
        ResultSet rs = null;
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select * from grup");
            rs = pst.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    @Override
    public Grup detail(Long id) {
        Grup grup = null;
        try {
            PreparedStatement pst = this.getConnect().prepareStatement("select * from grup where grup_id=?");
            pst.setLong(1, id);           
            ResultSet rs = pst.executeQuery();
            rs.next();
            grup = new Grup(rs.getLong("grup_id"), rs.getString("grup_adi"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return grup;
    }
}
