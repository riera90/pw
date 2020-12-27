package es.uco.pw;

import es.uco.pw.business.Utils.SqlQuery;
import es.uco.pw.business.dao.common.DBConn;
import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.business.dao.post.PostBuilder;
import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.business.dao.user.UserBuilder;
import es.uco.pw.data.dto.post.DTOPost;
import es.uco.pw.data.dto.user.DTOUser;


/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        for (DTOPost post : new DAOPost().get()) System.out.println(PostBuilder.toJson(post));
        //for (DTOUser user : new DAOUser().get()) System.out.println(UserBuilder.toJson(user));
        //DTOUser usr = new DTOUser("{id:7,isDeleted:false,}");
        //System.out.println("patch: "+new DAOUser().patch(usr).toJson());
        //System.out.println(new DAOUser().delete(7));
        //for (DTOUser user : new DAOUser().get()) System.out.println(user.toJson());
        /*
        FlashPostDaemon flashPostDaemon = new FlashPostDaemon("flashPostDaemon");
        flashPostDaemon.start();

        CliUserInterface.init();

        flashPostDaemon.stopThread();
        */
    }
}
