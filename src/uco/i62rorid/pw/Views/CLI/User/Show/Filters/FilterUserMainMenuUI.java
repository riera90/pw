package uco.i62rorid.pw.Views.CLI.User.Show.Filters;

import uco.i62rorid.pw.Utils.UserInput;

import java.security.NoSuchAlgorithmException;


/**
 * The type Filter user main menu ui.
 */
public class FilterUserMainMenuUI {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        String menu = "\t1) First Name\n"+
                "\t2) Second Name\n"+
                "\t3) email\n" +
                "\t4) role [TODO]\n" +
                "\t5) interests\n" +
                "\t6) age\n" +
                "\t0) Go back\n" +
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
                    FilterUserByFirstName.init();
                    break;
                case 2:
                    FilterUserByLastName.init();
                    break;
                case 3:
                    FilterUserByEmail.init();
                    break;
                case 4:
                    break;
                case 5:
                    FilterUserByInterests.init();
                    break;
                case 6:
                    FilterUserByAge.init();
                    break;
                default:
                    System.out.print("Not a valid option");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
