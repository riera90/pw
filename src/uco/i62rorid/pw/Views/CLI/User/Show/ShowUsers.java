package uco.i62rorid.pw.Views.CLI.User.Show;

import uco.i62rorid.pw.Controlers.UserController;
import uco.i62rorid.pw.Entities.User;
import uco.i62rorid.pw.Utils.UserInput;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;


/**
 * The type Show users.
 */
public class ShowUsers {
    /**
     * Init.
     *
     * @param users the users
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(LinkedList<User> users) throws NoSuchAlgorithmException {
        UserController userController = new UserController();
        for (User user:users) {
            System.out.println(user);
        }
        System.out.print("\tDo you want to select an user?\n\nselect the id or press enter to skip: ");
        Integer id = UserInput.getIdFromUser();
        if (id < 0) return;
        User user = userController.get(id);
        if (user.getId() != null)
            ShowUser.init(user);
        else {
            System.out.print("Id not valid\n");
            UserInput.pause();
        }

    }
}
