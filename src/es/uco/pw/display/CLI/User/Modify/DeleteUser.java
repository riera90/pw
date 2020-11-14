package es.uco.pw.display.CLI.User.Modify;

import es.uco.pw.business.Controlers.UserController;
import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.business.Utils.UserInput;

/**
 * The type Delete user.
 */
public class DeleteUser {
    /**
     * Init.
     *
     * @param user the user
     */
    public static void init(DAOUser user){
        UserController userController = new UserController();
        if (userController.delete(user))
            System.out.print("User deleted successfully");
        else
            System.out.print("User not found");
        UserInput.pause();
    }
}
