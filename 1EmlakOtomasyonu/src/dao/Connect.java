/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import utility.ConnectionManager;

/**
 *
 * @author m07er
 */
public abstract class Connect {
    
    private ConnectionManager cm;
    private Connection connect;

    public Connection getConnect() {
        if (this.connect == null) {
            this.connect = ConnectionManager.getConnection();
        }
        return connect;
    }


    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    public ConnectionManager getCm() {
        if(this.cm == null){
            this.connect = (Connection) ConnectionManager.getInstance();
        }
        return cm;
    }

    public void setCm(ConnectionManager cm) {
        this.cm = cm;
    }
    
    

}
