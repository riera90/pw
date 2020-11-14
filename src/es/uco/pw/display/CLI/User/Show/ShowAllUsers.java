package es.uco.pw.display.CLI.User.Show;

import es.uco.pw.business.dao.user.DAOUser;
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
        DAOUser userController = new DAOUser();
        ShowUsers.init(userController.get());
    }
}
