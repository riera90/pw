package es.uco.pw;

import es.uco.pw.business.dao.common.DBConn;
import es.uco.pw.data.dto.user.DTOUser;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

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
            DBConn conn = new DBConn();
            System.out.println("executing");
            ResultSet rs = conn.execStatement("select * from pw.userapp");
            System.out.println("result");
            rs.next();
            DTOUser user = new DTOUser(rs);
            System.out.println(user);
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
