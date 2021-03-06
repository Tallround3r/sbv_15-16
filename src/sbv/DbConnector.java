package sbv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    /*
     Connects to SQL Database And catches error if appers
     */
    public static Connection getConnection() {
        try {
            final String driver = "com.mysql.jdbc.Driver";                //chosing driver
            final String url = "jdbc:mysql://localhost:3307/sbv_aes_2013";//choosing mySQL server
            final String username = "root";                               //DB ussername and password
            final String password = "usbw";
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, username, password); //Connecting
            System.out.println("Connected");                                     //conectian establischt notification
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e + " => getConnection");
        } // catches error if appears

        return null;
    }

}
