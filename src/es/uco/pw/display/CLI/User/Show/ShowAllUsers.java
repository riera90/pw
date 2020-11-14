package es.uco.pw.display.CLI.User.Show;

import es.uco.pw.business.Controlers.UserController;
import es.uco.pw.business.Utils.UserInput;

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
