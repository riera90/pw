package es.uco.pw.display.CLI.User.Show.Filters;

import es.uco.pw.business.Controlers.UserController;
import es.uco.pw.display.CLI.User.Show.ShowUsers;
import es.uco.pw.business.Utils.UserInput;

import java.security.NoSuchAlgorithmException;

/**
 * The type Filter user by first name.
 */
public class FilterUserByFirstName {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        String firstName;
        UserController userController = new UserController();
        UserInput.clear();
        System.out.print("enter the first name: ");
        firstName = UserInput.getStringFromUser();
        ShowUsers.init(userController.getByFieldLike("firstName", '"' + firstName + '"'));
        UserInput.pause();
    }
}
