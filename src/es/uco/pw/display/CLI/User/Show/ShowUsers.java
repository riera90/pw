package es.uco.pw.display.CLI.User.Show;

import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.data.dto.user.DTOUser;
import es.uco.pw.business.Utils.UserInput;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;


/**
 * The type Show users.
 */
public class ShowUsers {
    /**
     * Init.
     *
     * @param users the users
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init(LinkedList<DTOUser> users) throws NoSuchAlgorithmException {
        DAOUser userController = new DAOUser();
        for (DTOUser user:users) {
            System.out.println(user);
        }
        System.out.print("\tDo you want to select an user?\n\nselect the id or press enter to skip: ");
        Integer id = UserInput.getIdFromUser();
        if (id < 0) return;
        DTOUser user = userController.get(id);
        if (user.getId() != null)
            ShowUser.init(user);
        else {
            System.out.print("Id not valid\n");
            UserInput.pause();
        }

    }
}
