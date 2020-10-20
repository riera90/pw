package uco.i62rorid.pw.Views.CLI.User.Show.Filters;

import uco.i62rorid.pw.Controlers.UserController;
import uco.i62rorid.pw.Views.CLI.User.Show.ShowUsers;
import uco.i62rorid.pw.Utils.UserInput;

import java.security.NoSuchAlgorithmException;

/**
 * The type Filter user by email.
 */
public class FilterUserByEmail {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        String email;
        UserController userController = new UserController();
        UserInput.clear();
        System.out.print("enter the email: ");
        email = UserInput.getStringFromUser();
        ShowUsers.init(userController.getByFieldLike("email", '"' + email + '"'));
        UserInput.pause();
    }
}
