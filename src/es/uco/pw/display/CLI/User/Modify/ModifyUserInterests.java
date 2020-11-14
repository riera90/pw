package es.uco.pw.display.CLI.User.Modify;

import es.uco.pw.business.Controlers.TopicController;
import es.uco.pw.business.Controlers.UserController;
import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.business.Utils.UserInput;

import java.util.LinkedList;

/**
 * The type Modify user interests.
 */
public class ModifyUserInterests {
    /**
     * Init.
     *
     * @param user the user
     */
    public static void init(DAOUser user){
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
        userController.patch(user);
        System.out.print("\nUser successfully Created\n");
        UserInput.pause();
    }
}
