package uco.i62rorid.pw.Views.CLI.User.Show.Filters;

import uco.i62rorid.pw.Controlers.UserController;
import uco.i62rorid.pw.Views.CLI.User.Show.ShowUsers;
import uco.i62rorid.pw.Utils.UserInput;

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
