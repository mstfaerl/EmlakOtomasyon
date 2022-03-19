/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface Dao<T> {

    void create(T o);

    void update(T o);

    ArrayList<T> list();

    void delete(T o);
 
    ResultSet rsList();
    
    T detail(Long id);
}
