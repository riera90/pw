package es.uco.pw.display.CLI.User.Create;

import es.uco.pw.business.Controlers.TopicController;
import es.uco.pw.business.Controlers.UserController;
import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.business.Utils.Algo;
import es.uco.pw.business.Utils.UserInput;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.LinkedList;

/**
 * The type Create user.
 */
public class CreateUser {

    /**
     * Init.
     *
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static void init() throws NoSuchAlgorithmException {
        DAOUser user = new DAOUser();

        UserInput.clear();
        System.out.print("\tEnter the following fields.\n\n");

        System.out.print("First Name: ");
        user.setFirstName(UserInput.getStringFromUser());

        System.out.print("Last Name: ");
        user.setLastName(UserInput.getStringFromUser());

        System.out.print("Email: ");
        user.setEmail(UserInput.getStringFromUser());

        System.out.print("Password: ");
        Algo algo = new Algo();
        user.setPassword(algo.getSHA256AsHex(UserInput.getStringFromUser()));

        Date date = null;
        do {
            System.out.print("born at: ");
            date = UserInput.getDateFromUser();
        }while(date == null);
        user.setBornAt(UserInput.getDateFromUser());

        System.out.print("now enter the interests indexes, input -1 to stop adding more\n");
        UserInput.pause();
        TopicController topicController = new TopicController();
        System.out.println(topicController.get());
        int index;
        LinkedList<Integer> interests = new LinkedList<>();
        do {
            System.out.print("Interest id: ");
            index = UserInput.getIdFromUser();
            if (index < 0) break;
            UserController userController = new UserController();
            if (userController.get(index).getId() != null)
                interests.add(index);
            else
                System.out.print("not a valid id\n");


        }while(true);
        user.setInterests(interests);

        UserController userController = new UserController();

        if (userController.getByField("email", "\""+user.getEmail()+"\"").size() != 0){
            System.out.print("\nThe email is already in use\n");
            UserInput.pause();
            return;
        }

        userController.post(user);
        System.out.print("\nUser successfully Created\n");
        UserInput.pause();
    }
}
