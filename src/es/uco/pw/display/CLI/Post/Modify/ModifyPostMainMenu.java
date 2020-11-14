package es.uco.pw.display.CLI.Post.Modify;

import es.uco.pw.business.dao.post.DAOPost;
import es.uco.pw.business.Utils.UserInput;
import es.uco.pw.display.CLI.Auth.AuthMainMenu;
import es.uco.pw.display.CLI.UserSessionStateSingleton;

import java.security.NoSuchAlgorithmException;

/**
 * The type Modify post main menu.
 */
public class ModifyPostMainMenu {
    public static void init(DAOPost post) throws NoSuchAlgorithmException {
        String menu = "\t1) DELETE\n"+
                "\t0) Go back\n" +
                "\n" +
                "Select an option: ";

        do {
            UserInput.clear();
            if (UserSessionStateSingleton.getInstance().LoggedUser==null){
                AuthMainMenu.init();
            }else{
                System.out.println("logged as "+ UserSessionStateSingleton.getInstance().LoggedUser.getEmail());
            }
            System.out.print(menu);
            switch (UserInput.getMenuOptionFromUser()){
                case 0:
                    return;
                case 1: // timeline
                    DeletePost.init(post);
                    break;
                default:
                    System.out.print("Not a valid option\n");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
