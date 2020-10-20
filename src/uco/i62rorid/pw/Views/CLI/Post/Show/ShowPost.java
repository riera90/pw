package uco.i62rorid.pw.Views.CLI.Post.Show;


import uco.i62rorid.pw.Controlers.UserController;
import uco.i62rorid.pw.Entities.Post;
import uco.i62rorid.pw.Utils.UserInput;
import uco.i62rorid.pw.Views.CLI.User.Show.ShowUser;

import java.security.NoSuchAlgorithmException;

/**
 * The type Show post.
 */
public class ShowPost {
    /**
     * Init.
     *
     * @param post the post
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(Post post) throws NoSuchAlgorithmException {
        String menu = "\t1) User\n"+
                "\t0) Go back\n" +
                "\n" +
                "Select an option: ";

        do {
            UserInput.clear();
            System.out.println(post);
            System.out.print(menu);
            switch (UserInput.getMenuOptionFromUser()){
                case 0:
                    System.out.print("Exiting");
                    return;
                case 1:
                    UserController userController = new UserController();
                    ShowUser.init(userController.get(post.getOwner()));
                    break;
                default:
                    System.out.print("Not a valid option\n");
                    UserInput.pause();
                    break;
            }
        }while(true);
    }
}
