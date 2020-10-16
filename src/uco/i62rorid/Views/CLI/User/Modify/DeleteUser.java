package uco.i62rorid.Views.CLI.User.Modify;

import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.Entities.User;
import uco.i62rorid.Utils.UserInput;

/**
 * The type Delete user.
 */
public class DeleteUser {
    /**
     * Init.
     *
     * @param user the user
     */
    public static void init(User user){
        UserController userController = new UserController();
        if (userController.delete(user))
            System.out.print("User deleted successfully");
        else
            System.out.print("User not found");
        UserInput.pause();
    }
}
