package es.uco.pw.display.CLI.User.Modify;

import es.uco.pw.business.Controlers.UserController;
import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.business.Utils.UserInput;

/**
 * The type Modify user first name.
 */
public class ModifyUserFirstName {
    /**
     * Init.
     *
     * @param user the user
     */
    public static void init(DAOUser user){
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