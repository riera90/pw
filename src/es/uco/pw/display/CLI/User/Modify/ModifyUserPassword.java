package es.uco.pw.display.CLI.User.Modify;

import es.uco.pw.business.Controlers.UserController;
import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.business.Utils.Algo;
import es.uco.pw.business.Utils.UserInput;

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
    public static void init(DAOUser user) throws NoSuchAlgorithmException {
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
