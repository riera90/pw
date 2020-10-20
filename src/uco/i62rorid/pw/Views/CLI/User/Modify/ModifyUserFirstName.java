package uco.i62rorid.pw.Views.CLI.User.Modify;

import uco.i62rorid.pw.Controlers.UserController;
import uco.i62rorid.pw.Entities.User;
import uco.i62rorid.pw.Utils.UserInput;

/**
 * The type Modify user first name.
 */
public class ModifyUserFirstName {
    /**
     * Init.
     *
     * @param user the user
     */
    public static void init(User user){
        UserController userController = new UserController();
        System.out.print("new First Name: ");
        String input = UserInput.getStringFromUser();
        if (input.equals("")) {
            System.out.print("Input is not valid\n");
            UserInput.pause();
            return;
        }
        user.setFirstName(input);
        userController.patch(user);
    }
}
