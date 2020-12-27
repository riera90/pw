package es.uco.pw.business.dao.common;

import java.io.*;
import java.sql.*;
import java.util.Properties;


public class DBConn {
    private Connection conn;


    public DBConn() throws SQLException, ClassNotFoundException {
        conn = getConn();
    }

    private Connection getConn() throws SQLException, ClassNotFoundException {
        String user = "";
        String password = "";
        String address = "";
        try {
            InputStream in = DBConn.class.getResourceAsStream("/config.properties");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Properties p = new Properties();
            p.load(reader);
            user = p.getProperty("MYSQL_USER");
            password = p.getProperty("MYSQL_PASSWORD");
            address = "jdbc:mysql://"+p.getProperty("MYSQL_ADDRESS")+"/"+p.getProperty("MYSQL_DATABASE");
            
        } catch (NullPointerException e){
            try {
                FileReader reader=new FileReader("config.properties");
                Properties p = new Properties();
                p.load(reader);
                user = p.getProperty("MYSQL_USER");
                password = p.getProperty("MYSQL_PASSWORD");
                address = "jdbc:mysql://"+p.getProperty("MYSQL_ADDRESS")+"/"+p.getProperty("MYSQL_DATABASE");
                
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Class.forName("com.mysql.jdbc.Driver");
        conn= DriverManager.getConnection(address, user, password);
        return conn;
    }

    public PreparedStatement prepareStatement(String stmt) throws SQLException {
        return this.conn.prepareStatement(stmt);
    }

    public Integer execStatement(String query) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement(query);
        return this.execStatement(ps);
    }

    public Integer execStatement(PreparedStatement ps) throws SQLException {
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }

    public ResultSet execQuery(String query) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement(query);
        return this.execQuery(ps);
    }

    public ResultSet execQuery(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }

    public void close() throws SQLException {
        this.conn.close();
    }
}
