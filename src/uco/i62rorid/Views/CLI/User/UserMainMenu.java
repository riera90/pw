package uco.i62rorid.Views.CLI.User;

import uco.i62rorid.Views.CLI.Auth.AuthMainMenu;
import uco.i62rorid.Views.CLI.User.Show.Filters.FilterUserMainMenuUI;
import uco.i62rorid.Views.CLI.User.Show.ShowAllUsers;
import uco.i62rorid.Utils.UserInput;
import uco.i62rorid.Views.CLI.UserSessionStateSingleton;

import java.security.NoSuchAlgorithmException;

/**
 * The type User main menu.
 */
public class UserMainMenu {

    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        String menu = "\t1) Show all users\n"+
                "\t2) Search user\n"+
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
                    System.out.print("Exiting");
                    return;
                case 1:
                    ShowAllUsers.init();
                    break;
                case 2:
                    FilterUserMainMenuUI.init();
                    break;
                default:
                    System.out.print("Not a valid option\n");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
