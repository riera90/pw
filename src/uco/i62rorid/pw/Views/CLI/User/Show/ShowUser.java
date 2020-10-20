package uco.i62rorid.pw.Views.CLI.User.Show;

import uco.i62rorid.pw.Entities.User;
import uco.i62rorid.pw.Utils.UserInput;

import java.security.NoSuchAlgorithmException;

/**
 * The type Show user.
 */
public class ShowUser {
    /**
     * Init.
     *
     * @param user the user
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(User user) throws NoSuchAlgorithmException {
        String menu = "\t1) Posts\n"+
                "\t0) Go back\n" +
                "\n" +
                "Select an option: ";

        do {
            UserInput.clear();
            System.out.println(user);
            System.out.print(menu);
            switch (UserInput.getMenuOptionFromUser()){
                case 0:
                    System.out.print("Exiting");
                    return;
                case 1:
                    break;
                default:
                    System.out.print("Not a valid option\n");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
