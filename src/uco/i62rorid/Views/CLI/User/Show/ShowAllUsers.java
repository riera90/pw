package uco.i62rorid.Views.CLI.User.Show;

import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.Utils.UserInput;

import java.security.NoSuchAlgorithmException;

/**
 * The type Show all users.
 */
public class ShowAllUsers {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        UserInput.clear();
        UserController userController = new UserController();
        ShowUsers.init(userController.get());
    }
}
