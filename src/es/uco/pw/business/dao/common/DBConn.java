package es.uco.pw.business.dao.common;

import java.io.*;
import java.sql.*;
import java.util.Properties;


public class DBConn {
    private DBConn(){

    }

    public static Connection getConn() throws SQLException, ClassNotFoundException {
        String user = "";
        String password = "";
        String address = "";
        Connection conn = null;
        try {
            InputStream in = DBConn.class.getResourceAsStream("/config.properties");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Properties p = new Properties();
            p.load(reader);
            user = p.getProperty("MYSQL_USER");
            password = p.getProperty("MYSQL_PASSWORD");
            address = p.getProperty("MYSQL_ADDRESS");
            
        } catch (NullPointerException e){
            try {
                FileReader reader=new FileReader("config.properties");
                Properties p = new Properties();
                p.load(reader);
                user = p.getProperty("MYSQL_USER");
                password = p.getProperty("MYSQL_PASSWORD");
                address = p.getProperty("MYSQL_ADDRESS")+"/"+p.getProperty("MYSQL_DATABASE");
                
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        conn=DriverManager.getConnection(address, user, password);
        Class.forName("com.mysql.jdbc.Driver");
        return conn;
    }

    static public ResultSet execQuery(Connection conn, String query) throws SQLException, ClassNotFoundException {
        Statement stmt= null;
        ResultSet result = null;
        assert conn != null;
        stmt = conn.createStatement();
        result = stmt.executeQuery(query);
        return result;
    }
}
