package es.uco.pw.display.CLI;

import es.uco.pw.display.CLI.Auth.AuthMainMenu;
import es.uco.pw.display.CLI.Post.PostMainMenu;
import es.uco.pw.display.CLI.Topic.TopicMainMenu;
import es.uco.pw.display.CLI.User.Modify.ModifyUserMainMenu;
import es.uco.pw.display.CLI.User.UserMainMenu;
import es.uco.pw.business.Utils.UserInput;

import java.security.NoSuchAlgorithmException;

/**
 * The type Cli user interface.
 */
public class CliUserInterface {

    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        String menu = "\t1) Users\n" +
                "\t2) Posts\n"+
                "\t3) Topics\n" +
                "\t4) Configuration\n" +
                "\t5) Log out\n" +
                "\t0) Exit" +
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
                    System.out.print("Exiting");
                    return;
                case 1:
                    UserMainMenu.init();
                    break;
                case 2:
                    PostMainMenu.init();
                    break;
                case 3:
                    TopicMainMenu.init();
                    break;
                case 4:
                    ModifyUserMainMenu.init(UserSessionStateSingleton.getInstance().LoggedUser);
                    break;
                case 5:
                    UserSessionStateSingleton.getInstance().LoggedUser = null;
                    break;
                default:
                    System.out.print("Not a valid option\n");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
