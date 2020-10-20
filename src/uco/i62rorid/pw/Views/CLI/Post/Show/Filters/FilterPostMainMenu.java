package uco.i62rorid.pw.Views.CLI.Post.Show.Filters;

import uco.i62rorid.pw.Utils.UserInput;

import java.security.NoSuchAlgorithmException;

/**
 * The type Filter post main menu.
 */
public class FilterPostMainMenu {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        String menu = "\t1) publication date\n"+
                "\t2) topic\n"+
                "\t3) title\n"+
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
                    FilterPostByPublicationDate.init();
                    break;
                case 2:
                    FilterPostByTopic.init();
                    break;
                case 3:
                    FilterPostByTitle.init();
                    break;
                default:
                    System.out.print("Not a valid option");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
