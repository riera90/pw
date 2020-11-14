package es.uco.pw.display.CLI.Post.Show;


import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.data.dto.post.DTOPost;
import es.uco.pw.business.Utils.UserInput;
import es.uco.pw.display.CLI.User.Show.ShowUser;

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
    public static void init(DTOPost post) throws NoSuchAlgorithmException {
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
                    DAOUser userController = new DAOUser();
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
