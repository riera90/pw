package es.uco.pw;

import es.uco.pw.business.Daemons.FlashPostDaemon;
import es.uco.pw.business.dao.common.DBConn;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        try {
            System.out.println("geting connection");
            Connection conn = DBConn.getConn();
            assert conn != null;
            System.out.println("executing");
            ResultSet rs = DBConn.execQuery(conn, "select * from pw.User");
            System.out.println("result");
            while (rs.next()){
                System.out.println("<"+rs.getString("firstName")+">");
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        /*
        FlashPostDaemon flashPostDaemon = new FlashPostDaemon("flashPostDaemon");
        flashPostDaemon.start();

        CliUserInterface.init();

        flashPostDaemon.stopThread();
        */
    }
}
