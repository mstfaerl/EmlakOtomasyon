package utility;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager implements Serializable {

    private volatile static ConnectionManager cm = null; //volatile : değişkenin, program dışında bir etki altında bulunabileceğini gösterir.
    
    private static String url = "jdbc:postgresql://localhost:5432/EmlakDatabase";
    private static String driverName = "org.postgresql.Driver";
    private static String username = "postgres";
    private static String password = "root1234";
    private static Connection conn;
    
    private ConnectionManager(){
        
    }
    
    public static ConnectionManager getInstance(){
        if(cm == null){
            synchronized(ConnectionManager.class){ //bir thread erişim sağladığında diğer thread ilk thread in işini bitirmesini beklemesini sağlar.
                if(cm == null){
                    cm = new ConnectionManager();
                }
            }
        } 
        return cm;
    }

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                System.out.println("Veritabanı oluşturulamadı.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
        }
        return conn;
    }

}
