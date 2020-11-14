package es.uco.pw.display.CLI.User.Show.Filters;

import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.display.CLI.User.Show.ShowUsers;
import es.uco.pw.business.Utils.UserInput;

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
        DAOUser userController = new DAOUser();
        UserInput.clear();
        System.out.print("enter the last name: ");
        lastName = UserInput.getStringFromUser();
        ShowUsers.init(userController.getByFieldLike("lastName", '"' + lastName + '"'));
        UserInput.pause();
    }
}
