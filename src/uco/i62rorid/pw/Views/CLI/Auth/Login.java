package uco.i62rorid.pw.Views.CLI.Auth;

import uco.i62rorid.pw.Controlers.UserController;
import uco.i62rorid.pw.Entities.User;
import uco.i62rorid.pw.Utils.Algo;
import uco.i62rorid.pw.Utils.UserInput;
import uco.i62rorid.pw.Views.CLI.UserSessionStateSingleton;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

/**
 * The type Login.
 */
public class Login {
    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        System.out.print("Log In\n\nEmail: ");
        String email = UserInput.getStringFromUser();

        UserController userController = new UserController();
        LinkedList<User> users = userController.getByField("email", '"'+email+'"');

        if (users.size()!=1){
            System.out.print("User not found\n");
            UserInput.pause();
            return;
        }

        System.out.print("Password: ");
        Algo algo = new Algo();
        String passwd = algo.getSHA256AsHex(UserInput.getStringFromUser());

        if (users.get(0).getPassword().equals(passwd)){
            UserSessionStateSingleton.getInstance().LoggedUser = users.get(0);
            return;
        }

        System.out.print("Invalid password\n");
        UserInput.pause();
    }
}
