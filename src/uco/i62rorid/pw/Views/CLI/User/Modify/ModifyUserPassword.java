package uco.i62rorid.pw.Views.CLI.User.Modify;

import uco.i62rorid.pw.Controlers.UserController;
import uco.i62rorid.pw.Entities.User;
import uco.i62rorid.pw.Utils.Algo;
import uco.i62rorid.pw.Utils.UserInput;

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
        Algo algo = new Algo();
        user.setPassword(algo.getSHA256AsHex(input));
        userController.patch(user);
    }
}
