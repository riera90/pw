package es.uco.pw.display.CLI.User.Modify;

import es.uco.pw.business.Controlers.UserController;
import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.business.Utils.UserInput;
import es.uco.pw.display.CLI.UserSessionStateSingleton;

import java.security.NoSuchAlgorithmException;

/**
 * The type Modify user main menu.
 */
public class ModifyUserMainMenu {
    /**
     * Init.
     *
     * @param user the user
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(DAOUser user) throws NoSuchAlgorithmException {
        String menu = "\t1) Modify First Name\n"+
                "\t2) Modify Last Name\n"+
                "\t3) Modify email\n" +
                "\t4) Modify password\n" +
                "\t5) Modify role [TODO]\n" +
                "\t6) Modify interests\n" +
                "\t7) DELETE\n" +
                "\t0) Go back\n" +
                "\n" +
                "Select an option: ";

        do {
            UserController userController = new UserController();
            user = userController.get(user.getId());
            UserInput.clear();
            System.out.print(user+"\n\n");
            System.out.print(menu);
            switch (UserInput.getMenuOptionFromUser()){
                case 0:
                    System.out.print("Exiting");
                    return;
                case 1:
                    ModifyUserFirstName.init(user);
                    UserSessionStateSingleton.getInstance().LoggedUser = userController.get(UserSessionStateSingleton.getInstance().LoggedUser.getId());
                    break;
                case 2:
                    ModifyUserLastName.init(user);
                    UserSessionStateSingleton.getInstance().LoggedUser = userController.get(UserSessionStateSingleton.getInstance().LoggedUser.getId());
                    break;
                case 3:
                    ModifyUserEmail.init(user);
                    UserSessionStateSingleton.getInstance().LoggedUser = userController.get(UserSessionStateSingleton.getInstance().LoggedUser.getId());
                    break;
                case 4:
                    ModifyUserPassword.init(user);
                    UserSessionStateSingleton.getInstance().LoggedUser = userController.get(UserSessionStateSingleton.getInstance().LoggedUser.getId());
                    break;
                case 5:
                    break;
                case 6:
                    ModifyUserInterests.init(user);
                    UserSessionStateSingleton.getInstance().LoggedUser = userController.get(UserSessionStateSingleton.getInstance().LoggedUser.getId());
                    break;
                case 7:
                    DeleteUser.init(user);
                    UserSessionStateSingleton.getInstance().LoggedUser=null;
                    return;
                default:
                    System.out.print("Not a valid option");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
