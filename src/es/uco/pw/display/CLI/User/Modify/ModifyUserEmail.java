package es.uco.pw.display.CLI.User.Modify;

import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.data.dto.user.DTOUser;
import es.uco.pw.business.Utils.UserInput;

/**
 * The type Modify user email.
 */
public class ModifyUserEmail {
    /**
     * Init.
     *
     * @param user the user
     */
    public static void init(DTOUser user){
        DAOUser userController = new DAOUser();
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
