/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author m07er
 */
public class TestConnection {

    private static String url = "jdbc:postgresql://localhost:5432/EmlakDatabase";
    private static String driverName = "org.postgresql.Driver";
    private static String username = "postgres";
    private static String password = "root1234";
    private static Connection conn;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("Veritabanına Bağlanıldı.");
            } catch (SQLException ex) {
                System.out.println("Veritabanı oluşturulamadı.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
        }
        return conn;
    }

    public static void main(String[] args) {
        TestConnection tc = new TestConnection();
        tc.getConnection();
    }

}
