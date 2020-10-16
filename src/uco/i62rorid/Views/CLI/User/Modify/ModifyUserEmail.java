package uco.i62rorid.Views.CLI.User.Modify;

import uco.i62rorid.Controlers.UserController;
import uco.i62rorid.Entities.User;
import uco.i62rorid.Utils.UserInput;

/**
 * The type Modify user email.
 */
public class ModifyUserEmail {
    /**
     * Init.
     *
     * @param user the user
     */
    public static void init(User user){
        UserController userController = new UserController();
        System.out.print("new Email: ");
        String input = UserInput.getStringFromUser();
        if (input.equals("")) {
            System.out.print("Input is not valid\n");
            UserInput.pause();
            return;
        }
        user.setEmail(input);
        userController.patch(user);
    }
}
