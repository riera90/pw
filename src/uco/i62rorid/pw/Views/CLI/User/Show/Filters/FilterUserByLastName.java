package uco.i62rorid.pw.Views.CLI.User.Show.Filters;

import uco.i62rorid.pw.Controlers.UserController;
import uco.i62rorid.pw.Views.CLI.User.Show.ShowUsers;
import uco.i62rorid.pw.Utils.UserInput;

import java.security.NoSuchAlgorithmException;

/**
 * The type Filter user by last name.
 */
public class FilterUserByLastName {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        String lastName;
        UserController userController = new UserController();
        UserInput.clear();
        System.out.print("enter the last name: ");
        lastName = UserInput.getStringFromUser();
        ShowUsers.init(userController.getByFieldLike("lastName", '"' + lastName + '"'));
        UserInput.pause();
    }
}
