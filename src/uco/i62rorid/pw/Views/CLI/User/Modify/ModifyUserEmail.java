package uco.i62rorid.pw.Views.CLI.User.Modify;

import uco.i62rorid.pw.Controlers.UserController;
import uco.i62rorid.pw.Entities.User;
import uco.i62rorid.pw.Utils.UserInput;

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
