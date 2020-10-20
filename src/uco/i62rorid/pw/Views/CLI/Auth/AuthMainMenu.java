package uco.i62rorid.pw.Views.CLI.Auth;

import uco.i62rorid.pw.Utils.UserInput;

import java.security.NoSuchAlgorithmException;

/**
 * The type Auth main menu.
 */
public class AuthMainMenu {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        String menu = "\t1) Log in\n" +
                "\t2) Register [TODO]\n"+
                "\t0) Exit" +
                "\n" +
                "Select an option: ";

        do {
            UserInput.clear();
            System.out.print(menu);
            switch (UserInput.getMenuOptionFromUser()){
                case 0:
                    System.out.print("Exiting");
                    return;
                case 1:
                    Login.init();
                    return;
                case 2:
                    Register.init();
                    return;
                default:
                    System.out.print("Not a valid option\n");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
