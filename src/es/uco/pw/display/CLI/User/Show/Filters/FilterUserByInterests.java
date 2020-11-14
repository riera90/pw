package es.uco.pw.display.CLI.User.Show.Filters;

import es.uco.pw.business.dao.topic.DAOTopic;
import es.uco.pw.business.dao.user.DAOUser;
import es.uco.pw.business.Utils.UserInput;

import java.util.LinkedList;

/**
 * The type Filter user by interests.
 */
public class FilterUserByInterests {
    /**
     * Init.
     */
    public static void init() {
        System.out.print("now enter the new interests indexes, input -1 to stop adding more\n");
        DAOTopic topicController = new DAOTopic();
        System.out.println(topicController.get());
        int index;
        LinkedList<Integer> interests = new LinkedList<>();
        do {
            System.out.print("Interest id: ");
            index = UserInput.getIdFromUser();
            if (index < 0) break;
            DAOUser userController = new DAOUser();
            if (userController.get(index).getId() != null)
                interests.add(index);
            else
                System.out.print("not a valid id\n");
        }while(true);
    }
}
