package uco.i62rorid.Views.CLI.User.Modify;

import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.Entities.User;
import uco.i62rorid.Utils.Algo;
import uco.i62rorid.Utils.UserInput;

import java.security.NoSuchAlgorithmException;

/**
 * The type Modify user password.
 */
public class ModifyUserPassword {
    /**
     * Init.
     *
     * @param user the user
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(User user) throws NoSuchAlgorithmException {
        UserController userController = new UserController();
        System.out.print("new Password: ");
        String input = UserInput.getStringFromUser();
        if (input.equals("")) {
            System.out.print("Input is not valid\n");
            UserInput.pause();
            return;
        }
        user.setPassword(Algo.getSHA256AsHex(input));
        userController.patch(user);
    }
}
